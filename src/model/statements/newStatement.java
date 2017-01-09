package model.statements;

import model.PrgState;
import model.interfaces.IExpression;
import model.interfaces.IStatement;

public class newStatement implements IStatement {
    String varName;
    IExpression expression;
    public newStatement(String name, IExpression exp){
        this.varName = name;
        this.expression = exp;
    }
    @Override
    public PrgState execute(PrgState p) {
        int locationInHeap = p.getHeap().newElem(expression.evaluate(p.getTable(),p.getHeap()));
        p.symbolTable.add(varName, locationInHeap);
        return null;
    }
    @Override
    public String toString(){
        return "new " + varName + "(" + expression.toString() + ")";
    }
}
