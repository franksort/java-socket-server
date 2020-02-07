package com.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

@Slf4j
@RequiredArgsConstructor
public class StringPrintingHandler implements Handler {

    final Socket socket;

    @Override
    public void run() {
        log.debug("Handling client connection with {}", this.getClass().getSimpleName());
        try (
                InputStream inputStream = socket.getInputStream();
		        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        ) {
            String line = bufferedReader.readLine();
            while (line != null) {
                log.info(String.format("Read line from client: %s", line));
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while reading lines from the input stream.", e);
        }
    }
}
