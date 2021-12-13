package com.myprog.unittests.game;

public class Game {

    private final Dice dice;
    private final GameWinnerPrinter winnerPrinter;

    public Game(Dice dice, GameWinnerPrinter winnerPrinter) {
        this.dice = dice;
        this.winnerPrinter = winnerPrinter;
    }

    public void playGame(Player player1, Player player2) {
        int player1Result = dice.roll();
        int player2Result = dice.roll();

        System.out.println("player1Result = " + player1Result);
        System.out.println("player2Result = " + player2Result);

        Player winner = (player1Result > player2Result)? player1: player2;
        winnerPrinter.printWinner(winner);
    }
}
