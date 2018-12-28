package com.app;

public class PayPersentsHandler extends Handler{
    @Override
    public void handle(Account account) {
        if (account instanceof AccountDecorator){
            Account component = ((AccountDecorator) account).getComponent();
            if (component instanceof CurrentAccount)
                ((CurrentAccount) component).payPersents();
            else super.handle(account);
        }else if (account instanceof CurrentAccount){
            ((CurrentAccount) account).payPersents();
        } else super.handle(account);
    }
}
