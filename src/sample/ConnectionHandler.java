package sample;

/**
 * Created by jfabiano on 9/2/2016.
 */
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import jodd.json.JsonParser;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ConnectionHandler implements Runnable{
    Socket connection;
    GraphicsContext gc;
    Canvas canvas;
    int x;
    int y;
    int strokeSize;
    Stroke myStroke = new Stroke();
    final double DEFAULT_SCENE_WIDTH = 800;
    final double DEFAULT_SCENE_HEIGHT = 600;
    public ConnectionHandler()
    {

    }
    public ConnectionHandler(Socket incomingConnection, GraphicsContext gc)
    {
        this.connection = incomingConnection;
        this.gc = gc;
    }
    public void run()
    {
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
            System.out.println("Incoming connection from " + connection.getInetAddress().getHostAddress());

            readToDoList();
            // this is how we read from the client
            BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(this.connection.getInputStream()));
            // this is how we write back to the client
            PrintWriter outputToClient = new PrintWriter(this.connection.getOutputStream(), true);
            //Enter the code for reading the graphics context and the (maybe?)canvas, and receive it over the network... maybe the getX and getY values...
            // read from the input until the client disconnects

            //On the server side, make sure the first message that each client sends is their name using a message structure
            //like this: name=name-of-client

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void readToDoList()//should probably be broken into 2 methods
    {

        try
        {
        BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(this.connection.getInputStream()));
        // this is how we write back to the client
        PrintWriter outputToClient = new PrintWriter(this.connection.getOutputStream(), true);

            //reader.
            JsonParser toDoItemParser = new JsonParser();
            //.class represents the blueprint of the class not an instance//this will be returned to a container item(User)
            //with instantiated array list member variable rather than directly to an array list as seen here...
            RunnableGC runGC;
            while(connection != null) {
//
                myStroke = toDoItemParser.parse(inputFromClient.readLine(), Stroke.class);
                System.out.println("myStroke after transfer = " + myStroke.toString());
                Platform.runLater(runGC = new RunnableGC(gc, myStroke));
//
                if (connection == null)
                {
                    break;
                }
            }

        }
        catch(IOException e)
        {
            //e.printStackTrace();
        }
        catch(Exception e)
        {

        }
    }
}
