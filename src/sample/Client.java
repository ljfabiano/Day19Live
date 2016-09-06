package sample;

/**
 * Created by jfabiano on 9/2/2016.
 */
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

public class Client extends Application implements Initializable, Runnable {
    final double DEFAULT_SCENE_WIDTH = 800;
    final double DEFAULT_SCENE_HEIGHT = 600;

    boolean toggleDraw = true;
    int strokeSize = 10;
    double xVal;
    double yVal;
    GraphicsContext gc;
    Stroke strokeUsed;
    Canvas canvas;
    Socket clientSocket;
    Thread handlingThreadClient = new Thread(this);
    Stroke myStroke;
    javafx.scene.paint.Paint myPaint;
    Color myColor;

    public Client ()
    {
        //Client newClient = ;
        //Thread handlingThreadClient; = new Thread(this);
    }
    public Client (Socket clientSocket)
    {
        this.clientSocket = clientSocket;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("initializing the window from the client code");

    }

    @Override
    public void start(Stage primaryStage) throws Exception{//receives the first stage from javafx


        System.out.println("This is the client");



//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));//This allows for the connection to the .fxml file which we are not using, so it is commented
        primaryStage.setTitle("Hello World");

        //we're using a grid layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setGridLinesVisible(true);

        // add buttons and canvas to the grid
        Text sceneTitle = new Text("Welcome to Paint application");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(sceneTitle, 0, 0);//1. column, 2. row
        //grid.add(sceneTitle, 0, 0, 2, 1);

        Button button = new Button("Sample paint button");
        HBox hbButton = new HBox(10);
        hbButton.setAlignment(Pos.TOP_CENTER);
        hbButton.getChildren().add(button);
        grid.add(hbButton, 0, 1);
        //grid.add(hbButton, 1, 1);

        Button networkButton = new Button("Network Button");
        HBox hButton = new HBox(100);
        hButton.setAlignment(Pos.TOP_LEFT);
        hButton.getChildren().add(networkButton);
        grid.add(hButton, 0, 1);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("I can switch to another scene here ...");
//                primaryStage.setScene(loginScene);
                //startSecondStage();//call the start second stage
            }
        });

        networkButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("I can connect to the server here ...");

                try {

                    //Scanner for the console
                    Scanner consoleInput = new Scanner(System.in);

                    // connect to the server on the target port
                    //Scanner ipScanner = new Scanner(System.in);
                    //Ask the user for their friend's IP address
                    System.out.println("Please enter your friend's IP address:");
                    String ipInput = consoleInput.nextLine();
                    clientSocket = new Socket(ipInput, 8005);
                    System.out.println("Connection to server is complete");
                    //handlingThreadClient = new Thread(this);
                    handlingThreadClient.start();
                    //setConnection();
//                primaryStage.setScene(loginScene);
                    //startSecondStage();//call the start second stage
                    //Change this string to point to the appropriate ip address, or localhost for myself, 127.0.0.1 = me, 10.0.0.139 = Ben

                    // once we connect to the server, we also have an input and output stream
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


                    //clientSocket.close();//change this so it has code in it before the connection is closed
                    //launch(args);
//            Client myClient = new Client();
//            myClient.start();
                }catch(IOException ioe)
                {
                    ioe.printStackTrace();
                }catch(Exception exc)
                {
                    exc.printStackTrace();
                }

            }
        });

        // add canvas
        canvas = new Canvas(DEFAULT_SCENE_WIDTH, DEFAULT_SCENE_HEIGHT-100);

        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setStroke(myColor = Color.color(Math.random(), Math.random(), Math.random()));
        gc.setLineWidth(5);

        canvas.setOnMouseMoved(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent e) {
//                System.out.println("x: " + e.getX() + ", y: " + e.getY());
                if(toggleDraw == true) {
                    gc.strokeOval(e.getX(), e.getY(), strokeSize, strokeSize);
                    xVal = e.getX();
                    //System.out.println("X = " + xVal);
                    yVal = e.getY();
                    //System.out.println("Y = " + yVal);
                    myStroke = new Stroke(xVal, yVal, strokeSize);
                    if(clientSocket != null) {
                        handleOutput(myStroke);
                    }
                    //strokeUsed = gc.strokeOval(e.getX(), e.getY(), strokeSize, strokeSize);
                    //add a stroke object with color, x, y, and stroke size
                    //kick off the handle output method here... perhaps take stroke as a parameter
                }
//                addStroke(e.getX(), e.getY(), 10);//saves every stroke made with mouse, adds to an array list, and in the method give second canvas, and write to the other canvas...
            }
        });

        grid.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                System.out.println("Key pressed was " + event.getCode().getName());
                System.out.println(event.getCode());
                System.out.println(event.getText());
                if(event.getCode().getName().equals("A"))
                {
                    //myPaint = Color.color(Math.random(), Math.random(), Math.random());
                    gc.setStroke(Color.color(Math.random(), Math.random(), Math.random()));
                    //myStroke.setMyPaint(Color.color(Math.random(), Math.random(), Math.random()));


                }
                if(event.getCode().getName().equals("D"))
                {
                    toggleDraw = !toggleDraw;

                }
                if(event.getCode().getName().equals("C"))
                {
                    gc.clearRect(0, 0, DEFAULT_SCENE_WIDTH, DEFAULT_SCENE_HEIGHT-100);

                }
                //Make sure the stroke size never goes below 2 or above 20 (hint: use a set method and add an if block there)
                //When the user presses the "up" arrow, increase the size of the stroke by 1
                if(event.getCode().getName().equals("Up") && strokeSize < 20)
                {
                    strokeSize += 1;
                }
                //When the user presses the "down" arrow, decrease the size of the stroke by 1
                if(event.getCode().getName().equals("Down") && strokeSize > 2)
                {
                    strokeSize -= 1;

                }
                if(event.getCode().getName().equals("Q"))
                {

                    try {
                        clientSocket.close();
                    }catch(IOException ex)
                    {
                        ex.printStackTrace();
                    }
                }

            }
        });


        grid.add(canvas, 0, 2);



        //add grid layout on the scene
        Scene defaultScene = new Scene(grid, DEFAULT_SCENE_WIDTH, DEFAULT_SCENE_HEIGHT);

        primaryStage.setScene(defaultScene);
        primaryStage.show();
    }

    private void writeToDoList()
    {
            //loop through the observable list and add the to do items 1 by 1 to the arraylist
            JsonSerializer jsonSerializer = new JsonSerializer().deep(true);
    }

    public void setConnection()
    {
        try {
            System.out.println("Server called by the client");
            ServerSocket serverListener = new ServerSocket(8005);
//            while(true) {//needed, but not correct right now. The server needs to be on its own thread to deal with connections in the background
            Socket clientSocket = serverListener.accept();
            //create new connection handler just accepted, and create the connection handler object, then create the thread, and then
            //pass it the thread
            ConnectionHandlerForClient myHandler = new ConnectionHandlerForClient(serverListener);
            Thread handlingThread = new Thread(myHandler);
            handlingThread.start();
//            }

        }catch(IOException e)
        {
            e.printStackTrace();
        }

    }

    public void run() {
        //handleOutput();
    }

    public void handleOutput(Stroke myStroke) {
        try

        {
            //Platform.runLater(new RunnableGC(gc, xVal, yVal));
            String jsonStringStroke;
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            System.out.println("The output Handler");

                    System.out.println("X value: " + xVal);
                    System.out.println("Y value: " + yVal);

            JsonSerializer jsonSerializer = new JsonSerializer().deep(true);
            //String jsonStringGC = jsonSerializer.serialize(runnable);//get string back of the object
            System.out.println("mystroke = " + myStroke.toString());

                jsonStringStroke = jsonSerializer.serialize(myStroke);

                System.out.println("mystroke serialized = " + jsonStringStroke.toString());

                out.println(jsonStringStroke);

                // change the todoItems being passed to the .serialize method to an insance of the User class which will contain the arrayList as a member variable

        } catch (Exception ioe) {//changed io exception to exception to avoid a compile error
            ioe.printStackTrace();


        }
        finally
        {
            //clientSocket.close();
        }

    }

    public static void main(String[] argsClient){

        launch(argsClient);

    }

}
