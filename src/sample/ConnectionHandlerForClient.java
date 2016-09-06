package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by jfabiano on 9/5/2016.
 */
//This class is currently unused
public class ConnectionHandlerForClient implements Runnable {
    ServerSocket connection;
    GraphicsContext gc;
    Canvas canvas;
    int x;
    int y;
    final double DEFAULT_SCENE_WIDTH = 800;
    final double DEFAULT_SCENE_HEIGHT = 600;

    public ConnectionHandlerForClient() {

    }

    public ConnectionHandlerForClient(ServerSocket incomingConnection) {
        this.connection = incomingConnection;
    }

    public void run() {
        handleInput();
    }

    public void handleInput() {
        try

        {
            String[] inputStringArray;//[1] = username
            ArrayList<String> aList = new ArrayList<String>();
            String name;
            String inputLine;
            // start a server on a specific port. This is what needs to happen in a thread
            // display information about who just connected to our server
            System.out.println("connecting to " + connection.getInetAddress().getHostAddress());

            // this is how we read from the client
            //BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(this.connection.getInputStream()));
            // this is how we write back to the client
            //PrintWriter outputToClient = new PrintWriter(this.connection.getOutputStream(), true);

            //Enter the code for reading the graphics context and the (maybe?)canvas, and receive it over the network... maybe the getX and getY values...


            // read from the input until the client disconnects

            //On the server side, make sure the first message that each client sends is their name using a message structure
            //like this: name=name-of-client
            //name = inputFromClient.readLine();
            //System.out.println(name);
            //inputStringArray = name.split("=");
//            if(inputStringArray[0].equals("name"))
//            {
//
//                outputToClient.println("I have your name. Speak, human.");
//                while ((inputLine = inputFromClient.readLine()) != null) {
//                    if (inputLine.equals("history")) {
//                        //aList.add(inputLine);
//                        //add a sys out to see what is actually happening here in the aList
//                        System.out.println("Before sending the list I have: " + aList);
//                        outputToClient.println(aList);//working on the 1st extra for the weekend assignment.
//                        //Not working quite right. The array of strings is not showing the latest addition to it in the printout.
//
//                    }
//                    else {
//                        System.out.println("Before adding the new piece I have: " + aList);
//                        aList.add(inputLine);
//                        System.out.println("After adding the new piece I have: " + aList);
//                        System.out.println(inputStringArray[1] + " says: " + inputLine);
//
//                    }
//
//                }
//
//            }
//            else
//            {
//                outputToClient.println("Your initial message did not begin with \"name=\". I don't know who you are, sorry.");
//            }

        } catch (Exception ioe) {//changed io exception to exception to avoid a compile error
            ioe.printStackTrace();
        }
    }
    }
