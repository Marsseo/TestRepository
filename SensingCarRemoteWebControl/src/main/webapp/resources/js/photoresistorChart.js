var photoresistorChart;
$(function() {
	
	photoresistorChart = new Highcharts.Chart({
		
		chart: {
			renderTo:"photoresistorSensorChartContainer",
			defaultSeriesType:"spline",
			events: {
				load: requestPhotoresistorSensorData
			}
		},
		colors: ['green'],
		title: {
			text: "PhotoresistorSensor(조도센서)"
		},
		xAxis:{
			type: "datetime",
			tickPixelInterval: 100,
			maxZoom: 20*1000
		},
		yAxis:{
			minPadding: 0.2,
			maxPadding: 0.2,
			title: {
				text: "조도",
				margin: 30
			}
		},
		series: [{
			name: "조도",
			date:[]
			}]
	});
});

function requestPhotoresistorSensorData(){
	var ws = new WebSocket("ws://"+location.host+"/SensingCarRemoteWebControl/websocket/photoresistorsensor");
	ws.onmessage = function(event){
		var data = JSON.parse(event.data);
		var series = photoresistorChart.series[0];
		var shift = series.data.length>20;
		series.addPoint([data.time, data.light], true, shift);
		
	};
}