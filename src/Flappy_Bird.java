
import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.converter.IntegerStringConverter;

/**
 *
 * @author ishan
 */
public class Flappy_Bird extends Application {

    static Label scoreLabel;
    static Label l2;
    static Button avatar;
    static Button btn;
    static int score;
    static int index;
    static Stage primaryStage;
    static Ellipse bird = new Ellipse();
    static Rectangle ground;
    static AudioClip audioShoot;
    static ArrayList<Rectangle> columns;
    static int W = 800;
    static int H = 700;
    static double ymotion;
    static int ticks;
    static Group root;
     static boolean gameOver;
    static Label l;
    static LinearGradient ld;
    static IntegerStringConverter str;
    static Timeline tim;
    static Scene scene;
    static int X;
    static Image cloud;
    static ImageView cloudv;
    static int Y;
    static DropShadow dsl;
    static MediaPlayer mediaPlayer;
     static int counter = 0;
    static String someString;
    static String[] newString;
    static String character;
    static Image img;
    static ImagePattern ip;
//    static String args;
    static String result;
     static String results = "0";
     static int count;
     static Button resulter;
    // static boolean image ;
    //

     static void music1() {
       // Media musicFile = new Media("file:///E:/College/Semester2/Java2/Project/Flappy_Bird/src/audio/newfly.wav");
         Media musicFile= new Media(Flappy_Bird.class.getResource("audio/newfly.wav").toExternalForm());
        mediaPlayer = new MediaPlayer(musicFile);
        mediaPlayer.setAutoPlay(true);
    }

     static void music2() {
        Media musicFile= new Media(Flappy_Bird.class.getResource("audio/point.wav").toExternalForm());

        mediaPlayer = new MediaPlayer(musicFile);
        mediaPlayer.setAutoPlay(true);
    }

   static   void music3() {
        Media musicFile= new Media(Flappy_Bird.class.getResource("audio/mario.wav").toExternalForm());

        mediaPlayer = new MediaPlayer(musicFile);
        mediaPlayer.setAutoPlay(true);
    }

   static   void music4() {
       Media musicFile= new Media(Flappy_Bird.class.getResource("audio/song.wav").toExternalForm());

        mediaPlayer = new MediaPlayer(musicFile);
        mediaPlayer.setAutoPlay(true);
    }

     static void Jump() {

        if (!gameOver) {
            if (ymotion > 0) {
                ymotion = 0;
            }
            ymotion = ymotion - 8.5;
            music1();
            int color = (int) (Math.random() * 255);
            int colors = (int) (Math.random() * 255);
            int colore = (int) (Math.random() * 255);
            scene.setFill(Color.rgb(color, colors, colore));

        }

    }

     static void fileExplorer() {
        
         
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Character");
        someString = fileChooser.showOpenDialog(primaryStage).toString();
         System.out.println(someString);
        String replaceString = someString.replace("\\", "/");
        newString = replaceString.split("/");
       
        character = ("images" + "/" + newString[(newString.length-1)]);
    
        img = new Image(character);
        ip = new ImagePattern(img);
        bird.setFill(ip);
    }
 
