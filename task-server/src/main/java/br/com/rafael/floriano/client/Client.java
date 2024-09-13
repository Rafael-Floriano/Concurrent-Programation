package br.com.rafael.floriano.client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Client class is mock of client requests
 * you can start another JVM running this class
 * to emulate a client
 */
public class Client implements Runnable {


    public void makeConnection() {
        final Socket socket = getConnectionWithTcpServer();
        new Thread(() -> createTcpOutputConnection(socket)).start();
        new Thread(() -> createTcpInputConnection(socket)).start();
    }

    private Socket getConnectionWithTcpServer() {
        try {
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Conection establish'");
            return socket;
        } catch (Exception exception) {
            throw new RuntimeException("Error to get connection with tcp server - ",exception);
        }
    }

    private void createTcpOutputConnection(final Socket socket) {
        try {
            PrintStream output = new PrintStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                final String command = scanner.next();
                if (command.trim().equals(":q")) {
                    closeSocketConnection(socket);
                    break;
                }
                output.println(scanner.next());
            }
        } catch (IOException e) {
            throw new RuntimeException("outputTask - ",e);
        }
    }

    private void createTcpInputConnection(final Socket socket) {
        try {
            Scanner scanner = new Scanner(socket.getInputStream());
            while (scanner.hasNext()) {
                System.out.println("SERVER RESPONSE - " + scanner.next());
            }
        } catch (IOException e) {
            throw new RuntimeException("outputTask - ",e);
        }
    }

    private void closeSocketConnection(final Socket socket) {
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error to close socket connection - ",e);
        }
    }

    @Override
    public void run() {
        this.makeConnection();
    }
}
