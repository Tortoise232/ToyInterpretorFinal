package model;
import java.io.*;
import java.util.concurrent.Callable;

import com.sun.xml.internal.bind.v2.model.core.ID;
import model.interfaces.*;


public class PrgState implements Serializable, Callable{
    private static final long serialVersionUID = -5116545090005092303L;
    int id = 1;
    public static int maxID = 1;
    protected ILatchTable<Integer,Integer> latchTable;
    public  IDictionary<String,Integer> symbolTable;
    protected  IStak<IStatement> stack;
    public  IList<Integer> output;
    protected  FileTable fileTable;
    public IHeap heap;
    protected IStak<MyStack<IStatement>> stacks;
    protected ProcTable<String,Procedure> procTable;
    public boolean isNotComplete(){
        return !stack.isEmpty();
    }

    public void setStack(IStak<IStatement> stack){
        this.stack = stack;
    }

    public void setTable(IDictionary<String,Integer> dictionary){
        this.symbolTable = dictionary;
    }

    public void setOutput(IList<Integer> output){
        this.output = output;
    }

    public void setFileTable(FileTable table){
        this.fileTable = table;
    }

    public void setHeap(IHeap newHeap){
        this.heap = newHeap;
    }

    public void setStacks(IStak<MyStack<IStatement>> stacks){ this.stacks = stacks; }

    public void setProcTable(ProcTable<String,Procedure> newProcTable){ this.procTable = newProcTable;}

    public void setLatchTable(LatchTable newLatchTable) { this.latchTable = newLatchTable;}

    public IHeap getHeap(){
        return this.heap;
    }

    public IStak<MyStack<IStatement>> getStacks() { return stacks;}

    public IStak<IStatement> getStack(){
        return stack;
    }

    public IDictionary<String,Integer> getTable(){
        return symbolTable;
    }

    public IList<Integer> getOutput(){
        return output;
    }

    public FileTable getFileTable(){
        return fileTable;
    }

    public ProcTable getProcTable() {return procTable;}

    public ILatchTable getLatchTable() {return latchTable;}

    public PrgState(){
        this.heap = new MyHeap();
        this.symbolTable = new MyDictionary();
        this.stacks = new MyStack();
        this.stack = new MyStack(); //top of the stacks
        this.output = new MyList();
        this.procTable = new ProcTable<>();
        this.fileTable = new FileTable();
        this.latchTable = new LatchTable();
    }

    public PrgState(IDictionary<String,Integer> dict,
                    IStak<IStatement> stack,
                    IList<Integer> output,
                    FileTable fileTable,
                    IHeap heap,
                    ProcTable<String,Procedure> procTable,
                    ILatchTable latchTable,
                    int id){
        this.heap = heap;
        this.symbolTable = dict;
        this.stacks = null;
        this.stack = stack;
        this.output = output;
        this.fileTable = fileTable;
        this.procTable = procTable;
        this.latchTable = latchTable;
        this.id = id;
    }

    public PrgState executeOneStep(){
        if(this.stack.isEmpty())
            try {
                throw new Exception("Program ended");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        IStatement currentStatement = this.stack.pop();
        return currentStatement.execute(this);
    }
    public int getID(){
        return id;
    }
    @Override
    public String toString(){
        String result = new String();
        result += "ID: " + id + "\n";
        result += "Stack(" + stack.size() + "): " + stack + "\n";
        result += "SymbolTable(" + symbolTable.size() + "): " + symbolTable + "\n";
        result += "Output(" + output.size() + "): " + output + "\n";
        result += "FileTable(" + fileTable.size() + "): " + fileTable + "\n";
        result += "LatchTable: " + latchTable + "\n";
        result += "Heap(" + heap.size() + "): " + heap + "\n";
        return result;
    }

    @Override
    public Object call() throws Exception {
        return this.executeOneStep();
    }


}
