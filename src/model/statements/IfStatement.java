package model.statements;

import model.PrgState;
import model.interfaces.IExpression;
import model.interfaces.IStatement;

public class IfStatement implements IStatement {
    IExpression expression;
    IStatement trueStatement;
    IStatement falseStatement;

    public IfStatement(IExpression exp, IStatement ts, IStatement fs){
        this.expression = exp;
        this.trueStatement = ts;
        this.falseStatement = fs;
    }

    @Override
    public PrgState execute(PrgState p) {
        if(expression.evaluate(p.getTable(),p.getHeap()) != 0)
            p = trueStatement.execute(p);
        else
            p = falseStatement.execute(p);
        return null;
    }

    @Override
    public String toString(){
        return "if(" + expression + ")then\n" +  trueStatement + "\nelse" + falseStatement;
    }

}
