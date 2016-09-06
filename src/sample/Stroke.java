package sample;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Created by jfabiano on 9/6/2016.
 */
public class Stroke {
    double x;
    double y;
    int strokeSize;
    //Paint myPaint;
    //Color myColor;

    Stroke()
    {

    }
    Stroke(double x, double y, int strokeSize)
    {
        this.x = x;
        this.y = y;
        this.strokeSize = strokeSize;
        //this.myColor = myColor;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getStrokeSize() {
        return strokeSize;
    }

    public void setStrokeSize(int strokeSize) {
        this.strokeSize = strokeSize;
    }

//    public Paint getMyPaint() {
//        return myPaint;
//    }
//
//    public void setMyPaint(Paint myPaint) {
//        this.myPaint = myPaint;
//    }
//
//    public Color getMyColor() {
//        return myColor;
//    }
//
//    public void setMyColor(Color myColor) {
//        this.myColor = myColor;
//    }
}
