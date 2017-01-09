package model;

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
}
