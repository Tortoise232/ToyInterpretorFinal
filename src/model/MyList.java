package model;

import java.util.ArrayList;
import java.util.Iterator;

import model.interfaces.IList;

public class MyList<E> implements IList<E> {
    protected ArrayList<E> list;

    public MyList(){
        list = new ArrayList<E>();
    }

    @Override
    public int size(){
        return list.size();
    }

    @Override
    public void add(E elem) {
        list.add(elem);

    }

    @Override
    public Iterator<E> getAll() {
        return list.iterator();
    }

    @Override
    public String toString(){
        String result = new String();
        result += "Output: " + list;
        return result;
    }
}
