package telran.net.service;
import java.net.*;
public class TcpServer implements Runnable {
ServerSocket serverSocket;
int port;
ApplProtocol protocol; // for connecting universal TCP Server with specific Application Server
public TcpServer(int port, ApplProtocol protocol)throws Exception {
	this.port = port;
	this.protocol = protocol;
	serverSocket = new ServerSocket(port);
	//ServerSocket functionality is listening the connections from the clients
	
}
@Override
	public void run() {
	System.out.println("Server is listening on the port " + port);
		try {
			while(true) {
				Socket socket = serverSocket.accept();//once a client sends request for connection
				//the method accept will return a Socket for the connection
				TcpSessionHandler sessionHandler = new TcpSessionHandler(socket, protocol);
				Thread thread = new Thread(sessionHandler);
				thread.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
