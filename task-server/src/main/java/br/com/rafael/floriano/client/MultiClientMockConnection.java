package br.com.rafael.floriano.client;

public class MultiClientMockConnection {

    public static void main(String[] args) {
        makeFiveConnectionsWithTCPServer();
    }
    public static void makeFiveConnectionsWithTCPServer() {
        for (int i = 0; i != 5; i++) {
            new Thread(new Client()).start();
        }
    }

}
