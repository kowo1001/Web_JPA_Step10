<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<% 	
	String url = application.getContextPath() + "/";
	System.out.println(url);
%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>재능 기부자 상세 정보 화면</title>
</head>
<body>
<br><br><br>
<center>

${requestScope.successMsg}

<h3>재능 기부자 list</h3>
<hr><p> 

	<table border="1">
	<tr>
		<th>기부자 id</th><th>기부자명</th><th>전공분야</th><th>수정하기</th><th>탈퇴하기</th>
	</tr>
	
	 	
	<c:forEach items="${sessionScope.allActivist}" var="activist">
 	<tr>
 		<td>${activist.activistid}</td>
 		<td>${activist.name}</td>
 		<td>${activist.major}</td>
 		<td> 
 			<button onclick="location.href='${pageContext.request.contextPath}/probono?command=activistUpdateReq&activistId=${activist.activistid}'">
 			수정하기</button>
 		</td>
 		<td> 
 			<button onclick="location.href='activistDetail.jsp'" type="hidden" name="command" value="activistDelete">탈퇴하기</button>
 		</td>
 	</tr>
 	</c:forEach>
</table>
	
<br><br><br>

&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/index.html">메인 화면 이동</a>

</center>
</body>
</html>






