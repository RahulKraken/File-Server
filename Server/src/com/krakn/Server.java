package com.krakn;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Server {

    private Socket socket;
    private DataOutputStream dataOutputStream;
    private OutputStream outputStream;

    public Server() throws IOException {
        ServerSocket serverSocket = new ServerSocket(5555, 10);
        socket = serverSocket.accept();
        System.out.println(socket.getInetAddress().getHostName());
        setupStreams();
        sendMessage();
        closeEverything();
    }

    private void setupStreams() throws IOException {
        outputStream = socket.getOutputStream();
        dataOutputStream = new DataOutputStream(outputStream);
    }

    // D:\Tutorials\netcampebooks\First_PHP_MySQL.pdf

    private void sendMessage() throws IOException {
        File file = new File("D:\\Google Drive\\PDF\\Programming books\\pandas.pdf");
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bytes = new byte[2048];
        int bit = 0;
        while ((bit = fileInputStream.read(bytes)) > 0) {
            System.out.println(bit);
            dataOutputStream.write(bytes, 0, bit);
        }
    }

    private void closeEverything() throws IOException {
        dataOutputStream.close();
        outputStream.close();
        socket.close();
    }

    private void resolvePackets(String path) {
        File file = new File(path);

        byte[] bytes = new byte[2048];
        int bytecount = 0, j = 0;
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            while ((bytecount = fileInputStream.read(bytes)) > -1) {

                File output = new File("E:\\Movies\\Parts\\output" + j + ".txt");
                FileOutputStream outputStream = new FileOutputStream(output);

                System.out.println(Arrays.toString(bytes));
                for (int i = 0; i < bytecount; i++) {
                    System.out.print(bytes[i]);
                }
                j += 1;
                outputStream.write(bytes);
                outputStream.close();
                System.out.println("Written file " + j);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
