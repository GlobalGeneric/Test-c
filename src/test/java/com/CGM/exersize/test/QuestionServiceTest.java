package com.CGM.exersize.test;

import com.CGM.exercise.exception.AnswerFormatException;
import com.CGM.exercise.exception.NotFoundException;
import com.CGM.exercise.exception.QuestionFormatException;
import com.CGM.exercise.exception.SizeLimitException;
import com.CGM.exercise.service.QuestionService;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class QuestionServiceTest {

    private QuestionService questionService = new QuestionService();
    private Map<String, List<String>> questionList = new HashMap<>();
    private String sampleInput = "What is Peters favorite food? \"Pizza\" \"Spaghetti\" \"Ice cream\"";
    private String sampleInput2 = "What is Peters favorite food2? \"Pizza2\" \"Spaghetti2\" \"Ice cream2\"";


    @Test(expected = NotFoundException.class)
    public void testing_For_none_available_question() throws NotFoundException {

        String question = "not valid question in program?";
        questionService.add(questionList, sampleInput);
        List<String> expected = initAnswer();
        List<String> ActualAnswers = questionService.find(questionList, question);
        assertThat(ActualAnswers, is(expected));

    }

    @Test
    public void testing_For_find_question_answers() throws NotFoundException {

        String question = "What is Peters favorite food?";
        questionService.add(questionList, sampleInput);
        List<String> expected = initAnswer();
        List<String> ActualAnswers = questionService.find(questionList, question);
        assertThat(ActualAnswers, is(expected));

    }

    @Test
    public void testing_For_addNew_question() {

        Map<String, List<String>> map;
        questionService.add(questionList, sampleInput);
        assertThat(questionList.size(), is(1));
        questionService.add(questionList, sampleInput2);
        assertThat(questionList.size(), is(2));
    }

    @Test
    public void testingForEqualityOfExpectedAnswers_ShouldBeEqual() throws SizeLimitException, QuestionFormatException, AnswerFormatException {

        List<String> expected = initAnswer();
        List<String> actual = questionService.extractAnswers(sampleInput);
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testingForEqualityOfExtractedQuestion() throws SizeLimitException, QuestionFormatException, AnswerFormatException {
        String expected = "What is Peters favorite food?";
        String actual = questionService.extraxtQuestion(sampleInput);
        Assert.assertEquals(expected, actual);
    }

    private List<String> initAnswer() {
        List<String> expected = new ArrayList<>();
        expected.add("Pizza");
        expected.add("Spaghetti");
        expected.add("Ice cream");
        return expected;
    }
}
