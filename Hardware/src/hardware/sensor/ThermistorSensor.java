package hardware.sensor;

import hardware.convertor.PCF8591;


public class ThermistorSensor {
	
	private PCF8591 pcf8591;
	
	public ThermistorSensor(PCF8591 pcf8591){
		this.pcf8591 = pcf8591;
	}
	
	public double getValue() throws Exception{
		int analogVal = pcf8591.analogRead();
		double value = 5*(double) analogVal/255.0;
		value = 10000 * value/(5-value);
		value = 1/ ((Math.log(value/10000)/3950) + (1/(273.15+25)) );
		value = value -273.15;
		return value;
	}
	
	public static void main(String[] args) throws InterruptedException, Exception {
		PCF8591 pcf8591 = new PCF8591(0x48, PCF8591.AIN2);
		ThermistorSensor ts = new ThermistorSensor(pcf8591);
		
		while(true){
			double value = ts.getValue();
			System.out.print("현재온도 : ");
			System.out.printf("%.2f 도", value);
			Thread.sleep(1000);
		}
	}
}
