var trackingChart;
$(function() {
	
	trackingChart = new Highcharts.Chart({
		
		chart: {
			renderTo:"trackingSensorChartContainer",
			defaultSeriesType:"spline",
			events: {
				load: requestTrackingSensorData
			}
		},
		colors: ['white'],
		title: {
			text: "TrackingSensor(적외선센서)"
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
				text: "적외선",
				margin: 30
			}
		},
		series: [{
			name: "적외선",
			date:[]
			}]
	});
});

function requestTrackingSensorData(){
	var ws = new WebSocket("ws://"+location.host+"/SensingCarRemoteWebControl/websocket/trackingsensor");
	ws.onmessage = function(event){
		var data = JSON.parse(event.data);
		var series = trackingChart.series[0];
		var shift = series.data.length>20;
		series.addPoint([data.time, data.tracking], true, shift);
		
	};
}