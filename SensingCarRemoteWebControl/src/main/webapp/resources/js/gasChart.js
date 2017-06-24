var gasChart;
$(function() {
	
	gasChart = new Highcharts.Chart({
		
		chart: {
			renderTo:"gasSensorChartContainer",
			defaultSeriesType:"spline",
			events: {
				load: requestGasData
			}
		},
		colors: ['yellow'],
		title: {
			text: "GasSensor(가스센서)"
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
				text: "가스",
				margin: 30
			}
		},
		series: [{
			name: "가스",
			date:[]
			}]
	});
});

function requestGasData(){
	var ws = new WebSocket("ws://"+location.host+"/SensingCarRemoteWebControl/websocket/gassensor");
	ws.onmessage = function(event){
		var data = JSON.parse(event.data);
		var series = gasChart.series[0];
		var shift = series.data.length>20;
		series.addPoint([data.time, data.gas], true, shift);
		
	};
}