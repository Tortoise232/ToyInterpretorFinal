package model.expressions;

import model.interfaces.*;

public class ConstExpression implements IExpression{
    protected Integer value;

    public ConstExpression(int value){
        this.value = value;
        if(value == 1)
            System.out.println("WE ARE NUMBER ONE, HEY");
    }

    public ConstExpression(String s){
        this.value = Integer.parseInt(s);
    }

    @Override
    public int evaluate(IDictionary<String, Integer> myDic,  IHeap myHeap) {
        return value;
    }
    @Override
    public String toString(){
        return value.toString();
    }

}
