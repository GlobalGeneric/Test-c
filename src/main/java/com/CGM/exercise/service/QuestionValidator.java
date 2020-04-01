package com.CGM.exercise.service;

import com.CGM.exercise.exception.AnswerFormatException;
import com.CGM.exercise.exception.QuestionFormatException;
import com.CGM.exercise.exception.SizeLimitException;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class QuestionValidator implements IQuestionValidator {

    public List<String> extractAnswers(String input) throws SizeLimitException, AnswerFormatException, QuestionFormatException {
        List<String> answers = new ArrayList<>();
        int index = input.indexOf("?");
//        validateFormat(input);
        int answerBeginIndex = index + 3;
        for (int i = answerBeginIndex; i <= input.length() - 1; i++) {
            if (input.charAt(i) == '"') {
                int lenth = i - answerBeginIndex;
                if (lenth > 255)
                    throw new SizeLimitException("SizeLimitException: The Input Answer Size Exceed 255 char");
//                if (lenth == 0)
//                    throw new SizeLimitException("SizeLimitException: Answer with length=0 not allowed.");
                String extractedAnswer = input.substring(answerBeginIndex, i);
                answers.add(extractedAnswer);
                answerBeginIndex = i + 3;
                if (answerBeginIndex < input.length())
                    if (input.charAt(answerBeginIndex) == '"')
                        throw new AnswerFormatException("AnswerFormatException: The Answer/s Should Have Follow Right Structure.");
                i = i + 3;
            }
        }
        return answers;
    }

    public String extraxtQuestion(String input) throws SizeLimitException, QuestionFormatException, AnswerFormatException {
        int index = input.indexOf("?");
//        validateFormat(input);
        String question = input.substring(0, index + 1);
        if (question.contains("\""))
            throw new QuestionFormatException("QuestionFormatException: The question should not contain char \" ");
        return question;
    }

    public void validateFormat(String input) throws SizeLimitException, QuestionFormatException, AnswerFormatException {
        int index = input.indexOf("?");
        int answerCount = StringUtils.countMatches(input, "\"") ;
        int spaceCount = StringUtils.countMatches(input, "\" \"");
        int answerStart = StringUtils.countMatches(input, "? \"");

        if (index == 0 || index == -1)
            throw new QuestionFormatException("QuestionFormatException: The question should follow right structure.");
        if (index > 255)
            throw new SizeLimitException("SizeLimitException: The Input Question Size Exceed 255 char");
        if (answerCount % 2 != 0
                || answerCount == 0  // the " count shloud be even
                || input.contains("\"\"") // empty answer is ban
                || spaceCount != answerCount/2 - 1 //answer seprator should (" ")
                || answerStart !=1)  //answer should strat with (? ")
            throw new AnswerFormatException("AnswerFormatException: The Answer/s Should Have Follow Right Structure.");


    }
}
