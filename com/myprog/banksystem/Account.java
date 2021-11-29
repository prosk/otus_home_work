package com.myprog.banksystem;

public class Account {
    // уникальный идентификатор счета
    private final int id;
    // кол-во золотых монет на счете
    private int balance;

    public Account(int id) {
        this.id = id;
        this.balance = 0;
    }

    public int getBalance() {
        return balance;
    }

    public int withdraw(int amount) throws Exception {
        if (balance < amount) {
            throw new Exception("Недостаточно средств!");
        }
        balance -= amount;
        return balance;
    }

    public int deposit(int amount) {
        balance += amount;
        return balance;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (this == other) return true;
        if (getClass() != other.getClass()) return false;

        Account otherAccount = (Account) other;
        return (this.id == otherAccount.id);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", balance=" + balance +
                '}';
    }
}
