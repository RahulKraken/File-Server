package com.krakn;

import java.io.Serializable;
import java.util.Arrays;

public class Packet implements Serializable {

    private byte[] bytes;
    private String name;

    /**
     * default constructor
     * @param size define size of the byte array
     * @param name define name of the dataPacket
     */
    public Packet(int size, String name) {
        this.name = name;
        bytes = new byte[size];
    }

    /**
     * if size not specifies make standard size packet of 2KB
     * @param name name of the dataPacket
     */
    public Packet(String name) {
        // empty constructor
        new Packet(2048, name);
    }

    /**
     * get the name of this packet
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * set name for this packet
     * @param name string name for this packet
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get the value for the byte array
     * @return "bytes"
     */
    public byte[] getBytes() {
        return bytes;
    }

    /**
     * set the value of the byte array
     * @param bytes equal to "bytes"
     */
    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    /**
     * convert the byte array to string array
     * @return "bytes" as string
     */
    @Override
    public String toString() {
        return "Packet{" +
                "bytes=" + Arrays.toString(bytes) +
                '}';
    }
}
