package com.myprog.wpooh;

import java.util.Scanner;

public class SimpleTestSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] questions = new String[]{
                "Куда попал из ружья Пятачок?",
                "Что Винни-Пух любит больше всего?",
                "Поступает мудро тот, кто ходит в гости: ",
                "Винни-Пух и Пятачок пришли в гости к: "
        };

        String[][] choices = new String[][] {
                {"В небо", "В Винни-Пуха", "В воздушный шарик"},
                {"Пельмени", "Варенье", "Хлеб", "Мед", "Тирамису"},
                {"По утрам", "По вечерам"},
                {"Зайцу", "Кролику", "Ослику Иа"}
        };

        int[] rightAnswers = new int[] {3, 4, 1, 2};

        System.out.println("Привет! Вам нужно будет пройти тест из " + questions.length + " вопросов. Нажмите Enter для продолжения...");
        sc.nextLine();

        int rightAnswersCount = 0;
        int j;
        int currAnswer;

        for(int i = 1; i <= questions.length; i++) {
            System.out.println("Вопрос № " + i + ". " + questions[i-1]);
            for(j = 1; j <= choices[i-1].length; j++) {
                System.out.println("" + j + ". " + choices[i-1][j-1]);
            }

            System.out.print("Номер вашего ответа: ");
            currAnswer = sc.nextInt();

            if (currAnswer == rightAnswers[i-1]) {
                rightAnswersCount++;
            }
            System.out.println();
        }

        System.out.printf("Доля верных ответов: %.3f", (double)rightAnswersCount / questions.length);

    }
}
