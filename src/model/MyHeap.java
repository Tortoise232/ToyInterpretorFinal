package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import model.interfaces.IExpression;
import model.interfaces.IHeap;

public class MyHeap implements IHeap {
    HashMap<Integer,Integer> map;
    static int memoryLocation = 1;
    public MyHeap(){
        this.map = new HashMap<Integer,Integer>();
    }

    @Override
    public int size(){
        return map.size();
    }

    @Override
    public int newElem(int value) {
        int locationForValue = generateLocation();
        map.put(locationForValue, value);
        return locationForValue;
    }

    @Override
    public int readHeap(int location) {
        try{
            return map.get(location);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int writeHeap(int location, int newValue) {
        map.put(location, newValue);
        return 0;
    }
    public int generateLocation(){
        return memoryLocation++;
    }

    @Override
    public void setMap(HashMap<Integer, Integer> map) {
        this.map = map;

    }

    @Override
    public HashMap<Integer, Integer> getMap() {
        return this.map;
    }

    @Override
    public ArrayList<Tuple<Integer,Integer>> getArray(){
        ArrayList<Tuple<Integer,Integer>> result = new ArrayList<>();
        for(Integer key : map.keySet()){
            result.add(new Tuple<Integer,Integer>(key, map.get(key)));
        }
        return result;
    }

    @Override
    public String toString(){
        String result = "";
        result += map;
        return result;
    }
}