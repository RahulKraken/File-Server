package com.krakn;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

public class FileInputHandler implements Runnable {

    private Socket socket;

    public FileInputHandler(Socket socket) {
        this.socket = socket;
    }

    private void writeFile() throws IOException, ClassNotFoundException {
        // class variables
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        Packet packet = (Packet) in.readObject();
        OutputStream out = new FileOutputStream("D:\\Dev\\Java\\FileShare\\Outputs\\" + packet.getName() + ".txt");
        out.flush();
        System.out.println(Arrays.toString(packet.getBytes()));
        System.out.println(packet.getBytes().length + " - " + packet.getName());
        out.write(packet.getBytes());
    }

    @Override
    public void run() {
        try {
            writeFile();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
