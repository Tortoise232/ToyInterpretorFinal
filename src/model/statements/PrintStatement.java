package model.statements;

import model.PrgState;
import model.interfaces.IExpression;
import model.interfaces.IStatement;

public class PrintStatement implements IStatement{
    IExpression expression;
    public PrintStatement(IExpression exp){
        this.expression = exp;
    }

    @Override
    public PrgState execute(PrgState p) {
        p.output.add(expression.evaluate(p.getTable(), p .getHeap()));
        return null;
    }

    @Override
    public String toString(){
        return expression.toString();
    }

}
