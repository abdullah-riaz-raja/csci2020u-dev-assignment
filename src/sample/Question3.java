//Written by Michael Loo - 100702210
//and Abdullah Riaz Raja - 100693348

package sample;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Question3 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        //initialize group
        Group group = new Group();

        //initializing window stage
        primaryStage.setWidth(500);
        primaryStage.setHeight(500);

        //initializing circles
        Circle mainCircle = new Circle(250, 250, 150);
        Circle miniCircle1 = new Circle(100, 250, 8);
        Circle miniCircle2 = new Circle(250, 100, 8);
        Circle miniCircle3 = new Circle(400, 250, 8);

        mainCircle.setStroke(Color.BLACK);
        mainCircle.setFill(Color.TRANSPARENT);
        miniCircle1.setStroke(Color.BLACK);
        miniCircle1.setFill(Color.RED);
        miniCircle2.setStroke(Color.BLACK);
        miniCircle2.setFill(Color.RED);
        miniCircle3.setStroke(Color.BLACK);
        miniCircle3.setFill(Color.RED);

        //initializing lines that connect the miniCircles
        Line line1 = new Line(); //miniCircles 1 and 2
        Line line2 = new Line(); //miniCircles 2 and 3
        Line line3 = new Line(); //miniCircles 3 and 1

        line1.startXProperty().bind(miniCircle1.centerXProperty());
        line1.startYProperty().bind(miniCircle1.centerYProperty());
        line1.endXProperty().bind(miniCircle2.centerXProperty());
        line1.endYProperty().bind(miniCircle2.centerYProperty());

        line2.startXProperty().bind(miniCircle2.centerXProperty());
        line2.startYProperty().bind(miniCircle2.centerYProperty());
        line2.endXProperty().bind(miniCircle3.centerXProperty());
        line2.endYProperty().bind(miniCircle3.centerYProperty());

        line3.startXProperty().bind(miniCircle3.centerXProperty());
        line3.startYProperty().bind(miniCircle3.centerYProperty());
        line3.endXProperty().bind(miniCircle1.centerXProperty());
        line3.endYProperty().bind(miniCircle1.centerYProperty());


        //initializing the angle labels on each miniCircle
        Text text1 = new Text("45"); //for miniCircle1
        text1.xProperty().bind(miniCircle1.centerXProperty());
        text1.yProperty().bind(miniCircle1.centerYProperty());

        Text text2 = new Text("90"); //for miniCircle1
        text2.xProperty().bind(miniCircle2.centerXProperty());
        text2.yProperty().bind(miniCircle2.centerYProperty());

        Text text3 = new Text("45"); //for miniCircle1
        text3.xProperty().bind(miniCircle3.centerXProperty());
        text3.yProperty().bind(miniCircle3.centerYProperty());


        //mouse input for miniCircle1
        miniCircle1.setOnMouseDragged((MouseEvent me) -> {

            //finds the center of the mainCircle and sets it so that miniCircle1 can only go around mainCircle's radius
            Point2D mainCenter = new Point2D(mainCircle.getCenterX(), mainCircle.getCenterY());
            Point2D mouse = new Point2D(me.getX(), me.getSceneY());
            Point2D centerToMouse = mouse.subtract(mainCenter);
            Point2D centerToNewPoint = centerToMouse.normalize().multiply(mainCircle.getRadius());
            Point2D newPoint = centerToNewPoint.add(mainCenter);
            miniCircle1.setCenterX(newPoint.getX());
            miniCircle1.setCenterY(newPoint.getY());

            //calculates dist and angle to set the angles of the miniCircles
            double d3 = calculateDistance(miniCircle1.getCenterX(),miniCircle1.getCenterY(),miniCircle2.getCenterX(),miniCircle2.getCenterY());
            double d1 = calculateDistance(miniCircle2.getCenterX(),miniCircle2.getCenterY(),miniCircle3.getCenterX(),miniCircle3.getCenterY());
            double d2 = calculateDistance(miniCircle3.getCenterX(),miniCircle3.getCenterY(),miniCircle1.getCenterX(),miniCircle1.getCenterY());
            int A1 = (int) Math.round(calculateAngle(d1,d2,d3));
            int A2 = (int) Math.round(calculateAngle(d2,d3,d1));
            int A3 = (int) Math.round(calculateAngle(d3,d2,d1));
            String sA1 = String.valueOf(A1);
            String sA2 = String.valueOf(A2);
            String sA3 = String.valueOf(A3);

            //updates the angles to the current angle positions
            text1.setText(sA1);
            text2.setText(sA2);
            text3.setText(sA3);
        });

        //mouse input for miniCircle2
        miniCircle2.setOnMouseDragged((MouseEvent me) -> {

            //finds the center of the mainCircle and sets it so that miniCircle2 can only go around mainCircle's radius
            Point2D mainCenter = new Point2D(mainCircle.getCenterX(), mainCircle.getCenterY());
            Point2D mouse = new Point2D(me.getX(), me.getSceneY());
            Point2D centerToMouse = mouse.subtract(mainCenter);
            Point2D centerToNewPoint = centerToMouse.normalize().multiply(mainCircle.getRadius());
            Point2D newPoint = centerToNewPoint.add(mainCenter);
            miniCircle2.setCenterX(newPoint.getX());
            miniCircle2.setCenterY(newPoint.getY());

            //calculates dist and angle to set the angles of the miniCircles
            double d3 = calculateDistance(miniCircle1.getCenterX(),miniCircle1.getCenterY(),miniCircle2.getCenterX(),miniCircle2.getCenterY());
            double d1 = calculateDistance(miniCircle2.getCenterX(),miniCircle2.getCenterY(),miniCircle3.getCenterX(),miniCircle3.getCenterY());
            double d2 = calculateDistance(miniCircle3.getCenterX(),miniCircle3.getCenterY(),miniCircle1.getCenterX(),miniCircle1.getCenterY());
            int A1 = (int) Math.round(calculateAngle(d1,d2,d3));
            int A2 = (int) Math.round(calculateAngle(d2,d3,d1));
            int A3 = (int) Math.round(calculateAngle(d3,d2,d1));
            String sA1 = String.valueOf(A1);
            String sA2 = String.valueOf(A2);
            String sA3 = String.valueOf(A3);

            //updates the angles to the current angle positions
            text1.setText(sA1);
            text2.setText(sA2);
            text3.setText(sA3);
        });

        //mouse input for miniCircle3
        miniCircle3.setOnMouseDragged((MouseEvent me) -> {

            //finds the center of the mainCircle and sets it so that miniCircle3 can only go around mainCircle's radius
            Point2D mainCenter = new Point2D(mainCircle.getCenterX(), mainCircle.getCenterY());
            Point2D mouse = new Point2D(me.getX(), me.getSceneY());
            Point2D centerToMouse = mouse.subtract(mainCenter);
            Point2D centerToNewPoint = centerToMouse.normalize().multiply(mainCircle.getRadius());
            Point2D newPoint = centerToNewPoint.add(mainCenter);
            miniCircle3.setCenterX(newPoint.getX());
            miniCircle3.setCenterY(newPoint.getY());

            //calculates dist and angle to set the angles of the miniCircles
            double d3 = calculateDistance(miniCircle1.getCenterX(),miniCircle1.getCenterY(),miniCircle2.getCenterX(),miniCircle2.getCenterY());
            double d1 = calculateDistance(miniCircle2.getCenterX(),miniCircle2.getCenterY(),miniCircle3.getCenterX(),miniCircle3.getCenterY());
            double d2 = calculateDistance(miniCircle3.getCenterX(),miniCircle3.getCenterY(),miniCircle1.getCenterX(),miniCircle1.getCenterY());
            int A1 = (int) Math.round(calculateAngle(d1,d2,d3));
            int A2 = (int) Math.round(calculateAngle(d2,d3,d1));
            int A3 = (int) Math.round(calculateAngle(d3,d2,d1));
            String sA1 = String.valueOf(A1);
            String sA2 = String.valueOf(A2);
            String sA3 = String.valueOf(A3);

            //updates the angles to the current angle positions
            text1.setText(sA1);
            text2.setText(sA2);
            text3.setText(sA3);
        });

        //off sets text so it doesn't overlap with the miniCircles
        text1.setTranslateX(15);
        text2.setTranslateX(15);
        text3.setTranslateX(15);

        //adding contents to the group
        group.getChildren().addAll(line1,line2,line3,text1,text2,text3, mainCircle,miniCircle1,miniCircle2, miniCircle3);

        //putting the group in the scene
        Scene scene = new Scene(group);
        primaryStage.setTitle("CircleWithTriangle");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //to calculate the distance between 2 points
    private double calculateDistance(double x1, double y1, double x2, double y2) {
        double p1 = Math.pow((x2 - x1),2);
        double p2 = Math.pow((y2 - y1),2);
        double dist = Math.sqrt(p1 + p2);
        return dist;
    }

    //calculating the angle
    private double calculateAngle(double a, double b, double c) {
        double p1 = (a*a - b*b - c*c);
        double p2 = (-2*b*c);

        double Ang = Math.toDegrees((Math.acos(p1/p2)));
        return Ang;
    }

    public static int getRandomNumber() {
        int result = (int) Math.random()*100;
        return result;
    }
}