package tests;
import model.*;
import model.expressions.*;
import model.interfaces.*;
public class testExpressions {
    public PrgState p;
    public testExpressions(){
        this.p = new PrgState();
    }
    public void run(){
        testArithmetic();
        testVariable();
        testBool();
        testReadHeap();
    }

    public void testArithmetic(){
        ArithmeticExpression exp1 = new ArithmeticExpression('+', new ConstExpression(4), new ConstExpression(3));
        assert(exp1.evaluate(p.getTable(), p.getHeap()) == 7);
        exp1 = new ArithmeticExpression('-', new ConstExpression(4), new ConstExpression(3));
        assert(exp1.evaluate(p.getTable(), p.getHeap()) == 1);
        exp1 = new ArithmeticExpression('*', new ConstExpression(4), new ConstExpression(3));
        assert(exp1.evaluate(p.getTable(), p.getHeap()) == 12);
        exp1 = new ArithmeticExpression('/', new ConstExpression(12), new ConstExpression(3));
        assert(exp1.evaluate(p.getTable(), p.getHeap()) == 4);
        exp1 = new ArithmeticExpression('%', new ConstExpression(12), new ConstExpression(5));
        assert(exp1.evaluate(p.getTable(), p.getHeap()) == 2);
    }

    public void testVariable(){
        VariableExpression exp = new VariableExpression("Alpha");
        p.getTable().add("Alpha", 4);
        assert(exp.evaluate(p.getTable(), p.getHeap()) == 4);
        exp = new VariableExpression("Teo");
        p.getTable().add("Teo", -1);
        assert(exp.evaluate(p.getTable(), p.getHeap()) == -1);
    }

    public void testBool(){
        IExpression a = new equalExpression(new ConstExpression(4), new ConstExpression(5));
        assert(a.evaluate(p.getTable(), p.getHeap()) == 0);
        a = new notEqualExpression(new ConstExpression(4), new ConstExpression(5));
        assert(a.evaluate(p.getTable(), p.getHeap()) == 1);
        a = new greaterOrEqualExpression(new ConstExpression(4), new ConstExpression(5));
        assert(a.evaluate(p.getTable(), p.getHeap()) == 0);
        a = new greaterThanExpression(new ConstExpression(4), new ConstExpression(5));
        assert(a.evaluate(p.getTable(), p.getHeap()) == 0);
        a = new smallerOrEqualExpression(new ConstExpression(4), new ConstExpression(5));
        assert(a.evaluate(p.getTable(), p.getHeap()) == 1);
        a = new smallerThanExpression(new ConstExpression(4), new ConstExpression(5));
        assert(a.evaluate(p.getTable(), p.getHeap()) == 1);
    }

    public void testReadHeap(){
        p.getTable().add("Test", 1);
        p.getHeap().newElem(0);
        IExpression a = new VariableExpression("Test");
        assert(a.evaluate(p.getTable(), p.getHeap()) == 0);
    }
}