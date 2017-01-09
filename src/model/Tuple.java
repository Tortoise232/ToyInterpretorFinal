
package model;

import java.io.Serializable;

public class Tuple<TypeA,TypeB> implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public TypeA x;
    public TypeB y;
    public Tuple(TypeA a, TypeB b){
        this.x = a;
        this.y = b;
    }
    @Override
    public String toString(){
        String result;
        result = "(" + x + ", " + y + ")";
        return result;
    }

}

