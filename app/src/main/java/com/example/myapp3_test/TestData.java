package com.example.myapp3_test;

public class TestData {
    public String Question;
    public String Answer1;
    public String Answer2;
    public String Answer3;
    public String Answer4;
    public String RealAnswer;

    public TestData(String question, String answer1, String answer2, String answer3, String answer4, String realAnswer) {
        Question = question;
        Answer1 = answer1;
        Answer2 = answer2;
        Answer3 = answer3;
        Answer4 = answer4;
        RealAnswer = realAnswer;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getAnswer1() {
        return Answer1;
    }

    public void setAnswer1(String answer1) {
        Answer1 = answer1;
    }

    public String getAnswer2() {
        return Answer2;
    }

    public void setAnswer2(String answer2) {
        Answer2 = answer2;
    }

    public String getAnswer3() {
        return Answer3;
    }

    public void setAnswer3(String answer3) {
        Answer3 = answer3;
    }

    public String getAnswer4() {
        return Answer4;
    }

    public void setAnswer4(String answer4) {
        Answer4 = answer4;
    }

    public String getRealAnswer() {
        return RealAnswer;
    }

    public void setRealAnswer(String realAnswer) {
        RealAnswer = realAnswer;
    }
}
