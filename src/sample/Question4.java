//Written by Michael Loo - 100702210
//and Abdullah Riaz Raja - 100693348

package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Question4 extends Application {

    //global variables defining height and length of the canvas
    int height = 400;
    int length = 800;

    //returns an array with the frequency of each letter regardless of upper/lower case
    int[] getFrequency(Scanner text) {
        int[] freq = new int[26];

        //checks if end of file has been reached
        while (text.hasNext()) {
            String line = text.nextLine();                      //stores the selected line as String
            int num = 0;

            //updates frequency of each character
            for (int i = 0; i < line.length(); i++) {
                num = (int) (line.charAt(i));
                if ((num > 64) && (num < 91)) {                 //makes sure only A-Z are accounted for
                    freq[line.charAt(i) - 'A']++;               //and all the other symbols are ignored
                } else if ((num > 96) && (num < 123)) {         //makes sure only a-z are accounted for
                    freq[line.charAt(i) - 'a']++;
                }
            }
        }
        return freq;
    }

    //returns a canvas to display
    Canvas createHistogram(int[] freq) {

        //finds max frequency for scaling purposes
        int max = freq[0];
        for (int i = 0; i < 26; i++) {
            if (max < freq[i]) {
                max = freq[i];
            }
        }

        //setting up canvas
        Canvas canvas = new Canvas(length, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(1.5);

        //draws 26 bars representing frequency of each letter
        for (int i = 0; i < 26; i++) {
            int barH = (freq[i] * 300/ max);
            gc.strokeRect(10+i*30, height-barH-50, 25, barH);
        }

        //draws the x-axis (letter labels)
        for (int i = 0; i < 26; i++) {
            char letter = (char) ('A' + i);
            gc.fillText(String.valueOf(letter), 20+i*30, height-30);
        }
        return canvas;
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {

        VBox vbox = new VBox();
        HBox hbox = new HBox();

        //initial empty canvas
        Canvas empty = new Canvas(length, height);

        //user controls
        Label fileName = new Label("   Filename");
        fileName.setPrefWidth(70);

        TextField pathField = new TextField();
        pathField.setPrefWidth(700);

        Button view = new Button("View");
        view.setOnAction( e -> {
            //takes user inputted path to the text file
            String path = (pathField.getText());

            //file is loaded
            File srcFile = new File(path);

            //read file
            Scanner input = null;
            try {
                input = new Scanner(srcFile);

                //initialize an array with frequency of each letter
                int freq[] = getFrequency(input);
                input.close();

                //previous canvas is replaced with the new canvas containing the updated histogram
                Canvas histogram = createHistogram(freq);
                hbox.getChildren().clear();
                vbox.getChildren().clear();
                hbox.getChildren().addAll(fileName, pathField, view);
                vbox.getChildren().addAll(histogram, hbox);
            } catch (FileNotFoundException ex) {
                System.out.println("Selected file does not exist");
            }

        });

        //Displaying everything to the user
        hbox.getChildren().addAll(fileName, pathField, view);
        vbox.getChildren().addAll(empty, hbox);
        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Question 4");
        primaryStage.show();
    }

    public static void main(String[] args) { launch(args); }
}
