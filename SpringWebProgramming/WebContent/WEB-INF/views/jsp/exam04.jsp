<%@page import="com.mycompany.myapp.dto.Exam07Member"%>
<%@page contentType="text/html; charset=UTF-8"%>

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
		<h3></h3>
		<div>
			<%String name = "홍길동";%>		
			이름: <%=name%>
		</div>
		<div>
			<%Exam07Member Exam07Member = new Exam07Member("홍길동", 30);%>		
			이름: <%=Exam07Member.getName()%>
			나이: <%=Exam07Member.getAge()%>
		</div>
		<hr/>

		<div>
			<% request.setAttribute("name", "홍길동");%>
			이름: <%=request.getAttribute("name") %> 
			이름: ${name}
		</div>
		<div>
			<% request.setAttribute("Exam07Member", new Exam07Member("홍길동", 30));%>
			이름: <%= ((Exam07Member)request.getAttribute("Exam07Member")).getName()%>
			이름: ${Exam07Member.name}
			나이: ${Exam07Member.age}
		</div>
		<div>			
			이름: ${name2}
			이름: <%= request.getAttribute("name2")%>
			이름: ${Exam07Member2.name}
			나이: ${Exam07Member2.age}
			나이: <%= ((Exam07Member)request.getAttribute("Exam07Member2")).getAge()%>			
		</div>
	</body>
</html>

