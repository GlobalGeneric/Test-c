package com.CGM.exercise.service;

import com.CGM.exercise.exception.AnswerFormatException;
import com.CGM.exercise.exception.QuestionFormatException;
import com.CGM.exercise.exception.SizeLimitException;

import java.util.List;

/**
 * Input Validator
 *
 * @version 1.0
 */

public interface IQuestionValidator {
    /**
     * Perform Answer Extraction
     *
     * @param input is sample input string
     * @return return extracted list of answers
     */
    List<String> extractAnswers(String input) throws SizeLimitException, AnswerFormatException, QuestionFormatException;

    /**
     * Perform Question Extraction from given input
     *
     * @param input is sample input string
     * @return return the extracted question as a result
     */
    String extraxtQuestion(String input) throws SizeLimitException, QuestionFormatException, AnswerFormatException;

    /**
     * Perform overall structural validation due to input string
     *
     * @param input is sample input for validation
     */
    void validateFormat(String input) throws SizeLimitException, QuestionFormatException, AnswerFormatException;
}
