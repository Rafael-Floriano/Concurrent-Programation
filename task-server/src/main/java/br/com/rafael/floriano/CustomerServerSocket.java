package br.com.rafael.floriano;

import br.com.rafael.floriano.task.PrimaryTask;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * CustomerServerSocket implements a Server Socket implementation
 * @code{serverSocket.accept()} pause the main thread and wait for
 * new connection of client socket, all requests create a new Thread
 * to process the client task
 */
public class CustomerServerSocket {

    public void init() {
        System.out.println("--- Initializing Socket Server ---!");
        try {
            java.net.ServerSocket serverSocket = new ServerSocket(12345);
            while (true) {
                Socket socket = serverSocket.accept();
                Thread thread = new Thread(new PrimaryTask(socket));
                thread.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
