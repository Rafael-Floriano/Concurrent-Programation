package br.com.rafael.floriano;

import br.com.rafael.floriano.server.CustomerServerSocket;

/**
 * Bro, this class literally makes the initialization of CustomerServerSocket, nothing more
 */
public class Main {
    public static void main(String[] args) {

        CustomerServerSocket customerServerSocket = new CustomerServerSocket();
        customerServerSocket.initWithCachedThreads();

    }
}