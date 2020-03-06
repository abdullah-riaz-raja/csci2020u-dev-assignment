//Written by Michael Loo - 100702210
//and Abdullah Riaz Raja - 100693348

package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.util.ArrayList;

public class Question1 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        //Array containing shuffled numbers from 1 - 52
        ArrayList<Integer> cardNum = new ArrayList<>();
        for (int i = 1; i < 53; i++) {
            cardNum.add(i);
        }
        java.util.Collections.shuffle(cardNum);

        //Selecting random cards to be displayed
        ArrayList<Image> images = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            images.add(new Image(new FileInputStream("C:\\Users\\Abdullah Riaz\\Desktop\\Codes\\csci2020u\\" +
                    "assignment01\\Cards\\" + cardNum.get(i) + ".png")));
        }

        //Adding image objects to Image View
        ArrayList<ImageView> imageViews = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            imageViews.add(new ImageView(images.get(i)));
        }

        //Setting the size of the cards
        for (int i = 0; i < 3; i++) {
            imageViews.get(i).setFitHeight(144);
            imageViews.get(i).setFitWidth(108);
        }

        Pane pane = new HBox(10);
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.getChildren().addAll(imageViews);
        Scene scene = new Scene(pane, 365, 250);
        primaryStage.setTitle("Question 1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
