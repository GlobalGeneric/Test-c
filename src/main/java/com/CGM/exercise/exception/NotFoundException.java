package com.CGM.exercise.exception;

/**exception is for if the question is not found in given list
 * @version 1.0
 */
public class NotFoundException extends Exception {
    public NotFoundException(String input) {
        super(input);
    }

}
