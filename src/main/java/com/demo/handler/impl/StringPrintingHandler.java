package com.demo.handler.impl;

import com.demo.handler.Handler;
import com.demo.server.Server;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

@Slf4j
public class StringPrintingHandler implements Handler {

    private final Socket socket;
    private final EventBus eventBus;

    public StringPrintingHandler(
            final Socket socket,
            final EventBus eventBus) {
        this.socket = socket;
        this.eventBus = eventBus;

        eventBus.register(this);
    }

    @Override
    public void run() {
        log.debug("Handling client connection with {}", this.getClass().getSimpleName());
        try (
                InputStream inputStream = socket.getInputStream();
		        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        ) {
            String line = bufferedReader.readLine();
            while (line != null) {
                log.info(String.format("Read line from client: %s", line));
                eventBus.post(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while reading lines from the input stream.", e);
        }
    }

    @Subscribe
    public void clientMessageListener(String message) {
        log.debug("Received message via EventBus: {}", message);
        if (socket.isClosed()) {
            log.debug("Socket is closed: {}", socket);
            return;
        }
        try {
            OutputStream outputStream = socket.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            PrintWriter printWriter = new PrintWriter(outputStreamWriter, true);
            printWriter.println(message);
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while writing messages to the output stream.", e);
        }
    }
}
