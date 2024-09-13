package br.com.rafael.floriano.task;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * PrimaryTask emulates a heavy task in the server
 * with @code{Thread.sleep(20000)}
 */
public class PrimaryTask implements Runnable {

    private Socket socket;

    @Override
    public void run() {
        System.out.println("Working in primary task of client: " + socket);
        readingClientCommands();
    }

    private void readingClientCommands() {
        try {
            Scanner scanner = new Scanner(socket.getInputStream());
            PrintStream outputClient = new PrintStream(socket.getOutputStream());
            while (scanner.hasNextLine()) {
                final String command = scanner.nextLine();
                switch (command) {
                    case "c1":
                        outputClient.print("c1 command executed");
                        break;
                    case "c2":
                        outputClient.print("c2 command executed");
                        break;
                    default:
                        outputClient.print("Not exists this command: " + command);
                }
            }
        } catch (IOException e) {
            System.out.println("Server cannot read");
        }
    }

    public PrimaryTask(Socket socket) {
        this.socket = socket;
    }
}
