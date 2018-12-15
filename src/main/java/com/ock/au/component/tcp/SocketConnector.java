package com.ock.au.component.tcp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketConnector implements Connector  {

    @Autowired
    private Environment env;

    private ServerSocket serverSocket;
    private Socket socket;

    public SocketConnector() {
        try {
            serverSocket = new ServerSocket(9000);

            System.out.println("Configurations set.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        try {
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public Socket connect() {
        try {
            return serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
