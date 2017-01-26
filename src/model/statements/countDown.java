package model.statements;

import model.PrgState;
import model.interfaces.IStatement;

/**
 * Created by Petean Mihai on 1/26/2017.
 */
public class countDown implements IStatement {
    private String name;

    public countDown(String name){
        this.name = name;
    }
    @Override
    public PrgState execute(PrgState p){
        int address = -1;
        try {
             address = p.getTable().getValue(name);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        int value = (int)p.getLatchTable().getLatch(address);
        if(value > 0) {
            p.getLatchTable().addLatch(address, value - 1);
            p.getOutput().add(p.getID());
        }
        return null;
    }

    public String toString(){
        return "countDown(" + name + ")";
    }

}
