package model.interfaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public interface IList<E> extends Serializable{
    //adds an element to the list
    public void add(E elem);

    //returns an iterator pointing to this object's elements
    public Iterator<E> getAll();

    //returns an arraylist for javafx

    public ArrayList<E> getList();

    //retunrs the number of elements contained in the list
    public int size();
}
