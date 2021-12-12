package com.myprog.unittests;

import com.myprog.unittests.assertions.*;
import com.myprog.unittests.game.*;


public class HomeWork {
    /*
        Заготовка для ДЗ представляет собой игру в кости.
        При вызове game.playGame(), два игрока кидают кости.
        Выигрывает игрок, у кого результат (1-6) больше

        Написать тесты (минимум 4) на классы DiceImpl и Game.
        Тесты должны найти не менее двух ошибок.

        Информацию о пройденном тесте предлагается выводить в System.out, а об упавшем в System.err
     */

    public static void testDiceForNotConstant() {
        String scenario = "Проверка, что бросание кости не возвращает одно и то же значение";
        int RollCnt = 100;
        try {
            Dice dice = new DiceImpl();
            int firstValue = dice.roll();
            int i = 0;
            while (dice.roll() == firstValue && i < RollCnt) i++;
            if (i == RollCnt) {
                throw new AssertionError("Бросание кости дает одинаковое значение " + firstValue);
            }
            System.out.printf("\"%s\" passed %n", scenario);
        }catch(Throwable e) {
            System.err.printf("\"%s\" fails with message \"%s\" %n", scenario, e.getMessage());
        }
    }


    public static void testDiceForRangeCorrectness() {
        String scenario = "Проверка, что бросание кости возвращает числа от 1 до 6";
        int RollCnt = 100;
        try {
            Dice dice = new DiceImpl();
            for (int i = 1; i <= RollCnt; i++) {
                Assertions.assertRange(1, 6, dice.roll());
            }
            System.out.printf("\"%s\" passed %n", scenario);
        }catch(Throwable e) {
            System.err.printf("\"%s\" fails with message \"%s\" %n", scenario, e.getMessage());
        }
    }

    public static void testGameForWinnerSelectionForSameValue() {
        String scenario = "Проверка, что игра не определяет победителя, когда у 2-х игроков выпало одно значение";

        StringBuffer winners = new StringBuffer();

        Dice fakeDice = new Dice() {
            @Override
            public int roll() {
                return 2;
            }
        };

        GameWinnerPrinter stringWinnerPrinter = new GameWinnerPrinter() {
            @Override
            public void printWinner(Player winner) {
                winners.append(winner.getName());
            }
        };

        try {
            Game game = new Game(fakeDice, stringWinnerPrinter);
            String firstPlayerName = "Вася", secondPlayerName = "Игорь";
            game.playGame(new Player(firstPlayerName), new Player(secondPlayerName));

            String WinnerName = winners.toString();

            if (WinnerName.equals(firstPlayerName) || WinnerName.equals(secondPlayerName)) {
                throw new AssertionError("В игре есть победитель, хотя у 2-х игроков выпало одинаковое значение");
            }
            System.out.printf("\"%s\" passed %n", scenario);
        }catch(Throwable e) {
            System.err.printf("\"%s\" fails with message \"%s\" %n", scenario, e.getMessage());
        }
    }

    public static void testGameForWinnerSelectionForDiffValue() {
        String scenario = "Проверка, что игра верно определяет победителя, когда у 2-го игрока значение больше";

        StringBuffer winners = new StringBuffer();

        Dice fakeDice = new Dice() {
            int CurrValue = 1;
            @Override
            public int roll() {
                return CurrValue++; // для следующего вызова даст большее значение
            }
        };

        GameWinnerPrinter stringWinnerPrinter = new GameWinnerPrinter() {
            @Override
            public void printWinner(Player winner) {
                winners.append(winner.getName());
            }
        };

        try {
            Game game = new Game(fakeDice, stringWinnerPrinter);
            String firstPlayerName = "Вася", secondPlayerName = "Игорь";
            game.playGame(new Player(firstPlayerName), new Player(secondPlayerName));

            String WinnerName = winners.toString();

            if (!WinnerName.equals(secondPlayerName)) {
                throw new AssertionError("В игре неверно определен победитель");
            }
            System.out.printf("\"%s\" passed %n", scenario);
        }catch(Throwable e) {
            System.err.printf("\"%s\" fails with message \"%s\" %n", scenario, e.getMessage());
        }
    }


    public static void main(String[] args) {
        System.out.println("Поехали!");

        testDiceForRangeCorrectness();
        testDiceForNotConstant();
        testGameForWinnerSelectionForSameValue();
        testGameForWinnerSelectionForDiffValue();
    }
    
}