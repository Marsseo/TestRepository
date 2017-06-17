package http.exam02;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;


public class HttpMultipartClient {
	public static void main(String[] args) throws IOException {
		String title = "제목";
		StringBody titleBody = new StringBody(title, ContentType.create("text/plain", Charset.forName("UTF-8")));
		
		String content = "내용";
		StringBody contentBody = new StringBody(content, ContentType.create("text/plain", Charset.forName("UTF-8")));
		
		File attach = new File("C:Temp/메인.png");
		FileBody attachBody = new FileBody(attach,ContentType.create("image/png"));
		
		HttpPost httpPost = new HttpPost("http://192.168.3.109:8080/IoTWebProgramming/http/exam02");		
		
		MultipartEntityBuilder multipartBuilder = MultipartEntityBuilder.create();
		
		//문자파트
		multipartBuilder.addPart("title", titleBody);
		multipartBuilder.addPart("content", contentBody);
		
		//파일파트
		multipartBuilder.setCharset(Charset.forName("UTF-8")); // 파일 이름이 한글이 포함 되어있는지
		multipartBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE); // 브라우저가 파일을 보내는 방식과 동일하게 함
		multipartBuilder.addPart("attach", attachBody);
		
		//멀티파트 인코딩된 본문 열기
		HttpEntity reqEntity = multipartBuilder.build();
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		httpPost.setEntity(reqEntity);
		
		CloseableHttpResponse response = httpClient.execute(httpPost);
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
						String restitle = jsonObject.getString("title");
						String resconent = jsonObject.getString("content");
						String resoriginalfilename = jsonObject.getString("originalfilename");
						String ressavedfilename = jsonObject.getString("savedfilename");
						String resfilecontenttype = jsonObject.getString("filecontenttype");
						System.out.println("제목: "+restitle);
						System.out.println("내용: "+resconent);
						System.out.println("파일 원래이름: "+resoriginalfilename);
						System.out.println("파일 저장된 이름: "+ressavedfilename);
						System.out.println("파일 타입: "+resfilecontenttype);
					} catch (Exception ex) {

						ex.printStackTrace();
					} finally {

						is.close();
					}
				}
			} finally {
				response.close();
			}
		
	}
}
