package model.interfaces;

import java.io.Serializable;
import java.util.*;

public interface IHeap extends Serializable{
    //returns the hashmap containing all variable output
    public HashMap<Integer,Integer> getMap();

    //replaces the map with another given map
    public void setMap(HashMap<Integer,Integer> map);

    //adds a value to the map, key is autogenerated
    public int newElem(int value);

    //returns an element associated with the given location (serves as key)
    public int readHeap(int  location);

    //replaces a value in the map, at the specified location (key)
    public int writeHeap(int location, int newValue);

    //increments and assigns keys to all values stored in the map
    public int generateLocation();

    //string representation of the heap
    public String toString();

    //size of elements stored in the heap
    public int size();
}
