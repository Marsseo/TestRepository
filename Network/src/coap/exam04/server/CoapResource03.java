package coap.exam04.server;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;


public class CoapResource03 extends CoapResource {
	
	private int value;	
	
	public CoapResource03() {
		super("resource03");
		// 관찰 기능 활성화
		setObservable(true);
		//관찰 기능을 제공한다라는 것을 클라이언트가 알 수 있도록 설정
		getAttributes().setObservable();
		
		Thread th = new Thread(){
			@Override
			public void run() {
				int i = 0;
				while(true){
					value = i;
					changed();
					try{Thread.sleep(500);}catch(Exception e){}
					i++;
				}
				
			}
			
		};
		th.start();
	}

	
	@Override
	public void handleGET(CoapExchange exchange) {
		exchange.respond("value"+value);
	}

	@Override
	public void handlePOST(CoapExchange exchange) {
		
	}
	
	
	
}
