package model.statements;

import model.PrgState;
import model.interfaces.IStatement;

/**
 * Created by Petean Mihai on 1/25/2017.
 */
public class SleepStatement implements IStatement{
    int time;

    public SleepStatement(int timeToSleep){
        time = timeToSleep;
    }

    @Override
    public PrgState execute(PrgState p) {
        if(time > 0)
            p.getStack().push(new SleepStatement(time - 1));
        return null;
    }

    public String toString(){
        return "sleep(" + time + ")";
    }
}
