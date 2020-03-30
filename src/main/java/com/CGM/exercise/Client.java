package com.CGM.exercise;

import com.CGM.exercise.exception.SizeLimitException;
import com.CGM.exercise.helper.Color;
import com.CGM.exercise.helper.Initializer;

public class Client {

    public static void main(String[] args) throws SizeLimitException {

        Initializer initializer = new Initializer();

        initializer.readTheQuestion();

    }

    public void decorateMenu() {
        System.out.print(Color.RESET);
        System.out.println("===============================");
        System.out.println("|     MENU SELECTION DEMO     |");
        System.out.println("===============================");
        System.out.println("| Options:                    |");
        System.out.println("|     1. enter new qusetion   |");
        System.out.println("|     2. ask a qustion        |");
        System.out.println("|     3. Exit                 |");
        System.out.println("===============================");
    }
}
