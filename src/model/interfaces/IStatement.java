package model.interfaces;

import java.io.Serializable;

import model.PrgState;

public interface IStatement extends Serializable{
    //executes the 1st statement in the execution stack for the given program state
    public PrgState execute(PrgState p);

}
