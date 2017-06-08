package hardware.sensor;

import hardware.convertor.PCF8591;


public class Photoresistor {
	
	private PCF8591 pcf8591;
	
	public Photoresistor(PCF8591 pcf8591){
		this.pcf8591 = pcf8591;
	}
	
	public double getValue() throws Exception{
		int analogVal = pcf8591.analogRead();
		return analogVal;
	}
	
	public static void main(String[] args) throws InterruptedException, Exception {
		PCF8591 pcf8591 = new PCF8591(0x48, PCF8591.AIN0);
		Photoresistor ts = new Photoresistor(pcf8591);
		
		while(true){
			double value = ts.getValue();
			System.out.print("현재밝기: "+value);
			//System.out.printf("%.2f 도", value);
			Thread.sleep(1000);
		}
	}
}
