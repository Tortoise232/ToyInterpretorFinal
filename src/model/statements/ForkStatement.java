package model.statements;

import model.MyDictionary;
import model.MyStack;
import model.PrgState;
import model.interfaces.IStak;
import model.interfaces.IStatement;

import java.util.Iterator;

public class ForkStatement implements IStatement {
    IStatement myState;
    public ForkStatement(IStatement statement){
        this.myState = statement;
    }
    @Override
    public PrgState execute(PrgState p) {
        IStak myStacks = new MyStack<>();
        //for (Iterator<IStak<IStatement>> it = p.getStacks().getAll(); it.hasNext(); ) {
       //     myStacks.push(it.next());

       // }
        MyDictionary<String, Integer> newDict = new MyDictionary<String, Integer>((MyDictionary<String, Integer>) p.getTable());
        myStacks.push(myState);
        PrgState newPrgState = new PrgState(newDict,
                myStacks,
                p.getOutput(),
                p.getFileTable(),
                p.getHeap(),
                p.getProcTable(),
                p.getLatchTable(),
                ++ p.maxID);
        return newPrgState;
    }

    @Override
    public String toString(){
        return "fork(" + myState.toString() + ")";
    }
}