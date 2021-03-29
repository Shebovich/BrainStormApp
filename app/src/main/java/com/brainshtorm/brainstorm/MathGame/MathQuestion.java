package com.brainshtorm.brainstorm.MathGame;

import java.util.ArrayList;
import java.util.List;

public class MathQuestion {
    private List<MathElement> MathElements;

    public MathQuestion(int sizeOfQuestionElemets) {
        MathElements = new ArrayList<MathElement>(sizeOfQuestionElemets);
    }

    public void addElement(MathElement MathElement) {
        MathElements.add(MathElement);
    }

    public List<MathElement> getElements() {
        return MathElements;
    }

    public int size() {
        return MathElements.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (MathElement MathElement : MathElements) {
            sb.append(MathElement);
        }
        return sb.toString().trim();
    }
}
