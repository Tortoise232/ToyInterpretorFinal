package model.expressions;

import model.interfaces.*;

public class ArithmeticExpression implements IExpression{

    public char operator;
    public IExpression firstOperand;
    public IExpression secondOperand;
    public ArithmeticExpression(char operator, IExpression fo, IExpression so){
        this.operator = operator;
        this.firstOperand = fo;
        this.secondOperand = so;
    }
    @Override
    public int evaluate(IDictionary<String, Integer> myDic, IHeap myHeap) {
        int resultFirst = firstOperand.evaluate(myDic, myHeap);
        int resultSecond = secondOperand.evaluate(myDic, myHeap);
        switch(operator){
            case '+' : return resultFirst + resultSecond;
            case '-' : return resultFirst - resultSecond;
            case '*' : return resultFirst * resultSecond;
            case '/' : return resultFirst / resultSecond;
            case '%' : return resultFirst % resultSecond;
            default:
                throw new RuntimeException("Invalid operator!");
        }
    }
    @Override
    public String toString(){
        return "(" + firstOperand + " " + operator + " " + secondOperand + ")";
    }

}
