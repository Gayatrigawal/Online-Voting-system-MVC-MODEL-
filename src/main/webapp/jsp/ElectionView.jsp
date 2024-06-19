<%@page import="in.co.online.vote.controller.ElectionCtl"%>
<%@page import="in.co.online.vote.util.DataUtility"%>
<%@page import="in.co.online.vote.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Election</title>
</head>
<body>
<div class="site-wrap">
<%@ include file="Header.jsp" %>
 <div class="bg-light py-3">
      <div class="container">
        <div class="row">
          <div class="col-md-12 mb-0"><strong class="text-black">Add Election</strong></div>
        </div>
      </div>
    </div>  

    <div class="site-section">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <h2 class="h3 mb-3 text-black">Add Election</h2>
            
          </div>
          <div class="col-md-7">
			
            <form action="<%=OVSView.ELECTION_CTL%>" method="post">
            
            <jsp:useBean id="bean" class="in.co.online.vote.bean.ElectionBean"
			scope="request"></jsp:useBean>
			
			<input type="hidden" name="id" value="<%=bean.getId()%>">
              
              <div class="p-3 p-lg-5 border">
               <center><b><font color="red"> <%=ServletUtility.getErrorMessage(request)%></font></b>
               <b><font color="green"> <%=ServletUtility.getSuccessMessage(request)%></font></b>
               </center>
               
                <div class="form-group row">
                  <div class="col-md-12">
                    <label for="c_email" class="text-black">Name<span class="text-danger">*</span></label>
                    <input type="text" class="form-control"  name="electionName" placeholder="Enter Election Name" 
                    value="<%=DataUtility.getStringData(bean.getElectionName())%>" >
                    <font color="red"> <%=ServletUtility.getErrorMessage("electionName", request)%></font>
                  </div>
                </div>
                
                <div class="form-group row">
                  <div class="col-md-12">
                    <label for="c_subject" class="text-black">Date<span class="text-danger">*</span></label>
                      	<input type="text"  id="datepicker"  class="form-control" placeholder="Enter Date"
										name="date" value="<%=DataUtility.getDateString(bean.getElectionDate())%>" >
										<font  color="red"><%=ServletUtility.getErrorMessage("date", request)%></font>
                  </div>
                </div>
                
              
                <div class="container text-center">
                    <input type="submit" class="btn btn-success" name="operation" value="<%=ElectionCtl.OP_SAVE%>">
                </div>
                    
              </div>
            </form>
          </div>
 </div>
      </div>
    </div>

<%@ include file="Footer.jsp" %>
</div>
</body>
</html>