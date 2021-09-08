<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	
	<h3> 아이디 : <%-- <% out.println(request.getParameter("id")); %> --%>
				<%=request.getAttribute("id") %>
	</h3>
	<h3> 패스워드 : <%-- <% out.println(request.getParameter("pwd")); %> --%>
				<%=request.getAttribute("pwd") %>
	</h3>
</body>
</html>