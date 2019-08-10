package sample;

import Game.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Controller {
    private int Width = 600;
    private int Height = 600;
    private int TileSize = 40;
    private int TileShift = 7;

    public void window(Stage primaryStage)throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        Board board = new Board();
        Group gr = createRectangle(board);


        gr.setOnMouseClicked((event -> {
            int x = (int)event.getSceneX();
            int y = (int)event.getSceneY();

            x = x / TileSize;
            y = y / TileSize;

            x = x*TileSize+TileShift;
            y = y*TileSize+TileShift;

            System.out.println(x + "  " + y);

            Rectangle r = new Rectangle();

            r.setX(x);
            r.setY(y);
            r.setHeight(TileSize - 2);
            r.setWidth(TileSize - 2);
            r.setFill(Color.AQUA);

            gr.getChildren().add(r);
        }));

        primaryStage.setTitle("Minesweeper");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(gr, Width, Height));
        primaryStage.show();
    }

    private Group createRectangle(Board board){
        Group group = new Group();

        int x = TileShift;
        int y = TileShift;

        for(int i = 0; i < board.getX(); ++i){
            for(int j = 0; j < board.getY(); ++j) {
                Rectangle r = new Rectangle();

                r.setX(x);
                r.setY(y);
                r.setHeight(TileSize - 2);
                r.setWidth(TileSize - 2);
                r.setFill(Color.DARKGRAY);

                group.getChildren().add(r);

                y += TileSize;
            }
            x += TileSize;
            y = TileShift;
        }
        return group;
    }

}
