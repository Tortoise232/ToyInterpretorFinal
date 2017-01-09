package model;
import java.io.*;
import java.util.concurrent.Callable;

import model.interfaces.*;
public class PrgState implements Serializable, Callable{
    private static final long serialVersionUID = -5116545090005092303L;
    int id = 1;
    public  IDictionary<String,Integer> symbolTable;
    protected  IStak<IStatement> stack;
    public  IList<Integer> output;
    protected  FileTable fileTable;
    public IHeap heap;
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

    public IHeap getHeap(){
        return this.heap;
    }

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

    public PrgState(){
        this.heap = new MyHeap();
        this.symbolTable = new MyDictionary();
        this.stack = new MyStack();
        this.output = new MyList();
        this.fileTable = new FileTable();
    }

    public PrgState(IDictionary<String,Integer> dict,
                    IStak<IStatement> stack,
                    IList<Integer> output,
                    FileTable fileTable,
                    IHeap heap, int id){
        this.heap = heap;
        this.symbolTable = dict;
        this.stack = stack;
        this.output = output;
        this.fileTable = fileTable;
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
        result += "Heap(" + heap.size() + "): " + heap + "\n";
        return result;
    }

    @Override
    public Object call() throws Exception {
        return this.executeOneStep();
    }


}
