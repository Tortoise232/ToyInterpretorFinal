
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
    @Override
    public String toString(){
        String result;
        result = "(" + first + ", " + second + ")";
        return result;
    }

}

