package com.CGM.exercise.service;

import com.CGM.exercise.exception.NotFoundException;

import java.util.List;
import java.util.Map;

public interface IQuestionService {

    Map<String, List<String>> add(Map<String, List<String>> newQuestion, String input);

    List<String> find(Map<String, List<String>> questionList, String questionToFind) throws NotFoundException;
}
