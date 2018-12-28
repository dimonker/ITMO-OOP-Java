package com.app;

public abstract class Handler {
    private Handler next;

    public void setNext(Handler handler){
        next = handler;
    }

    public void handle(Account account){
        if (next != null)
            next.handle(account);
    }
}
