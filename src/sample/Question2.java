//Written by Michael Loo - 100702210
//and Abdullah Riaz Raja - 100693348

package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.lang.Math;
import java.text.DecimalFormat;

public class Question2 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        //creating the v and h boxes to put in the text and textfields into rows and columns
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
        HBox hBox1 = new HBox();
        HBox hBox2 = new HBox();
        HBox hBox3 = new HBox();
        HBox hBox4 = new HBox();
        HBox hBox5 = new HBox();
        hBox1.setSpacing(10);
        hBox1.setAlignment(Pos.CENTER);
        hBox2.setSpacing(10);
        hBox2.setAlignment(Pos.CENTER);
        hBox3.setSpacing(10);
        hBox3.setAlignment(Pos.CENTER);
        hBox4.setSpacing(10);
        hBox4.setAlignment(Pos.CENTER);
        hBox5.setSpacing(10);
        hBox5.setAlignment(Pos.CENTER);

        //columns/rows for the text/inputs/outputs
        Label IALabel = new Label("Investment Amount");
        TextField tfIA = new TextField();
        tfIA.setAlignment(Pos.CENTER_RIGHT);
        IALabel.setMinWidth(110);

        Label YSLabel = new Label("Years");
        TextField tfYS = new TextField();
        tfYS.setAlignment(Pos.CENTER_RIGHT);
        YSLabel.setMinWidth(110);


        Label AIRLabel = new Label("Annual Interest Rate");
        TextField tfAIR = new TextField();
        tfAIR.setAlignment(Pos.CENTER_RIGHT);
        AIRLabel.setMinWidth(110);

        Label FVLabel = new Label("Future Value");
        TextField tfFV = new TextField();
        tfFV.setAlignment(Pos.CENTER_RIGHT);
        tfFV.setEditable(false);
        tfFV.setStyle("-fx-background-color: LIGHTGREY");
        FVLabel.setMinWidth(110);

        Label BLANKLabel = new Label(" "); //to make space for formatting
        BLANKLabel.setMinWidth(200);

        //adding the text/text fields to the hboxes(horizontally along each "row")
        hBox1.getChildren().addAll(IALabel, tfIA);
        hBox2.getChildren().addAll(YSLabel, tfYS);
        hBox3.getChildren().addAll(AIRLabel, tfAIR);
        hBox4.getChildren().addAll(FVLabel, tfFV);


        //button
        Button btCALC = new Button("Calculate");
        hBox5.getChildren().addAll(BLANKLabel, btCALC);

        //adding hboxes to the vbox, (placing the rows starting with the top)
        vBox.getChildren().addAll(hBox1,hBox2,hBox3,hBox4,hBox5);

        //adding function to th button
        EventHandler<ActionEvent> calculate = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {

                //creating variables for the formula
                Double A = Double.parseDouble(tfIA.getText());
                double B = Math.pow((1 + (Float.parseFloat(tfAIR.getText()))/1200), (Float.parseFloat(tfYS.getText()))*12 );

                double result = A*B;

                //keeping the final result at 2 decimal place
                DecimalFormat df = new DecimalFormat("#.00");

                //Float.parseFloat(tfIA.getText()) * Math.pow((1 + (Float.parseFloat(tfAIR.getText()))/1200), (Float.parseFloat(tfYS.getText()))*12 )

                //setting the final value text field as the calculated result
                tfFV.setText(Double.toString(
                        Double.parseDouble(df.format(result))
                ));
            }
        };

        btCALC.setOnAction(calculate);

        Scene scene = new Scene(vBox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

