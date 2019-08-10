package Game;

public class Cell {
    private ConditionCell ccl;
    private int number;
    public boolean mode = false;

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
}
