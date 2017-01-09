package model.interfaces;

import java.io.Serializable;
import java.util.Iterator;

public interface IStak<T> extends Serializable{
    public void push(T mySta);
    public T pop();
    public Iterator<T> getAll();
    public boolean isEmpty();
    public int size();
}
