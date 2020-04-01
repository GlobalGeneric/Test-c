package com.CGM.exercise;

import com.CGM.exercise.exception.SizeLimitException;
import com.CGM.exercise.helper.Color;
import com.CGM.exercise.helper.Initializer;

/** entry point for test
 * @version 1.0
 */
public class Client {

    public static void main(String[] args)  {
        Initializer initializer = new Initializer();
        initializer.readTheQuestion();
    }
}
