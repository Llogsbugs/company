package telran.net.client;

import java.io.*;
import java.net.*;

import telran.net.dto.Request;
import telran.net.dto.Response;

public class TcpClient implements Closeable{
	Socket socket;
	ObjectOutputStream output;
	ObjectInputStream input;
	public TcpClient(String host, int port)throws Exception {
		socket = new Socket(host, port);//establishing of a connection with
		//the server running on the given host and listening connections on 
		//the given port
		output = new ObjectOutputStream(socket.getOutputStream());
		input = new ObjectInputStream(socket.getInputStream());
	}
	public <T> T send(String requestType, Serializable requestData) {
		Request request = new Request(requestType, requestData);
		try {
			output.writeObject(request);
			Response response = (Response) input.readObject();
			if (!response.responseCode().equals(Response.OK)) {
				throw new RuntimeException(response.responseData().toString());
				//Implied that in the case of wrong request responseCode contains 
				//appropriate message, and responseData contains message explaining the error
			}
			T result = (T) response.responseData();
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}

	@Override
	public void close() throws IOException {
		socket.close();
		
	}

}
