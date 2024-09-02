package br.com.rafael.floriano.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        try {
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Conection establish'");

            Scanner scanner = new Scanner(System.in);

            scanner.next();

            socket.close();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
