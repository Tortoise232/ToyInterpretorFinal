package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import model.interfaces.IDictionary;

public class MyDictionary<T,V> implements IDictionary<T, V> {
    protected HashMap<T,V> map;


    HashMap<T,V> getMap(){
        return map;
    }

    public MyDictionary(){
        this.map = new HashMap<T, V>();
    }

    public MyDictionary(MyDictionary<T,V> newMap){
        this.map = (HashMap<T, V>) newMap.getMap().clone();
    }

    @Override
    public int size(){
        return map.size();
    }
    @Override
    public void add(T name, V value) {
        map.put(name,value);

    }

    @Override
    public boolean contains(T name) {
        if(map.containsKey(name))
            return true;
        else
            return false;
    }

    @Override
    public void setValue(T name, V value) {
        map.put(name, value);
    }

    @Override
    public V getValue(T name) {
        return map.get(name);
    }
    @Override
    public String toString(){
        String result = new String("");
        result += map;
        return result;
    }
    @Override
    public void remove(T name){
        map.remove(name);
    }

    @Override
    public boolean containsValue(V value) {
        return map.containsValue(value);
    }

    @Override
    public ArrayList<V> getValues(){
        ArrayList<V> result = new ArrayList<V>();
        Iterator<V> it = map.values().iterator();
        while(it.hasNext())
            result.add(it.next());
        return result;
    }
}