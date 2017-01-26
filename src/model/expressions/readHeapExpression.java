package model.expressions;

import model.interfaces.*;


public class readHeapExpression implements IExpression {
    String varName;

    public readHeapExpression(String name){
        this.varName = name;
    }
    @Override
    public int evaluate(IDictionary<String, Integer> myDic, IHeap myHeap) {
        int addressInHeap = myDic.getValue(varName);
        return myHeap.readHeap(addressInHeap);
    }

    @Override
    public String toString(){
        return "readHeap(" + varName + ")";
    }

}

