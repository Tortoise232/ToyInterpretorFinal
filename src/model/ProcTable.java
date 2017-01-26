package model;

import model.interfaces.IDictionary;
import model.interfaces.IStatement;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Petean Mihai on 1/25/2017.
 */
public class ProcTable<T,V> implements IDictionary<T,V> {
    protected HashMap<T,V> data;

    public ProcTable(){
        data = new HashMap<>();
    }

    @Override
    public void add(T name, V value) {
        data.put(name,value);
    }

    @Override
    public boolean contains(T name) {
        return data.containsKey(name);
    }

    @Override
    public boolean containsValue(V value) {
        return data.containsValue(value);
    }

    @Override
    public void setValue(T name, V value) {
        data.put(name,value);
    }

    @Override
    public V getValue(T name) {
        return data.get(name);
    }

    @Override
    public void remove(T name) {
        data.remove(name);
    }

    @Override
    public ArrayList<V> getValues() {
        ArrayList<V> result = new ArrayList<V>();
        for(T name: data.keySet())
            result.add(data.get(name));
        return result;
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public ArrayList<Tuple> getPairs() {
        ArrayList<Tuple> result = new ArrayList<>();
        for(T name: data.keySet())
            result.add(new Tuple(name,data.get(name)));
        return result;
    }
}
