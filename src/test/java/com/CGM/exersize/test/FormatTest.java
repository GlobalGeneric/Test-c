package com.CGM.exersize.test;

import com.CGM.exercise.exception.AnswerFormatException;
import com.CGM.exercise.exception.QuestionFormatException;
import com.CGM.exercise.exception.SizeLimitException;
import com.CGM.exercise.helper.Color;
import com.CGM.exercise.service.QuestionService;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.fail;

public class FormatTest {

    //    private Initializer initializer = new Initializer();
    private QuestionService initializer = new QuestionService();

    private static final int allowedSize = 255;
    private String sampleWrongInput_For_Question = "What is Peters favorite food \"Pizza\" \"Spaghetti\" \"Ice cream\"";
    private String sampleWrongInput_For_Answer_without_required_Doublecot = "What is Peters favorite food? Pizza";
    private String sampleWrongInput_For_Answer_with_missing_doublecoat = "What is Peters favorite food? \"Pizza\" \"Spaghetti";
    private static final String questionWithMoreThan255 = "What is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite food? \"What is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite food\" \"Spaghetti\" \"Ice cream\"";
    private static final String answerWithMoreThan255 = "What is Peters favorite food? \"What is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite food\" \"Spaghetti\" \"Ice cream\"";


    @Test(expected = AnswerFormatException.class)
    public void checkFor_AnswerStructure_required_Doublecot() throws SizeLimitException, QuestionFormatException, AnswerFormatException {
        initializer.extractAnswers(sampleWrongInput_For_Answer_without_required_Doublecot);
    }

    @Test(expected = AnswerFormatException.class)
    public void checkFor_AnswerStructure_with_missing_doublecoat() throws SizeLimitException, QuestionFormatException, AnswerFormatException {
        initializer.extractAnswers(sampleWrongInput_For_Answer_with_missing_doublecoat);
    }

    @Test
    public void question_Should_followedWith_questionMark1() {
        long actual = sampleWrongInput_For_Question.indexOf('?');
        Assert.assertEquals(-1, actual);
    }

    @Test
    public void question_Should_followedWith_questionMark2() throws AnswerFormatException, SizeLimitException {
        try {
            initializer.extraxtQuestion(sampleWrongInput_For_Question);
            fail();
        } catch (QuestionFormatException e) {
            System.out.println(Color.RED + "QuestionFormatException");
        }
    }

    @Test(expected = SizeLimitException.class)
    public void testingTheSizeOfQuestion_shouldLessThan255() throws SizeLimitException {
        int index = questionWithMoreThan255.indexOf("?");
        if (index > allowedSize) {
            throw new SizeLimitException(Color.RED + "The Input Question Size Exceed 255 chars");
        }
    }

    @Test
    public void testingTheSizeOfAnswer_shouldLessThan255() throws QuestionFormatException {
        try {
            try {
                initializer.extractAnswers(answerWithMoreThan255);
            } catch (AnswerFormatException e) {
                e.printStackTrace();
            }
            fail();
        } catch (SizeLimitException e) {
            System.out.println(Color.RED + "SizeLimitException");
        }
    }

    @Test
    public void color_test() {

        System.out.print(Color.BLACK_BOLD);
        System.out.println("  . Black_Bold");
        System.out.print(Color.RESET);

        System.out.print(Color.YELLOW);
        System.out.print(Color.BLUE_BACKGROUND);
        System.out.println("YELLOW & BLUE");
        System.out.print(Color.RESET);

        System.out.print(Color.RED);
        System.out.println(Color.RED + "Red");
        System.out.print(Color.RESET);


    }
}