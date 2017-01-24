package model.statements;

import model.MyDictionary;
import model.MyStack;
import model.PrgState;
import model.interfaces.IStak;
import model.interfaces.IStatement;

public class ForkStatement implements IStatement {
    IStatement myState;
    public ForkStatement(IStatement statement){
        this.myState = statement;
    }
    @Override
    public PrgState execute(PrgState p) {
        IStak<IStatement> myStack = new MyStack<IStatement>();
        MyDictionary<String, Integer> newDict = new MyDictionary<String, Integer>((MyDictionary<String, Integer>)p.getTable());
        myStack.push(myState);
        PrgState newPrgState = new PrgState(newDict,
                myStack,
                p.getOutput(),
                p.getFileTable(),
                p.getHeap(),
                p.getID() + 1);
        return newPrgState;
    }

    @Override
    public String toString(){
        return "fork(" + myState.toString() + ")";
    }
}