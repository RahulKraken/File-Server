package com.krakn;

import java.io.*;
import java.net.Socket;

public class Client {

    private Socket socket;
    private InputStream inputStream;
    private DataInputStream dataInputStream;

    public Client() throws IOException {
        socket = new Socket("127.0.0.1", 5555);
        System.out.println("connected to server.");
        setupStreams();
        readMessage();
        closeEverything();
    }

    // D:\Dev\Java\FileShare\Outputs\test1

    private void setupStreams() throws IOException {
        inputStream = socket.getInputStream();
        dataInputStream = new DataInputStream(inputStream);
    }

    private void readMessage() throws IOException {
        File file = new File("D:\\dev\\Java\\FileShare\\Outputs\\test.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        while (dataInputStream.read() != -1) {
            System.out.println(dataInputStream.read());
            fileOutputStream.write(dataInputStream.read());
        }
//        System.out.println("nyx");
    }

    private void closeEverything() throws IOException {
        dataInputStream.close();
        inputStream.close();
        socket.close();
    }
}
