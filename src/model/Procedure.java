package model;

import model.interfaces.IStatement;

import java.util.ArrayList;

/**
 * Created by Petean Mihai on 1/25/2017.
 */
public class Procedure {
    String name;
    private ArrayList<String> parameters;
    IStatement statement;
    public Procedure(String name, ArrayList<String> params, IStatement stmt){
        this.name = name;
        this.parameters = params;
        this.statement = stmt;
    }

    public String getName(){
        return name;
    }

    public void setName(String newName){
        this.name = newName;
    }

    public ArrayList<String> getParameters(){
        return this.parameters;
    }

    public void setParameters(ArrayList<String> newParams){
        this.parameters = newParams;
    }

    public IStatement getStatement(){
        return this.statement;
    }

    public void setStatement(IStatement newState){
        this.statement = newState;
    }

    public String toString(){
        return "" + name + " " + parameters + " " + statement;
    }
}
