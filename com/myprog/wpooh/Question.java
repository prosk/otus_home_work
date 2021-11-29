package com.myprog.wpooh;

class Question {
    private String text;
    private String[] choices;
    private int rightChoiceNum;

    public Question(String text) {
        this.text = text;
    }

    public void setChoices(String[] choices) {
        this.choices = choices;
    }

    public void setRightChoiceNum(int rightChoiceNum) {
        this.rightChoiceNum = rightChoiceNum;
    }

    public int getRightChoiceNum() {
        return rightChoiceNum;
    }

    @Override
    public String toString() {
        String result = text;
        int i = 1;
        for(String choice: choices) {
            result = result + '\n' + i + ". " + choice;
            i++;
        }
        return result;
    }
}

