package br.com.rafael.floriano.server;

import br.com.rafael.floriano.task.PrimaryTask;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CustomerServerSocket implements a Server Socket implementation
 * @code{serverSocket.accept()} pause the main thread and wait for
 * new connection of client socket, all requests create a new Thread
 * to process the client task
 */
public class CustomerServerSocket {

    /**
     * Starts the server without a specified thread size, we can create as many as we want
     * */
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

    /**
     * initWithFixedPoolOfThreads create a pool of threads with
     * specific size through Executors class with newFixedThreadPool.
     * "newFixedThreadPool" threads in a non-horizontally scalable way,
     * the system will always have this specific number of threads to work with when we refer to the specific pool created by the executor,
     * if new requests are made to the server and all threads are busy, the client will enter a waiting mode, where when a new thread becomes free,
     * the client request that was waiting will be processed normally
     */
    public void initWithFixedPoolOfThreads() {

        ExecutorService threadsPool = Executors.newFixedThreadPool(2);

        System.out.println("--- Initializing Socket Server ---!");
        try {
            java.net.ServerSocket serverSocket = new ServerSocket(12345);
            while (true) {
                Socket socket = serverSocket.accept();
                threadsPool.execute(new PrimaryTask(socket));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method initializes the server with a thread pool determined as "newCachedThreadPool"
     * which means that we can always call the pool to obtain a new thread,
     * unlike the "newFixedThreadPool" method if all created threads are busy,
     * the pool can create a new thread to take care of the new requested process,
     * the threads created during this pool will be removed if they are not used within 60 seconds
     * */
    public void initWithCachedThreads() {

        ExecutorService threadsPool = Executors.newCachedThreadPool();

        System.out.println("--- Initializing Socket Server ---!");
        try {
            java.net.ServerSocket serverSocket = new ServerSocket(12345);
            while (true) {
                Socket socket = serverSocket.accept();
                threadsPool.execute(new PrimaryTask(socket));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
