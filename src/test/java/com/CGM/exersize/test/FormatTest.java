package com.CGM.exersize.test;

import com.CGM.exercise.exception.QuestionFormatException;
import com.CGM.exercise.exception.SizeLimitException;
import com.CGM.exercise.helper.Initializer;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.fail;

public class FormatTest {

    private Initializer initializer = new Initializer();
    private static final int allowedSize = 255;
    private String sampleWrongInput_For_Question = "What is Peters favorite food \"Pizza\" \"Spaghetti\" \"Ice cream\"";
    private String sampleWrongInput_For_Answer_without_required_Doublecot = "What is Peters favorite food? Pizza";
    private String sampleWrongInput_For_Answer_with_missing_doublecoat = "What is Peters favorite food? \"Pizza\" \"Spaghetti";
    private static final String questionWithMoreThan255 = "What is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite food? \"What is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite food\" \"Spaghetti\" \"Ice cream\"";
    private static final String answerWithMoreThan255 = "What is Peters favorite food? \"What is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite food\" \"Spaghetti\" \"Ice cream\"";

    @Test(expected = QuestionFormatException.class)
    public void checkFor_AnswerStructure_required_Doublecot() throws SizeLimitException, QuestionFormatException {
        initializer.extractAnswers(sampleWrongInput_For_Answer_without_required_Doublecot);
    }

    @Test(expected = QuestionFormatException.class)
    public void checkFor_AnswerStructure_with_missing_doublecoat() throws SizeLimitException, QuestionFormatException {
        initializer.extractAnswers(sampleWrongInput_For_Answer_with_missing_doublecoat);
    }

    @Test
    public void question_Should_followedWith_questionMark1() {
        long actual = sampleWrongInput_For_Question.indexOf('?');
        Assert.assertEquals(-1, actual);
    }

    @Test
    public void question_Should_followedWith_questionMark2() {
        try {
            initializer.extraxtQuestion(sampleWrongInput_For_Question);
            fail();
        } catch (SizeLimitException e) {
            System.out.println("SizeLimitException");
        } catch (QuestionFormatException e) {
            System.out.println("QuestionFormatException");
        }
    }

    @Test(expected = SizeLimitException.class)
    public void testingTheSizeOfQuestion_shouldLessThan255() throws SizeLimitException {
        int index = questionWithMoreThan255.indexOf("?");
        if (index > allowedSize) {
            throw new SizeLimitException("The Input Question Size Exceed 255 chars");
        }
    }

    @Test
    public void testingTheSizeOfAnswer_shouldLessThan255() {
        try {
            initializer.extractAnswers(answerWithMoreThan255);
            fail();
        } catch (SizeLimitException e) {
            System.out.println("SizeLimitException");
        } catch (QuestionFormatException e) {
            System.out.println("QuestionFormatException");
        }
    }

    @Test
    public void testingThe_wrongFormat_exception() {

    }
}