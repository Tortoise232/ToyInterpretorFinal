package model;

import java.util.HashMap;
import java.util.Map;

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
    public String toString(){
        String result = "";
        result += map;
        return result;
    }
}