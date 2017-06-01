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
				var bpassword = $("#bpassword").val();
				if(bpassword==""){
					$("#bpassword").attr("placeholder", "비밀번호를 입력하셔야 합니다.");
					$("#bpassword").css("border-color", "red");
					return;
				}
				$.ajax({
					url:"exam05CheckBpassword",
					method:"post",
					//data:"bno=${b.bno}&bpassword="+bpassword
					data:{"bno":"${b.bno}","bpassword":bpassword},
					success: function(data){
						if(data.result=="success"){
							location.href="exam05Update?bno=${b.bno}";
						}else{
							$("#bpassword").val("");
							$("#bpassword").attr("placeholder", "비밀번호가 틀립니다.");
							$("#bpassword").css("border-color", "red");
						}
					}
				});
			}
			function handleBtnDelete(){
				var bpassword = $("#bpassword").val();
				if(bpassword==""){
					$("#bpassword").attr("placeholder", "비밀번호를 입력하셔야 합니다.");
					$("#bpassword").css("border-color", "red");
					return;
				}
				$.ajax({
					url:"exam05CheckBpassword",
					method:"post",
					data:{"bno":"${b.bno}","bpassword":bpassword},
					success: function(data){
						if(data.result=="success"){
							location.href="exam05Delete?bno=${b.bno}";
						}else{
							$("#bpassword").val("");
							$("#bpassword").attr("placeholder", "비밀번호가 틀립니다.");
							$("#bpassword").css("border-color", "red");
						}
					}
				});
			}
		</script>	
	</head>
<body>
<h3>게시물 쓰기</h3><br/><br/>
<form method="post" action="" style="padding: 0px 20px" enctype="multipart/form-data">
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-user"></span>
					</span>
					<input type="text" class="form-control" placeholder="번호" name="bno" value="${b.bno}" disabled/>
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-user"></span>
					</span>
					<input type="text" class="form-control" placeholder="제목" name="btitle" value="${b.btitle}" disabled/>
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-tag"></span>
					</span>
					<input type="text" class="form-control" placeholder="글쓴이" name="bwriter" value="${b.bwriter}" disabled/>
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-lock"></span>
					</span>
					<input type="date" class="form-control" placeholder="글쓴날짜" name="bdate" value="${b.bdate}" disabled />
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-lock"></span>
					</span>
					<input type="number" class="form-control" placeholder="조회수" name="bhitcount" value="${b.bhitcount}" disabled />
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-lock"></span>
					</span>
					<textarea rows="5" class="form-control" placeholder="내용" name="bcontent" disabled>${b.bcontent}</textarea>
				</div>
			</div>	
						
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-camera"></span>
					</span>
					<a href="#" class="form-control"> ${b.boriginalfilename}</a>
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-lock"></span>
					</span>
					<input type="password" class="form-control" placeholder="비밀번호" name="bpassword" id="bpassword" />
				</div>
			</div>
			<a href="exam05" class="btn btn-info" >목록</a>
			<input onclick="handleBtnUpdate()" type="button" class="btn btn-warning" value="수정"/>
			<input onclick="handleBtnDelete()" type="button" class="btn btn-danger" value="삭제"/>
			
			<!-- 
			<button>버튼</button>					//submit 기능
			<input type="button" value=""/>
			<input type="submit" value=""/> 	//submit 기능
			<input type="cancel" value=""/>
			
			<input type="image" value=""/> 		//submit 기능
			<img src="버튼.jpg"/>					//submit 기능 없는 이미지
			
			 -->
			
		</form>
</body>
</html>