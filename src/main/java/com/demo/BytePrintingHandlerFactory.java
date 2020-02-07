package com.demo;

import java.net.Socket;

public class BytePrintingHandlerFactory implements HandlerFactory {

    @Override
    public Handler createHandler(Socket socket) {
        return new BytePrintingHandler(socket);
    }
}
