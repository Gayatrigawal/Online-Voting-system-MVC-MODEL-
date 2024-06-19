
<%@page import="in.co.online.vote.controller.LoginCtl"%>
<%@page import="in.co.online.vote.controller.OVSView"%>
<%@page import="in.co.online.vote.bean.UserBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="/OnlineVotingSystem/css/font-awesome.min.css">
    <link href="/OnlineVotingSystem/css/bootstrap.min.css" rel="stylesheet">

    <link href="/OnlineVotingSystem/css/mdb.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/OnlineVotingSystem/css/style.css">

<script src="/OnlineVotingSystem/css/bootstrap.min.js"></script>
<script src="/OnlineVotingSystem/css/jquery-1.11.1.min.js"></script>
 <link rel="stylesheet" href="/OnlineVotingSystem/css/jquery-ui.css">
  <link rel="stylesheet" href="/OnlineVotingSystem/css/style.css">
  <script src="OnlineVotingSystem/css/jquery-ui.js"></script>

<style type="text/css">
    .whov:hover { background-color: #00695c!important; }
.view {
    background-position: center center;
    background-repeat: no-repeat;
    height: 500px;
}
.secondbase {
    background-color: rgba(255,255,255,.6); 
    margin-top: -90px;
}
.filterable {
    margin-top: 15px;
}
.filterable .panel-heading .pull-right {
    margin-top: -20px;
}
.filterable .filters input[disabled] {
    background-color: transparent;
    border: none;
    cursor: auto;
    box-shadow: none;
    padding: 0;
    height: auto;
}
.filterable .filters input[disabled]::-webkit-input-placeholder {
    color: #333;
}
.filterable .filters input[disabled]::-moz-placeholder {
    color: #333;
}
.filterable .filters input[disabled]:-ms-input-placeholder {
    color: #333;
}

</style>
</head>
<body>
<header>
<%
    UserBean userBean = (UserBean) session.getAttribute("user");

    boolean userLoggedIn = userBean != null;

    String welcomeMsg = "Hi, ";

    

%>
    <nav class="navbar navbar-expand-lg navbar-dark default-color-dark fixed-top">
        <a class="navbar-brand" href="<%=OVSView.WELCOME_CTL%>">Online Voting System</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
            aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse " id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
              <%
        if (userLoggedIn) {
   			 %>
   			 
   			 <%if(userBean.getRoleId()==1){ %>
                <li class="nav-item active">
                    <a class="nav-link" href="<%=OVSView.WELCOME_CTL%>">Home<span class="sr-only">(current)</span></a>
                </li>
               
                
              <li class="nav-item">
                    <a class="nav-link" href="<%=OVSView.USER_LIST_CTL%>">User List</a>
                </li>
                
                 <li class="nav-item">
                    <a class="nav-link" href="<%=OVSView.PARTY_CTL%>">Add Political Parties</a>
                </li>
                
                 <li class="nav-item">
                    <a class="nav-link" href="<%=OVSView.PARTIES_LIST_CTL%>">Political Parties List</a>
                </li>
                
                  <li class="nav-item">
                    <a class="nav-link" href="<%=OVSView.CANDIDATE_CTL%>">Add Candidate</a>
                </li>
                
                 <li class="nav-item">
                    <a class="nav-link" href="<%=OVSView.CANDIDATE_LIST_CTL%>">Candidate List</a>
                </li>
                
                  <li class="nav-item">
                    <a class="nav-link" href="<%=OVSView.ELECTION_CTL%>">Add Election</a>
                </li>
                
                  <li class="nav-item">
                    <a class="nav-link" href="<%=OVSView.ELECTION_LIST_CTL%>">Election List</a>
                </li>
                
                <li class="nav-item">
                    <a class="nav-link" href="<%=OVSView.RESULT_LIST_CTL%>">Result</a>
                </li>
                <%} 
   			        else if(userBean.getRoleId()==2){
                %>
                <li class="nav-item active">
                    <a class="nav-link" href="<%=OVSView.WELCOME_CTL%>">Home<span class="sr-only">(current)</span></a>
                </li>
                
                 <li class="nav-item">
                    <a class="nav-link" href="<%=OVSView.VOTER_APPLICATION_LIST_CTL%>">Voter Application</a>
                </li>
              <%--   <li class="nav-item">
                    <a class="nav-link" href="<%=OVSView.VOTE_CTL%>">Vote</a>
                </li> --%>
                 
                
                <%}else{ %>
                
                 <li class="nav-item active">
                    <a class="nav-link" href="<%=OVSView.WELCOME_CTL%>">Home<span class="sr-only">(current)</span></a>
                </li>
                
                  <li class="nav-item">
                    <a class="nav-link" href="<%=OVSView.VOTER_APPLICATION_CTL%>">Voter Application Form</a>
                </li>
                
                  <li class="nav-item">
                    <a class="nav-link" href="<%=OVSView.VOTER_APPLICATION_LIST_CTL%>">Voter Application List</a>
                </li>
                
                  <li class="nav-item">
                    <a class="nav-link" href="<%=OVSView.ELECTION_LIST_CTL%>">Election</a>
                </li>
                
                <%}} %>
                
            </ul>
             
              <ul class="navbar-nav ml-auto nav-flex-icons">
                 <%
				if (userLoggedIn) {
				 %> 
                <li class="nav-item">
                    <a class="nav-link" href="<%=OVSView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>">LogOut</a>
                </li>
                <%
					} else {
				%>
			<%-- 	 <li class="nav-item">
                    <a class="nav-link" href="<%=OVSView.USER_LOGIN_CTL%>">User Login</a>
                </li> --%>
				 <li class="nav-item">
                    <a class="nav-link" href="<%=OVSView.LOGIN_CTL%>">Login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%=OVSView.USER_REGISTRATION_CTL%>">Registration</a>
                </li>
                
				<%
             }
             %>
            </ul>
        </div>
    </nav>
    </header>
    <br>
    <br>
    <br>
    
    <hr>
   
</body>
</html>