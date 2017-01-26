package model.expressions;

import model.interfaces.IDictionary;
import model.interfaces.IExpression;
import model.interfaces.IHeap;

public class equalExpression implements IExpression {
    IExpression a;
    IExpression b;
    public equalExpression (IExpression a, IExpression b){
        this.a = a;
        this.b = b;
    }
    @Override
    public int evaluate(IDictionary<String, Integer> myDic, IHeap myHeap) {
        if( a.evaluate(myDic, myHeap) == b.evaluate(myDic, myHeap))
            return 1;
        return 0;

    }
    @Override
    public String toString(){
        return "" + a + "==" + b;
    }
}
