package com.demo;

import java.net.Socket;

public class StringPrintingHandlerFactory implements HandlerFactory {

    @Override
    public Handler createHandler(Socket socket) {
        return new StringPrintingHandler(socket);
    }
}
