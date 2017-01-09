package model.statements;

import model.PrgState;
import model.interfaces.*;

public class WhileStatement implements IStatement {
    IStatement myStatement;
    IExpression toEval;

    public WhileStatement(IStatement myState, IExpression exp){
        this.myStatement = myState;
        this.toEval = exp;
    }
    @Override
    public PrgState execute(PrgState p) {
        if(toEval.evaluate(p.getTable(), p.getHeap()) != 0)
        {
            p.getStack().push(this);
            p.getStack().push(myStatement);
        }
        return null;
    }

    @Override
    public String toString(){
        return "while(" + toEval.toString() + ") do(" + myStatement.toString() + ")";

    }

}
