package com.demo.server.impl;

import com.demo.handler.Handler;
import com.demo.handler.HandlerFactory;
import com.demo.server.Server;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;

@Slf4j
public class ServerImpl implements Server {

    private final ServerSocket serverSocket;
    private final ExecutorService executorService;
    private final HandlerFactory handlerFactory;

    public ServerImpl(
            final ServerSocket serverSocket,
            final ExecutorService executorService,
            final HandlerFactory handlerFactory) {
        this.serverSocket = serverSocket;
        this.executorService = executorService;
        this.handlerFactory = handlerFactory;
    }

    public void listen() throws IOException {
        log.debug("Server listening on port {}", serverSocket.getLocalPort());
        while (true) {
            log.debug("Waiting for client connection");
            Handler handler = handlerFactory.createHandler(serverSocket.accept());
            log.debug("Submitting handler to thread executor");
            executorService.submit(handler);
        }
    }

    public void stop() {
        executorService.shutdown();
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while closing the server socket.", e);
        }
    }
}
