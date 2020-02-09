package com.demo.server;

import com.demo.handler.HandlerFactory;
import com.demo.server.impl.ServerImpl;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ServerFactory {

    private final static int DEFAULT_PORT = 8080;
    private final static int DEFAULT_CONNECTIONS = 10;

    public static Server createFixedConnectionServer(HandlerFactory handlerFactory) {
        return createFixedConnectionServer(DEFAULT_PORT, DEFAULT_CONNECTIONS, handlerFactory);
    }

    public static Server createFixedConnectionServer(int port, int connections, HandlerFactory handlerFactory) {
        final ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException("Could not create server instance.", e);
        }
        log.debug("Creating thread pool to handle {} connections", connections);
        final ExecutorService executorService = Executors.newFixedThreadPool(connections);
        return new ServerImpl(serverSocket, executorService, handlerFactory);
    }
}
