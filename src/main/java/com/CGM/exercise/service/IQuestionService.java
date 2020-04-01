package com.CGM.exercise.service;

import com.CGM.exercise.exception.NotFoundException;

import java.util.List;
import java.util.Map;

/**
 * Perform Action like "add" and "find" on questions list
 *
 * @version 1.0
 */
public interface IQuestionService {

    /**
     * perform adding new input to the existing list of questions and answers
     * @param questionList is existing list of question with answers
     * @param input is the given input that will be add to existing question list
     * @return this is the final list of question with answer
     */
    Map<String, List<String>> add(Map<String, List<String>> questionList, String input);

    /**
     * perform finding question's answers
     * @param questionList is existing list of question with answers
     * @param questionToFind is the question that must look for possible answers
     * @return return the possible list of answers for given question
     * @throws NotFoundException is possible exception if question is not valid
     */
    List<String> find(Map<String, List<String>> questionList, String questionToFind) throws NotFoundException;
}
