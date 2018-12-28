package com.app;

import java.time.LocalDate;

public abstract class AccountFactory {
    public abstract Account createCurrentAccount(Client client);
    public abstract Account createDeposit(Client client, LocalDate depositTerm);
    public abstract Account createCreditAccount(Client client);
}
