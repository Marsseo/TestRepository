package exam30.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;


public class ServerController implements Initializable {

	@FXML
	private Button btnStartStop;
	@FXML
	private TextArea txtDisplay;
	
	private ExecutorService executorService;
	private ServerSocket serverSocket;
	private List<Client> connections = new Vector<Client>();
	
	public static ServerController instance;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		ServerController.instance = this;
		btnStartStop.setOnAction(e->handleBtenStartStop(e));
	}	

	private void handleBtenStartStop(ActionEvent e) {
		if(btnStartStop.getText().equals("시작")){
			startServer();
		}else if(btnStartStop.getText().equals("멈춤")){
			stopServer();
		}
	}

	private void startServer() {
		//스레드풀 생성
		executorService = Executors.newFixedThreadPool(30);
		
		try {
			//서버 소켓 생성
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress("192.168.3.26", 50001));
		} catch (IOException ex) { 
			stopServer();
			return;
		}
		
		
			//연결 수락 작업 정의
			Runnable acceptTask = ()->{
				//UI 변경작업이기 때문에 JavaFX Application Thread가
				//넣어주기 위해서 이벤트 큐에 runLater를 이용해 넣음
				Platform.runLater(()->{
					btnStartStop.setText("멈춤");
					display("시작");
				});
				while(true){
					try {
						//수락 코드
						Socket socket = serverSocket.accept();
						//연결된 클라이언트 정보 출력
						String clientInfo = "[연결수락: "+socket.getRemoteSocketAddress()+"]";
						Platform.runLater(()->display(clientInfo));
						//통신용 클라이언트 객체 생성
						Client client = new Client(socket);
						// Vector에 Client 저장
						connections.add(client);
						// 총 client 수 출력
						Platform.runLater(()->display("[연결개수: "+connections.size()+"]"));
					} catch (IOException ex) {
						stopServer();
						break;
					}
				}
			};
			
			//스레드풀의 작업큐에 작업 넣기
			executorService.submit(acceptTask);
		
						
		
	}

	public void stopServer() {
		try {
			//how1 소켓 다닫고 리스트를 비워버림
			for(Client client : connections){
				client.socket.close();
			}
			connections.clear();

			//how2	Iterator를 이용하여 하나씩 Vector에서 하나씩 제거하고 소켓도 닫음
//			Iterator<Client> iterator = connections.iterator();
//			while(iterator.hasNext()){
//				Client client = iterator.next();
//				client.socket.close();
//				iterator.remove();
//			}
			executorService.shutdownNow();
			serverSocket.close();
			Platform.runLater(()->{
				btnStartStop.setText("시작");
				display("멈춤");
			});
		} catch (IOException ex) {
			ex.printStackTrace();	}
		
	}
	
	private void display(String text){
		txtDisplay.appendText(text+"\n");
	}
	class Client {
		Socket socket;

		public Client(Socket socket) {
			this.socket = socket;
			receive();
		}
		private void receive(){
			//받기 작업 정의
			Runnable receiveTask = ()->{
					try {
						while(true){
						InputStream is = socket.getInputStream();
						byte[] byteArr = new byte[100];
						int readBytes = is.read(byteArr);
						if(readBytes == -1) throw new Exception(); // 정상종료시 예외발생
						String strData = new String(byteArr, 0, readBytes);
						for(Client client : connections){
							client.send(strData);
						}
						}
					} catch (Exception ex) {
						try {
							String clientInfo = "[연결끊김: "+socket.getRemoteSocketAddress()+"]";
							//연결 종료
							this.socket.close();
							Platform.runLater(()->display(clientInfo));
							connections.remove(Client.this);
							Platform.runLater(()->display("[연결개수: "+connections.size()+"]"));
						} catch (IOException ex1) {	}
					}
			};
			//스레드풀 작업큐에 받은 작업을 넣기
			executorService.submit(receiveTask);
		}
		private void send(String message){
			try {
				OutputStream os = socket.getOutputStream();
				byte[] byteArr = message.getBytes();
				os.write(byteArr);
				os.flush();
			} catch (IOException ex) {
				connections.remove(Client.this);
				try {
					socket.close();
				} catch (IOException ex1) {}
			}
			
		}
	}
}
