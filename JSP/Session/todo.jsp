<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<!-- Step1: Create HTML Form -->
<form action=todo.jsp>
	Add newitem: <input type="text" name="theItem"/>
	<input type="submit" value="Submit" />
</form>

<br>
Item entered: <%= request.getParameter("theItem") %>


<!--  Step2: Add new item to "To do" list -->
<%
	// get the TO DO items from the session
	List<String> items = (List<String>) session.getAttribute("myToDoList");

	// if the To do otems doesn't exist, then create a new one
	if (items == null) {
		items = new ArrayList<String>();
		session.setAttribute("myToDoList", items);
	}
	
	// see if there is form data to add
	String theItem = request.getParameter("theItem");
	if (theItem != null) {
		items.add(theItem);
	}
%>
<!-- Steo3: Display all "To Do" item for session -->
<br>
<b>To List Items:</b><br>
<ol>
	<% 
		for (String temp: items) {
			out.println("<li>" + temp + "</li>");
		}
	%>
</ol>
</body>
</html>