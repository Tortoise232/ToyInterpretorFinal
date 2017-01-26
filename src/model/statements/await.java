package model.statements;

import model.PrgState;
import model.interfaces.IStatement;

/**
 * Created by Petean Mihai on 1/26/2017.
 */
public class await implements IStatement {
    private String var;

    public await(String var){
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState p) {
        if((int)p.getLatchTable().getLatch(p.getTable().getValue(var)) != 0)
            p.getStack().push(this);
        return null;
    }

    @Override
    public String toString(){
        return "await(" + var + ")";
    }
}
