package model.interfaces;
import java.io.Serializable;

import model.interfaces.IDictionary;
public interface IExpression extends Serializable{
    //having given a dictionary and heap, returns the integer value associated to an expression
    public int evaluate(IDictionary<String,Integer> myDic, IHeap myHeap);
}


