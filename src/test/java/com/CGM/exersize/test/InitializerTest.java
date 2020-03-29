package com.CGM.exersize.test;

import com.CGM.exercise.exception.QuestionFormatException;
import com.CGM.exercise.exception.SizeLimitException;
import com.CGM.exercise.helper.Initializer;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class InitializerTest {

    private Initializer initializer = new Initializer();
    private String sampleInput = "What is Peters favorite food? \"Pizza\" \"Spaghetti\" \"Ice cream\"";

    @Test
    public void testingForEqualityOfExpectedAnswers_ShouldBeEqual() throws SizeLimitException, QuestionFormatException {

        List<String> expected = new ArrayList<>();
        expected.add("Pizza");
        expected.add("Spaghetti");
        expected.add("Ice cream");
        List<String> result = initializer.extractAnswers(sampleInput);
        Assert.assertEquals(expected, result);

    }

    @Test
    public void testingForEqualityOfExtractedQuestion() throws SizeLimitException, QuestionFormatException {
        String expected = "What is Peters favorite food?";
        String result = initializer.extraxtQuestion(sampleInput);
        Assert.assertEquals(expected, result);
    }
}
