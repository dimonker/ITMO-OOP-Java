package com.app;

import java.time.LocalDate;


public class Deposit extends Account {
    private final LocalDate depositTerm;

    public Deposit(Client client, LocalDate depositTerm){
        super(client);
        this.depositTerm = depositTerm;
    }

    @Override
    protected boolean isEnoughMoney(int amount) {
        return money >= amount;
    }

    @Override
    protected boolean isPossibleWithdrawMoney() {
        return depositTerm.compareTo(LocalDate.now()) < 0;
    }
}
