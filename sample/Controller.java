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
        Group gr = createRectangles(board);


        gr.setOnMouseClicked((event -> {
            onClick(gr,board,(int)event.getSceneX()/TileSize,(int)event.getSceneY()/TileSize);
        }));

        primaryStage.setTitle("Minesweeper");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(gr, Width, Height));
        primaryStage.show();
    }

    private Group createRectangles(Board board){
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

    private void onClick(Group group , Board b , int x , int y ){
        if(b.board[x][y].getCondition() == ConditionCell.BOMB){
            b.board[x][y].setMode(false);
            Text text = new Text("X");

            int x1 = x * TileSize + TileShift;
            int y1 = y * TileSize + TileShift;

            x = x*TileSize + TileSize/2 + 2;
            y = y*TileSize + TileSize - TileShift;

            text.setX(x);
            text.setY(y);
            text.setFill(Color.DARKRED);

            group.getChildren().addAll(createTile(x1,y1),text);

        }else if(b.board[x][y].getCondition() == ConditionCell.NUMBER) {
            b.board[x][y].setMode(false);
            Text text = new Text(Integer.toString(b.board[x][y].getNumber()));

            int x1 = x * TileSize + TileShift;
            int y1 = y * TileSize + TileShift;

            x = x*TileSize + TileSize/2 + 2;
            y = y*TileSize + TileSize - TileShift;

            text.setX(x);
            text.setY(y);

            group.getChildren().addAll(createTile(x1,y1),text);
        }else{
            destroyEmptyTile(group,b,x,y);
        }
    }

    private void destroyEmptyTile(Group group,Board b, int x , int y){
        b.board[x][y].setMode(false);

        int x1 = x * TileSize + TileShift;
        int y1 = y * TileSize + TileShift;

        if(b.board[x][y].getCondition() == ConditionCell.NUMBER){
            Text text = new Text(Integer.toString(b.board[x][y].getNumber()));

            int xText = x*TileSize + TileSize/2 + 2;
            int yText = y*TileSize + TileSize - TileShift;

            text.setX(xText);
            text.setY(yText);

            group.getChildren().addAll(createTile(x1,y1),text);

            return;
        }
        
        group.getChildren().add(createTile(x1,y1));


        if(((x>0 && y>0) ? b.board[x-1][y-1].getCondition() : ConditionCell.BOMB) == ConditionCell.EMPTY ||
                ((x>0 && y>0) ? b.board[x-1][y-1].getCondition() : ConditionCell.BOMB) == ConditionCell.NUMBER ){
            if(b.board[x-1][y-1].isClose()) destroyEmptyTile(group,b,x-1,y-1);
        }
        if((x>0 ? b.board[x-1][y].getCondition() : ConditionCell.BOMB) == ConditionCell.EMPTY ||
                (x>0 ? b.board[x-1][y].getCondition() : ConditionCell.BOMB) == ConditionCell.NUMBER){
            if(b.board[x-1][y].isClose()) destroyEmptyTile(group,b,x-1,y);
        }
        if(((x>0 && y<(b.getY()-1)) ? b.board[x-1][y+1].getCondition() : ConditionCell.BOMB) == ConditionCell.EMPTY ||
                ((x>0 && y<(b.getY()-1)) ? b.board[x-1][y+1].getCondition() : ConditionCell.BOMB) == ConditionCell.NUMBER){
            if(b.board[x-1][y+1].isClose()) destroyEmptyTile(group,b,x-1,y+1);
        }

        if((y>0 ? b.board[x][y-1].getCondition() : ConditionCell.BOMB) == ConditionCell.EMPTY ||
                (y>0 ? b.board[x][y-1].getCondition() : ConditionCell.BOMB) == ConditionCell.NUMBER){
            if(b.board[x][y-1].isClose()) destroyEmptyTile(group,b,x,y-1);
        }
        if((y<(b.getY()-1) ? b.board[x][y+1].getCondition() : ConditionCell.BOMB) == ConditionCell.EMPTY ||
                (y<(b.getY()-1) ? b.board[x][y+1].getCondition() : ConditionCell.BOMB) == ConditionCell.NUMBER){
            if(b.board[x][y+1].isClose()) destroyEmptyTile(group,b,x,y+1);
        }

        if(((x<(b.getX()-1) && y>0) ? b.board[x+1][y-1].getCondition() : ConditionCell.BOMB) == ConditionCell.EMPTY ||
                ((x<(b.getX()-1) && y>0) ? b.board[x+1][y-1].getCondition() : ConditionCell.BOMB) == ConditionCell.NUMBER){
            if(b.board[x+1][y-1].isClose()) destroyEmptyTile(group,b,x+1,y-1);
        }
        if((x<(b.getX()-1) ? b.board[x+1][y].getCondition() : ConditionCell.BOMB) == ConditionCell.EMPTY ||
                (x<(b.getX()-1) ? b.board[x+1][y].getCondition() : ConditionCell.BOMB) == ConditionCell.NUMBER){
            if(b.board[x+1][y].isClose()) destroyEmptyTile(group,b,x+1,y);
        }
        if(((x<(b.getX()-1) && y<(b.getY()-1)) ? b.board[x+1][y+1].getCondition() : ConditionCell.BOMB) == ConditionCell.EMPTY ||
                ((x<(b.getX()-1) && y<(b.getY()-1)) ? b.board[x+1][y+1].getCondition() : ConditionCell.BOMB) == ConditionCell.NUMBER){
            if(b.board[x+1][y+1].isClose()) destroyEmptyTile(group,b,x+1,y+1);
        }
    }

    private Rectangle createTile(int x , int y){
        Rectangle r = new Rectangle();

        r.setX(x);
        r.setY(y);
        r.setHeight(TileSize - 2);
        r.setWidth(TileSize - 2);
        r.setFill(Color.WHITE);

        return r;
    }
}
