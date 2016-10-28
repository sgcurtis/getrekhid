package com.huskygames.rekhid.slugger.menu;


import java.util.LinkedList;
import java.util.List;

public abstract class Container extends MenuItem {


    LinkedList<MenuItem> list = new LinkedList<>();

    public void addElement(MenuItem a){

    }

    public List<MenuItem> getElements(){
        return list;
    }
}
