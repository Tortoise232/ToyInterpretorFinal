package model;

import model.interfaces.ILatchTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Petean Mihai on 1/26/2017.
 */
public class LatchTable implements ILatchTable<Integer,Integer> {
    private ConcurrentHashMap<Integer, Integer> data;
    static int id;
    @Override
    public int getID(){
        return id++;
    }

    public LatchTable(ConcurrentHashMap<Integer, Integer> data) {
        this.data = data;
    }

    public LatchTable() {
        this.data = new ConcurrentHashMap<>();
    }

    @Override
    public void addLatch(Integer name, Integer location) {
        data.put(name, location);
    }

    @Override
    public void removeLatch(Integer name) {
        data.remove(name);
    }

    @Override
    public void countDown(Integer name) {
        data.put(name, data.get(name) - 1);
    }

    @Override
    public Integer getLatch(Integer name) {
        return data.get(name);
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public String toString(){
        return "" + data;
    }

    @Override
    public ArrayList<Tuple> getTuples(){
        ArrayList<Tuple> result = new ArrayList<>();
        for(int key: data.keySet()){
            result.add(new Tuple(key,data.get(key)));
        }
        return result;
    }

}
