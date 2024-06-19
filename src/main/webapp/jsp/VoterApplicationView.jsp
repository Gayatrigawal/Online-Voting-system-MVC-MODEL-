<%@page import="in.co.online.vote.util.DataUtility"%>
<%@page import="in.co.online.vote.controller.VoterApplicationCtl"%>
<%@page import="in.co.online.vote.util.ServletUtility"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Voter Application Form</title>
</head>
<body>
	<%@include file="Header.jsp"%>
	<%
		UserBean user = (UserBean) session.getAttribute("user");
	%>
	<div class="container mt-2"
		style="position: relative; min-height: 72vh">
		<h3 class="text-center mt-5">Voter Application Form</h3>
		<hr>
		<form action="<%=OVSView.VOTER_APPLICATION_CTL%>" method="post" enctype="multipart/form-data">
			<h4 class="text-center mt-5" style="color: red;"><%=ServletUtility.getErrorMessage(request)%></h4>
			<h4 class="text-center mt-5" style="color: green;"><%=ServletUtility.getSuccessMessage(request)%></h4>

			<jsp:useBean id="bean" scope="request"
				class="in.co.online.vote.bean.VoterApplicationBean" />
			<input type="hidden" name="id" value="<%=bean.getId()%>">

			<div class="container mt-5">
				<div class="row h-100 justify-content-center align-items-center">

					<div class="col-6">
						<label for="exampleInputEmail1" class="form-label">First
							Name</label> <input type="text" name="firstname" class="form-control "
							placeholder="Enter Name here..." readonly="readonly"
							value="<%=user.getFirstName()%>" id="exampleInputEmail1"
							aria-describedby="emailHelp">
						<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("firstname", request)%></div>
					</div>

					<div class="col-6">
						<label for="exampleInputEmail1" class="form-label">Last
							Name</label> <input type="text" name="lastname" class="form-control "
							placeholder="Enter Name here..." readonly="readonly"
							value="<%=user.getLastName()%>" id="exampleInputEmail1"
							aria-describedby="emailHelp">
						<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("lastname", request)%></div>
					</div>

					<div class="col-6">
						<label for="exampleInputEmail1" class="form-label">Email</label> <input
							type="text" name=login class="form-control "
							placeholder="Enter Name here..." readonly="readonly"
							value="<%=user.getLogin()%>" id="exampleInputEmail1"
							aria-describedby="emailHelp">
						<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("login", request)%></div>
					</div>

					<div class="col-6">
						<label for="exampleInputEmail1" class="form-label">Mobile
							No</label> <input type="text" name="mobileNo" class="form-control "
							placeholder="Enter Name here..." readonly="readonly"
							value="<%=user.getMobileNo()%>" id="exampleInputEmail1"
							aria-describedby="emailHelp">
						<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("mobileNo", request)%></div>
					</div>

					<div class="col-6 text-center mt-3">
						<label for="exampleInputEmail1" class="form-label"><b>ID Proof</b></label> <input type="file" class="form-control"
							placeholder="Enter Id Proof" required="required" name="image"
							value="<%=DataUtility.getStringData(bean.getIdProof())%>">
						<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("image", request)%></div>
					</div>

					<div class="container text-center mt-4">
						<input type="submit" class="btn btn-primary" name="operation"
							value="<%=VoterApplicationCtl.OP_SAVE%>">
					</div>
				</div>
			</div>
		</form>
	</div>
	<%@include file="Footer.jsp"%>

</body>
</html>