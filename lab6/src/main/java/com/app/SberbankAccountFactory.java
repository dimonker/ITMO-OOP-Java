package com.app;

import java.time.LocalDate;

public class SberbankAccountFactory extends AccountFactory {
    private final int residuePercent = 5;
    private final int commission = 60;
    private final int limit = 1000;

    private Account checkClient(Client client, Account account) {
        if (client.getAddress() == null || client.getPassportID() == null)
            return new DoubtfulAccount(client, account);
        return account;
    }

    @Override
    public Account createCurrentAccount(Client client) {
        Account account = new CurrentAccount(client, residuePercent);
        return checkClient(client, account);
    }


    @Override
    public Account createDeposit(Client client, LocalDate depositTerm) {
        Account account = new Deposit(client, depositTerm);
        return checkClient(client, account);
    }

    @Override
    public Account createCreditAccount(Client client) {
        Account account = new CreditAccount(client, limit, commission);
        return checkClient(client, account);
    }
}