   static void start1() {

        bird.setCenterX(W / 2 - 10);
        bird.setCenterY(H / 2 - 10);

        gameOver = false;
        ymotion = 0;
        score = 0;
        scoreLabel.setText("Score" + str.toString(score));
        // result = str.toString(score);
        // System.out.println(result);
        root.getChildren().remove(btn);
        root.getChildren().remove(resulter);
        root.getChildren().removeAll(columns);
        columns.clear();
        int i = 0;
        while (i < 20) {
            addColumn.addColumn();
            i++;
        }
        l2.setText("Click to start");
        l2.setFont(new Font("Arial", 50));
        root.getChildren().add(avatar);
        l2.setLayoutX(primaryStage.getWidth() / 2 - 150);
        l2.setLayoutY(primaryStage.getHeight() / 2 - 100);
        l2.setTextFill(Color.RED);
        tim.pause();
        avatar.setOnMouseClicked(k -> {
            
           try{ 
            fileExplorer();}
            catch(Exception e){
                System.out.println("hello");
            }
            
        });
        root.getChildren().add(l2);

        scene.setOnMouseClicked(k -> {

            root.getChildren().addAll(columns);
            root.getChildren().remove(l2);
            root.getChildren().remove(avatar);
            tim.play();

        });
        scene.setOnKeyReleased(k -> {
            String code = k.getCode().toString();
            if (code == "UP") {
                root.getChildren().addAll(columns);
                root.getChildren().remove(l2);
                root.getChildren().remove(avatar);
                tim.play();
            }
        });


        
        
         if (gameOver) {

            bird.setCenterY(H - 120 - bird.getRadiusY());
            l.setText("Game over\n  Score:" + str.toString(score / 2));
            result = str.toString(score / 2);
           
            if(count == 0){ 
            results = result;
            RWscore.writeToFile(results);
            System.out.println(results);
            count++;
            }
            
            l.setFont(new Font("Arial", 50));

            l.setLayoutX(primaryStage.getWidth() / 2 - 130);
            l.setLayoutY(primaryStage.getHeight() / 2 - 50);
            l.setTextFill(Color.ORANGE);

        }
        
    }

     static void Collision() {
        for (Rectangle column : columns) {
            if ((column.getBoundsInParent().intersects(bird.getBoundsInParent()))) {
                gameOver = true;
                if (bird.getCenterX() <= column.getX()) {
                    bird.setCenterX(column.getX() - 2 * bird.getRadiusX() + 10);
                } else {
                    if (column.getY() != 0) {
                        bird.setCenterY(column.getY() - 2 * bird.getRadiusY());

                    } else if (bird.getCenterY() > column.getHeight()) {
                        bird.setCenterY(column.getHeight());
                    }
                }
            }
        }
        if (bird.getCenterY() > H - 120 || bird.getCenterY() < 0) {
            gameOver = true;
        }

        if (gameOver) {

            bird.setCenterY(H - 120 - bird.getRadiusY());
            l.setText("Game over\n  Score:" + str.toString(score / 2));
            result = str.toString(score / 2);
           
            if(count == 0){ 
            results = result;
            RWscore.writeToFile(results);
            System.out.println(results);
            count++;
            }
            
            l.setFont(new Font("Arial", 50));

            l.setLayoutX(primaryStage.getWidth() / 2 - 130);
            l.setLayoutY(primaryStage.getHeight() / 2 - 50);
            l.setTextFill(Color.ORANGE);

        }

    }

