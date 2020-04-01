package com.CGM.exercise.service;

import com.CGM.exercise.exception.AnswerFormatException;
import com.CGM.exercise.exception.NotFoundException;
import com.CGM.exercise.exception.QuestionFormatException;
import com.CGM.exercise.exception.SizeLimitException;
import com.CGM.exercise.helper.Color;

import java.util.List;
import java.util.Map;

public class QuestionService implements IQuestionService {

    IQuestionValidator questionValidator = new QuestionValidator();

    public QuestionService() {
    }

    @Override
    public Map<String, List<String>> add(Map<String, List<String>> questionList, String input) {

        try {
            questionValidator.validateFormat(input);
            questionList.put(questionValidator.extraxtQuestion(input), questionValidator.extractAnswers(input));
            return questionList;
        } catch (AnswerFormatException e) {
            System.out.println(Color.RED + "AnswerFormatException: The Answer Should Have Follow Right Structure.");
            System.out.print(Color.RESET);
        } catch (QuestionFormatException e) {
            if (e.getMessage().contains("\"")) {
                System.out.println(Color.RED + "QuestionFormatException: The question should not contain char (\").");
                System.out.print(Color.RESET);
            } else {
                System.out.println(Color.RED + "QuestionFormatException: The question should followed with \"?\" char.");
                System.out.print(Color.RESET);
            }
        } catch (SizeLimitException e) {
            if (e.getMessage().contains("Answer")) {
                System.out.println(Color.RED + "SizeLimitException: The Input Answer Size Exceed 255 char");
                System.out.print(Color.RESET);
            } else {
                System.out.println(Color.RED + "SizeLimitException: The Input Question Size Exceed 255 char");
                System.out.print(Color.RESET);
            }
        }
        return null;
    }

    @Override
    public List<String> find(Map<String, List<String>> questionList, String questionToFind) throws NotFoundException {

        if (questionList.get(questionToFind) == null)
            throw new NotFoundException("the answer to life, universe and everything is 42");
        return questionList.get(questionToFind);
    }
}
