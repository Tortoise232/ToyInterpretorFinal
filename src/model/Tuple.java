
package model;

import java.io.Serializable;

public class Tuple<TypeA,TypeB> implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public TypeA first;
    public TypeB second;
    public Tuple(TypeA a, TypeB b){
        this.first = a;
        this.second = b;
    }

    public TypeA getFirst(){
        return first;
    }

    public void setFirst(TypeA newValue){
        first = newValue;
    }

    public TypeB getSecond(){
        return second;
    }

    public void setSecond(TypeB newValue){
        second = newValue;
    }

    @Override
    public String toString(){
        String result;
        result = "(" + first + ", " + second + ")";
        return result;
    }

}

