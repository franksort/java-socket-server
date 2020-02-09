package com.demo.handler.impl;

import com.demo.handler.Handler;
import com.demo.handler.HandlerFactory;

import java.net.Socket;

public class BytePrintingHandlerFactory implements HandlerFactory {

    @Override
    public Handler createHandler(Socket socket) {
        return new BytePrintingHandler(socket);
    }
}
