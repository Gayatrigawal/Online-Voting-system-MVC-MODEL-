<%@page import="in.co.online.vote.util.PropertyReader"%>
<%@page import="in.co.online.vote.util.DataUtility"%>
<%@page import="in.co.online.vote.bean.PartiesBean"%>
<%@page import="in.co.online.vote.model.VotePartiesModel"%>
<%@page import="in.co.online.vote.bean.VoteBean"%>
<%@page import="in.co.online.vote.util.ServletUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.online.vote.controller.OVSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Result</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<main class="login-form">
	<div class="cotainer">
		<div class="row justify-content-center">
			<div class="col-md-8">
				<div class="card">
					<div class="card-header">Result</div>

					<div class="card-body">

						<form action="<%=OVSView.VOTE_LIST_CTL%>" method="post">


							<%-- <%
								int l = (int) request.getAttribute("list");
							    int l2 = (int) request.getAttribute("list2");
								int l3 = (int) request.getAttribute("list3"); 
							%> --%>
							<%-- <%
								String one = (String) request.getAttribute("list");
								String two = (String) request.getAttribute("list2");
								String three = (String) request.getAttribute("list3");
							%> --%>
							<% PartiesBean bean = null;
							VoteBean bean1=new VoteBean();
							
							int pageNo = 1;
							int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
							%>
							<%
							VotePartiesModel model = new VotePartiesModel();
							List list1 = null;
							list1  = model.list();
							Iterator it1 = list1.iterator();
							while (it1.hasNext()) {
							 bean = (PartiesBean) it1.next();
							%>
							
							<% bean1.setPartyId(bean.getId());
							List list = model.search(bean1, pageNo, pageSize);
							request.setAttribute("list",list.size());
							%>
							<%int l1 = (Integer) request.getAttribute("list"); %>
					<%-- 		<%
								int index = 1;
								List list = ServletUtility.getList(request);
								Iterator it = list.iterator();
								while (it.hasNext()) {
									VoteBean bean1 = (VoteBean) it.next();
							%> 
							<h1><%=bean1.getPartyName()%></h1>
							<%} %> --%>

							<%-- <td scope="col"><font size="13px"><b><%=bean.getPartyName() %></font></b></td> --%>
							<div class="form-group row">
								<label for="email_address"
									class="col-md-4 col-form-label text-md-right"><%=bean.getName()%><font
									color="red">*</font></label>
								<div class="col-md-6">
									<img alt="" src="/OnlineVotingSystem/image?id=<%=bean.getId()%>"
										style="height: 50px;" width="50px;">&ensp;&ensp;&ensp;&ensp;&ensp;
									<%=l1%>
								</div>
							</div>

					<%-- 		<div class="form-group row">
								<label for="email_address"
									class="col-md-4 col-form-label text-md-right">Republicans<font
									color="red">*</font></label>
								<div class="col-md-6">
									<img alt=""
										src="/OnlineVotingSystem/img/2000px-Republicanlogo.svg.png"
										style="height: 40px">&ensp;&ensp;&ensp;&ensp;
									<%=l2%>
								</div>
							</div>

							<div class="form-group row">
								<label for="email_address"
									class="col-md-4 col-form-label text-md-right">Independents<font
									color="red">*</font></label>
								<div class="col-md-6">
									<img alt="" src="/OnlineVotingSystem/img/IPNY_Logo_3423432.png"
										style="height: 40px">&ensp;&ensp;&ensp;&ensp;&ensp;
									<%=l3%>
								</div>
							</div> --%>



							<%
								}
							%>

						</form>
					</div>

				</div>
			</div>
		</div>
	</div>
	</main>
	<div style="margin-top: 168px">
		<%@ include file="Footer.jsp"%>
	</div>
</body>
</html>