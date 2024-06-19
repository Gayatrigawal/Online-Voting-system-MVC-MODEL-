<%@page import="in.co.online.vote.controller.DoVoteCtl"%>
<%@page import="in.co.online.vote.controller.UserLoginCtl"%>
<%@page import="in.co.online.vote.util.DataUtility"%>
<%@page import="in.co.online.vote.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<main class="login-form">
	<div class="cotainer">
		<div class="row justify-content-center">
			<div class="col-md-8">
				<div class="card">
					<div class="card-header">
						Do Vote 
						<h6 style="color: red;"><%=ServletUtility.getErrorMessage(request)%></h6>
						<h6 style="color: green;"><%=ServletUtility.getSuccessMessage(request)%></h6>
					</div>

					<div class="card-body">

						<form action="<%=OVSView.DO_VOTE_CTL%>" method="post">

							<jsp:useBean id="bean" class="in.co.online.vote.bean.VoteBean"
								scope="request"></jsp:useBean>

							<%
								String uri = (String) request.getAttribute("uri");
							%>
							<input type="hidden" name="uri" value="<%=uri%>"> <input
								type="hidden" name="id" value="<%=bean.getId()%>">
								<input
								type="hidden" name="candidateName" value="<%=bean.getCandidateName()%>">
								


							<div class="form-group row">
								<label for="email_address" 
									class="col-md-4 col-form-label text-md-right">Voter Id<font color="red">*</font></label>
								<div class="col-md-6">
									<input type="text" id="email_address"  class="form-control" placeholder="Enter Voter Id"
										name="voterId" value="<%=DataUtility.getStringData(bean.getVoterid())%>" >
										<font  color="red"><%=ServletUtility.getErrorMessage("voterId", request)%></font>
								</div>
							</div>
							
							<%-- <div class="form-group row">
								<label for="email_address" 
									class="col-md-4 col-form-label text-md-right">Candidate Name<font color="red">*</font></label>
								<div class="col-md-6">
									<input type="text" id="email_address"  class="form-control" placeholder="Enter Voter Id"
										name="candidateName" value="<%=DataUtility.getStringData(bean.getCandidateName())%>" >
										<font  color="red"><%=ServletUtility.getErrorMessage("voterId", request)%></font>
								</div>
							</div> --%>

							

							<div class="col-md-6 offset-md-4">
								<input type="submit" class="btn btn-primary" name="operation" value="<%=DoVoteCtl.OP_SAVE%>">
							</div>
						</form>
					</div>

				</div>
			</div>
		</div>
	</div>
	</main>
	<div style="margin-top: 224px">
		<%@ include file="Footer.jsp"%>
	</div>
</body>
</html>
