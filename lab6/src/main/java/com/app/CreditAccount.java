package com.app;

public class CreditAccount extends Account {
    private final int limit;
    private final int commission;

    public CreditAccount(Client client, int limit, int commission){
        super(client);
        this.limit = limit;
        this.commission = commission;
    }

    @Override
    protected boolean isEnoughMoney(int amount) {
        return money + limit > amount;
    }

    @Override
    protected boolean isPossibleWithdrawMoney() {
        return true;
    }

    public void commissionDeduction(){
        if(money < 0)
            money -= commission;
    }
}
