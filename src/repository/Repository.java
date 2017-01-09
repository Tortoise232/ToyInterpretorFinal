package repository;

import java.util.ArrayList;
import model.PrgState;
import java.io.*;

public class Repository implements IRepository{
    protected boolean printProgramStates;
    public ArrayList<PrgState> listOfStates;
    protected String logPath = "log.txt";

    public Repository(boolean printProgramStates){
        this.printProgramStates = printProgramStates;
        this.listOfStates = new ArrayList<PrgState>();
    }
    //default constructor disables printing on screen; only uses logs
    public Repository(){
        this.listOfStates = new ArrayList<PrgState>();
        this.printProgramStates = false;
    }

    //adds a program state to the repository
    @Override
    public void addPrgState(PrgState p) {
        listOfStates.add(p);
    }

    //logs the execution of a certain program state to file and screen (if allowed)
    @Override
    public void logPrgStateExec(PrgState p) throws IOException{
        PrintWriter logFile;
        logFile = new PrintWriter(new BufferedWriter(new FileWriter( "log" + p.getID() + ".txt", true)));
        logFile.write("" + p.toString() + "\n");
        if(this.printProgramStates)
            System.out.print(p.toString() + "\n");
        logFile.close();
    }

    @Override
    public void serializeToFile(String filePath) {

        try{
            FileOutputStream fileOut = new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            try{
                out.writeObject(listOfStates.get(0));
            }catch(NotSerializableException e){
                //too bad there isn't any serialization for buffered readers, right?
            }
            out.close();
            fileOut.close();
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public void deserializeFromFile(String filePath) {
        PrgState prog = null;

        try {
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            try {
                prog = (PrgState) in.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            in.close();
            fileIn.close();
        }catch(IOException i) {
            i.printStackTrace();

            return;
        }

        listOfStates.clear();
        listOfStates.add(prog);
    }

    @Override
    public void setPrgState(ArrayList<PrgState> newSet){
        this.listOfStates = newSet;
    }

    @Override
    public ArrayList<PrgState> getPrgStates(){
        return listOfStates;
    }

}
