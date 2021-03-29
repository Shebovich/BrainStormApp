package com.brainshtorm.brainstorm.MathGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MathGenerator {
    private static final int NUMBER_OF_MathQuestionS = 10;
    private static final int MIN_MathQuestion_ELEMENTS = 2;
    private static final int MAX_MathQuestion_ELEMENTS = 4;
    private static final int MIN_MathQuestion_ELEMENT_VALUE = 1;
    private static final int MAX_MathQuestion_ELEMENT_VALUE = 100;
    private final Random randomGenerator = new Random();
    public List<MathQuestion> getGeneratedRandomMathQuestions() {
        List<MathQuestion> randomMathQuestions = new ArrayList<MathQuestion>(NUMBER_OF_MathQuestionS);
        for (int i = 0; i < NUMBER_OF_MathQuestionS; i++) {
            int randomMathElementsCapacity = getRandomQuestionElementsCapacity();
            MathQuestion MathQuestion = new MathQuestion(randomMathElementsCapacity);
            for (int j = 0; j < randomMathElementsCapacity; j++) {
                boolean isLastIteration = j + 1 == randomMathElementsCapacity;

                MathElement MathElement = new MathElement();
                MathElement.setValue(getRandomQuestionElementValue());
                MathElement.setOperator(isLastIteration ? null
                        : Operator.values()[randomGenerator.nextInt(Operator.values().length)]);

                MathQuestion.addElement(MathElement);
            }
            randomMathQuestions.add(MathQuestion);
        }
        return randomMathQuestions;
    }
    private int getRandomQuestionElementsCapacity() {
        return getRandomIntegerFromRange(MIN_MathQuestion_ELEMENTS, MAX_MathQuestion_ELEMENTS);
    }

    private int getRandomQuestionElementValue() {
        return getRandomIntegerFromRange(MIN_MathQuestion_ELEMENT_VALUE, MAX_MathQuestion_ELEMENT_VALUE);
    }

    private int getRandomIntegerFromRange(int min, int max) {
        return randomGenerator.nextInt(max - min + 1) + min;
    }
}
