import com.app.*;
import org.junit.Test;
import org.junit.Assert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    @Test
    public void createClient(){
        Client client = Client.Builder("Victor", "Ivanov")
                .setAddress("Pushkina 10")
                .setPassportID("2442 234153")
                .build();
        Assert.assertEquals("Victor", client.getFirstName());
        Assert.assertEquals("Ivanov", client.getSecondName());
        Assert.assertEquals("Pushkina 10", client.getAddress());
        Assert.assertEquals("2442 234153", client.getPassportID());
    }



    @Test
    public void createDoubtfulAccountAndWithdrawMoney(){
        Client client = Client.Builder("Victor", "Ivanov")
                .build();
        SberbankAccountFactory sberbankAccountFactory = new SberbankAccountFactory();
        Account account = sberbankAccountFactory.createCurrentAccount(client);
        Assert.assertEquals(account.getClass(), DoubtfulAccount.class);

        account.putMoney(1500);
        try{
            account.withdrawMoney(1200);
        }catch (RuntimeException e){
            Assert.assertTrue(e.getMessage().startsWith("You can't withdraw more then "));
        }

    }

    @Test
    public void handleAccounts(){
        Client client1 = Client.Builder("Victor", "Ivanov")
                .build();
        Client client2 = Client.Builder("Alexey", "Petrov")
                .setPassportID("2345 253233")
                .setAddress("Moscovskaya 22")
                .build();

        SberbankAccountFactory sberbankAccountFactory = new SberbankAccountFactory();

        Account account1 = sberbankAccountFactory.createCurrentAccount(client1);
        Account account2 = sberbankAccountFactory.createDeposit(client1, LocalDate.parse("2019-02-02"));
        Account account3 = sberbankAccountFactory.createCreditAccount(client1);
        Account account4 = sberbankAccountFactory.createCurrentAccount(client2);
        Account account5 = sberbankAccountFactory.createDeposit(client2, LocalDate.parse("2019-04-04"));
        Account account6 = sberbankAccountFactory.createCreditAccount(client2);

        ArrayList<Account> list = new ArrayList<>();
        list.addAll(Arrays.asList(account1,account2,account3,account4,account5,account6));

        account1.putMoney(1000);
        account3.withdrawMoney(100);
        account4.putMoney(1300);
        account6.putMoney(100);
        PayPersentsHandler payPersentsHandler = new PayPersentsHandler();
        CommissionDeductionHandler commissionDeductionHandler = new CommissionDeductionHandler();
        payPersentsHandler.setNext(commissionDeductionHandler);

        for(Account account : list){
            payPersentsHandler.handle(account);
        }

        Assert.assertEquals(account1.getAmountOfMoney(), 1004.17, 0.001);
        Assert.assertEquals(account3.getAmountOfMoney(), -160.0, 0.001);
        Assert.assertEquals(account4.getAmountOfMoney(), 1305.42, 0.001);
        Assert.assertEquals(account6.getAmountOfMoney(), 100.0, 0.001);

    }
}

