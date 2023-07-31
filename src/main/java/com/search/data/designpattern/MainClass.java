package com.search.data.designpattern;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class MainClass {

    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException {

        Singleton obj1 = Singleton.getObjectWithDoubleCheckLock();
        Singleton obj3 = Singleton.getObjectWithDoubleCheckLock();
        Singleton obj2 = null;
//reflection api can change accessibility of a constructor at run time.
        Constructor[] constructors = Singleton.class.getDeclaredConstructors();

        for (Constructor constructor : constructors) {
            constructor.setAccessible(true);
            obj2 = (Singleton) constructor.newInstance();

        }
        System.out.println(obj1.hashCode() + " ---" + obj3.hashCode() + "---" + obj2.hashCode());


        //NOTE:
        //to overcome from reflection , we can create enum instead of singleton to represent singleton properties


        //Factory design pattern

        FactoryManager factoryManager = new FactoryManager();
        Validator dataValidator = factoryManager.validate("dataValidator");
        dataValidator.validateMethod();

    }

}
