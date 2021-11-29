package com.myprog.banksystem;

import java.util.List;

public interface GoldenBank {
    void add(Client client, Account account);
    List<Account> getAccounts(Client client);
    Client findClient(Account account);
}
