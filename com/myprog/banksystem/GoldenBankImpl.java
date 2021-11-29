package com.myprog.banksystem;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class GoldenBankImpl implements GoldenBank {
    private Map<Client, List<Account>> clientMap;
    private Map<Account, Client> accountClientMap;

    public GoldenBankImpl() {
        clientMap = new HashMap<>();
        accountClientMap = new HashMap<>();
    }

    // минус метода: нельзя добавить клиента без счета
    public void add(Client client, Account account) {
        if (client == null || account == null) {
            throw new IllegalArgumentException("Аргументы должны быть не равны null");
        }
        // Добавление в clientMap
        List<Account> accountListForClient = clientMap.get(client);
        if (accountListForClient == null) {
            // такого клиента нет, нужно добавить клиента и первый счет для него
            accountListForClient = new ArrayList<>();
            accountListForClient.add(account);
            clientMap.put(client, accountListForClient);
        }else {
            // клиент и список счетов есть, нужно только добавить счет
            accountListForClient.add(account);
        }
        // Добавление в accountClientMap
        accountClientMap.put(account, client);
    }

    public List<Account> getAccounts(Client client) {
        return clientMap.get(client);
    }

    public Client findClient(Account account) {
        return accountClientMap.get(account);
    }

}
