package com.mycompany.myapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
	
	
	@RequestMapping("/")
	public String home(Model model) {
		
		CoapClient coapClient=null;
		JSONObject jsonObject=null;
		String json = null;
		CoapResponse coapResponse = null;
		
//-------------------------------------------------------------------------------------
		jsonObject = new JSONObject();
		jsonObject.put("command", "status");
		json = jsonObject.toString();
		coapClient = new CoapClient();
		coapClient.setURI("coap://192.168.3.54/camera");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();
		jsonObject = new JSONObject(json);
		model.addAttribute("leftright", jsonObject.getString("leftright"));
		model.addAttribute("updown", jsonObject.getString("updown"));

//--------------------------------------------------------------------------------------------------
		
		jsonObject = new JSONObject();
		jsonObject.put("command", "status");
		json = jsonObject.toString();
		coapClient.setURI("coap://192.168.3.54/rgbled");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();
		jsonObject = new JSONObject(json);
		model.addAttribute("red", jsonObject.getString("red"));
		model.addAttribute("green", jsonObject.getString("green"));
		model.addAttribute("blue", jsonObject.getString("blue"));
		
//--------------------------------------------------------------------------------------------------
		
		jsonObject = new JSONObject();
		jsonObject.put("command", "status");
		json = jsonObject.toString();
		coapClient.setURI("coap://192.168.3.54/laseremitter");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();
		jsonObject = new JSONObject(json);
		model.addAttribute("laseremitterStatus", jsonObject.getString("status"));

//----------------------------------------------------------------------------------------
		jsonObject = new JSONObject();
		jsonObject.put("command", "status");
		json = jsonObject.toString();
		coapClient.setURI("coap://192.168.3.54/buzzer");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();
		jsonObject = new JSONObject(json);
		model.addAttribute("buzzerStatus", jsonObject.getString("status"));	

//---------------------------------------------------------------------------------------
		jsonObject = new JSONObject();
		jsonObject.put("command", "status");
		json = jsonObject.toString();
		coapClient.setURI("coap://192.168.3.54/ultrasonicsensor");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();
		jsonObject = new JSONObject(json);
		model.addAttribute("ultrasonicsensorAngle", jsonObject.getString("angle"));	
		model.addAttribute("ultrasonicsensorDistance", jsonObject.getString("distance"));

//-----------------------------------------------------------------------------------------------------
		jsonObject = new JSONObject();
		jsonObject.put("command", "status");
		json = jsonObject.toString();
		coapClient.setURI("coap://192.168.3.54/lcd");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();
		jsonObject = new JSONObject(json);
		model.addAttribute("lcdline0", jsonObject.getString("line0"));	
		model.addAttribute("lcdline1", jsonObject.getString("line1"));
		
//--------------------------------------------------------------------------------------------------
		jsonObject = new JSONObject();
		jsonObject.put("command", "status");
		json = jsonObject.toString();
		coapClient.setURI("coap://192.168.3.54/backtire");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();
		jsonObject = new JSONObject(json);
		model.addAttribute("backtireDirection", jsonObject.getString("direction"));	
		model.addAttribute("backtireSpeed", jsonObject.getString("speed"));
		
//--------------------------------------------------------------------------------------------------
		
		//model.addAttribute("cameraUrl", "http://192.168.3.54:50001?action=stream" );

		return "controlpanel";
	}
	

	@RequestMapping("/camera")
	public void camera(String command, String leftright, String updown, HttpServletResponse response) throws IOException {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);
		jsonObject.put("leftright", leftright);
		jsonObject.put("updown", updown);
		String json = jsonObject.toString();
		
		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://192.168.3.54/camera");
		CoapResponse coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();
		coapClient.shutdown();
		
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter pwr = response.getWriter();
		pwr.write(json);
		pwr.flush();
		pwr.close();
		
	}
	
	@RequestMapping("/rgbled")
	public void rgbled(String command, String red, String green, String blue, HttpServletResponse response, Model model) throws IOException{
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);
		jsonObject.put("red", red);
		jsonObject.put("green", green);
		jsonObject.put("blue", blue);
		String json = jsonObject.toString();
		
		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://192.168.3.54/rgbled");
		CoapResponse coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		String resJson = coapResponse.getResponseText();
		coapClient.shutdown();		
		
		
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter pwr = response.getWriter();
		pwr.write(resJson);
		pwr.flush();
		pwr.close();
	}
	
	@RequestMapping("/laseremitter")
	public void laseremitter(String command, String status, HttpServletResponse response) throws IOException {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);
		jsonObject.put("status", status);
		String json = jsonObject.toString();
		
		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://192.168.3.54/laser");
		CoapResponse coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();
		coapClient.shutdown();
		
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter pwr = response.getWriter();
		pwr.write(json);
		pwr.flush();
		pwr.close();
		
	}
	
	@RequestMapping("/buzzer")
	public void buzzer(String command, String status, HttpServletResponse response) throws IOException {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);
		jsonObject.put("status", status);
		String json = jsonObject.toString();
		
		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://192.168.3.54/buzzer");
		CoapResponse coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();
		coapClient.shutdown();
		
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter pwr = response.getWriter();
		pwr.write(json);
		pwr.flush();
		pwr.close();
		
	}
	
	@RequestMapping("/ultrasonicsensor")
	public void ultrasonic(String command, String angle, HttpServletResponse response) throws IOException {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);
		jsonObject.put("angle", angle);
		String json = jsonObject.toString();
		
		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://192.168.3.54:5683/ultrasonicsensor");
		CoapResponse coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();
		coapClient.shutdown();
		
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter pwr = response.getWriter();
		pwr.write(json);
		pwr.flush();
		pwr.close();
		
	}
	
	@RequestMapping("/lcd")
	public void lcd(String command, String line0, String line1, HttpServletResponse response) throws IOException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);
		jsonObject.put("line0", line0);
		jsonObject.put("line1", line1);
		String reqJson = jsonObject.toString();
		
		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://192.168.3.54/lcd");
		CoapResponse coapResponse = coapClient.post(reqJson, MediaTypeRegistry.APPLICATION_JSON);
		String resJson = coapResponse.getResponseText();
		coapClient.shutdown();
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.write(resJson);
		pw.flush();
		pw.close();
	}
	
	@RequestMapping("/fronttire")
	public void fronttire(String command, String angle, HttpServletResponse response) throws IOException {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);
		jsonObject.put("angle", angle);
		String json = jsonObject.toString();
		
		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://192.168.3.54:5683/fronttire");
		CoapResponse coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();
		coapClient.shutdown();
		
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter pwr = response.getWriter();
		pwr.write(json);
		pwr.flush();
		pwr.close();
		
	}
	
	@RequestMapping("/backtire")
	public void backtire(String command, String direction, String speed, HttpServletResponse response) throws IOException {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);
		jsonObject.put("direction", direction);
		jsonObject.put("speed", speed);
		String json = jsonObject.toString();
		
		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://192.168.3.54:5683/backtire");
		CoapResponse coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();
		coapClient.shutdown();
		
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter pwr = response.getWriter();
		pwr.write(json);
		pwr.flush();
		pwr.close();
		
	}
	
	
}
