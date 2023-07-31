package com.search.data.designpattern;

import lombok.Synchronized;

public class Singleton {
    //3 ways to create->1. eager,2.lazy,3.lazy with thread safe,4.lazy with thread safe with double check lock



//lazy way (when need to make object)
    private static Singleton singleton;
    private Singleton(){
    }

    //lazy initialization
    public static Singleton getObject(){
        if(singleton==null){
            singleton=new Singleton();
        }
       return singleton;
    }


    //lazy initialization with thread safe
    synchronized public  static Singleton getObjectWithThreadSafe(){
        if(singleton==null)
            singleton=new Singleton();
        return singleton;
    }

    // lazy initialization with double check lock

    public static Singleton getObjectWithDoubleCheckLock(){
        if(singleton==null){
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }


    //1. eager

    private static Singleton singleton1=new Singleton();

    public static Singleton getInstance(){
        return singleton1;
    }



}
