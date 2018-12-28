package com.app;

public abstract class AccountDecorator extends Account {
    protected Account component;

    public AccountDecorator(Client client, Account account) {
        super(client);
        component = account;
    }

    public Account getComponent(){
        return component;
    }

    @Override
    public double getAmountOfMoney() {
        return component.getAmountOfMoney();
    }

    @Override
    protected boolean isEnoughMoney(int amount) {
        return true;
    }

    @Override
    protected boolean isPossibleWithdrawMoney() {
        return true;
    }

    @Override
    public void withdrawMoney(int amount) {
        component.withdrawMoney(amount);
    }

    @Override
    public void putMoney(int amount) {
        component.putMoney(amount);
    }

    @Override
    public void transferMoney(Account account, int amount) {
        component.transferMoney(account, amount);
    }
}
