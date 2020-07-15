<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Student Regeisteration</title>
</head>
<body>
	The student is confirmed: ${param.firstName} ${param.lastName} 
	<br><br>
	The student's country: ${param.country}
	<br><br>
	The student's favorite language : ${param.language }
	
	<!-- display list of "favorite language -->
	<ul>
		<%
			String[] langs = request.getParameterValues("interestLanguage");
			
			if (langs != null){
				for (String tempLang : langs) {
					out.println("<li>" + tempLang + "</li>");
				}
			}
		%>
	</ul>
</body>
</html>