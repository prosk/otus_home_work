package com.myprog.wpooh;

public class TestSystemApp {

    private static Question[] initQuestions() {
        Question[] questions = new Question[4];
        questions[0] = new Question("Куда попал из ружья Пятачок?");
        questions[1] = new Question("Что Винни-Пух любит больше всего?");
        questions[2] = new Question("Поступает мудро тот, кто ходит в гости: ");
        questions[3] = new Question("Винни-Пух и Пятачок пришли в гости к: ");

        questions[0].setChoices(new String[]{"В небо", "В Винни-Пуха", "В воздушный шарик"});
        questions[1].setChoices(new String[]{"Пельмени", "Варенье", "Хлеб", "Мед", "Тирамису"});
        questions[2].setChoices(new String[]{"По утрам", "По вечерам"});
        questions[3].setChoices(new String[]{"Зайцу", "Кролику", "Ослику Иа"});

        questions[0].setRightChoiceNum(3);
        questions[1].setRightChoiceNum(4);
        questions[2].setRightChoiceNum(1);
        questions[3].setRightChoiceNum(2);

        return questions;
    }

    public static void main(String[] args) {
        Question[] questions = initQuestions();
        TestSystem ts = new TestSystem("Винни-Пух и все-все-все", questions);
        ts.runTest();
    }
}
