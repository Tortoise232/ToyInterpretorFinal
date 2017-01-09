package sample;

import controller.Controller;
import controller.GUIController;
import tests.*;
import repository.Repository;
import model.*;
import javafx.application.Application;
import model.expressions.*;
import model.interfaces.*;
import model.statements.*;
public class Main {

    public static void main(String[] args) {
        System.out.println("STARTING PROGRAM");
        IExpression daddysfirstexpression = new VariableExpression("v");
        IStatement printState = new PrintStatement(daddysfirstexpression);
        IExpression constExpr = new ConstExpression(2);
        IStatement assignState = new AssignStatement("v",constExpr);
        IStatement compState = new CompoundStatement(assignState, printState);
        IStatement openFileState = new openRFile("file.txt","VarFromFile");

        //heap code
        IStatement newStuff = new newStatement("Valcea",constExpr);
        IStatement newStuff2 = new newStatement("Eroina",constExpr);

        IStatement forkState = new ForkStatement(compState);
        IStatement readFile = new readFile(new ConstExpression(0),"MyVar");
        MyHeap heap  = new MyHeap();
        MyStack<IStatement> newStak = new MyStack<IStatement>();
        MyDictionary<String, Integer> newDict = new MyDictionary<String, Integer>();
        MyList<Integer> newList = new MyList<Integer>();
        FileTable newFileTable = new FileTable();
        PrgState newPrgState = new PrgState(newDict, newStak, newList, newFileTable, heap, 1);
        Repository newRepo = new Repository(true);
        newStak.push(new WhileStatement(new AssignStatement("v",new ConstExpression(0)),daddysfirstexpression));
        newStak.push(newStuff);
        newStak.push(newStuff2);
        //newStak.push(compState);
        newStak.push(assignState);
        newRepo.addPrgState(newPrgState);
        newStak.push(readFile);
        newStak.push(openFileState);
        newStak.push(forkState);
        Controller myController = new Controller(newRepo);
        GUIController myGUI = new GUIController(myController);
        IStatement interpretedState = myController.interpretStatement("var a = 2");
        myController.getRepo().getPrgStates().iterator().next().getStack().push(interpretedState);
        myController.allStep();
        //testing code expressions/statements
        testExpressions testE = new testExpressions();
        testStatements testS= new testStatements();
        testE.run();
        testS.run();


    }

}