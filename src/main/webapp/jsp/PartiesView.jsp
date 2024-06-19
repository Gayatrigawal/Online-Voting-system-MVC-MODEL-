<%@page import="in.co.online.vote.controller.PartiesCtl"%>
<%@page import="in.co.online.vote.util.DataUtility"%>
<%@page import="in.co.online.vote.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Parties</title>
</head>
<body>
<%@ include file="Header.jsp"%>
	<main class="login-form">
	<div class="cotainer">
		<div class="row justify-content-center">
			<div class="col-md-8">
				<div class="card">
					<div class="card-header">
						Add Parties
						<h6 style="color: red;"><%=ServletUtility.getErrorMessage(request)%></h6>
						<h6 style="color: green;"><%=ServletUtility.getSuccessMessage(request)%></h6>
					</div>

					<div class="card-body">

						<form action="<%=OVSView.PARTY_CTL%>" method="post" enctype="multipart/form-data">
						
						

							<jsp:useBean id="bean" class="in.co.online.vote.bean.PartiesBean"
								scope="request"></jsp:useBean>

							<input
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
									class="col-md-4 col-form-label text-md-right">Party Name<font color="red">*</font></label>
								<div class="col-md-6">
									<input type="text"   class="form-control" placeholder="Enter Party Name"
										name="name" value="<%=DataUtility.getStringData(bean.getName())%>" >
										<font  color="red"><%=ServletUtility.getErrorMessage("name", request)%></font>
								</div>
							</div>
							
								<div class="form-group row">
								<label for="email_address" 
									class="col-md-4 col-form-label text-md-right">Party Logo<font color="red">*</font></label>
								<div class="col-md-6">
									<input type="file"   class="form-control" placeholder="Enter Party Logo" required="required"
										name="image" value="<%=DataUtility.getStringData(bean.getImage())%>" >
										<%if(bean.getId()>0) {%>
										<img class="m-2"
								src="/OnlineVotingSystem/image?id=<%=bean.getId()%>"
								style="height: 100px;" width="100px;">
								<%} %>
										<font  color="red"><%=ServletUtility.getErrorMessage("image", request)%></font>
								</div>
							</div>
							

							<div class="col-md-6 offset-md-4">
								<input type="submit" class="btn btn-primary" name="operation" value="<%=PartiesCtl.OP_SAVE%>">
								
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