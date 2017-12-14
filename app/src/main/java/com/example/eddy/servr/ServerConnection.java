package com.example.eddy.servr;

import android.os.StrictMode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

public class ServerConnection {

    private static Socket socket;
    private static PrintWriter out;
    private static BufferedReader in;

    public ServerConnection(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        listenSocket();
        sendPing();
        System.out.println("Pinged");
    }

    private void listenSocket(){
        try{
            socket = new Socket("10.178.155.72", 8000);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    private void sendPing(){
        try {
            out.println("Ping");
            System.out.println(in.readLine());
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
}
