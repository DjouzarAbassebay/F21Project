package com.company.interfaces;

import com.company.interfaces.Observer;

public interface Subject {
    public void registerObserver(Observer obs);
    public void removeObserver(Observer obs);
    public void notifyObservers();

}
