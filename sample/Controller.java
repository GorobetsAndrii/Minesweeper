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
import javafx.scene.text.Text;

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
            OnClick(gr,board,(int)event.getSceneX()/TileSize,(int)event.getSceneY()/TileSize);
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

    private void OnClick(Group group , Board b , int x , int y ){
        if(b.board[x][y].getCondition() == ConditionCell.BOMB){
            Text text = new Text("X");

            int x1 = x * TileSize + TileShift;
            int y1 = y * TileSize + TileShift;

            x = x*TileSize + TileSize/2 + 2;
            y = y*TileSize + TileSize - TileShift;

            Rectangle r = new Rectangle();

            r.setX(x1);
            r.setY(y1);
            r.setHeight(TileSize - 2);
            r.setWidth(TileSize - 2);
            r.setFill(Color.WHITE);

            text.setX(x);
            text.setY(y);

            group.getChildren().addAll(r,text);

        }else if(b.board[x][y].getCondition() == ConditionCell.NUMBER) {
            Text text = new Text(Integer.toString(b.board[x][y].getNumber()));

            int x1 = x * TileSize + TileShift;
            int y1 = y * TileSize + TileShift;

            x = x*TileSize + TileSize/2 + 2;
            y = y*TileSize + TileSize - TileShift;

            Rectangle r = new Rectangle();

            r.setX(x1);
            r.setY(y1);
            r.setHeight(TileSize - 2);
            r.setWidth(TileSize - 2);
            r.setFill(Color.WHITE);

            text.setX(x);
            text.setY(y);

            group.getChildren().addAll(r,text);
        }else{
            x = x * TileSize + TileShift;
            y = y * TileSize + TileShift;

            Rectangle r = new Rectangle();

            r.setX(x);
            r.setY(y);
            r.setHeight(TileSize - 2);
            r.setWidth(TileSize - 2);
            r.setFill(Color.AQUA);

            group.getChildren().add(r);
        }
    }
}
