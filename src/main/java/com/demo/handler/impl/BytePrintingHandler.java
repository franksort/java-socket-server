package com.demo.handler.impl;

import com.demo.handler.Handler;
import com.demo.handler.impl.support.ByteUtility;
import com.demo.server.Server;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

@Slf4j
public class BytePrintingHandler implements Handler {

    private final Socket socket;
    private final EventBus eventBus;

    public BytePrintingHandler(
            final Socket socket,
            final EventBus eventBus) {
        this.socket = socket;
        this.eventBus = eventBus;

        eventBus.register(this);
    }

    @Override
    public void run() {
        log.debug("Handling client connection with {}", this.getClass().getSimpleName());
        try (InputStream inputStream = socket.getInputStream()) {
            int readByte = inputStream.read();
            while (readByte != -1) {
                log.info(String.format("Int: %s", readByte));
                log.info(String.format("Byt: %s", ByteUtility.byteToString(readByte)));
                readByte = inputStream.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while reading bytes from the input stream.", e);
        }
    }

    @Subscribe
    public void clientMessageListener(String message) {

    }
}
