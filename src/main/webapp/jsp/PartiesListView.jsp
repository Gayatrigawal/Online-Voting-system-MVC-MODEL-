<%@page import="in.co.online.vote.bean.PartiesBean"%>
<%@page import="in.co.online.vote.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>


<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@include file="Header.jsp"%>
	<div class="container">
		<h1>Parties List</h1>
		<div class="row">
			<div class="col-12">
				<form action="<%=OVSView.PARTIES_LIST_CTL%>" method="post">
					<h5 style="color: red;" class="text-center"><%=ServletUtility.getErrorMessage(request)%></h5>
					<h5 style="color: green;" class="text-center"><%=ServletUtility.getSuccessMessage(request)%></h5>
					<%
						int index = 1;
						List list = ServletUtility.getList(request);
						Iterator it = list.iterator();
						while (it.hasNext()) {
							PartiesBean bean = (PartiesBean) it.next();
					%>
					<div class="card mt-3">
						<div class="text-center">
						<h5 class="card-title text-center mt-3 text-uppercase font-weight-bold">
								Party Name :<%=bean.getName()%></h5>
							<img class="m-2"
								src="/OnlineVotingSystem/image?id=<%=bean.getId()%>"
								style="height: 200px;" width="200px;">
						</div>
						<div class="card-body">
							<div class="container text-center">
								<a
									href="<%=OVSView.PARTY_CTL%>?id=<%=bean.getId()%>"
									class="btn btn-success">Edit</a>
								<a
									href="<%=OVSView.PARTIES_LIST_CTL%>?id=<%=bean.getId()%>"
									class="btn btn-danger">Delete</a>
							</div>
						</div>
					</div>

					<%
						}
					%>



				</form>
			</div>
		</div>
	</div>
	<%@include file="Footer.jsp"%>
</body>
</html>