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
	<h4 style="text-align: center; margin-right: 20%">회원 목록</h4>
	<div style="margin-top: 20px; width: 65%; text-align: right; margin-left: 100px;">
			<a href="exam06" class="btn btn-info">리스트로 보기</a>
	</div>
	<table class="table table-condensed" style="width: 65%; margin-left: 100px;">
				<tr>
				<c:forEach var="m" items="${list}">			
					<td><a href="exam06Detail?mid=${m.mid}&pageNo=${pageNo}">
						<img src="exam06Download?savedfilename=${m.msavedfilename}" width="120px" height="160px"/></a>
						<br/>${m.mid}
						<br/>${m.mname}
						<br/>${m.mage}
					</td>
				</c:forEach>
				</tr>
		</table>
		<div style="margin-top: 50px; margin-left: 100px; width: 65%; text-align: center;">
			<a href="exam06Different?pageNo=1" >[처음]</a>
			<c:if test="${groupNo>1}"><a href="exam06Different?pageNo=${startPageNo-1}">[이전]</a></c:if>
			<c:forEach var="i" begin="${startPageNo}" end="${endPageNo}">
			&nbsp;
			<a href="exam06Different?pageNo=${i}"<c:if test="${pageNo==i}">style="fontweight: bold; color: red;"</c:if>>${i}</a>
			&nbsp;
			</c:forEach>
			<c:if test="${groupNo<totalGroupNo}"><a href="exam06Different?pageNo=${endPageNo+1}">[다음]</a></c:if>
			<a href="exam06Different?pageNo=${totalPageNo}">[맨끝]</a>
		</div>
		<div style="margin-top: 20px; width: 65%; text-align: right; margin-left: 100px;">
			<a href="exam03" class="btn btn-success">회원 등록</a>
		</div>
</body>
</html>