/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.application.Application;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author ishan
 */
public class addColumn extends Application{
    
    static void addColumn() {

        
     
        
        int space = 300;
        int width = 100;
        int height = 50 + (int) (Math.random() * 300);

        Flappy_Bird.columns.add(new Rectangle(Flappy_Bird.W + width + (Flappy_Bird.columns.size() * 200), Flappy_Bird.H - height - 120, width, height));
        Flappy_Bird.columns.add(new Rectangle(Flappy_Bird.W + width + (Flappy_Bird.columns.size() - 1) * 200, 0, width, Flappy_Bird.H - height - space));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
