<%@page import="in.co.online.vote.bean.VoterApplicationBean"%>
<%@page import="in.co.online.vote.bean.CandidateBean"%>
<%@page import="in.co.online.vote.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Voter Application List</title>
</head>
<body>
	<%@include file="Header.jsp"%>
	<br>
	<%
		UserBean user = (UserBean) session.getAttribute("user");
	%>
	<h2 align="center">Voter Application List</h2>

	<h4 style="color: red;"><%=ServletUtility.getErrorMessage(request)%></h4>
	<h4 style="color: green;"><%=ServletUtility.getSuccessMessage(request)%></h4>

	<form action="<%=OVSView.VOTER_APPLICATION_LIST_CTL%>" method="post">

		<table class="ml-4 mr-4 table table-striped">
			<tr>

				<th scope="col">First Name</th>
				<th scope="col">Last Name</th>
				<th scope="col">LoginId</th>
				<th scope="col">PhoneNo</th>
				<th scope="col">Voter ID</th>
				<th scope="col">ID Proof</th>
				<th scope="col">Action</th>
			</tr>
			<%
				int index = 1;
				List list = ServletUtility.getList(request);
				Iterator it = list.iterator();
				while (it.hasNext()) {
					VoterApplicationBean bean = (VoterApplicationBean) it.next();
			%>
			<tr>

				<td><%=bean.getFirstName()%></td>
				<td><%=bean.getLastName()%></td>
				<td><%=bean.getLogin()%></td>
				<td><%=bean.getMobileNo()%></td>
				<%if(bean.getVoterID()==0){ %>
				<td>-------------</td>
				<%}else{ %>
				<td><%=bean.getVoterID()%></td>
				<%} %>
				<td><img class="m-2"
								src="/OnlineVotingSystem/idProof?id=<%=bean.getId()%>"
								style="height: 100px;" width="100px;"></td>
				

             <%if(user.getRoleId()==3) {%>

				<td>---------------</td>

					<%}else{ %>
					
									<td><a class="btn btn-info"
					href="<%=OVSView.VOTER_APPLICATION_LIST_CTL%>?vid=<%=bean.getId()%>">Generate VoterID</a>
<%-- 
				<a class="btn btn-danger"
					href="<%=OVSView.VOTER_APPLICATION_LIST_CTL%>?id=<%=bean.getId()%>">Delete</a></td> --%>
				<%
					}}
				%>
			</tbody>
		</table>


	</form>
	<br>
	<hr>
	<%@include file="Footer.jsp"%>
</body>
</html>