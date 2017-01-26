package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import model.interfaces.IStak;

public class MyStack<T> implements IStak<T> {
    protected Stack<T> myStack;

    public MyStack(){
        myStack = new Stack<T>();
    }
    @Override
    public int size(){
        return myStack.size();
    }
    @Override
    public void push(T mySta) {
        myStack.push(mySta);

    }

    @Override
    public T pop() {
        return myStack.pop();
    }

    @Override
    public Iterator<T> getAll() {
        return myStack.iterator();
    }

    @Override
    public boolean isEmpty() {
        return myStack.isEmpty();
    }

    @Override
    public String toString(){
        return "" + myStack;
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public ArrayList<String> getList() {
        ArrayList<String> result = new ArrayList<>();
        for (Iterator<T> it = getAll(); it.hasNext(); ) {
            T object = it.next();
            result.add(object.toString());
        }
        for(int i = 0; i < result.size() /2 ; i++){
            String aux =  result.get(i);
            result.set(i, result.get(result.size() - i - 1));
            result.set(result.size() - i - 1, aux);
        }

        return result;
    }

    @Override
    public T top() {
        return myStack.peek();
    }
}
