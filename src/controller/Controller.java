package controller;
import java.io.IOException;
import java.io.NotSerializableException;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.*;
import model.interfaces.*;
import model.statements.AssignStatement;
import model.*;
import model.expressions.ConstExpression;
import repository.IRepository;
import repository.Repository;
public class Controller {
    protected ExecutorService executor;
    protected IRepository repo;

    //deletes all entries in the heap that have no pointer in the symbol table;
    //DOES INTERPRET FILE POINTERS AS POINTING TO HEAP AREAS
    public IHeap conservativeGarbageCollector(
            IDictionary<String,Integer> symbolTable,
            IHeap heap){
        IHeap result = new MyHeap();
        Stream goodAddresses = ( heap.getMap().entrySet().stream()
                .filter(e->symbolTable.getValues().contains( e.getKey() ) ));
        Iterator<Entry<Integer,Integer>> addressesIterator = (Iterator<Entry<Integer,Integer>>)goodAddresses.iterator();
        while(addressesIterator.hasNext()){
            Entry<Integer,Integer> a = addressesIterator.next();
            result.getMap().put(a.getKey(), a.getValue());
        }
        return result;

    }
    //constructor with a repository with data
    public Controller(IRepository repo){
        this.repo = repo;
    }

    //default constructor
    public Controller(){
        this.repo = new Repository();
    }

    //executes one statement in the program with state p
    public void executeOneStep(PrgState p){
        IStak<IStatement> exec = p.getStack();
        if(!exec.isEmpty()){
            IStatement statement = exec.pop();
            statement.execute(p);
        }
    }

    //serializes the program state to a given filepath
    public void serialize(String filePath){
        this.repo.serializeToFile(filePath);
    }

    //deserializes a program state from the given file;
    //THIS PROGRAM STATE WILL BEGIN EXECUTION AFTER DESERIALIZATION;
    public void deserialize(String filePath){
        this.repo.deserializeFromFile(filePath);
    }

    //returns a reference to the repository object containing program states
    public IRepository getRepo(){
        return this.repo;
    }

    //filters from a list of program states those that are not yet finished (have statements to be executed
    public List<PrgState> removeCompletedPrg(List<PrgState> listOfPrgs){
        return listOfPrgs.stream()
                .filter(p -> p.isNotComplete())
                .collect(Collectors.toList());
    }

    //executes one statement in each program from the repository, that hasn't reached end of execution
    public void oneStepForAll(List<PrgState> list){
        List<Callable<PrgState>> callList = new ArrayList<Callable<PrgState>>();
        for(PrgState state: list){
            callList.add((Callable<PrgState>)()->state.executeOneStep());
        }
        try {
            List<PrgState> newPrgList =
                    executor.invokeAll(callList).stream().map(future->{
                        //lambda function that does stuff, i can't eli5 it so i'm not sure
                        try {
                            return future.get();
                        } catch(Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }).filter(p -> p!=null).collect(Collectors.toList());
            this.getRepo().getPrgStates().addAll(newPrgList);
            this.getRepo().getPrgStates().forEach(prg ->{
                try {
                    this.getRepo().logPrgStateExec(prg);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            });

        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //runs all programs until end of execution
    public void allStep() {
        executor = Executors.newFixedThreadPool(10);
        while(true){
            //remove the completed programs
            List<PrgState> prgList=removeCompletedPrg(this.getRepo().getPrgStates());
            if(prgList.size() == 0)
                break; //complete the execution of all threads
            this.oneStepForAll(prgList);
        }
        executor.shutdownNow();
    }

    public void allStepGUI() {
        executor = Executors.newFixedThreadPool(10);
        List<PrgState> prgList=removeCompletedPrg(this.getRepo().getPrgStates());
        if(prgList.size() == 0)
            return;
        this.oneStepForAll(prgList);
        executor.shutdownNow();
    }

    //return the first position of a given character in the given string
    public int getFirstPos(String s, char a){
        for(int counter = 0; counter < s.toCharArray().length; counter++)
            if(s.toCharArray()[counter] == a)
                return counter;
        return -1;
    }
    //returns the string given as parameter without the given character
    public String removeAll(String s, char a){
        for(int counter = 0; counter < s.length(); counter++)
            if(s.toCharArray()[counter] == ' '){
                s = s.substring(0, counter) + s.substring(counter + 1);
                if(counter > 0)
                    counter --;
            }
        return s;
    }

    //interprets input line as statement
    public IStatement interpretStatement(String s){

        //find the type of statement, to interpret the statement
        String statement = "";
        int firstPosition = getFirstPos(s, ' ');
        statement += s.substring(0, firstPosition);
        s = s.substring(firstPosition);

        //interpret statements
        switch(statement){

            //assign statement
            case("var"):
                s = removeAll(s,' ');
                String varName = s.substring(0,getFirstPos(s,'='));
                System.out.println(varName);
                IExpression value = new ConstExpression(s.substring(getFirstPos(s,'=') + 1));
                System.out.println(value);
                return new AssignStatement(varName,value);
        }
        return null;
    }

}
