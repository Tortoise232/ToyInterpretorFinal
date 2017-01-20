package sample;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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

        myStatements.add(compState2);
        IStatement forkState = new ForkStatement(compState);
        IStatement readFile = new readFile(new ConstExpression(0), "MyVar");
        CompoundStatement compState3 = new CompoundStatement(forkState, readFile);

        myStatements.add(compState3);

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
                myController.executeOneStep(myController.getRepo().getPrgStates().get(0));
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
        PrgState originalPrgState = myController.getRepo().getPrgStates().get(0);

        //visual elements
        Stage programStateStage = new Stage();
        GridPane programStateGrid = new GridPane();
        TextField nrPrgStates = new TextField("" + myController.getRepo().getPrgStates().size());

        //init heap
        TableView<Tuple<Integer,Integer>> heapTable = new TableView<>();
        TableColumn heapColumn1 = new TableColumn("LOCATION");
        TableColumn heapColumn2 = new TableColumn("VALUE");
        heapColumn1.setCellValueFactory(new PropertyValueFactory<Tuple<Integer,Integer>,String>("x"));
        heapColumn2.setCellValueFactory(new PropertyValueFactory<Tuple<Integer,Integer>,String>("y"));
        heapTable.getColumns().addAll(heapColumn1, heapColumn2);
        programStateGrid.addColumn(1,new Label("Heap:"),heapTable);
        heapTable.setItems(FXCollections.observableArrayList(originalPrgState.getHeap().getArray()));

        //init out
        ListView<Integer> outView = new ListView<>();
        outView.setItems(FXCollections.observableArrayList(originalPrgState.getOutput().getList()));
        programStateGrid.addColumn(1,new Label("Out: "),outView);

        //initFileTable
        TableView<Tuple<Integer,String>> fileTableView = new TableView<>();
        TableColumn fileColumn1 = new TableColumn("ID");
        TableColumn fileColumn2 = new TableColumn("NAME");
        fileColumn1.setCellValueFactory(new PropertyValueFactory<Tuple<Integer,String>,String>("x"));
        fileColumn2.setCellValueFactory(new PropertyValueFactory<Tuple<Integer,String>,String>("y"));
        fileTableView.getColumns().addAll(fileColumn1,fileColumn2);
        fileTableView.setItems(FXCollections.observableArrayList(originalPrgState.getFileTable().getArray()));
        programStateGrid.addColumn(2,new Label("FileTable: "), fileTableView);


        //init PrgState list
        ListView<Integer> stateView = new ListView<>();
        ArrayList<Integer> prgStateIDs = new ArrayList<>();
        for(PrgState state: myController.getRepo().getPrgStates()){
               prgStateIDs.add(state.getID());
        }
        stateView.setItems(FXCollections.observableArrayList(prgStateIDs));
        stateView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                    //startSymTable(observable);
                    //startExeStack(observable);
            }});
        programStateGrid.addColumn(2,new Label("PrgState: "),stateView);

        //init run button
        Button executeProgram = new Button ("RUN");
        executeProgram.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e){
               myController.allStep();
                heapTable.setItems(FXCollections.observableArrayList(originalPrgState.getHeap().getArray()));
                outView.refresh();
                fileTableView.refresh();
                stateView.refresh();
            }
        });

        //programStateGrid.addColumn(4, executeProgram);
        programStateStage.setScene(new Scene(programStateGrid,400,600));
        programStateStage.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        startSelectExample();
    }
}