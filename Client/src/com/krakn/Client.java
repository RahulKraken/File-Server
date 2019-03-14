package com.krakn;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;

public class Client {

    /**
     * connect to the server with the socket
     * read the file from the path
     * send the file to the server
     * @param path : path of file to be sent
     * @throws IOException : thrown by socket, input and output streams
     */
    public static void sendFile(String path) throws IOException {
        String host = "127.0.0.1";

        Socket socket = new Socket(host, 4444);

        File file = new File(path);
        // Get the size of the file
        long length = file.length();
        byte[] bytes = new byte[16 * 1024];
        InputStream in = new FileInputStream(file);
        OutputStream out = socket.getOutputStream();

        int count;
        while ((count = in.read(bytes)) > 0) {
            System.out.println(count);
            System.out.println(Arrays.toString(bytes));
            out.write(bytes, 0, count);
        }

        out.close();
        in.close();
        socket.close();
    }
}