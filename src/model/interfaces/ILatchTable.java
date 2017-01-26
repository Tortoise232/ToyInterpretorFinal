package model.interfaces;

import model.Tuple;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Petean Mihai on 1/26/2017.
 */
public interface ILatchTable<T,V> {
    void addLatch(T location,V value);
    void removeLatch(T value);
    void countDown(T value);
    V getLatch(T value);
    int size();
    int getID();
    ArrayList<Tuple> getTuples();


}
