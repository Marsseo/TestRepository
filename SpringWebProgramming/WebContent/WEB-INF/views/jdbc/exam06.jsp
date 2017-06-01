<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
		<title>JSP Page</title>
		<link href="<%= application.getContextPath() %>/resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
		<script src="<%= application.getContextPath() %>/resources/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
		<script src="<%= application.getContextPath() %>/resources/bootstrap-3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
	</head>
<body>
	<h4>회원 목록</h4>
	<hr/>
	<table class="table table-condensed" style="width: 70%">
			<tr class="info">
				<td>아이디</td>
				<td>이름</td>
				<td>나이</td>
			</tr>
			<c:forEach var="m" items="${list}">
				<tr>
					<td>${m.mid}</td>
					<td><a href="exam06Detail?mid=${m.mid}">${m.mname}</a></td>
					<td>${m.mage}</td>
				</tr>
			</c:forEach>
		</table>
		<div style="margin-top: 20px; width: 70%; text-align: center;">
			<a href="exam06?pageNo=1" >[처음]</a>
			<c:if test="${groupNo>1}"><a href="exam06?pageNo=${startPageNo-1}">[이전]</a></c:if>
			<c:forEach var="i" begin="${startPageNo}" end="${endPageNo}">
			&nbsp;
			<a href="exam06?pageNo=${i}"<c:if test="${pageNo==i}">style="fontweight: bold; color: red;"</c:if>>${i}</a>
			&nbsp;
			</c:forEach>
			<c:if test="${groupNo<totalGroupNo}"><a href="exam06?pageNo=${endPageNo+1}">[다음]</a></c:if>
			<a href="exam06?pageNo=${totalPageNo}">[맨끝]</a>
		</div>
		<div style="margin-top: 20px; width: 70%; text-align: right;">
			<a href="exam03" class="btn btn-success">회원 등록</a>
		</div>
</body>
</html>