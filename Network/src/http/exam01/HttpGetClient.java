package http.exam01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

public class HttpGetClient {

	public static void main(String[] args) throws IOException, URISyntaxException {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			URIBuilder uriBuilder = new URIBuilder("http://192.168.3.109:8080/IoTWebProgramming/http/exam01");
			uriBuilder.setParameter("thermistor", String.valueOf(25));
			uriBuilder.setParameter("photoresistor", String.valueOf(100));
			HttpGet httpGet = new HttpGet(uriBuilder.build());

			System.out.println("Executing request " + httpGet.getRequestLine());
			CloseableHttpResponse response = httpClient.execute(httpGet);
			try {

				HttpEntity resEntity = response.getEntity(); //응답에서 바디부분을 얻어라

				if (resEntity != null) { // 내용이 있냐없냐
					InputStream is = resEntity.getContent();
					try {
						InputStreamReader isr = new InputStreamReader(is);
						BufferedReader br = new BufferedReader(isr);
						String json = "";
						while (true) {
							String data = br.readLine();
							if (data == null) {
								break;
							}
							json += data;
						}
						JSONObject jsonObject = new JSONObject(json);
						String thermistor = jsonObject.getString("thermistor");
						String photoresistor = jsonObject.getString("photoresistor");
						System.out.println("온도: "+thermistor);
						System.out.println("조도: "+photoresistor);
					} catch (Exception ex) {

						ex.printStackTrace();
					} finally {

						is.close();
					}
				}
			} finally {
				response.close();
			}
		} finally {
			httpClient.close();
		}
	}
}
