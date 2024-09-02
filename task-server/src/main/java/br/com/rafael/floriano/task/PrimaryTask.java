package br.com.rafael.floriano.task;

import java.net.Socket;

public class PrimaryTask implements Runnable {

    private Socket socket;

    @Override
    public void run() {
        System.out.println("Working in primary task of client: " + socket);
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public PrimaryTask(Socket socket) {
        this.socket = socket;
    }
}
