<%@page import="java.util.HashMap"%>
<%@page import="in.co.online.vote.util.HTMLUtility"%>
<%@page import="java.util.List"%>
<%@page import="in.co.online.vote.controller.CandidateCtl"%>
<%@page import="in.co.online.vote.controller.UserRegistrationCtl"%>
<%@page import="in.co.online.vote.util.DataUtility"%>
<%@page import="in.co.online.vote.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Candidate</title>

  <script>
  $( function() {
    $( "#datepicker" ).datepicker();
  } );
  </script>
</head>
<body>
<%@ include file="Header.jsp"%>
	<%
			List partylist =(List)request.getAttribute("partyName");
	%> 
	<main class="login-form">
	<div class="cotainer">
		<div class="row justify-content-center">
			<div class="col-md-8">
				<div class="card">
					<div class="card-header">
						Add Candidate
						<h6 style="color: red;"><%=ServletUtility.getErrorMessage(request)%></h6>
						<h6 style="color: green;"><%=ServletUtility.getSuccessMessage(request)%></h6>
					</div>

					<div class="card-body">

						<form action="<%=OVSView.CANDIDATE_CTL%>" method="post">
						
						

							<jsp:useBean id="bean" class="in.co.online.vote.bean.CandidateBean"
								scope="request"></jsp:useBean>

							<%
								String uri = (String) request.getAttribute("uri");
							%>
							<input type="hidden" name="uri" value="<%=uri%>"> <input
								type="hidden" name="id" value="<%=bean.getId()%>"> <input
								type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
							<input type="hidden" name="modifiedBy"
								value="<%=bean.getModifiedBy()%>"> <input type="hidden"
								name="createdDatetime"
								value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
							<input type="hidden" name="modifiedDatetime"
								value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

							<div class="form-group row">
								<label for="email_address" 
									class="col-md-4 col-form-label text-md-right">First Name<font color="red">*</font></label>
								<div class="col-md-6">
									<input type="text"   class="form-control" placeholder="Enter First Name"
										name="firstName" value="<%=DataUtility.getStringData(bean.getFirstName())%>" >
										<font  color="red"><%=ServletUtility.getErrorMessage("firstName", request)%></font>
								</div>
							</div>
							
							<div class="form-group row">
								<label for="email_address" 
									class="col-md-4 col-form-label text-md-right">Last Name<font color="red">*</font></label>
								<div class="col-md-6">
									<input type="text"   class="form-control" placeholder="Enter Last Name"
										name="lastName" value="<%=DataUtility.getStringData(bean.getLastName())%>" >
										<font  color="red"><%=ServletUtility.getErrorMessage("lastName", request)%></font>
								</div>
							</div>

							<div class="form-group row">
								<label for="email_address" 
									class="col-md-4 col-form-label text-md-right">Login Id<font color="red">*</font></label>
								<div class="col-md-6">
									<input type="email" id="email_address"  class="form-control" placeholder="Enter Login Id"
										name="login" value="<%=DataUtility.getStringData(bean.getLogin())%>" >
										<font  color="red"><%=ServletUtility.getErrorMessage("login", request)%></font>
								</div>
							</div>
							
							<div class="form-group row">
								<label for="email_address" 
									class="col-md-4 col-form-label text-md-right">Password<font color="red">*</font></label>
								<div class="col-md-6">
									<input type="password"  class="form-control" placeholder="Enter Password"
										name="password" value="<%=DataUtility.getStringData(bean.getPassword())%>" >
										<font  color="red"><%=ServletUtility.getErrorMessage("password", request)%></font>
								</div>
							</div>

							
							<div class="form-group row">
								<label for="email_address" 
									class="col-md-4 col-form-label text-md-right">Date Of Birth<font color="red">*</font></label>
								<div class="col-md-6">
									<input type="text"  id="datepicker"  class="form-control" placeholder="Enter Date Of Birth"
										name="dob" value="<%=DataUtility.getDateString(bean.getDob())%>" >
										<font  color="red"><%=ServletUtility.getErrorMessage("dob", request)%></font>
								</div>
							</div>
							
							<div class="form-group row">
								<label for="email_address" 
									class="col-md-4 col-form-label text-md-right">Mobile No.<font color="red">*</font></label>
								<div class="col-md-6">
									<input type="text" id="email_address"  class="form-control" placeholder="Enter Mobile No"
										name="mobile" value="<%=DataUtility.getStringData(bean.getMobileNo())%>" >
										<font  color="red"><%=ServletUtility.getErrorMessage("mobile", request)%></font>
								</div>
							</div>
							
							
							 <%
							HashMap<String,String> map = new HashMap<String,String>();
							map.put("Male", "Male");
							map.put("Female", "Female");
						%> 
							<div class="form-group row">
								<label for="email_address" 
									class="col-md-4 col-form-label text-md-right">Select Gender<font color="red">*</font></label>
								<div class="col-md-6">
									 <%=HTMLUtility.getList("gender",String.valueOf(bean.getGender()), map)%>
									 <font color="red"><%=ServletUtility.getErrorMessage("gender", request)%></font>
								</div>
							</div>
							
							<div class="form-group row">
								<label for="email_address" 
									class="col-md-4 col-form-label text-md-right">Select Party<font color="red">*</font></label>
								<div class="col-md-6">
									<select name="partyid" class="form-control">
						<%=HTMLUtility.getList("partyid", String.valueOf(bean.getPartyid()), partylist)%>
						 </select>
								</div>
							</div>
							
							
							
							<div class="col-md-6 offset-md-4">
								<input type="submit" class="btn btn-primary" name="operation" value="<%=CandidateCtl.OP_SAVE%>">
								
							</div>
						</form>
					</div>

				</div>
			</div>
		</div>
	</div>
	</main>
	<div style="margin-top: 120px">
		<%@ include file="Footer.jsp"%>
	</div>
</body>
</html>