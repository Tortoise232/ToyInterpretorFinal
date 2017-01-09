package model.statements;

import java.io.BufferedReader;
import java.io.IOException;

import model.PrgState;
import model.interfaces.IExpression;
import model.interfaces.IStatement;

public class readFile implements IStatement {
    IExpression id;
    String name;
    public readFile(IExpression id, String name){
        this.id = id;
        this.name = name;
    }
    @Override
    public PrgState execute(PrgState p) {
        BufferedReader br = p.getFileTable().getReader(id.evaluate(p.getTable(), p.getHeap()));
        try {

            int value = Integer.parseInt(br.readLine());
            p.symbolTable.add(name, value);
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString(){
        return "fread(" + name + "," + id + ")";
    }

}