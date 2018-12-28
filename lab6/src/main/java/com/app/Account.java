package com.app;

public abstract class Account {
    protected double money;
    private Client owner;

    public Account(Client client){
        owner = client;
    }

    protected abstract boolean isEnoughMoney(int amount);
    protected abstract boolean isPossibleWithdrawMoney();

    public double getAmountOfMoney(){
        return ((double) Math.round(money * 100)) / 100 ;
    }

    public void withdrawMoney(int amount){
        if (!isEnoughMoney(amount)){
            System.err.println("Not enough money");
            return;
        }
        if (!isPossibleWithdrawMoney()){
            System.err.println("Impossible withdraw money");
            return;
        }
        money -= amount;
    }

    public void putMoney(int amount){
        money += amount;
    }

    public void transferMoney(Account account, int amount){
        if (!isEnoughMoney(amount)){
            System.err.println("Not enough money");
            return;
        }
        if (!isPossibleWithdrawMoney()){
            System.err.println("Impossible withdraw money");
            return;
        }
        account.putMoney(amount);
    }



}
