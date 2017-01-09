package model.statements;

import model.PrgState;
import model.interfaces.IStatement;

public class writeHeapStatement implements IStatement{
    String varName;
    int newValue;
    @Override
    public PrgState execute(PrgState p) {
        int addressInHeap = p.getTable().getValue(varName);
        p.getHeap().writeHeap(addressInHeap, newValue);
        return null;
    }

}
