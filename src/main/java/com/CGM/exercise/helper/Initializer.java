package com.CGM.exercise.helper;

import com.CGM.exercise.exception.NotFoundException;
import com.CGM.exercise.service.IQuestionService;
import com.CGM.exercise.service.QuestionService;

import java.util.*;


/** initial the consul structure for test
 * the property questionList is structure to keep all question
 * @version 1.0
 */
public class Initializer {
    private Map<String, List<String>> questionList = new HashMap<>();
    IQuestionService questionService = new QuestionService();

    public void readTheQuestion() {
        boolean flage = true;
        while (flage) {
            decorateMenu();
//            System.out.print("please select number (1-3)->");
            Scanner in = new Scanner(System.in);
            try {
                int number = in.nextInt();
                switch (number) {
                    case 1: {
                        System.out.println("please enter qustion with answer/s");
                        in.nextLine();
                        String input = in.nextLine();
                        questionService.add(questionList, input);
                        break;
                    }
                    case 2: {
                        System.out.println("enter exact question to find:");
                        in.nextLine();
                        String question = in.nextLine();
                        try {
//                        questionService.find(questionList, question).forEach(System.out::println);
                            questionService.find(questionList, question).forEach(
                                    answer -> System.out.println(Color.GREEN + " . " + answer));
                            System.out.print(Color.RESET);

                        } catch (NotFoundException e) {
                            System.out.println(Color.RED + "the answer to life, universe and everything is 42.");
                            System.out.print(Color.RESET);
                        }
                        break;
                    }
                    case 3:
                        System.exit(0);

                }
            } catch (InputMismatchException e) {
                System.out.println(Color.RED + "Please select number between 1-3.");
                System.out.print(Color.RESET);
            }
        }
    }
    private static void decorateMenu() {
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
