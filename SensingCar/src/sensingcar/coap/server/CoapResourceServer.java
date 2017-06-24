package sensingcar.coap.server;

import java.net.InetSocketAddress;
import java.util.logging.Level;
import org.eclipse.californium.core.CaliforniumLogger;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.network.CoapEndpoint;
import org.eclipse.californium.scandium.ScandiumLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sensingcar.coap.server.resource.BackTireResource;
import sensingcar.coap.server.resource.BuzzerResource;
import sensingcar.coap.server.resource.CameraResource;
import sensingcar.coap.server.resource.FrontTireResource;
import sensingcar.coap.server.resource.GasSensorResource;
import sensingcar.coap.server.resource.LaserEmitterResource;
import sensingcar.coap.server.resource.LcdResource;
import sensingcar.coap.server.resource.PhotoresistorSensorResource;
import sensingcar.coap.server.resource.RGBLedResource;
import sensingcar.coap.server.resource.ThermistorResource;
import sensingcar.coap.server.resource.TrackingSensorResource;
import sensingcar.coap.server.resource.UltraSonicSensorResource;


public class CoapResourceServer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoapResourceServer.class);
	private CoapServer coapServer;
	
	//static block ( californium의 자체 로그 출력 금지)
	static {
			CaliforniumLogger.initialize();
			CaliforniumLogger.setLevel(Level.OFF);
			ScandiumLogger.initialize();
			ScandiumLogger.setLevel(Level.OFF);
		}
	
	
	public CoapResourceServer() throws Exception {
		
		coapServer = new CoapServer();
		
		InetSocketAddress bindToAddress1 = new InetSocketAddress("192.168.3.54", 5683);
		InetSocketAddress bindToAddress2 = new InetSocketAddress("localhost", 5683);
		coapServer.addEndpoint(new CoapEndpoint(bindToAddress1));
		coapServer.addEndpoint(new CoapEndpoint(bindToAddress2));
		
		coapServer.add(new BackTireResource());
		coapServer.add(new FrontTireResource());
		coapServer.add(new BuzzerResource());
		coapServer.add(new LaserEmitterResource());
		coapServer.add(new CameraResource());
		coapServer.add(new RGBLedResource());
		coapServer.add(new LcdResource());
		coapServer.add(new UltraSonicSensorResource());
		coapServer.add(new ThermistorResource());
		coapServer.add(new PhotoresistorSensorResource());
		coapServer.add(new TrackingSensorResource());
		coapServer.add(new GasSensorResource());
		
	}
	
	public void start(){
		LOGGER.info("실행");
		coapServer.start();
	}
	
	public void stop(){
		LOGGER.info("실행");
		coapServer.stop();
		coapServer.destroy();
	}
	
}
