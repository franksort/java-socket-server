package com.demo;


import com.demo.handler.HandlerFactory;
import com.demo.handler.impl.StringPrintingHandlerFactory;
import com.demo.server.Server;
import com.demo.server.ServerFactory;
import com.google.common.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class DemoApplication {

    public static void main(String[] args) throws IOException {
        log.debug("Starting application");

        EventBus eventBus = new EventBus();
        HandlerFactory handlerFactory = new StringPrintingHandlerFactory(eventBus);
        Server server = ServerFactory.createFixedConnectionServer(handlerFactory);
        server.listen();
    }
}
