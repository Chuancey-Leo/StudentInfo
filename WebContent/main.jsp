
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%
	if(session.getAttribute("currentUser")==null){
		response.sendRedirect("login.jsp");
		return;
	}
	String mainPage=(String)request.getAttribute("mainPage");
	if(mainPage==null || mainPage.equals("")){
		mainPage="common/default.jsp";
	}
%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>主页面</title>
</head>

<link href="${pageContext.request.contextPath}/style/studentInfo.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>

<body>
<div class="container">
	<jsp:include page="common/head.jsp"></jsp:include>
	<jsp:include page="common/menu.jsp"></jsp:include>
	<jsp:include page="common/nav.jsp"></jsp:include>
	<jsp:include page="<%=mainPage %>"></jsp:include>
	<jsp:include page="common/foot.jsp"></jsp:include>
</div>
</body>
</html>