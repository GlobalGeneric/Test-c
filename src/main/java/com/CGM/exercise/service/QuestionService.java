package com.CGM.exercise.service;

import com.CGM.exercise.exception.AnswerFormatException;
import com.CGM.exercise.exception.NotFoundException;
import com.CGM.exercise.exception.QuestionFormatException;
import com.CGM.exercise.exception.SizeLimitException;
import com.CGM.exercise.helper.Color;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class QuestionService implements IQuestionService {

    Scanner in = new Scanner(System.in);

    public QuestionService() {
    }

    @Override
    public Map<String, List<String>> add(Map<String, List<String>> questionList, String input) {
        try {
            try {
                questionList.put(extraxtQuestion(input), extractAnswers(input));
            } catch (AnswerFormatException e) {
                System.out.println(Color.RED + "AnswerFormatException: answer format exception");
                System.out.print(Color.RESET);
            }
            return questionList;
        } catch (QuestionFormatException e) {
            System.out.println(Color.RED + "QuestionFormatException: The question should followed with \"?\" char");
            System.out.print(Color.RESET);
        } catch (SizeLimitException e) {
            System.out.println(Color.RED + "SizeLimitException: you shlould enter with less than 255 char");
            System.out.print(Color.RESET);
        }
        return null;
    }

    @Override
    public List<String> find(Map<String, List<String>> questionList, String questionToFind) throws NotFoundException {

        if (questionList.get(questionToFind) == null) {
            throw new NotFoundException("the answer to life, universe and everything is 42");
        }
        return questionList.get(questionToFind);
    }

    public List<String> extractAnswers(String input) throws SizeLimitException, AnswerFormatException, QuestionFormatException {
        List<String> answers = new ArrayList<>();
        int index = input.indexOf("?");
        validateFormat(input);
        int answerBeginIndex = index + 3;
        for (int i = answerBeginIndex; i <= input.length() - 1; i++) {
            if (input.charAt(i) == '"') {
                validateLenth(i - answerBeginIndex, "The Input Answer Size Exceed 255 char");
                String extractedAnswer = input.substring(answerBeginIndex, i);
//                validateFormat(input);
                answers.add(extractedAnswer);
                answerBeginIndex = i + 3;
                i = i + 3;
            }
        }
        return answers;
    }

    public String extraxtQuestion(String input) throws SizeLimitException, QuestionFormatException, AnswerFormatException {
        int index = input.indexOf("?");
        validateFormat(input);
        String question = input.substring(0, index + 1);
        return question;
    }

    public void validateFormat(String input) throws SizeLimitException, QuestionFormatException, AnswerFormatException {

        int index = input.indexOf("?");
        int answerCount = StringUtils.countMatches(input, "\"");
        validateLenth(index, "The Input Question Size Exceed 255 char");
        if (answerCount == 0) {
            throw new AnswerFormatException("The question Should have at least one answer.");
        }
        if (answerCount % 2 != 0) {
            throw new AnswerFormatException("The Answer/s Should Have Follow Right Structure.");
        }
        if (index == -1) {
            throw new QuestionFormatException("The question should followed with \"?\" char");
        }
    }

    private void validateLenth(int index, String s) throws SizeLimitException {
        if (index > 255) {
            throw new SizeLimitException(s);
        }
    }
}
