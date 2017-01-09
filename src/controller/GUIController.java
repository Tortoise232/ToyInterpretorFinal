package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.PrgState;
import model.expressions.ConstExpression;
import model.expressions.VariableExpression;
import model.interfaces.IExpression;
import model.interfaces.IStatement;
import model.statements.*;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Petean Mihai on 1/9/2017.
 */
public class GUIController {
    Controller myController;

    ArrayList<IStatement> examples;
    //window for selecting your example program
    Stage selectExample;
    GridPane exampleView;
    Button select;
    public ListView<String> listOfExamples= new ListView<>();

    //main window, for execution of a program
    Stage mainWindow;
    GridPane mainView;

    public GUIController(Controller ctrl){
        this.myController = ctrl;
        initExampleStage();
        initMainWindow();


    };

    public void loadExamples(){
        ArrayList<CompoundStatement> myStatements = new ArrayList<>();

        IExpression daddysfirstexpression = new VariableExpression("v");
        IStatement printState = new PrintStatement(daddysfirstexpression);
        IExpression constExpr = new ConstExpression(2);
        IStatement assignState = new AssignStatement("v",constExpr);
        CompoundStatement compState = new CompoundStatement(assignState, printState);


        myStatements.add(compState);

        IStatement openFileState = new openRFile("file.txt","VarFromFile");

        //heap code
        IStatement newStuff = new newStatement("Valcea",constExpr);
        IStatement newStuff2 = new newStatement("Eroina",constExpr);

        IStatement compState15 = new CompoundStatement(compState,newStuff);
        CompoundStatement compState2 = new CompoundStatement(compState15,newStuff2);

        myStatements.add(compState2);
        IStatement forkState = new ForkStatement(compState);
        IStatement readFile = new readFile(new ConstExpression(0),"MyVar");
        CompoundStatement compState3 = new CompoundStatement(forkState,readFile);

        ArrayList<String> strings = new ArrayList<>();
        myStatements.forEach(stmt->{strings.add(stmt.toString());});
        myStatements.add(compState3);
        ObservableList<String> obsList = FXCollections.observableArrayList(strings);
        listOfExamples = new ListView<String>(obsList);
    }

    public void initExampleStage(){
        loadExamples();
        exampleView.addRow(0,listOfExamples);
        selectExample.setScene(new Scene(exampleView,400,500));
        selectExample.show();

    }

    public void initMainWindow(){

    }


}
