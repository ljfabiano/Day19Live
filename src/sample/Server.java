package sample;

/**
 * Created by jfabiano on 9/2/2016.
 */

import javafx.scene.canvas.GraphicsContext;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{
    GraphicsContext gc;

public Server()
{

}
public Server(GraphicsContext gc)
{
    this.gc = gc;
}
    public void run() {
        setConnection();
    }
    public void setConnection()
    {
        try {
            System.out.println("Server called");
            ServerSocket serverListener = new ServerSocket(8005);
            while(true) {//needed, but not correct right now. The server needs to be on its own thread to deal with connections in the background
                Socket clientSocket = serverListener.accept();
                //create new connection handler just accepted, and create the connection handler object, then create the thread, and then
                //pass it the thread
                ConnectionHandler myHandler = new ConnectionHandler(clientSocket, gc);//use these for the server itself
                Thread handlingThread = new Thread(myHandler);
                handlingThread.start();
            }
//            while(true) {//needed, but not correct right now. The server needs to be on its own thread to deal with connections in the background
            //create new connection handler just accepted, and create the connection handler object, then create the thread, and then
            //pass it the thread

        }catch(IOException e)
        {
            e.printStackTrace();
        }

    }

}
