function ultrasonic(command, angle){
	
	var json = {"command":command, "angle": angle};
	
	$.ajax({
		url: "http://"+location.host+"/SensingCarRemoteWebControl/ultrasonic",
		data: json,
		method: "post",
		success: function(data){
			if(data.result == "success"){
				$("#ultrasonicStatus").html("angle="+data.angle+"; distance="+data.distance);
			}
		} 
	}); 
}