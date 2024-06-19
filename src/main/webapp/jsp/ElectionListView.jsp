<%@page import="in.co.online.vote.bean.ElectionBean"%>
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
<title>Election List</title>
</head>
<body>
	<%@include file="Header.jsp"%>
	<br>
		<%
		UserBean user = (UserBean) session.getAttribute("user");
	%>
	<h2 align="center">Election List</h2>

	<h4 style="color: red;"><%=ServletUtility.getErrorMessage(request)%></h4>
	<h4 style="color: green;"><%=ServletUtility.getSuccessMessage(request)%></h4>

	<form action="<%=OVSView.ELECTION_LIST_CTL%>" method="post">

		<table class="ml-4 mr-4 table table-striped">
			<tr>

				<th scope="col">Election Name</th>
				
				<%if(user.getRoleId()==3){ %>
				
				<%}else{ %>
				<th scope="col">Election Date</th>
				<%} %>
				<th scope="col">Action</th>
			</tr>
			<%
				int index = 1;
				List list = ServletUtility.getList(request);
				Iterator it = list.iterator();
				while (it.hasNext()) {
					ElectionBean bean = (ElectionBean) it.next();
			%>
			<tr>

				<td><%=bean.getElectionName()%></td>
				<%if(user.getRoleId()==3){ %>
				
				<%}else{ %>
				<td><%=bean.getElectionDate()%></td>
				<%} %>
				
				<%if(user.getRoleId()==3){ %>

				<td><a class="btn btn-info" href="<%=OVSView.CANDIDATE_LIST_CTL%>?elecationName=<%=bean.getElectionName()%>">View Candidate</a>

				<a class="btn btn-primary"
					href="<%=OVSView.RESULT_LIST_CTL%>">View Result</a></td>
					
					<%}else{ %>
					
					<td><a class="btn btn-info"
					href="<%=OVSView.ELECTION_CTL%>?id=<%=bean.getId()%>">Update</a>

				<a class="btn btn-danger"
					href="<%=OVSView.ELECTION_LIST_CTL%>?id=<%=bean.getId()%>">Delete</a></td>
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