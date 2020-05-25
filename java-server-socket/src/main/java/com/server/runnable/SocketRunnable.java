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
	private static final String ADDR = "localhost";

    public static void main(String[] args) {
    	try {
			startServer(PORT, ADDR);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public static void startServer(int port, String addr) throws IOException {
    	InetAddress address = InetAddress.getByName(addr);
    	ServerSocket serverSocket = new ServerSocket(port, 50, address);
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
