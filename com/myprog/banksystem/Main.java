package com.myprog.banksystem;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        // Тестируем банк
        GoldenBank myBank = new GoldenBankImpl();

        // Создаем 3-х клиентов и их счета
        // Client baby = new Client(1, "Baby", LocalDate.of(2020, 12, 10)); // будет ошибка, т.к. возраст неверный

        Client frodo = new Client(1, "Фродо Бэггинс", LocalDate.of(2000, 12, 10));
        Client gendalf = new Client(2, "Гендальф Серый", LocalDate.of(2001, 11, 15));

        Account frodoDeposit = new Account(11);
        Account frodoCarCredit = new Account(12);

        Account gendalfDeposit = new Account(21);
        Account genfalfCashback = new Account(22);

        // Добавляем Фродо, Гендальфа и их счета
        myBank.add(frodo, frodoDeposit);
        myBank.add(frodo, frodoCarCredit);
        myBank.add(gendalf, gendalfDeposit);
        myBank.add(gendalf, genfalfCashback);

        // Добавляем еще клиентов и их счетов побольше
        Client currClient;
        Account currAccount;
        for(int i = 3; i <= 3000; i++) {
            for(int j = 1; j <= 5; j++) {
                currClient = new Client(i, "Client_" + i, LocalDate.of(1981, 11, 15));
                currAccount = new Account(j* 10000 + i);
                currAccount.deposit(j*10);
                myBank.add(currClient, currAccount);
            }
        }

        // Проверяем, что методы быстро работают
        System.out.println(myBank.findClient(frodoCarCredit));
        System.out.println(myBank.findClient(genfalfCashback));
        System.out.println(myBank.findClient(new Account(4*10000 + 1256)));

        System.out.println(myBank.getAccounts(gendalf));
        System.out.println(myBank.getAccounts(new Client(1500, "test", LocalDate.of(1976, 11, 2))));
    }
}
