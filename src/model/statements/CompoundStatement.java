package model.statements;

import model.PrgState;
import model.interfaces.IStatement;

public class CompoundStatement implements IStatement{
    IStatement firstStatement;
    IStatement secondStatement;

    public CompoundStatement(IStatement fs, IStatement ss){
        this.firstStatement = fs;
        this.secondStatement = ss;
    }


    @Override
    public PrgState execute(PrgState p) {
        p.getStack().push(secondStatement);
        p.getStack().push(firstStatement);
        return null;
    }

    @Override
    public String toString(){
        return "{ " + firstStatement  + "; " + secondStatement + " }";
    }


}
