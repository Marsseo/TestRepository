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
			if($("#mattach")[0].files.lenth!=0){
				var originalfilename = $("#mattach")[0].files[0].name;
				$("#spanFileName").text(originalfilename);
			}
		}
			function handlenull(){
				var form = $("#form");
				var mpassword = $("#mpassword").val();
				if(mpassword==""){
					$("#mpassword").attr("placeholder", "비밀번호를 입력하셔야 합니다.");
					$("#mpassword").css("border-color", "red");
					return;
				}else{
					form.submit();
				}
			}
		</script>
	</head>
<body>
<h3 style="text-align: center; margin-right: 20%">회원 정보</h3><br/><br/>
<form method="post" id="form" style="padding: 20px 80px; width: 80%; text-align: center;" enctype="multipart/form-data">
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-user"></span>
					</span>
					<input type="text" class="form-control" placeholder="아이디" name="mid" value="${m.mid}" readOnly/>
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-tag"></span>
					</span>
					<input type="text" class="form-control" placeholder="이름" name="mname" value="${m.mname}"/>
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-lock"></span>
					</span>
					<input type="password" class="form-control" placeholder="비밀번호" name="mpassword" id="mpassword" value="${m.mpassword}"/>
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-phone-alt"></span>
					</span>
					<input type="text" class="form-control" placeholder="전화번호" name="mtel" value="${m.mtel}" />
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-envelope"></span>
					</span>
					<input type="text" class="form-control" placeholder="이메일" name="memail" value="${m.memail}" />
				</div>
			</div>	
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-heart"></span>
					</span>
					<input type="text" class="form-control" placeholder="나이" name="mage" value="${m.mage}" />
				</div>
			</div>	
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-home"></span>
					</span>
					<input type="text" class="form-control" placeholder="주소" name="maddress" value="${m.maddress}"/>
				</div>
			</div>	
						
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-picture"></span>
					</span>
					<div class="form-control" style="height: 47px;text-align: left;">
						<span id="spanFileName">${m.moriginalfilename}</span>
						<label for="mattach" class = "btn btn-default">변경</label>
						<input id="mattach"  style="visibility:hidden;" type="file"placeholder="변경" name="mattach" onchange="fileChange()" style="display: inline-block;" />
					</div>
				</div>
			</div>

			<input onclick="handlenull()" type="button" class="btn btn-info" value="수정완료"/>
		</form>
</body>
</html>