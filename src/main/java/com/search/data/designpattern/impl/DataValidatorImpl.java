package com.search.data.designpattern.impl;

import com.search.data.designpattern.Validator;
import org.springframework.stereotype.Component;

@Component
public class DataValidatorImpl implements Validator {
    @Override
    public void validateMethod() {
        System.out.println("inside DataValidatorImpl");
    }
}
