package model;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import model.interfaces.IDictionary;

public class MyFileTable<T, V> implements IDictionary<T, V> {
    protected HashMap<Integer, Tuple<T,BufferedReader>> map;
    protected HashMap<String, Integer> checkMap;
    static int ID = 0;

    @Override
    public int size(){
        return map.size();
    }

    @Override
    public ArrayList<String> getPairs() {
        return null;
    }

    @Override
    public void add(T name, V value) {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader((String) value));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        if(map.containsKey(name))
            return;
        Tuple<T,BufferedReader> resultTuple = new Tuple(value,br);
        int id =  generateID();
        map.put(id, resultTuple);
        checkMap.put((String)name, id);


    }

    public boolean containsKey(T name) {
        if(checkMap.containsKey(name))
            return true;
        else
            return false;
    }
    @Override
    public boolean contains(T name){
        if(map.containsValue(name))
            return true;
        else
            return false;
    }

    @Override
    public boolean containsValue(V value){
        if(map.containsValue(value))
            return true;
        return false;

    }

    @Override
    public void setValue(T name, V value) {
        int id = checkMap.get(name);
        map.put(id, (Tuple<T,BufferedReader>)value);
    }

    @Override
    public V getValue(T name) {
        int id = checkMap.get(name);
        return (V)map.get(id);
    }
    @Override
    public String toString(){
        String result = new String("Dictionary: ");
        result += map;
        return result;
    }
    @Override
    public void remove(T name){
        int id = checkMap.get(name);
        map.remove(id);
    }

    public int generateID(){
        return ID++;
    }

    @Override
    public ArrayList<V> getValues(){
        return (ArrayList<V>) map.values();
    }

}
