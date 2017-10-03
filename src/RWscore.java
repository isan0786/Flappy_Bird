/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author ishan
 */
public class RWscore extends Application {

     static String name;
    @Override
    public void start(Stage primaryStage) {

        Label lblname = new Label("Flappy Bird!");
        lblname.setFont(Font.font("Berlin Sans FB", 32));
        lblname.setStyle(" -fx-text-fill:white");
        TextField tfname = new TextField();
        tfname.setPrefHeight(30);
        tfname.setText("UserName");
        tfname.setFont(Font.font("Algerian", 14));
        Button add = new Button("ENTER");
        add.setFont(Font.font("Arial", 14));
        add.setStyle("-fx-background-color:lightblue");

        add.setPrefWidth(165);
        add.setPrefHeight(30);

        GridPane center = new GridPane();
        center.add(lblname, 1, 0);

        center.add(tfname, 1, 1);

        center.add(add, 1, 2);
       
        StackPane spc = new StackPane();
        center.setAlignment(Pos.CENTER);
        center.setHgap(10);
        center.setVgap(10);

        BorderPane bp = new BorderPane();
        spc.getChildren().add(center);
        bp.setCenter(spc);
        StackPane spb = new StackPane();

        Scene scene = new Scene(bp, 800, 700);
        bp.setStyle("-fx-background-color:orange");

        add.setOnAction(e -> {
     
            name = tfname.getText();
            String[] arghs = {"ishan", "anand"};
            tfname.setText("");
          //  writeToFile(name);
            
            Stage sss = new Stage();
            Flappy_Bird.starter(sss);
         
            primaryStage.hide();
        });

        primaryStage.setTitle("Flappy Bird !");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void writeToFile(String name) {
        try {
            //Identifies the file we will use
            File myFile = new File("scores.txt");

            //If the file does not exist, then create it.
            if (!myFile.exists()) {
                //Could throw an exception
                myFile.createNewFile();
            }

            FileWriter fw = new FileWriter(
                    myFile.getAbsoluteFile(), true);

            //Use a Buffered Writer so we can write Strings
            BufferedWriter bw = new BufferedWriter(fw);

            //Write to the file.
           
            
            bw.write(toStringer());
            bw.newLine();

            //Close
            bw.close();
            fw.close();

        } catch (IOException ioe) {
            System.out.println(ioe);
        }
        
    }

   // @Override
    public static String toStringer() {
        return RWscore.name +":"+ Flappy_Bird.result;////
    }
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        //Flappy_Bird.main(args);
    }

}
