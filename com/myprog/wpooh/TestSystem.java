package com.myprog.wpooh;

import java.util.Scanner;

class TestSystem {
    private String subjectArea;
    private Question[] questions;

    public TestSystem(String subjectArea, Question[] questions) {
        this.subjectArea = subjectArea;
        this.questions = questions;
    }

    /**
     * Запуск теста
     */
    public void runTest() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Привет! Вам нужно будет пройти тест из " + questions.length +
                " вопросов на тему '" + subjectArea + "'. Нажмите Enter для продолжения...");
        sc.nextLine();

        int rightAnswersCount = 0;
        int currAnswer;

        for(int i = 1; i <= questions.length; i++) {
            System.out.println("Вопрос " + i + ". " + questions[i-1]);

            System.out.print("Номер вашего ответа: ");
            currAnswer = sc.nextInt();

            if (currAnswer == questions[i-1].getRightChoiceNum()) {
                rightAnswersCount++;
            }
            System.out.println();
        }

        double rightAnswersPercent = (double) rightAnswersCount * 100.0 / questions.length;
        System.out.printf("Процент верных ответов: %.3f", rightAnswersPercent);
    }
}
