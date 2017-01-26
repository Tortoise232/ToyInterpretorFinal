package model.statements;

import model.PrgState;
import model.interfaces.IExpression;
import model.interfaces.IStatement;

/**
 * Created by Petean Mihai on 1/26/2017.
 */
public class newLatch implements IStatement {
    private String name;
    private IExpression value;

    public newLatch(String newName, IExpression newExp) {
        name = newName;
        value = newExp;
    }

    @Override
    public PrgState execute(PrgState p) {
        int intValue = value.evaluate(p.getTable(), p.getHeap());
        int id = p.getLatchTable().getID();
        p.getLatchTable().addLatch(id,intValue);
        p.getTable().add(name,id);
        return null;
    }

    public String toString(){
        return "newLatch(" + name + "," + value + ")";
    }
}
