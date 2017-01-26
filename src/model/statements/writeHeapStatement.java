package model.statements;

import model.PrgState;
import model.expressions.ConstExpression;
import model.interfaces.IExpression;
import model.interfaces.IStatement;

public class writeHeapStatement implements IStatement{
    String varName;
    int newValue = -1;
    IExpression newValueAsExpr = null;
    public writeHeapStatement(String var, int value){
        varName = var;
        newValue = value;
    }

    public writeHeapStatement(String var, IExpression value){
        varName = var;
        newValueAsExpr = value;
    }

    @Override
    public PrgState execute(PrgState p) {

        int addressInHeap = p.getHeap().generateLocation();

        try {
            addressInHeap = p.getTable().getValue(varName);
        }catch (Exception e){
            p.getTable().add(varName,addressInHeap);
        };
        if (newValue != -1)
            p.getHeap().writeHeap(addressInHeap, newValue);
        else
            newValue = newValueAsExpr.evaluate(p.getTable(), p.getHeap());
            p.getHeap().writeHeap(addressInHeap,newValue);
        return null;
    }

    @Override
    public String toString(){
        if(newValueAsExpr != null)
            return "writeHeap(" + varName + "," + newValueAsExpr +")";
        else
            return "writeHeap(" + varName + "," + newValue +")";
    }
}