    public static void starter(Stage window) {

        primaryStage = window;

        primaryStage.setTitle("Flappy Bird");
        primaryStage.setHeight(H);
        primaryStage.setWidth(W);
        primaryStage.setResizable(false);

        root = new Group();
        DropShadow ds1 = new DropShadow();
        ds1.setOffsetY(4.0f);
        ds1.setOffsetX(4.0f);
        ds1.setColor(Color.GREY);

        DropShadow ds2 = new DropShadow();
        ds1.setOffsetY(4.0f);
        ds1.setOffsetX(4.0f);
        ds1.setColor(Color.BLACK);
        character = "images/parrot.png";

        img = new Image(character);
        ip = new ImagePattern(img);
        cloud = new Image("images/batman.gif");
        cloudv = new ImageView(cloud);

        X = W + (int) cloud.getWidth();
        cloudv.setX(X);
        Y = 10 + (int) (Math.random() * 100);
        cloudv.setY(Y);

        bird.setFill(ip);
        bird.setRadiusX((img.getWidth() / 2) + 2);
        bird.setRadiusY((img.getHeight() / 2) + 2);
        bird.setCenterX((W / 2 - 10));
        bird.setCenterY((H / 2 - 10));
        bird.setEffect(ds1);

        index = 0;
        ymotion = 0;

        str = new IntegerStringConverter();

        l = new Label();
        l2 = new Label();

        scoreLabel = new Label();
        scoreLabel.setFont(new Font("Arial", 20));

        ld = new LinearGradient(0.0, 0.0, 1.0, 0.0, true,
                CycleMethod.NO_CYCLE,
                new Stop(0.0, Color.GREY),
                new Stop(1.0, Color.BLACK));

        columns = new ArrayList<Rectangle>();
        Image imger = new Image("images/water1.gif");
        ImagePattern il = new ImagePattern(imger);
        ground = new Rectangle(0, H - 120, W, 120);
        ground.setFill(il);

        tim = new Timeline();
        tim.setCycleCount(Animation.INDEFINITE);

        gameOver = false;

        btn = new Button("Restart");
        btn.setTranslateX(350);
        btn.setTranslateY(600);
        btn.setPrefSize(100, 50);
        btn.setTextFill(Color.BLUE);
        btn.setFont(new Font("Arial", 20));
        btn.setEffect(ds2);
        
         resulter = new Button("Results");
        resulter.setTranslateX(300);
        resulter.setTranslateY(450);
        resulter.setPrefSize(200, 50);
        resulter.setTextFill(Color.ORANGE);
        resulter.setFont(new Font("Arial", 25));
        resulter.setEffect(ds1);


        avatar = new Button("Character");
        avatar.setTranslateX(300);
        avatar.setTranslateY(400);
        avatar.setPrefSize(200, 50);
        avatar.setTextFill(Color.BLUE);
        avatar.setFont(new Font("Arial", 20));

        KeyFrame kf = new KeyFrame(Duration.millis(20), (ActionEvent e) -> {

            ticks++;
            if (ticks % 2 == 0 && ymotion < 15) {
                ymotion = ymotion + 2;
            }
            X = X - 2;
            cloudv.setX(X);
            if (X < (0 - (int) cloud.getWidth())) {
                X = W + (int) cloud.getWidth();
                cloudv.setX(X);
                Y = 10 + (int) (Math.random() * 100);
                cloudv.setY(Y);
            }

            int y = (int) ((int) bird.getCenterY() + ymotion);
            bird.setCenterY(y);

           
            
            scene.setOnKeyReleased(k -> {

                String code = k.getCode().toString();
                if (code == "UP") {

                    Jump();

                }
            });
             scene.setOnMouseClicked(k -> {

                Jump();

            });

            Collision();

            if (gameOver) {

                if (!(root.getChildren().contains(l))) {
                    root.getChildren().addAll(l, btn,resulter);
                    music3();
                }
                btn.setOnMouseClicked(k -> {
                    root.getChildren().remove(l);
                    start1();
                });
                
                resulter.setOnMouseClicked((MouseEvent k) -> {
                  
                   Stage s3 = new Stage();
                   
                    gameEnd.starting(s3);
                   
                   gameEnd.readFromFile();
                //btn   System.out.println(gameEnd.readFromFile());
                   //gameOver.main(args);
                });
            }
        });

        KeyFrame kf2 = new KeyFrame(Duration.millis(20), e -> {

            for (int i = 0; i < columns.size(); i++) {

                Rectangle column = columns.get(i);
                column.setFill(ld);
                column.setEffect(ds1);
                column.setX((column.getX() - 5));

                if (column.getY() == 0 && bird.getCenterX() + bird.getRadiusX() > column.getX() + column.getWidth() / 2 - 5 && bird.getCenterX() + bird.getRadiusX() < column.getX() + column.getWidth() / 2 + 5) {
                    music2();
                    score++;
                    scoreLabel.setText("Score:" + str.toString(score / 2));
                    scoreLabel.setTextFill(Color.DARKBLUE);
                }
            }
            for (int i = 0; i < columns.size(); i++) {
                Rectangle column = columns.get(i);

                if ((column.getX() + column.getWidth()) < 0) {
                    columns.remove(i);
                }
            }
        });

        tim.getKeyFrames().addAll(kf, kf2);

        root.getChildren().add(cloudv);
        root.getChildren().addAll(scoreLabel);
        root.getChildren().add(ground);
        root.getChildren().add(bird);

        scene = new Scene(root);

        start1();
        // primaryStage.initOwner(stage);
        primaryStage.requestFocus();
        primaryStage.setScene(scene);
        primaryStage.setHeight(H);
        primaryStage.setWidth(W);
        primaryStage.show();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
