<%@page import="in.co.online.vote.util.PropertyReader"%>
<%@page import="in.co.online.vote.util.DataUtility"%>
<%@page import="in.co.online.vote.model.VoteModel"%>
<%@page import="in.co.online.vote.bean.VoteBean"%>
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
<title>Result List</title>
</head>
<body>
	<%@include file="Header.jsp"%>
	<br>
		<%
		UserBean user = (UserBean) session.getAttribute("user");
	%>
		<div class="cotainer">
		<div class="row justify-content-center">
			<div class="col-md-8">
				<div class="card">
					<div class="card-header"><b>Result</b></div>

					<div class="card-body">

	<h4 style="color: red;"><%=ServletUtility.getErrorMessage(request)%></h4>
	<h4 style="color: green;"><%=ServletUtility.getSuccessMessage(request)%></h4>

	<form action="<%=OVSView.RESULT_LIST_CTL%>" method="post">

		<table class="ml-4 mr-4 table table-striped">
			<tr>

				<th scope="col" style="color: blue">Candidate Name</th>
				<!-- <th scope="col">Election Name</th> -->
				<th scope="col" style="color: blue">Vote</th>
			</tr>
			
			<%
			VoteModel model =  new VoteModel();
			VoteBean bean =new VoteBean();
			VoteBean bean1 =new VoteBean();

			int pageNo = 1;
			int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
			%>
			<%
				int index = 1;
				List list = ServletUtility.getList(request);
				Iterator it = list.iterator();
				while (it.hasNext()) {
					bean = (VoteBean) it.next();
			%>
			
				            <%
				            bean1.setCandidateName(bean.getCandidateName());
							List list1 = model.search(bean1, pageNo, pageSize);
							request.setAttribute("list1",list1.size());
							%>
							<%int l1 = (Integer) request.getAttribute("list1"); %>
			<tr>

				<td><%=bean.getCandidateName()%></td>
				<%-- <td><%=bean.getElecationName()%></td> --%>
				<td><%=l1%></td>
				
				
				<%
					}
				%>
			</tbody>
		</table>


	</form>
	</div>

				</div>
			</div>
		</div>
	</div>
	<br>
	<hr>
	<%@include file="Footer.jsp"%>
</body>
</html>