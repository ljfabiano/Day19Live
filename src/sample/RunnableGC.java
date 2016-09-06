package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.*;

import java.awt.*;

/**
 * Created by jfabiano on 9/6/2016.
 */
public class RunnableGC implements Runnable {

    private GraphicsContext gc = null;
    //private Stroke stroke = null;
    double xLocation;
    double yLocation;
    int strokeSize;
    Stroke myStroke = new Stroke();
    //Paint paint;
    public RunnableGC()
    {

    }
    public RunnableGC(GraphicsContext gc) {
        this.gc = gc;
        //this.xLocation = xLocation;
        //this.yLocation = yLocation;
        //this.paint = paint;

        //this.stroke = stroke;
    }
    public RunnableGC(GraphicsContext gc, Stroke myStroke) {
        this.gc = gc;
        //this.xLocation = xLocation;
        //this.yLocation = yLocation;
        //this.paint = paint;
        this.myStroke = myStroke;
        //this.stroke = stroke;
    }
    public RunnableGC(GraphicsContext gc, double xLocation, double yLocation) {
        this.gc = gc;
        this.xLocation = xLocation;
        this.yLocation = yLocation;
        //this.paint = paint;

        //this.stroke = stroke;
    }

    public RunnableGC(GraphicsContext gc, double xLocation, double yLocation, int strokeSize) {
        this.gc = gc;
        this.xLocation = xLocation;
        this.yLocation = yLocation;
        this.strokeSize = strokeSize;
        //this.paint = paint;

        //this.stroke = stroke;
    }

    public double getxLocation() {
        return xLocation;
    }

    public void setxLocation(double xLocation) {
        this.xLocation = xLocation;
    }

    public double getyLocation() {
        return yLocation;
    }

    public void setyLocation(double yLocation) {
        this.yLocation = yLocation;
    }

    public int getStrokeSize() {
        return strokeSize;
    }

    public void setStrokeSize(int strokeSize) {
        this.strokeSize = strokeSize;
    }

    public void run() {
        //gc.setStroke(javafx.scene.paint.Color.color(Math.random(), Math.random(), Math.random()));
        //gc.setStroke(myStroke.getMyPaint());
        gc.strokeOval(myStroke.getX(), myStroke.getY(), myStroke.getStrokeSize(), myStroke.getStrokeSize()); // <---- this is the actual work we need to do
    }
}
