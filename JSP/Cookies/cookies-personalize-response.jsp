<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
	 // read from data
	String favLang = request.getParameter("favoriteLanguage");
	
	// create the cookie
	Cookie theCookie = new Cookie("myApp.favoriteLanguage", favLang);
	
	// set the life span ... total number of seconds
	theCookie.setMaxAge(60*60*24*365);
	
	// send cooke to browser
	response.addCookie(theCookie);
%>
	Favorite Language to : ${param.favoriteLanguage}
	<br><br>
	<a href="cookies-homepage.jsp">Return to homepage</a>
</body>
</html>