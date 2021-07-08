package com.example.eddy.servr;

import android.os.StrictMode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

/** January 4, 2018
 *  Eddy Yao
 *
 *      Server API class for the client-end. Allows a server object (located in BufferingActivity)
 *  to call upon various methods in order to interact and pass information between the client and
 *  server. Public static elements (namely user, userServices, and streamServices) can be referenced
 *  anywhere within the application, and are updated as necessary.
 *      This class will also package and unpack the information sent between the client and
 *  server, as all information passed is in raw string, but sometimes an object's class needs to be
 *  preserved.
 */

public class ServerConnection {

    // Declares I/O objects to be used
    private static Socket socket;
    private static PrintWriter out;
    private static BufferedReader in;
    private static String temp;

    private static String output;
    private static ArrayList<ArrayList<String>> printedOutput;

    // Public variables which can be referenced and used throughout the app
    public static ArrayList<String> user;
    public static ArrayList<ArrayList<String>> userServices;
    public static ArrayList<ArrayList<String>> streamServices;

    // Constructor method. This ensures that everything is working
    public ServerConnection(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        getServiceStream();
    }

    // Registration process given a String parameter, representing the user
    public void register(String user){
        // Opens socket connection with server
        listenSocket();
        try {
            // Indicates this connection is a registration attempt and prints out the user
            out.println("register");
            out.println(user);

            // Pings back the server response
            System.out.println(in.readLine());
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    // Registration process given a String parameter, representing the login credentials
    public void login(String credentials){
        // Opens socket connection
        listenSocket();
        try {
            // Indicates login attempt and prints credentials to server
            out.println("login");
            out.println(credentials);

            // Parsing the String received and turns it into an ArrayList
            temp = in.readLine();
            if (temp.equals("null")){
                temp = null;
            } else {
                temp = temp.replace("[", "");
                temp = temp.replace(" ", "");
                temp = temp.replace("]", "");

                // Assigns user object (as given by the server) to public variable
                user = new ArrayList<>(Arrays.asList(temp.split(",")));

                // Retrieves all services pertaining to the user that just logged in
                getUserServices(Integer.parseInt(user.get(0)));
            }
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    // Retrieves all services created by a user, represented by their user ID
    public void getUserServices(int userID){
        // Opens socket connection
        listenSocket();
        try {
            // Indicates a request for all services by a single user and passes on their ID
            out.println("personal_services");
            out.println(userID);

            // Assigns returned value to public variable
            userServices = getNestedArray(in.readLine());
            System.out.println(userServices);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Creates a service given a String descriptor representing the service
    public void addServices(String service){
        // Opens socket connection
        listenSocket();
        try{
            // Indicates attempt to add service
            out.println("create_service");

            // Passes service to server and prints out returned ping
            out.println(service);
            System.out.println("Successfully added service: " + service);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // Searches services using a given query
    public void searchServices(String searchQuery){
        // Opens socket connection
        listenSocket();
        try{
            // Indicates a search attempt
            out.println("search_services");

            // Passes on search query and assigns returned information to public variable
            out.println(searchQuery);
            streamServices = getNestedArray(in.readLine());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Retrieves the list of services to be displayed in the stream, as per our current algorithm (most recent)
    public void getServiceStream(){
        // Opens socket connection
        listenSocket();
        try{
            // Indicates a request for the stream
            out.println("stream_service");

            // Assigns output from server to public variable
            streamServices = getNestedArray(in.readLine());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // Retrieves the information about a user given their ID
    public ArrayList<String> getUser(int userID){
        // Opens socket connection
        listenSocket();
        try{
            // Indicates a request to retrieve user information
            out.println("get_user");

            // Passes on user ID to server and returns reformatted output
            out.println(userID);
            return new ArrayList<>(Arrays.asList(in.readLine().split(",")));
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    // Opens a socket connection directly with the server
    private void listenSocket(){
        try{
            // Different IP addresses depending on where the server is hosted
            // A constant IP will be available once the server can be hosted on a live platform / webhook
            //@school: 10.178.155.72
            //@home: 192.168.2.13
            socket = new Socket("10.178.155.72", 8001);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            System.out.println(Arrays.asList(e.getStackTrace()));
        }
    }

    // Turns a String into a nested ArrayList - Useful as information can only be passed as a String
    // but sometimes the data structure needs to be preserved
    private ArrayList<ArrayList<String>> getNestedArray(String inputArray){
        printedOutput = new ArrayList<>();
        output = inputArray.substring(1, inputArray.length() - 1);

        // Strips String of outside brackets so that the remainder is simply a chain of ArrayLists
        // separated by commas
        while (output.contains("]")){
            printedOutput.add(new ArrayList<>(Arrays.asList(output.substring(1, output.indexOf("]")).split(","))));
            if (output.indexOf("]") < output.length() - 1){
                output = output.substring(output.indexOf("]") + 2);
            } else {
                break;
            }
        }

        // Returns final, processed output
        return printedOutput;
    }
}
