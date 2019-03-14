package com.krakn;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Server {

    /**
     * create the server socket
     * wait for connection
     * download file being sent from the connected client
     * write received file to the path
     * @throws IOException : thrown by socket, input and output streams
     */
    public static void receiveFile() throws IOException {
        ServerSocket serverSocket = new ServerSocket(4444);
        Socket socket = serverSocket.accept();

        InputStream in = socket.getInputStream();
        OutputStream out =
                new FileOutputStream("D:\\Dev\\Java\\FileShare\\Outputs\\test1.pdf");

        byte[] bytes = new byte[16 * 1024];
        int count;
        while ((count = in.read(bytes)) > 0) {
            System.out.println(count);
            System.out.println(Arrays.toString(bytes));
            out.write(bytes, 0, count);
        }

        out.close();
        in.close();
        socket.close();
        serverSocket.close();
    }
}