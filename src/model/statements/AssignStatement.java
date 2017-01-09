package model.statements;

import model.PrgState;
import model.interfaces.IDictionary;
import model.interfaces.IExpression;
import model.interfaces.IStatement;

public class AssignStatement implements IStatement{
    String var;
    IExpression expression;

    public AssignStatement(String var,IExpression expression){
        this.var = var;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState p) {
        p.getTable().add(var, expression.evaluate(p.getTable(), p.getHeap()));
        return null;
    }

    @Override
    public String toString(){
        return "" + var + " = " + expression;
    }


}
