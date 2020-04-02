package com.CGM.exercise;

import com.CGM.exercise.helper.Initializer;
import com.CGM.exercise.service.QuestionService;
import com.CGM.exercise.service.QuestionValidator;

/** entry point for test
 * @version 1.0
 */
public class Client {

    public static void main(String[] args)  {
        Initializer initializer = new Initializer(new QuestionService(new QuestionValidator()));
        initializer.readTheQuestion();
    }
}
