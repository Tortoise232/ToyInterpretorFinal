package repository;
import java.io.*;
import java.util.*;

import model.*;

public interface IRepository extends Serializable{
    public void addPrgState(PrgState p);
    public void logPrgStateExec(PrgState p)throws IOException;
    public void serializeToFile(String filePath);
    public void deserializeFromFile(String filePath);
    public void setPrgState(ArrayList<PrgState> newSet);
    public PrgState getByID(int it);
    public ArrayList<PrgState> getPrgStates();
}
