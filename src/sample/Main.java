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
        IExpression constExpr = new ConstExpression(3);
        IStatement assignState = new AssignStatement("v", constExpr);
        CompoundStatement compState = new CompoundStatement(assignState, printState);


        myStatements.add(compState);

        IStatement openFileState = new openRFile("file.txt", "VarFromFile");

        //heap code
        IStatement newStuff = new newStatement("Test", constExpr);
        IStatement newStuff2 = new AssignStatement("Copac", constExpr);
        IExpression scadere = new ArithmeticExpression('-',new VariableExpression("Copac"),new ConstExpression(1));
        IStatement assign = new AssignStatement("Copac",scadere);
        IStatement whilest = new WhileStatement(assign,new VariableExpression("Copac"));
        IStatement compState15 = new CompoundStatement(compState, newStuff);
        CompoundStatement compState2 = new CompoundStatement(newStuff2, whilest);
        CompoundStatement cs3 = new CompoundStatement(compState2,openFileState);

        myStatements.add(cs3);
        IStatement forkState = new ForkStatement(compState);
        IStatement readFile = new readFile(new ConstExpression(0), "MyVar");
        CompoundStatement compState3 = new CompoundStatement(forkState, readFile);
        CompoundStatement cs4 = new CompoundStatement(forkState, cs3);

        myStatements.add(cs4);

        CompoundStatement compState5 = new CompoundStatement(compState3, new SleepStatement(10));

        myStatements.add(compState5);

        return myStatements;
    }

    public ArrayList<CompoundStatement> examStatement(){
        ArrayList<CompoundStatement> myStatements = new ArrayList<>();
        //a
        IStatement writeV1 = new writeHeapStatement("v1",2);
        IStatement writeV2 = new writeHeapStatement("v2",3);
        IStatement writeV3 = new writeHeapStatement("v3",4);
        IStatement latch = new newLatch("cnt",new readHeapExpression("v2"));

        IStatement writeH1 = new writeHeapStatement("v1",
                    new ArithmeticExpression('*',new readHeapExpression("v1"),new ConstExpression(10)));
        IStatement writeH2 = new writeHeapStatement("v2",
                new ArithmeticExpression('*',new readHeapExpression("v2"),new ConstExpression(10)));
        IStatement writeH3 = new writeHeapStatement("v3",
                new ArithmeticExpression('*',new readHeapExpression("v3"),new ConstExpression(10)));

        IStatement countd = new countDown("cnt");
        IStatement wait = new await("cnt");
        IStatement printcnt = new PrintStatement(new VariableExpression("cnt"));

        IStatement print1 = new PrintStatement(new readHeapExpression("v1"));
        IStatement print2 = new PrintStatement(new readHeapExpression("v2"));
        IStatement print3 = new PrintStatement(new readHeapExpression("v3"));

        IStatement compound1 = new CompoundStatement(writeH1,print1);
        IStatement comp1p = new CompoundStatement(compound1,countd);

        IStatement compound2 = new CompoundStatement(writeH2,print2);
        IStatement comp2p = new CompoundStatement(compound2,countd);

        IStatement compound3 = new CompoundStatement(writeH3,print3);
        IStatement comp3p = new CompoundStatement(compound3,countd);

        IStatement fork1 = new ForkStatement(comp1p);
        IStatement fork2 = new ForkStatement(comp2p);
        IStatement fork3 = new ForkStatement(comp3p);



        IStatement c1 = new CompoundStatement(writeV1,writeV2);
        IStatement c2 = new CompoundStatement(c1,writeV3);
        IStatement c3 = new CompoundStatement(c2,latch);
        IStatement c4 = new CompoundStatement(c3,fork1);
        IStatement c5 = new CompoundStatement(c4,fork2);
        IStatement c6 = new CompoundStatement(c5,fork3);
        IStatement c7 = new CompoundStatement(c6,wait);

        IStatement c8 = new CompoundStatement(c7,printcnt);
        IStatement c9 = new CompoundStatement(c8,countd);
        CompoundStatement c10 = new CompoundStatement(c9, printcnt);
        myStatements.add(c10);

        //test a

        IStatement newLatch = new newLatch("latch1",new ConstExpression(3));
        IStatement forka = new ForkStatement(newLatch);
        CompoundStatement resultCompa = new CompoundStatement(newLatch,new PrintStatement(new VariableExpression("latch1")));
        CompoundStatement compa2 = new CompoundStatement(resultCompa,new countDown("latch1"));
        //myStatements.add(compa2);

        //b

        IExpression var = new VariableExpression("v");
        IStatement printv = new PrintStatement(var);
        IStatement assignv = new AssignStatement("v", new ArithmeticExpression('-',var,new ConstExpression(1)));
        IStatement forkComp = new CompoundStatement(printv,assignv);
        IStatement fork = new ForkStatement(forkComp);
        IStatement assignvp =  new AssignStatement("v", new ArithmeticExpression('+',var,new ConstExpression(1)));
        IStatement repeatComp = new CompoundStatement(fork,assignvp);
        IStatement repeat = new RepeatUntilStatement(repeatComp,new equalExpression(var,new ConstExpression(3)));
        IStatement leftComp = new CompoundStatement(new AssignStatement("v",new ConstExpression(0)), repeat);
        IStatement rightComp1 = new CompoundStatement(leftComp,new AssignStatement("x",new ConstExpression(1)));
        IStatement rightComp2 = new CompoundStatement(rightComp1,new AssignStatement("y",new ConstExpression(2)));
        IStatement rightComp3 = new CompoundStatement(rightComp2,new AssignStatement("z",new ConstExpression(3)));
        IStatement rightComp4 = new CompoundStatement(rightComp3,new AssignStatement("w",new ConstExpression(4)));
        CompoundStatement resultComp = new CompoundStatement(rightComp4, new PrintStatement(new ArithmeticExpression('*',var,new ConstExpression(10))));
        myStatements.add(resultComp);
        return myStatements;
    }

    //selectExample
    Stage selectExample = new Stage();
    ListView<CompoundStatement> examples;

    public void startSelectExample(){
        myStatements = examStatement();
        selectExample = new Stage();
        GridPane exampleView = new GridPane();
        examples = new ListView<CompoundStatement>();
        examples.setItems(FXCollections.observableArrayList(myStatements));
        examples.setMinSize(1000,400);
        examples.setMaxSize(1000,400);
        examples.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CompoundStatement>() {
            @Override
            public void changed(ObservableValue<? extends CompoundStatement> observable, CompoundStatement oldValue, CompoundStatement newValue) {
                startProgram(newValue);
                selectExample.close();
            }

        });
        selectExample.setTitle("> Select Example");
        //exampleView.addRow(0,new Label("Choose a program to run:"));
        exampleView.addRow(1,examples);
        selectExample.setScene(new Scene(exampleView, 400, 200));
        selectExample.setResizable(true);
        selectExample.show();

    }

    public Procedure addProc(){
        ArrayList<String> params =  new ArrayList<String>();
        params.add("a");
        params.add("b");
        IExpression add = new ArithmeticExpression('-',new VariableExpression("a"),new VariableExpression("b"));
        IStatement myState = new CompoundStatement(new AssignStatement("a",add),new PrintStatement(new VariableExpression("a")));
        Procedure addProc = new Procedure("add", params, myState);
        return addProc;
    }

    public Controller initController(CompoundStatement statement){
        //initialising the controller
        Repository myRepo = new Repository();
        PrgState myState = new PrgState();
        myState.getStack().push(statement);
        myState.getProcTable().add("add",addProc());
        myRepo.addPrgState(myState);
        Controller myController = new Controller(myRepo);
        return myController;
    }



    public void startProgram(CompoundStatement statement){
        myController = initController(statement);
        //visual elements
        Stage programStateStage = new Stage();
        programStateStage.setTitle("> Program Execution");
        GridPane programStateGrid = new GridPane();
        TextField nrPrgStates = new TextField("" + myController.getRepo().getPrgStates().size());

        //init heap
        TableView<Tuple> heapTable = new TableView<>();
        TableColumn heapcol1 = new TableColumn("ADDRESS");
        TableColumn heapcol2 = new TableColumn("VALUE");
        heapcol1.setCellValueFactory(new PropertyValueFactory<Tuple,String>("first"));
        heapcol2.setCellValueFactory(new PropertyValueFactory<Tuple, String>("second"));
        heapTable.getColumns().addAll(heapcol1,heapcol2);
        programStateGrid.addColumn(1,new Label("Heap:"),heapTable);

        //init out
        ListView<Integer> outView = new ListView<>();
        programStateGrid.addColumn(1,new Label("Out: "),outView);

        //initFileTable
        TableView<Tuple> fileTableView = new TableView<>();
        TableColumn filecol1 = new TableColumn("FILENAME");
        TableColumn filecol2 = new TableColumn("ADDRESS");
        filecol1.setCellValueFactory(new PropertyValueFactory<Tuple,String>("first"));
        filecol2.setCellValueFactory(new PropertyValueFactory<Tuple, String>("second"));
        fileTableView.getColumns().addAll(filecol1,filecol2);
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

        //initLatchTable
        TableView<Tuple> latchTableView = new TableView<>();
        TableColumn latchcol1 = new TableColumn("ADDRESS");
        TableColumn latchcol2 = new TableColumn("VALUE");
        latchcol1.setCellValueFactory(new PropertyValueFactory<Tuple,String>("first"));
        latchcol2.setCellValueFactory(new PropertyValueFactory<Tuple, String>("second"));
        latchTableView.getColumns().addAll(latchcol1,latchcol2);
        programStateGrid.addColumn(3,new Label("LatchTable: "), latchTableView);



        //init SymTable
        TableView<Tuple> symtable = new TableView<>();
        TableColumn symcol1 = new TableColumn("NAME");
        TableColumn symcol2 = new TableColumn("ADDRESS");
        symcol1.setCellValueFactory( new PropertyValueFactory<Tuple,String>("first"));
        symcol2.setCellValueFactory( new PropertyValueFactory<Tuple,String>( "second"));
        symtable.getColumns().addAll(symcol1,symcol2);
        programStateGrid.addColumn(2, new Label("SymTable: "),symtable);
        stateView.setItems(FXCollections.observableArrayList(prgStateIDs));
        stateView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {

                try {
                    PrgState originalPrgState = myController.getRepo().getByID(stateView.getSelectionModel().getSelectedItem());
                    heapTable.setItems(FXCollections.observableArrayList(originalPrgState.getHeap().getArray()));
                    outView.setItems(FXCollections.observableArrayList(originalPrgState.getOutput().getList()));
                    fileTableView.setItems(FXCollections.observableArrayList(originalPrgState.getFileTable().getArray()));
                    stackView.setItems(FXCollections.observableArrayList(originalPrgState.getStack().getList()));
                    symtable.setItems(FXCollections.observableArrayList(originalPrgState.getTable().getPairs()));
                    latchTableView.setItems(FXCollections.observableArrayList(originalPrgState.getLatchTable().getTuples()));
                    prgStateIDs.clear();
                    for(PrgState state: myController.getRepo().getPrgStates()){
                        prgStateIDs.add(state.getID());
                    }
                    if(prgStateIDs.size() > 0)
                        stateView.setItems(FXCollections.observableArrayList(prgStateIDs));
                }
                catch (Exception e){

                }

            }});
        programStateGrid.addColumn(4,new Label("PrgState: "),stateView);

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
                latchTableView.setItems(FXCollections.observableArrayList(originalPrgState.getLatchTable().getTuples()));
                prgStateIDs.clear();
                for(PrgState state: myController.getRepo().getPrgStates()){
                    prgStateIDs.add(state.getID());
                }
                if(prgStateIDs.size() > 0)
                    stateView.setItems(FXCollections.observableArrayList(prgStateIDs));

            }
        });

        programStateGrid.addRow(3, executeProgram);
        programStateStage.setScene(new Scene(programStateGrid,1000,800));
        programStateStage.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        startSelectExample();
    }
}