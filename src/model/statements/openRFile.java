package model.statements;

import model.PrgState;
import model.interfaces.IStatement;

public class openRFile implements IStatement{
    public String filename;
    public String fileID;
    public openRFile(String fileName, String fileID){
        this.filename = fileName;
        this.fileID = fileID;
    }
    @Override
    public PrgState execute(PrgState p) {
        int id = p.getFileTable().addValue(filename);
        p.getTable().add(fileID, id);
        return null;
    }
    @Override
    public String toString(){
        return "fopen(" + filename + "," + fileID + ")";
    }
}
