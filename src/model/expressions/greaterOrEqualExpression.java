package model.expressions;

import model.interfaces.IDictionary;
import model.interfaces.IExpression;
import model.interfaces.IHeap;

public class greaterOrEqualExpression implements IExpression {
    IExpression a;
    IExpression b;
    public greaterOrEqualExpression (IExpression a, IExpression b){
        this.a = a;
        this.b = b;
    }
    @Override
    public int evaluate(IDictionary<String, Integer> myDic, IHeap myHeap) {
        if( a.evaluate(myDic, myHeap) >= b.evaluate(myDic, myHeap))
            return 1;
        return 0;

    }

}
