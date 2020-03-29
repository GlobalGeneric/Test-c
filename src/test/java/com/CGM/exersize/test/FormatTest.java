package com.CGM.exersize.test;

import com.CGM.exercise.exception.QuestionFormatException;
import com.CGM.exercise.exception.SizeLimitException;
import com.CGM.exercise.helper.Initializer;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

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

    @Test(expected = QuestionFormatException.class)
    public void checkForQuestionSeprator() {
        long index = sampleWrongInput_For_Question.indexOf('?');
        Assert.assertNotEquals(-1, index);
    }

    @Test(expected = SizeLimitException.class)
    public void question_Should_fallowedWith_questionMark() throws SizeLimitException,
            QuestionFormatException {
        initializer.extraxtQuestion(sampleWrongInput_For_Question);

    }

    @Test(expected = SizeLimitException.class)
    public void testingTheSizeOfQuestion_shouldLessThan255() throws SizeLimitException {
        int index = questionWithMoreThan255.indexOf("?");
        if (index > allowedSize) {
            throw new SizeLimitException("The Input Question Size Exceed 255 chars");
        }
    }

    @Test(expected = SizeLimitException.class)
    public void testingTheSizeOfAnswer_shouldLessThan255() throws SizeLimitException, QuestionFormatException {

        List<String> answers = new ArrayList<>();
            answers = initializer.extractAnswers(answerWithMoreThan255);
//            fail();
//        } catch (QuestionFormatException | SizeLimitException e) {
//            assertEquals (e.getMessage() ,"The Answer/s Should Have Follow Right Structure.");
//        }
//        for (int i = 0; i < answers.size(); i++) {
//            if (answers.get(i).length() > allowedSize)
//                try {
//                    throw new SizeLimitException("The Input Answer Size Exceed 255 chars");
//                } catch (SizeLimitException e) {
//                    assert (e.getMessage() == "The Answer/s Should Have Follow Right Structure.");
//                }
//        }
//        assert (1 == 1);
    }
}
