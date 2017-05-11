<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Home</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=no">
		<link href="/WebApplication/resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
		<script src="/WebApplication/resources/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
		<script src="/WebApplication/resources/bootstrap-3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
	</head>
	
	<body>
		WebApplication Home
		<hr/>
		<h4>html태그</h4>
		<a href="html/exam01" class="btn btn-warning">exam01</a>
		<h4>CSS</h4>
		<a href="css/exam01" class="btn btn-success">exam01</a><br/>
		<a href="css/exam02" class="btn btn-success">exam02</a><br/>
		<a href="css/exam03" class="btn btn-success">exam03</a><br/>
		
		<h4>CSS</h4>
		<%for(int i=1;i<=11;i++){
			String exam="exam";
			if(i<10) {exam += "0"+i;}
			else {exam+=String.valueOf(i);}%>
		
		<a href="javascript/<%=exam%>" class="btn btn-primary"><%=exam%></a>
		<%}%>
		
		<h4>Jquery</h4>
		<%for(int i=1;i<=4;i++){
			String exam="exam";
			
				exam+="0"+String.valueOf(i);
			
		%>
		<a href="javascript/<%=exam%>" class="btn btn-primary"><%=exam%></a>
		
		<%}%>
		
		<h4>bootstrap</h4>
		<%for(int i=1;i<=3;i++){
			String exam="exam";
			
				exam+="0"+String.valueOf(i);
			
		%>
		<a href="bootstrap/<%=exam%>" class="btn btn-primary"><%=exam%></a>
		
		<%}%>
		
		
	</body>
</html>
