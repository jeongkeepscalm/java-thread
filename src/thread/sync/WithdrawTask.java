package thread.sync;

/*
    출금하는 Runnable
 */
public class WithdrawTask implements Runnable {

    private BankAccount bankAccount;
    private int amount;
    public WithdrawTask(BankAccount bankAccount, int amount) {
        this.bankAccount = bankAccount;
        this.amount = amount;
    }

    @Override
    public void run() {
        bankAccount.withdraw(amount);
    }

}