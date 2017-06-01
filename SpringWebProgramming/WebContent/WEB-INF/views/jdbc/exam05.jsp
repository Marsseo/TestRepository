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
	<h4>게시물 내용 상세보기</h4>
	<hr/>
	<table class="table table-bordered" style="width: 80% ;">
			<tr class="success">
				<td style="text-align: center;">번호</td>
				<td style="text-align: center;">제목</td>
				<td style="text-align: center;">글쓴이</td>
				<td style="text-align: center;">날짜</td>
				<td style="text-align: center;">조회수</td>
			</tr>
			<c:forEach var="b" items="${list}">
				<tr>
					<td style="text-align: center;">${b.bno}</td>
					<td><a href="exam05Detail?bno=${b.bno}">${b.btitle}</a></td>
					<td style="text-align: center;">${b.bwriter}</td>
					<td style="text-align: center;">${b.bdate}</td>
					<td style="text-align: center;">${b.bhitcount}</td>	
				</tr>
			</c:forEach>
		</table>
		<div style="margin-top: 20px; width: 80%; text-align: center;">
			<a href="exam05?pageNo=1" >[처음]</a>
			<c:if test="${groupNo>1}"><a href="exam05?pageNo=${startPageNo-1}">[이전]</a></c:if>
			<c:forEach var="i" begin="${startPageNo}" end="${endPageNo}">
			&nbsp;
			<a href="exam05?pageNo=${i}"<c:if test="${pageNo==i}">style="fontweight: bold; color: red;"</c:if>>${i}</a>
			&nbsp;
			</c:forEach>
			<c:if test="${groupNo<totalGroupNo}"><a href="exam05?pageNo=${endPageNo+1}">[다음]</a></c:if>
			<a href="exam05?pageNo=${totalPageNo}">[맨끝]</a>
		</div>
		<div style="margin-top: 20px; width: 80%; text-align: right;">
			<a href="exam02" class="btn btn-success">글쓰기</a>
		</div>
		
</body>
</html>