package com.demo;

import java.io.IOException;

public interface Server {

    void listen() throws IOException;
    void stop();
}
