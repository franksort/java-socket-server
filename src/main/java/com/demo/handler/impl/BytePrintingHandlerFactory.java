package com.demo.handler.impl;

import com.demo.handler.Handler;
import com.demo.handler.HandlerFactory;
import com.demo.server.Server;
import com.google.common.eventbus.EventBus;
import lombok.RequiredArgsConstructor;

import java.net.Socket;

@RequiredArgsConstructor
public class BytePrintingHandlerFactory implements HandlerFactory {

    private final EventBus eventBus;

    @Override
    public Handler createHandler(Socket socket) {
        return new BytePrintingHandler(socket, eventBus);
    }
}
