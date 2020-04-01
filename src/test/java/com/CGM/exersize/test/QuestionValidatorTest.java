package com.CGM.exersize.test;

import com.CGM.exercise.exception.AnswerFormatException;
import com.CGM.exercise.exception.QuestionFormatException;
import com.CGM.exercise.exception.SizeLimitException;
import com.CGM.exercise.helper.Color;
import com.CGM.exercise.service.IQuestionValidator;
import com.CGM.exercise.service.QuestionService;
import com.CGM.exercise.service.QuestionValidator;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;

public class QuestionValidatorTest {

    private IQuestionValidator questionValidator = new QuestionValidator();

    private static final int allowedSize = 255;
    private static final String sampleValidInput = "What is Peters favorite food? \"Pizza\" \"Spaghetti\" \"Ice cream\"";
    private static final String sampleWrongInput_For_Question = "What is Peters favorite food \"Pizza\" \"Spaghetti\" \"Ice cream\"";
    private static final String sampleWrongInput_For_Answer_without_required_Doublecot = "What is Peters favorite food? Pizza";
    private static final String sampleWrongInput_For_Answer_with_missing_doublecoat = "What is Peters favorite food? \"Pizza\" \"Spaghetti";
    private static final String questionWithMoreThan255 = "What is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite food? \"What is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite food\" \"Spaghetti\" \"Ice cream\"";
    private static final String answerWithMoreThan255 = "What is Peters favorite food? \"What is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite food\" \"Spaghetti\" \"Ice cream\"";

    @Test
    public void testingFor_validateFormat() throws SizeLimitException, QuestionFormatException, AnswerFormatException {
        String wrongInputWithMoreSpac = "What is Peters favorite food? \"Pizza\" \"Spaghetti\" \"Ice cream\"";
        questionValidator.validateFormat(wrongInputWithMoreSpac);
    }

    @Test(expected = AnswerFormatException.class)
    public void testingFor_DobleSpace_between_answers() throws SizeLimitException, QuestionFormatException, AnswerFormatException {
        String wrongInputWithMoreSpac = "What is Peters favorite food? \"Pizza\"  \"Spaghetti\" \"Ice cream\"";
        questionValidator.extractAnswers(wrongInputWithMoreSpac);
    }

    @Test
    public void testingForEqualityOfExpectedAnswers_ShouldBeEqual() throws SizeLimitException, QuestionFormatException, AnswerFormatException {

        List<String> expected = initAnswer();
        List<String> actual = questionValidator.extractAnswers(sampleValidInput);
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testingForEqualityOfExtractedQuestion() throws SizeLimitException, QuestionFormatException, AnswerFormatException {
        String expected = "What is Peters favorite food?";
        String actual = questionValidator.extraxtQuestion(sampleValidInput);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = AnswerFormatException.class)
    public void checkFor_AnswerStructure_required_Doublecot() throws SizeLimitException, QuestionFormatException, AnswerFormatException {
        questionValidator.validateFormat(sampleWrongInput_For_Answer_without_required_Doublecot);
    }

    @Test(expected = AnswerFormatException.class)
    public void checkFor_AnswerStructure_with_missing_doublecoat() throws SizeLimitException, QuestionFormatException, AnswerFormatException {
        questionValidator.validateFormat(sampleWrongInput_For_Answer_with_missing_doublecoat);
    }

    @Test
    public void question_Should_followedWith_questionMark1() {
        long actual = sampleWrongInput_For_Question.indexOf('?');
        Assert.assertEquals(-1, actual);
    }

    @Test
    public void question_Should_followedWith_questionMark2() throws AnswerFormatException, SizeLimitException {
        try {
            questionValidator.validateFormat(sampleWrongInput_For_Question);
            fail();
        } catch (QuestionFormatException e) {
            System.out.println("QuestionFormatException");
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
    public void testingTheSizeOfAnswer_shouldLessThan255() throws QuestionFormatException, AnswerFormatException {
        try {
            questionValidator.extractAnswers(answerWithMoreThan255);
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

    private List<String> initAnswer() {
        List<String> expected = new ArrayList<>();
        expected.add("Pizza");
        expected.add("Spaghetti");
        expected.add("Ice cream");
        return expected;
    }
}