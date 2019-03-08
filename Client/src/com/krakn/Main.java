package com.krakn;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            new Client();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
