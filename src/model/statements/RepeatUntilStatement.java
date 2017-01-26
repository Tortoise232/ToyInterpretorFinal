package model.statements;

import model.PrgState;
import model.expressions.ConstExpression;
import model.expressions.equalExpression;
import model.expressions.notEqualExpression;
import model.interfaces.IExpression;
import model.interfaces.IStatement;

/**
 * Created by Petean Mihai on 1/26/2017.
 */
public class RepeatUntilStatement implements IStatement {
    protected IStatement myStatement;
    protected IExpression myExpr;

    public RepeatUntilStatement(IStatement newState, IExpression newExp){
        myStatement = newState;
        myExpr = newExp;
    }

    @Override
    public PrgState execute(PrgState p) {

        IStatement whileState = new WhileStatement(myStatement,new equalExpression(new ConstExpression(0),myExpr));
        p.getStack().push(whileState);
        return null;
    }
    @Override
    public String toString() {
        return "" + "repeat " + myStatement + " until " + myExpr;
    }
}
