package tests;

import model.PrgState;
import model.statements.*;
import model.expressions.*;
import model.interfaces.*;

public class testStatements {
    public PrgState p;
    public testStatements(){
        this.p = new PrgState();
    }
    public void run(){
        testAssignStatement();
    }

    public void testAssignStatement(){
        IStatement statement = new AssignStatement("Alpha", new ConstExpression(5));
        statement.execute(p);
        VariableExpression exp = new VariableExpression("Alpha");
        assert( exp.evaluate(p.getTable(), p.getHeap()) == 5);
        statement = new AssignStatement("Alpha", new ConstExpression(10));
        statement.execute(p);
        assert (exp.evaluate(p.getTable(), p.getHeap()) == 10);
    }

    public void test(){}
}
