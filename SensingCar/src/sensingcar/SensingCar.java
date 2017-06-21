
package sensingcar;

import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sensingcar.coap.server.CoapResourceServer;


public class SensingCar {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SensingCar.class);
	
	private CoapResourceServer coapResourceServer;

	public SensingCar() throws Exception {
		
		coapResourceServer = new CoapResourceServer();
	}
	
	
	public void start(){
		LOGGER.info("실행");
		coapResourceServer.start();
		System.out.println("SensingCar start!");
	}
	
	public void stop(){
		LOGGER.info("실행");
		coapResourceServer.stop();
		System.out.println("SensingCar stop ....");
		return ;
	}
	
	public static void main(String[] args) throws Exception {
		SensingCar sensingCar = new SensingCar();
		
		sensingCar.start();
		
		System.out.print("please q to quit: ");
		
		Scanner scanner = new Scanner(System.in);
		String command = scanner.nextLine();
		if(command.equals("q")){
			sensingCar.stop();
		}
		
		
		
	}
	
}
