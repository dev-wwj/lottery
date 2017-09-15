package com.scrop.mimpl;

import com.scrop.minterface.MyObserver;
import com.scrop.minterface.MySubject;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by Scrop on 2017/8/8.
 */

public class ConcreteMySubject implements MySubject {

    //向量容器，保存所有注册的观察者
    private Vector<MyObserver> observers = new Vector<>();

    @Override
    public void addObserver(MyObserver observer) {
        observers.addElement(observer);
    }

    @Override
    public void delObserver(MyObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        Enumeration enumeration = observers.elements();
        while (enumeration.hasMoreElements()){
            ((MyObserver)enumeration.nextElement()).update();
        }
    }
}
