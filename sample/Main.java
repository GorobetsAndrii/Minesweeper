package sample;

import Game.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Controller controller = new Controller();
        controller.window(primaryStage);
    }


    public static void main(String[] args) {
        /*
        Board b = new Board();
        Cell[][] c = b.getBoard();

        for(int i = 0; i < 15; ++i) {
            for (int j = 0; j < 15; ++j) {
                if(c[i][j].getCondition() == ConditionCell.NUMBER){
                    System.out.print(c[i][j].getNumber() + "  ");
                }else {
                    System.out.print(c[i][j].getCondition() + "  ");
                }
            }
            System.out.println();
        }
        */
        launch(args);
    }
}
