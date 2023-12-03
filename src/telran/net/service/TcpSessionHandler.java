package telran.net.service;

import java.net.Socket;
import java.io.*;
import telran.net.dto.*;
/**
 * working with a client over the received Socket
 */
public class TcpSessionHandler implements Runnable {
	Socket socket;
	ApplProtocol protocol;
	ObjectInputStream input;
	ObjectOutputStream output;

	public TcpSessionHandler(Socket socket, ApplProtocol protocol) throws IOException {
		this.socket = socket;
		this.protocol = protocol;
		input = new ObjectInputStream(socket.getInputStream());
		output = new ObjectOutputStream(socket.getOutputStream());
	}

	@Override
	public void run() {
		try {
			while(true) {
				Request request = (Request) input.readObject(); // reading request from a client
				Response response = protocol.getResponse(request);
				output.writeObject(response);
			}
		} catch (EOFException e) {
			System.out.println("Client closed connection");
		} catch (Exception e) {
			System.out.println("Abnormal closing connection " + e.getMessage());
		}

	}

}
