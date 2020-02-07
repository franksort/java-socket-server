package com.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;

public class OutputStreamTest {

    @Test
    void printBytes() {

        byte b = 127;
        System.out.println(b);

        byte[] byteArray = byteArrayWithFullRangeOfByteValues();

        System.out.println(Arrays.toString(byteArray));

        for (int i = 0; i < byteArray.length; ++i) {
            System.out.print(byteArray[i]);
        }
        System.out.println();

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);

        int currentByte = byteArrayInputStream.read();
        while(currentByte != -1) {
            System.out.print(currentByte);
            // get next byte from stream
            currentByte = byteArrayInputStream.read();
        }
        System.out.println();

        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
        System.out.println(byteBuffer.getInt());

        Assertions.assertTrue(true);
    }

    public byte[] byteArrayWithFullRangeOfByteValues() {
        int distinctByteValues = 256;
        byte[] byteArray = new byte[256];
        byte byteValue = Byte.MIN_VALUE;
        for (int i = 0; i < distinctByteValues; ++i) {
            byteArray[i] = byteValue;
            byteValue = (byte) (byteValue + 1);
        }
        return byteArray;
    }
}
