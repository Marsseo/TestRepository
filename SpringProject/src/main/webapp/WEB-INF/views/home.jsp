<%@page contentType="text/html" pageEncoding="UTF-8"%><!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
		<title>Home</title>
		<link href="<%= application.getContextPath() %>/resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
		<script src="<%= application.getContextPath() %>/resources/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
		<script src="<%= application.getContextPath() %>/resources/bootstrap-3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
		<script>

		  window.fbAsyncInit = function() {
		    FB.init({
		      appId      : '174958859707959',
		      cookie     : true,
		      xfbml      : true,
		      version    : 'v2.8'
		    });
		
		    FB.AppEvents.logPageView();   
		
		  };		
		
		  (function(d, s, id){
		     var js, fjs = d.getElementsByTagName(s)[0];
		     
		     if (d.getElementById(id)) {return;}
		
		     js = d.createElement(s); js.id = id;
		
		     js.src = "//connect.facebook.net/en_US/sdk.js";
		
		     fjs.parentNode.insertBefore(js, fjs);
		
		   }(document, 'script', 'facebook-jssdk'));
			
		  FB.getLoginStatus(function(response) {

		      statusChangeCallback(response);

		  });
		  
		  function checkLoginState() {

		    FB.getLoginStatus(function(response) {

		      statusChangeCallback(response);

		    });

		  }
		</script>	
	</head>
	<body>
		<h3>집가고 싶다</h3>
		<br/>
		<a href="member/newMember" class="btn btn-primary">가입</a>
		<br/>
		<fb:login-button
			scope="public_profile,email"
		  	onlogin="checkLoginState();">
		</fb:login-button>
	</body>
</html>
