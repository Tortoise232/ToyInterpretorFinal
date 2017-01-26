package model.interfaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public interface IStak<T> extends Serializable, Cloneable{
    public void push(T mySta);
    public T pop();
    public Iterator<T> getAll();
    public boolean isEmpty();
    public int size();
    public Object clone() throws CloneNotSupportedException;
    public ArrayList<String> getList();
    public T top();
}
