<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>JSP Built-IN objects</title>
</head>
<body>
<h3>JSP Built-IN objects</h3>
Request user agent: <%= request.getHeader("User-Agent") %>
<br><br>
Request language: <%= request.getLocale() %>
</body>
</html>