package model.interfaces;

import java.io.Serializable;
import java.util.ArrayList;

public interface IDictionary<T,V> extends Serializable{
    //adds an element to the dictionary
    public void add(T name, V value);

    //shows if a key is stored in the dictionary
    public boolean contains(T name);

    //shows if a value is stored in the dictionary
    public boolean containsValue(V value);

    //changes a value in the dictionary
    public void setValue(T name, V value);

    //obtains a value associated to the given key from the dictionary
    public V getValue(T name);

    //returns the string object describing the dictionary
    public String toString();

    //removes a value from the dictionary having given the associated key
    public void remove(T name);

    //returns an arraylist containing all the values in the dictionary
    public ArrayList<V> getValues();

    //returns the size of the dictionary (nr of key & value pairs)
    public int size();

}
