package com.scrop.minterface;


/**
 * Created by Scrop on 2017/8/8.
 */

public interface MySubject {

    public void addObserver(MyObserver observer);

    public void delObserver(MyObserver observer);

    void notifyObservers();
}
