package com.server.clienthandle;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Logger;

import com.server.api.APIProxy;
import com.server.protocol.Protocol;
import com.server.protocol.ProtocolHandler;

public class ClientHandle implements Runnable {
	
	private static final Logger LOGGER = Logger.getLogger(ClientHandle.class.getName());

	private Socket clientSocket;

	public ClientHandle(Socket socket) {
		this.clientSocket = socket;
	}

	@Override
	public void run() {
		try (ObjectOutputStream outputStream = new ObjectOutputStream(this.clientSocket.getOutputStream());
			ObjectInputStream inputStream = new ObjectInputStream(this.clientSocket.getInputStream())){
			
			String request = inputStream.readUTF();
			Protocol protocol = ProtocolHandler.buildProtocolBaseOnRequest(request);
			
			if(protocol.isValid()) {
				outputStream.writeUTF(ProtocolHandler.buildResponse(APIProxy.getMovies(protocol.getQueryLength(), protocol.getQuery())));
				outputStream.flush();
			} else {
				LOGGER.warning("Invalid request. Request: " + request);
				outputStream.writeUTF("Invalid input. Please, try again following this pattern \"<queryLength>\":\"<query>\"");
				outputStream.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
