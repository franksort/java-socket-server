package com.demo.handler;

import java.net.Socket;

public interface HandlerFactory {

    Handler createHandler(Socket socket);
}
