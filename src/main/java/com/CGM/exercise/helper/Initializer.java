package com.CGM.exercise.helper;

import com.CGM.exercise.entity.Question;
import com.CGM.exercise.exception.QuestionFormatException;
import com.CGM.exercise.exception.SizeLimitException;
import org.apache.commons.lang3.StringUtils;

import java.util.*;


public class Initializer {

    Scanner in = new Scanner(System.in);

    private Map<String, List<String>> questionList = new HashMap<>();

    private List<Question> questions = new ArrayList<>();
    private Question newQuestion = new Question();

    public void getTheQuestion() throws SizeLimitException {
        System.out.println("1- enter new qusetion with answers");
        System.out.println("2- ask a qustion");
        System.out.print("!please choose => ");
        Scanner in = new Scanner(System.in);
        int number = in.nextInt();

        if (number == 1) {
            System.out.println("please enter qustion with answer/s");
            String input1 = in.nextLine();
            String input = in.nextLine();

            try {
                questionList.put(extraxtQuestion(input), extractAnswers(input));
            } catch (QuestionFormatException e) {
                System.out.println("question format exception");
            }
        }
        if (number == 2) {
            System.out.println("please ask question/s");
        }
    }

    public List<String> extractAnswers(String input) throws SizeLimitException, QuestionFormatException {
        List<String> answers = new ArrayList<>();
        int index = input.indexOf("?");
//        int answerCount = StringUtils.countMatches(input, "\"") / 2;
//        if (answerCount == 0 || answerCount % 2 != 0)
//            throw new QuestionFormatException("The Answer/s Should Have Follow Right Structure.");

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

    public String extraxtQuestion(String input) throws SizeLimitException, QuestionFormatException {
        int index = input.indexOf("?");
        validateFormat(input);
        String question = input.substring(0, index + 1);
        return question;
    }

    public void validateFormat(String input) throws SizeLimitException, QuestionFormatException {

        int index = input.indexOf("?");
        int answerCount = StringUtils.countMatches(input, "\"") / 2;
        validateLenth(index, "The Input Question Size Exceed 255 char");
        if (answerCount == 0 || answerCount % 2 != 0) {
            throw new QuestionFormatException("The Answer/s Should Have Follow Right Structure.");
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
