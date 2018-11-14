package infrastructure;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.util.concurrent.TimeoutException;

import java.net.Socket;

public class ClientRequestHandler {

	private String host;
	private int port;
	
	private Socket socket;
	private DataInputStream inFromServer;
	private DataOutputStream outToServer;
	
	private int sentMessageSize;
	private int receivedMessageSize;
	
	public ClientRequestHandler(String host, int port) throws IOException, TimeoutException {
		this.host = host;
		this.port = port;
		
		socket = new Socket(host, port);
		inFromServer = new DataInputStream(socket.getInputStream());
		outToServer = new DataOutputStream(socket.getOutputStream());
	}

	public void send(byte[] msg) throws IOException, InterruptedException, TimeoutException {
		sentMessageSize = msg.length;
		
		outToServer.writeInt(sentMessageSize);
		outToServer.write(msg, 0 , sentMessageSize);
		outToServer.flush();
	}

	public byte[] receive() throws Exception {
		receivedMessageSize = inFromServer.readInt();
        byte[] msgReceived = new byte[receivedMessageSize];
        
        inFromServer.read(msgReceived, 0, receivedMessageSize);
        
        return msgReceived;
	}

}