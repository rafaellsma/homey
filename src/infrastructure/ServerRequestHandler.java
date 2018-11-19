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

	private int sentMessageSize;
	private int receivedMessageSize;
	
	public ServerRequestHandler(Socket socket) {
		this.socket = socket;
	}

	public void send(byte[] msg) throws IOException {
		outToClient = new DataOutputStream(socket.getOutputStream());
		
		sentMessageSize = msg.length;
		
		outToClient.writeInt(sentMessageSize);
		outToClient.write(msg, 0, sentMessageSize);
		outToClient.close();
		inFromClient.close();
		socket.close();
	}

	public byte[] receive() throws IOException {
		inFromClient = new DataInputStream(socket.getInputStream());
		
		receivedMessageSize = inFromClient.readInt();
		byte[] msg = new byte[receivedMessageSize];
		inFromClient.read(msg, 0, receivedMessageSize);
		return msg;
	}
}