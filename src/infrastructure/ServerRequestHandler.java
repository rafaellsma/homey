package infrastructure;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeoutException;

public class ServerRequestHandler {
	private int port;

	private DataInputStream inFromClient;
	private DataOutputStream outToClient;
	
	private Socket socket;
	private ServerSocket serverSocket;
	
	private int sentMessageSize;
	private int receivedMessageSize;
	
	public ServerRequestHandler(int port) throws IOException, TimeoutException {
		this.port = port;
		this.serverSocket = new ServerSocket(port);	
	}

	public void send(byte[] msg) throws IOException, InterruptedException, TimeoutException {
		outToClient = new DataOutputStream(socket.getOutputStream());
		
		sentMessageSize = msg.length;
		
		outToClient.writeInt(sentMessageSize);
		outToClient.write(msg, 0, sentMessageSize);
	}

	public byte[] receive() throws IOException, InterruptedException, TimeoutException {
		socket = serverSocket.accept();
		inFromClient = new DataInputStream(socket.getInputStream());
		
		receivedMessageSize = inFromClient.readInt();
		byte[] msg = new byte[receivedMessageSize];
		inFromClient.read(msg, 0, receivedMessageSize);
		
		return msg;
	}
}