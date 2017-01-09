package model.expressions;

import model.interfaces.*;


public class VariableExpression implements IExpression{

    protected String name;
    public VariableExpression(String name){
        this.name = name;
    }
    @Override
    public int evaluate(IDictionary<String, Integer> myDic,  IHeap myHeap) {
        return myDic.getValue(name);
    }
    @Override
    public String toString(){
        return name;
    }
}
