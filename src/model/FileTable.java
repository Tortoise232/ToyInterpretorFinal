package model;

import java.io.*;
import java.util.*;

public class FileTable implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public HashMap<Integer,Tuple<String,BufferedReader>> map;
    public HashMap<String,Integer> checkMap;
    static int ID = 0;
    public FileTable(){
        map = new HashMap<Integer,Tuple<String,BufferedReader>>();
        checkMap = new HashMap<String,Integer>();
    }
    int generateID(){
        return ID++;
    }

    public int addValue(String filePath){
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader(filePath));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return -1;
        }

        Tuple<String,BufferedReader> myTuple = new Tuple(filePath,br);
        if(checkMap.containsKey(filePath) == false){
            int id = generateID();
            map.put(id, myTuple);
            checkMap.put(filePath, id);
            return id;

        }
        else{
            return -1;
        }
    }

    public BufferedReader getReader(int id){
        try{
            if(!checkMap.containsValue(id)){
                System.out.println(checkMap.values());
                throw new Exception("This id does not correspond to a value!");
            }
        }
        catch(Exception e){
            e.printStackTrace();

        }
        return map.get(id).y;

    }
    public String toString(){
        String result = "";
        Set<String> keys = checkMap.keySet();
        for(String key: keys){
            result += " " + key + " " + checkMap.get(key) + " ";
        }
        return result;
    }
    public int size() {
        // TODO Auto-generated method stub
        return map.size();
    }

}
