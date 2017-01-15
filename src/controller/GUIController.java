package controller;

import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
import java.util.List;

/**
 * Created by Petean Mihai on 1/9/2017.
 */
public class GUIController {
    Controller myController;
    ArrayList<IStatement> examples;
    //window for selecting your example program
    GridPane exampleView = new GridPane();
    Button select = new Button("HELLO");
    TableView<CompoundStatement> listOfExamples = new TableView<>();
    TableColumn<CompoundStatement,String> exColumn = new TableColumn<>();

    //main window, for execution of a program
    //Stage mainWindow;
    //GridPane mainView;

    public GUIController(Controller ctrl){
        this.myController = ctrl;
    };

    public void initExampleStage(){

        exampleView = new GridPane();
        exColumn = new TableColumn<>();
        exColumn.setCellValueFactory(statement->{return new ReadOnlyObjectWrapper<String>(statement.toString());});
        //listOfExamples = new TableView<>();
        listOfExamples.getColumns().add(0,exColumn);
        listOfExamples.getItems().addAll(FXCollections.observableArrayList(getStatements()));
        exampleView.addRow(0,listOfExamples);
        Stage selectExample = new Stage();
        selectExample.setTitle(">Example");
        selectExample.setScene(new Scene(exampleView,400,500));
        selectExample.show();

    }

    public  ArrayList<CompoundStatement> getStatements() {
        ArrayList<CompoundStatement> myStatements = new ArrayList<>();

        IExpression daddysfirstexpression = new VariableExpression("v");
        IStatement printState = new PrintStatement(daddysfirstexpression);
        IExpression constExpr = new ConstExpression(2);
        IStatement assignState = new AssignStatement("v", constExpr);
        CompoundStatement compState = new CompoundStatement(assignState, printState);


        myStatements.add(compState);

        IStatement openFileState = new openRFile("file.txt", "VarFromFile");

        //heap code
        IStatement newStuff = new newStatement("Valcea", constExpr);
        IStatement newStuff2 = new newStatement("Eroina", constExpr);

        IStatement compState15 = new CompoundStatement(compState, newStuff);
        CompoundStatement compState2 = new CompoundStatement(compState15, newStuff2);

        myStatements.add(compState2);
        IStatement forkState = new ForkStatement(compState);
        IStatement readFile = new readFile(new ConstExpression(0), "MyVar");
        CompoundStatement compState3 = new CompoundStatement(forkState, readFile);

        myStatements.add(compState3);

        return myStatements;
    }

    Stage selectExample;
    Stage executeProgram;
    TableView<CompoundStatement> selectExampleView;

    public void initMainWindow(){

    }


}
