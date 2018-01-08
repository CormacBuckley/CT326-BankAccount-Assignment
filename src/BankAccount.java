import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

//Cormac Buckley 15534413

public class BankAccount implements Serializable {

	private String name;
	private int accNo;
	private double balance;
	private String transaction;
	private static transient double overdraft;
	static AtomicInteger nextAcc = new AtomicInteger();

	ArrayList<Transaction> trans = new ArrayList();

	public BankAccount(String name, String transaction, double bal, double overdraft) {
		this.name = name;
		this.accNo = nextAcc.incrementAndGet() + new Random().nextInt(20000); // Creating a large random number for
																				// Account number
		this.balance = bal;
		this.overdraft = overdraft;
		this.transaction = transaction;
		Transaction t = new Transaction("16/08/2017", transaction, 100);
		trans.add(t);

	}

	//Deposit method
	public void deposit(String date, double amount) {
		trans.add(new Transaction(date, "Deposit", amount));
		balance += amount;
	}

	//Withdraw method
	public void withdraw(String date, double amount) {
		if (amount <= (balance)) {
			balance -= amount;
			trans.add(new Transaction(date, "Withdraw", amount));
		} else {
			System.out.println("Insufficient fund");
		}
	}

	//Transactions 
	public String getTransactionDetail() {
		String output = "";
		for (int i = 0; i < trans.size(); i++) {
			output += trans.get(i).toString();
		}
		return output;
	}

	public String toString() {
		return "Account No: " + accNo + "    Name: " + name + "    Balance: " + balance + "    Overdraft: " + overdraft
				+ "\n" + getTransactionDetail();
	}

}
