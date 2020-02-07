package com.demo;

import java.net.Socket;

interface HandlerFactory {

    Handler createHandler(Socket socket);
}
