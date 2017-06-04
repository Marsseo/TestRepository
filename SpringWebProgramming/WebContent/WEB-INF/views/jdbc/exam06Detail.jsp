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
			function handleBtnUpdate(){
				var mpassword = $("#mpassword").val();
				if(mpassword==""){
					$("#mpassword").attr("placeholder", "비밀번호를 입력하셔야 합니다.");
					$("#mpassword").css("border-color", "red");
					return;
				}
				$.ajax({
					url:"exam06CheckMpassword",
					method:"post",
					//data:"bno=${b.bno}&mpassword="+mpassword
					data:{"mid":"${m.mid}","mpassword":mpassword},
					success: function(data){
						if(data.result=="success"){
							location.href="exam06Update?mid=${m.mid}&pageNo=${pageNo}";
						}else{
							$("#mpassword").val("");
							$("#mpassword").attr("placeholder", "비밀번호가 틀립니다.");
							$("#mpassword").css("border-color", "red");
						}
					}
				});
			}
			function handleBtnDelete(){
				var mpassword = $("#mpassword").val();
				if(mpassword==""){
					$("#mpassword").attr("placeholder", "비밀번호를 입력하셔야 합니다.");
					$("#mpassword").css("border-color", "red");
					return;
				}
				$.ajax({
					url:"exam06CheckMpassword",
					method:"post",
					data:{"mid":"${m.mid}","mpassword":mpassword},
					success: function(data){
						if(data.result=="success"){
							location.href="exam06Delete?mid=${m.mid}";
						}else{
							$("#mpassword").val("");
							$("#mpassword").attr("placeholder", "비밀번호가 틀립니다.");
							$("#mpassword").css("border-color", "red");
						}
					}
				});
			}
		</script>
	</head>
<body>
<div>
	<h3 style="text-align: center; margin-right: 20%">회원 정보</h3><br/>
</div>
<div style="float:left; padding: 20px 0px; margin-left: 40px;">
	<!-- E:IoTCourse/EclipseWorkspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/SpringWebProgramming/WEB-INF/upload/ -->
	 <!-- <%= application.getContextPath() %>/WEB-INF/upload/${m.msavedfilename} -->
	<img src="<%= application.getContextPath() %>/resources/upload/${m.msavedfilename}" width="300px" height="400px"/>
</div>
<form method="post" style="padding: 20px 20px; width: 60%; float:left;" enctype="multipart/form-data">
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
					<input type="text" class="form-control" placeholder="이름" name="mname" value="${m.mname}" readOnly/>
				</div>
			</div>
			<div class="form-group" >
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-lock"></span>
					</span>
					<input type="password" class="form-control" placeholder="비밀번호" name="mpassword" id="mpassword" />
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-phone-alt"></span>
					</span>
					<input type="text" class="form-control" placeholder="전화번호" name="mtel" value="${m.mtel}" readOnly/>
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-envelope"></span>
					</span>
					<input type="text" class="form-control" placeholder="이메일" name="memail" value="${m.memail}" readOnly/>
				</div>
			</div>	
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-heart"></span>
					</span>
					<input type="text" class="form-control" placeholder="나이" name="mage" value="${m.mage}" readOnly/>
				</div>
			</div>	
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-home"></span>
					</span>
					<input type="text" class="form-control" placeholder="주소" name="maddress" value="${m.maddress}" readOnly/>
				</div>
			</div>	
						
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-picture"></span>
					</span>
					<a href="exam06Download?savedfilename=${m.msavedfilename}" class="form-control">${m.moriginalfilename}</a>
				</div>
			</div>

			<a href="exam06?pageNo=${pageNo}" class="btn btn-info" >목록</a>
			<input onclick="handleBtnUpdate()" type="button" class="btn btn-warning" value="수정"/>
			<input onclick="handleBtnDelete()" type="button" class="btn btn-danger" value="삭제"/>
		</form>
</body>
</html>