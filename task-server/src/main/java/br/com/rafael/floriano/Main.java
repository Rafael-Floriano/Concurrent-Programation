package br.com.rafael.floriano;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Initializing Socket Server ---!");

        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Accept conection in port: " + socket.getPort());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}