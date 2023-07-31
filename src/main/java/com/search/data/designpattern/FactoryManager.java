package com.search.data.designpattern;

import com.search.data.designpattern.impl.DataValidatorImpl;
import com.search.data.designpattern.impl.TypeValidatorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

//@Component
public class FactoryManager {

    @Autowired
    private DataValidatorImpl dataValidator;

    @Autowired
    private TypeValidatorImpl typeValidator;


    public Validator validate(String validatorType){

        if(StringUtils.isEmpty(validatorType))
            return null;
        switch (validatorType){
            case "dataValidator":
                return new DataValidatorImpl();//dataValidator;
            case "typeValidator":
                return typeValidator;
            default:
                throw new RuntimeException();

        }

    }
}
