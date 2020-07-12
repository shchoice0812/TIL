<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h3>1. Hello First JSP World!</h3>
	
	<h3>2. JSP Expression Test</h3>
	The time on the server is <%= new java.util.Date() %>
	<br><br>
	Converting a string to uppercase: <%= new String("Hello World").toUpperCase() %>
	<br><br>
	25 곱하기 4는: <%= 25*4 %>
	<br><br>
	Is 75 less than 69? <%= 75 < 69 %>
	<br><br>
	
	<h3>3. JSP Scriptlet Test</h3>
	<%
		for (int i=0; i< 5; i++) {
			out.println("<br>for loop: " + i);
		}
	%>
	<br>
	
	<h3>4. JSP Declaration Test</h3>
	<%!
	String makeLower(String data){
		return data.toLowerCase();
	}
	%>
	Lower Case "Hello World": <%= makeLower("Hello World") %>
	
</body>
</html>