package com.CGM.exercise;

import com.CGM.exercise.exception.SizeLimitException;
import com.CGM.exercise.helper.Initializer;

public class Client {

    public static void main(String[] args) throws SizeLimitException {

        Initializer initializer = new Initializer();
        initializer.getTheQuestion();
    }
}
