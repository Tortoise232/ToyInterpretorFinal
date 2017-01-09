package model.expressions;

import model.interfaces.*;


public class readHeapExpression implements IExpression {
    String varName;
    @Override
    public int evaluate(IDictionary<String, Integer> myDic, IHeap myHeap) {
        int addressInHeap = myDic.getValue(varName);
        return myHeap.readHeap(addressInHeap);
    }

}

