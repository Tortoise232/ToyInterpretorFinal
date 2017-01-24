package sample;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import tests.*;
import repository.Repository;
import model.*;
import javafx.application.Application;
import model.expressions.*;
import model.interfaces.*;
import model.statements.*;



import java.util.ArrayList;
import java.util.HashMap;

public class Main extends Application{
    private static Controller myController;
    private static ArrayList<CompoundStatement> myStatements;
    public static void main(String[] args) {
        //testing code expressions/statements
        testExpressions testE = new testExpressions();
        testStatements testS= new testStatements();
        testE.run();
        testS.run();
        launch(args);

    }
    //this is where the example statements are built;
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
        IStatement newStuff = new newStatement("Test", constExpr);
        IStatement newStuff2 = new newStatement("Copac", constExpr);

        IStatement compState15 = new CompoundStatement(compState, newStuff);
        CompoundStatement compState2 = new CompoundStatement(compState15, newStuff2);
        CompoundStatement cs3 = new CompoundStatement(compState2,openFileState);

        myStatements.add(cs3);
        IStatement forkState = new ForkStatement(compState);
        IStatement readFile = new readFile(new ConstExpression(0), "MyVar");
        CompoundStatement compState3 = new CompoundStatement(forkState, readFile);
        CompoundStatement cs4 = new CompoundStatement(forkState, cs3);

        myStatements.add(cs4);

        return myStatements;
    }

    //selectExample
    Stage selectExample = new Stage();
    ListView<CompoundStatement> examples;

    public void startSelectExample(){
        myStatements = getStatements();
        selectExample = new Stage();
        GridPane exampleView = new GridPane();
        examples = new ListView<CompoundStatement>();
        examples.setItems(FXCollections.observableArrayList(myStatements));
        examples.setMinSize(400,200);
        examples.setMaxSize(400,200);
        examples.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CompoundStatement>() {
            @Override
            public void changed(ObservableValue<? extends CompoundStatement> observable, CompoundStatement oldValue, CompoundStatement newValue) {
                startProgram(newValue);
            }
        });
        selectExample.setTitle("START");
        //exampleView.addRow(0,new Label("Choose a program to run:"));
        exampleView.addRow(1,examples);
        selectExample.setScene(new Scene(exampleView, 400, 200));
        selectExample.setResizable(false);
        selectExample.show();

    }

    public Controller initController(CompoundStatement statement){
        //initialising the controller
        Repository myRepo = new Repository();
        PrgState myState = new PrgState();
        myState.getStack().push(statement);
        myRepo.addPrgState(myState);
        Controller myController = new Controller(myRepo);
        return myController;
    }



    public void startProgram(CompoundStatement statement){
        myController = initController(statement);
        //visual elements
        Stage programStateStage = new Stage();
        GridPane programStateGrid = new GridPane();
        TextField nrPrgStates = new TextField("" + myController.getRepo().getPrgStates().size());

        //init heap
        ListView<Tuple> heapTable = new ListView<>();
        programStateGrid.addColumn(1,new Label("Heap:"),heapTable);

        //init out
        ListView<Integer> outView = new ListView<>();
        programStateGrid.addColumn(1,new Label("Out: "),outView);

        //initFileTable
        ListView<Tuple> fileTableView = new ListView<>();
        programStateGrid.addColumn(2,new Label("FileTable: "), fileTableView);

        //initiate statement stack
        ListView<String> stackView = new ListView<>();
        programStateGrid.addColumn(3,new Label("ExeStack: "), stackView);

        //init PrgState list
        ListView<Integer> stateView = new ListView<>();
        ArrayList<Integer> prgStateIDs = new ArrayList<>();
        prgStateIDs.clear();
        for(PrgState state: myController.getRepo().getPrgStates()){
               prgStateIDs.add(state.getID());
        }

        //init SymTable
        ListView<String> symtable = new ListView<>();
        programStateGrid.addColumn(2, new Label("SymTable: "),symtable);

        stateView.setItems(FXCollections.observableArrayList(prgStateIDs));
        stateView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                PrgState originalPrgState = myController.getRepo().getByID(stateView.getSelectionModel().getSelectedItem());
                heapTable.setItems(FXCollections.observableArrayList(originalPrgState.getHeap().getArray()));
                heapTable.refresh();
                outView.setItems(FXCollections.observableArrayList(originalPrgState.getOutput().getList()));
                fileTableView.setItems(FXCollections.observableArrayList(originalPrgState.getFileTable().getArray()));
                fileTableView.refresh();
                stackView.setItems(FXCollections.observableArrayList(originalPrgState.getStack().getList()));
                symtable.setItems(FXCollections.observableArrayList(originalPrgState.getTable().getPairs()));
                prgStateIDs.clear();
                for(PrgState state: myController.getRepo().getPrgStates()){
                    prgStateIDs.add(state.getID());
                }
                stateView.setItems(FXCollections.observableArrayList(prgStateIDs));
            }});
        programStateGrid.addColumn(3,new Label("PrgState: "),stateView);

        //init run button
        Button executeProgram = new Button ("RUN");
        executeProgram.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e){
                myController.allStepGUI();
                PrgState originalPrgState = myController.getRepo().getByID(stateView.getSelectionModel().getSelectedItem());
                heapTable.setItems(FXCollections.observableArrayList(originalPrgState.getHeap().getArray()));
                outView.setItems(FXCollections.observableArrayList(originalPrgState.getOutput().getList()));
                fileTableView.setItems(FXCollections.observableArrayList(originalPrgState.getFileTable().getArray()));
                stackView.setItems(FXCollections.observableArrayList(originalPrgState.getStack().getList()));
                symtable.setItems(FXCollections.observableArrayList(originalPrgState.getTable().getPairs()));
                prgStateIDs.clear();
                for(PrgState state: myController.getRepo().getPrgStates()){
                    prgStateIDs.add(state.getID());
                }
                if(prgStateIDs.size() > 0)
                    stateView.setItems(FXCollections.observableArrayList(prgStateIDs));

            }
        });

        programStateGrid.addRow(3, executeProgram);
        programStateStage.setScene(new Scene(programStateGrid,800,600));
        programStateStage.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        startSelectExample();
    }
}