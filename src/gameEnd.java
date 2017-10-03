/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author ishan
 */
public class gameEnd extends Application {

    
    
    
    static String nameSaver;
    static Group root;
    static GridPane gp;
    static Scene scene;
    static String[] name = new String[10000];
    static int[] score = new int[10000];
    static int counter = 0;

    static int maxScore;

    public static void starting(Stage primaryStage) {
        
        RWscore.writeToFile(Flappy_Bird.result);
        
        Label l = new Label("Results");
        readFromFile();
        l.setStyle("-fx-text-fill:red;-fx-font-size:100;");
        l.setLayoutX(250);
        l.setLayoutY(10);
        Image img = new Image("images/over.png");
        ImageView imgv = new ImageView(img);

        
        Label scorel = new Label("Your Score: " + RWscore.name + " " + Flappy_Bird.result);

        scorel.setStyle("-fx-text-fill:blue;-fx-font-size:40;");
        scorel.setLayoutX(200);
        scorel.setLayoutY(250);

        Label zscore = new Label("High-Score: " + nameSaver + " " + maxScore );
        zscore.setStyle("-fx-text-fill:blue;-fx-font-size:40;");
        zscore.setLayoutX(200);
        zscore.setLayoutY(400);

        root = new Group();

        root.getChildren().add(imgv);
        root.getChildren().add(scorel);
       
        root.getChildren().add(zscore);
        root.getChildren().add(l);
        root.setStyle("-fx-background-color: lightgreen;");

        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Flappy Bird");
        primaryStage.setHeight(700);
        primaryStage.setWidth(800);
        primaryStage.setResizable(false);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void readFromFile() {
        try {
            //Identifies the file we will use
            File myFile = new File("scores.txt");

            //If the file does not exist, throw Exception
            if (!myFile.exists()) {
                throw new FileNotFoundException();
            }

            //Open the File so we can read from the file.
            FileReader fr = new FileReader(
                    myFile.getAbsoluteFile());

            //Use a Buffered Reader so we can write Strings
            BufferedReader br = new BufferedReader(fr);

            //Read from the file.
            String s;
               
            while ((s = br.readLine()) != null) {

                String[] parts = s.split(":");
                name[counter] = parts[0];
                score[counter + 1] = Integer.parseInt(parts[1]);

                if (maxScore < score[counter + 1]) {
                    maxScore = score[counter + 1];
                    //   nameSaver = counter;
                                  nameSaver = name[counter];
                }

                counter++;
            }
            System.out.println(maxScore);
            System.out.println(nameSaver);
            //   System.out.println(RWscore.name + );

            //Close
            br.close();
            fr.close();

        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

}
