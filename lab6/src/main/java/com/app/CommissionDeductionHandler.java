package com.app;

public class CommissionDeductionHandler extends Handler {
    @Override
    public void handle(Account account) {
        if (account instanceof AccountDecorator){
            Account component = ((AccountDecorator) account).getComponent();
            if (component instanceof CreditAccount)
                ((CreditAccount) component).commissionDeduction();
            else super.handle(account);
        }else if (account instanceof CreditAccount){
            ((CreditAccount) account).commissionDeduction();
        }
        else super.handle(account);
    }
}
