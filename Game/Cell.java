package Game;

public class Cell {
    private ConditionCell ccl;
    private int number;
    private boolean mode = true;

    public Cell(ConditionCell c){
        ccl = c;
    }

    public Cell(){
        ccl = ConditionCell.EMPTY;
    }

    public ConditionCell getCondition(){
        return ccl;
    }

    public void setCondition(ConditionCell c){
        ccl = c;
    }

    public int getNumber(){
        return number;
    }

    public void setNumber(int num){
        number = num;
        ccl = ConditionCell.NUMBER;
    }

    public boolean isClose(){
        return mode;
    }

    public void setMode(boolean b){
        mode = b;
    }
}
