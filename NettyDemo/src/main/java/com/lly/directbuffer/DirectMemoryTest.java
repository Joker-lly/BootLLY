package com.lly.directbuffer;

import java.nio.ByteBuffer;

public class DirectMemoryTest {

    public static void headAccess(){
        ByteBuffer buffer = ByteBuffer.allocate(1000);
    }

    public static void directAccess() {
        ByteBuffer direct = ByteBuffer.allocateDirect(1000);

    }

    public static void main(String[] args) {

    }
}
