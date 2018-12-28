package com.app;

public class DoubtfulAccount extends AccountDecorator {
    private final int fixedAmount = 1000;

    public DoubtfulAccount(Client client, Account account) {
        super(client, account);
    }

    @Override
    public void withdrawMoney(int amount){
        if (amount > fixedAmount)
            throw new RuntimeException("You can't withdraw more then " + fixedAmount);
        super.withdrawMoney(amount);
    }

    @Override
    public void transferMoney(Account account, int amount){
        if (amount > fixedAmount)
            throw new RuntimeException("You can't transfer more then " + fixedAmount);
        super.transferMoney(account, amount);
    }

}
