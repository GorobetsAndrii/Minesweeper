package Game;

import java.lang.Math.*;

public class Board {
    private int X = 15;
    private int Y = 15;
    private int NumberOfBombs = 36;
    public Cell[][] board;

    public Board(){
        board = new Cell[X][Y];

        for(int i = 0; i < X; ++i) {
            for (int j = 0; j < Y; ++j) {
                board[i][j] = new Cell();
            }
        }

        fillBoard(createBombs());
        addNumbers();
    }

    public Cell[][] getBoard(){
        return board;
    }

    private int[] createBombs(){
        int [] bombs = new int[NumberOfBombs];
        int counter = 0;
        int rnd = 0;
        boolean tmp = true;

        while(counter < NumberOfBombs){
            rnd = (int)(Math.random()*X*Y);

            for(int i : bombs){
                if(i == rnd) tmp = false;
            }

            if(tmp){
                bombs[counter] = rnd;
                counter++;
            }

            tmp = true;
        }
        return bombs;
    }

    private void fillBoard(int [] bombs){
        int x = 0;
        int y = 0;

        for(int num : bombs){
            x = num/X;
            y = num%Y;
            board[x][y].setCondition(ConditionCell.BOMB);
        }
    }

    private void addNumbers(){
        int count = 0;

        for(int i = 0; i < X; ++i) {
            for (int j = 0; j < Y; ++j) {
                if(board[i][j].getCondition() == ConditionCell.BOMB) continue;

                if(((i>0 && j>0) ? board[i-1][j-1].getCondition() : ConditionCell.EMPTY) == ConditionCell.BOMB) count++;
                if((i>0 ? board[i-1][j].getCondition() : ConditionCell.EMPTY) == ConditionCell.BOMB) count++;
                if(((i>0 && j<Y-1) ? board[i-1][j+1].getCondition() : ConditionCell.EMPTY) == ConditionCell.BOMB) count++;

                if((j>0 ? board[i][j-1].getCondition() : ConditionCell.EMPTY) == ConditionCell.BOMB) count++;
                if((j<Y-1 ? board[i][j+1].getCondition() : ConditionCell.EMPTY) == ConditionCell.BOMB) count++;

                if(((i<X-1 && j>0) ? board[i+1][j-1].getCondition() : ConditionCell.EMPTY) == ConditionCell.BOMB) count++;
                if((i<X-1 ? board[i+1][j].getCondition() : ConditionCell.EMPTY) == ConditionCell.BOMB) count++;
                if(((i<X-1 && j<Y-1) ? board[i+1][j+1].getCondition() : ConditionCell.EMPTY) == ConditionCell.BOMB) count++;


                board[i][j].setNumber(count);
                count = 0;
            }
        }
    }

    public int getX(){return X;}

    public int getY(){return Y;}
}
