package com.example.eddy.servr;

import android.os.StrictMode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class ServerConnection {

    private static Socket socket;
    private static PrintWriter out;
    private static BufferedReader in;
    private static String temp;
    public static ArrayList<String> user;

    private static final String input = "[[1, 1, Alpha Tester, Testing for alpha, $1.99], [2, 1, Alpha Alpha Tester, Testing for alpha alpha, $11.99], [4, 3, Cheesecake Eater, Eating cheesecake professionally, $2.99]]";
    private static String output;
    private static ArrayList<ArrayList<String>> printedOutput;


    public ServerConnection(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        sendMessage("Ping");

        System.out.println(getNestedArray(input));
    }

    public void sendMessage(String message){
        listenSocket();
        try {
            out.println(message);
            System.out.println(in.readLine());
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public void register(String user){
        listenSocket();
        try {
            out.println("register");
            out.println(user);
            System.out.println(in.readLine());
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public void login(String credentials){
        listenSocket();
        try {
            out.println("login");
            out.println(credentials);
            temp = in.readLine();
            if (temp.equals("null")){
                temp = null;
            } else {
                temp = temp.replace("[", "");
                temp = temp.replace(" ", "");
                temp = temp.replace("]", "");
                user = new ArrayList<>(Arrays.asList(temp.split(",")));
            }
            System.out.println(user);
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    private void listenSocket(){
        try{
            //@school: 10.178.155.72
            //@home: 192.168.2.13
            socket = new Socket("192.168.2.13", 8001);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            System.out.println(Arrays.asList(e.getStackTrace()));
        }
    }

    private ArrayList<ArrayList<String>> getNestedArray(String inputArray){
        printedOutput = new ArrayList<>();
        output = inputArray.substring(1, inputArray.length() - 1);

        while (output.contains("]")){
            printedOutput.add(new ArrayList<>(Arrays.asList(output.substring(1, output.indexOf("]")).split(","))));
            if (output.indexOf("]") < output.length() - 1){
                output = output.substring(output.indexOf("]") + 2);
            } else {
                break;
            }
        }

        return printedOutput;
    }
}
