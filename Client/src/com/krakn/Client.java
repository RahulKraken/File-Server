package com.krakn;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.Socket;
import java.util.Arrays;

public class Client {
    private final ObjectOutputStream out;
    private Socket socket;

//
//    private Socket socket;
//    private InputStream inputStream;
//    private DataInputStream dataInputStream;
//
//    public Client() throws IOException {
//        socket = new Socket("127.0.0.1", 5555);
//        System.out.println("connected to server.");
//        setupStreams();
//        readMessage();
//        closeEverything();
//    }
//
//    // D:\Dev\Java\FileShare\Outputs\test1
//
//    private void setupStreams() throws IOException {
//        inputStream = socket.getInputStream();
//        dataInputStream = new DataInputStream(inputStream);
//    }
//
//    private void readMessage() throws IOException {
//        File file = new File("D:\\dev\\Java\\FileShare\\Outputs\\test.txt");
//        FileOutputStream fileOutputStream = new FileOutputStream(file);
//        while (dataInputStream.read() != -1) {
//            System.out.println(dataInputStream.read());
//            fileOutputStream.write(dataInputStream.read());
//        }
////        System.out.println("nyx");
//    }
//
//    private void closeEverything() throws IOException {
//        dataInputStream.close();
//        inputStream.close();
//        socket.close();
//    }
//

    public Client() throws IOException {
        socket = null;
        String host = "127.0.0.1";

        socket = new Socket(host, 4544);

        File file = new File("D:\\Tutorials\\netcampebooks\\CCNA_Prep_Guide.pdf");
        out = new ObjectOutputStream(socket.getOutputStream());
        out.flush();
        // Get the size of the file

        // this part will be handled by the resolvePackets method
        resolvePackets(file.getPath());

        // this block will go inside sendData packet block
//        int count;
//        while ((count = in.read(bytes)) > 0) {
//            out.write(bytes, 0, count);
//        }

        // this will go inside the closeEverything method
        closeEverything();
//        out.close();
////        in.close();
//        socket.close();
    }

    private synchronized void sendPacket(Packet packet) {
        try {
            out.writeObject(packet);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error sending some packets.");
        }
    }

    private void closeEverything() {
        try {
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error closing something.");
        }
    }

    public void resolvePackets(String path) {
        File file = new File(path);

        int size = 4 * 1024;

        byte[] bytes = new byte[size];
        int bytecount = 0, j = 0;
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            while ((bytecount = fileInputStream.read(bytes)) > -1) {

//                File output = new File("D:\\Dev\\Java\\FileShare\\Outputs\\test" + j + ".txt");
//                FileOutputStream outputStream = new FileOutputStream(output);

//                System.out.println(Arrays.toString(bytes));
//                for (int i = 0; i < bytecount; i++) {
//                    System.out.print(bytes[i]);
//                }
                Packet packet = new Packet(size, "test" + j);
                packet.setBytes(bytes);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        sendPacket(packet);
                    }
                }).start();
//                System.out.println(Arrays.toString(bytes));
                System.out.println("Sent file " + j + " - with byteCount - " + bytecount);
                j += 1;
//                outputStream.write(bytes);
//                outputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
