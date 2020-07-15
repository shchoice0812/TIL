<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Cookies Example</title>
</head>
<body>
<h3>Training Portal</h3>

<!-- read the favorite programming language cookie -->
<%
	String favLang = "Java";

	// get teh cookies from the browser request
	Cookie[] theCookies = request.getCookies();
	
	if (theCookies != null) {
		for (Cookie tempCookie : theCookies) {
			if ("myApp.favoriteLanguage".equals(tempCookie.getName())) {
				favLang = tempCookie.getValue();
				break;
			}
		}
	}
%>

<!-- show information  -->
<h4> New Books for <%= favLang %></h4>
<ul>
	<li>1</li>
	<li>2</li>
</ul>
</body>
</html>