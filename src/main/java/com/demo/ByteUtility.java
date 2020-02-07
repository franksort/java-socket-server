package com.demo;

public class ByteUtility {

    public static String byteToString(int b) {
        if (b < -1 || b > 255) throw new IllegalArgumentException("Integer outside of range -1 to 255");
        return String.format("%8s", Integer.toBinaryString(b)).replace(' ', '0');
    }
}
