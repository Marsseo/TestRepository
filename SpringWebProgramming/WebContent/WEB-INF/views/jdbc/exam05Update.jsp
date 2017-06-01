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
		<script type="text/javascript">
			function fileChange(){
				if($("#battach")[0].files.lenth!=0){
					var originalfilename = $("#battach")[0].files[0].name;
					$("#spanFileName").text(originalfilename);
				}
			}
			function handlenull(){
				var form = $("#form");
				var bpassword = $("#bpassword").val();
				if(bpassword==""){
					$("#bpassword").attr("placeholder", "비밀번호를 입력하셔야 합니다.");
					$("#bpassword").css("border-color", "red");
					return;
				}else{
					form.submit();
				}
			}
		</script>
	</head>
<body>
<h3>게시물 수정</h3><br/><br/>
<form method="post" id="form" style="padding: 0px 20px" enctype="multipart/form-data">
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-user"></span>
					</span>
					<input type="hidden" class="form-control" placeholder="번호" name="bno" value="${b.bno}"/>
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-user"></span>
					</span>
					<input type="text" class="form-control" placeholder="제목" name="btitle" value="${b.btitle}"/>
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-tag"></span>
					</span>
					<input type="text" class="form-control" placeholder="글쓴이" name="bwriter"value="${b.bwriter}" disabled/>
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-lock"></span>
					</span>
					<input type="password" class="form-control" placeholder="비밀번호" name="bpassword" value="${b.bpassword}" id="bpassword"/>
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-lock"></span>
					</span>
					<textarea rows="5" class="form-control" placeholder="내용" name="bcontent">${b.bcontent}</textarea>
				</div>
			</div>	
						
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-camera"></span>
					</span>
					<div class="form-control" style="height: 47px;">
						<span id="spanFileName">${b.boriginalfilename}</span>
						<label for="battach" class = "btn btn-default">변경</label>
						<input id="battach"  style="visibility:hidden;" type="file"placeholder="변경" name="battach" onchange="fileChange()" style="display: inline-block;" />
					</div>
				</div>
			</div>

			<input onclick="handlenull()" type="button" class="btn btn-info" value="수정 완료"/>
		</form>
</body>
</html>