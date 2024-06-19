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
<title>Candidate List</title>
</head>
<body>
	<%@include file="Header.jsp"%>
	<br>
		<%
		UserBean user = (UserBean) session.getAttribute("user");
	%>
	<h2 align="center">Candidate List</h2>

	<h4 style="color: red;"><%=ServletUtility.getErrorMessage(request)%></h4>
	<h4 style="color: green;"><%=ServletUtility.getSuccessMessage(request)%></h4>

	<form action="<%=OVSView.CANDIDATE_LIST_CTL%>" method="post">

		<table class="ml-4 mr-4 table table-striped">
			<tr>

				<th scope="col">First Name</th>
				<th scope="col">Last Name</th>
				
				<th scope="col">Party</th>
				<%if(user.getRoleId()==3){ %>
				
				<%}else{ %>
				<th scope="col">LoginId</th>
				<th scope="col">PhoneNo</th>
				<th scope="col">Gender</th>
				<th scope="col">DOB</th>
				<%} %>
				<th scope="col">Action</th>
			</tr>
			<%CandidateBean bean = new CandidateBean();
			%>
			<%
				int index = 1;
				List list = ServletUtility.getList(request);
				Iterator it = list.iterator();
				while (it.hasNext()) {
					 bean = (CandidateBean) it.next();
			%>
			<tr>

				<td><%=bean.getFirstName()%></td>
				<td><%=bean.getLastName()%></td>
				<td><%=bean.getPartyName()%></td>
					<%if(user.getRoleId()==3){ %>
				
				<%}else{ %>
			<td><%=bean.getLogin()%></td>
				<td><%=bean.getMobileNo()%></td>
				<td><%=bean.getGender()%></td>
				<td><%=bean.getDob()%></td>
				<%} %>
				
				
					<%if(user.getRoleId()==3){ %>
						
							<td><a class="btn btn-info"
					href="<%=OVSView.DO_VOTE_CTL%>?id=<%=bean.getId()%>&candidateName=<%=bean.getFirstName()%>">Vote</a></td>
					<%}else{ %>
					
							<td><a class="btn btn-info"
					href="<%=OVSView.CANDIDATE_CTL%>?id=<%=bean.getId()%>">Update</a>

				<a class="btn btn-danger"
					href="<%=OVSView.CANDIDATE_LIST_CTL%>?id=<%=bean.getId()%>">Delete</a></td>
					<%} %>

				<%
					}
				%>
			</tbody>
		</table>


	</form>
	<br>
	<hr>
	<%@include file="Footer.jsp"%>
</body>
</html>