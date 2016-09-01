package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    final double DEFAULT_SCENE_WIDTH = 800;
    final double DEFAULT_SCENE_HEIGHT = 600;

    boolean toggleDraw = true;
    int strokeSize = 10;
    double xVal;
    double yVal;
    GraphicsContext gc;
    Canvas canvas;

    @Override
    public void start(Stage primaryStage) throws Exception{//receives the first stage from javafx
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
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
        hbButton.setAlignment(Pos.TOP_LEFT);
        hbButton.getChildren().add(button);
        grid.add(hbButton, 0, 1);
        //grid.add(hbButton, 1, 1);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("I can switch to another scene here ...");
//                primaryStage.setScene(loginScene);
                startSecondStage();//call the start second stage
            }
        });

        // add canvas
        canvas = new Canvas(DEFAULT_SCENE_WIDTH, DEFAULT_SCENE_HEIGHT-100);

        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setStroke(Color.color(Math.random(), Math.random(), Math.random()));
        gc.setLineWidth(5);

        canvas.setOnMouseMoved(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent e) {
//                System.out.println("x: " + e.getX() + ", y: " + e.getY());
                if(toggleDraw == true) {
                    gc.strokeOval(e.getX(), e.getY(), strokeSize, strokeSize);
                    xVal = e.getX();
                    yVal = e.getY();
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
                    gc.setStroke(Color.color(Math.random(), Math.random(), Math.random()));

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
            }
        });


        grid.add(canvas, 0, 2);



        //add grid layout on the scene
        Scene defaultScene = new Scene(grid, DEFAULT_SCENE_WIDTH, DEFAULT_SCENE_HEIGHT);

        primaryStage.setScene(defaultScene);
        primaryStage.show();
    }

    //start second stage method to be added
    public void startSecondStage() {//serialize over a server socket for the weekend assignment. We are initiating the connection from the client to the server(starting screen is )
        Stage secondaryStage = new Stage();
        secondaryStage.setTitle("Welcome to JavaFX");

        // we're using a grid layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setGridLinesVisible(true);
//        grid.setPrefSize(primaryStage.getMaxWidth(), primaryStage.getMaxHeight());

        // add buttons and canvas to the grid
        Text sceneTitle = new Text("Welcome to Paint application");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(sceneTitle, 0, 0);

        Canvas canvasTwo = new Canvas(DEFAULT_SCENE_WIDTH, DEFAULT_SCENE_HEIGHT-100);

        GraphicsContext gcTwo = canvasTwo.getGraphicsContext2D();
        gcTwo.setFill(Color.GREEN);
        gcTwo.setStroke(Color.BLUE);
        gcTwo.setStroke(Color.color(Math.random(), Math.random(), Math.random()));
        gcTwo.setLineWidth(5);

        Button button = new Button("Sample paint button");
        HBox hbButton = new HBox(10);
        hbButton.setAlignment(Pos.TOP_LEFT);
        hbButton.getChildren().add(button);
        grid.add(hbButton, 0, 1);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("I can switch to another scene here ...");
                startSecondStage();
            }
        });

        canvas.setOnMouseMoved(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent e) {
//                System.out.println("x: " + e.getX() + ", y: " + e.getY());
                if(toggleDraw == true) {
                    //gc.strokeOval(e.getX(), e.getY(), strokeSize, strokeSize);
                    gc.strokeOval(e.getX(), e.getY(), strokeSize, strokeSize);
                    gcTwo.strokeOval(e.getX(), e.getY(), strokeSize, strokeSize);
                }
//                addStroke(e.getX(), e.getY(), 10);//saves every stroke made with mouse, adds to an array list, and in the method give second canvas, and write to the other canvas...
            }
        });

        // add canvas
        //new EventHandler<>()

        grid.add(canvasTwo, 0, 2);
        // set our grid layout on the scene
        Scene defaultScene = new Scene(grid, DEFAULT_SCENE_WIDTH, DEFAULT_SCENE_HEIGHT);

        secondaryStage.setScene(defaultScene);
        System.out.println("About to show the second stage");

        secondaryStage.show();
//        while(true) {
//            if (toggleDraw == true) {
//                //gc.strokeOval(e.getX(), e.getY(), strokeSize, strokeSize);
//                gcTwo.strokeOval(xVal, yVal, strokeSize, strokeSize);
//            }
//        }
    }



    public static void main(String[] args) {
        launch(args);
    }
}
