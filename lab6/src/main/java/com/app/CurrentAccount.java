package com.app;

public class CurrentAccount extends Account {
    private final int residuePercent;

    public CurrentAccount(Client client, int residuePercent){
        super(client);
        this.residuePercent = residuePercent;
    }

    @Override
    protected boolean isEnoughMoney(int amount) {
        return money >= amount;
    }

    @Override
    protected boolean isPossibleWithdrawMoney() {
        return true;
    }

    public void payPersents(){
        money += money / 100 * residuePercent / 12;
    }
}
