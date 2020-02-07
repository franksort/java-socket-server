package com.demo;


import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class DemoApplication {

    public static void main(String[] args) throws IOException {
        log.debug("Starting demo server");
        HandlerFactory handlerFactory = new StringPrintingHandlerFactory();
//        HandlerFactory handlerFactory = new BytePrintingHandlerFactory();
        Server server = ServerFactory.createFixedConnectionServer(handlerFactory);
        server.listen();
    }
}
