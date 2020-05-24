package com.server.runnable;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

import com.server.clienthandle.ClientHandle;

public class SocketRunnable {

	private static final Logger LOGGER = Logger.getLogger(SocketRunnable.class.getName());
	private static final int PORT = 5555;

    public static void main(String[] args) {
    	try {
			startServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    private static void startServer() throws IOException {
    	InetAddress addr = InetAddress.getByName("192.168.15.5");
    	ServerSocket serverSocket = new ServerSocket(PORT, 50, addr);
    	serverSocket.setReuseAddress(true);
    	LOGGER.info("Starting server on " + PORT + " port...");

    	startNewClientThread(serverSocket);
    	
    }

    private static void startNewClientThread(ServerSocket serverSocket) throws IOException {
    	while(true) {
    		new Thread(createNewConnection(serverSocket)).start();
    	}
    }

    private static ClientHandle createNewConnection(ServerSocket serverSocket) throws IOException {
    	Socket client = serverSocket.accept();
    	LOGGER.info("New client connected: " + client.getInetAddress().getHostAddress());
		return new ClientHandle(client);
    }

}
