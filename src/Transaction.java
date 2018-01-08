import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

//Cormac Buckley 15534413

public class Transaction implements Serializable {

	private int transactionNo;
	private String date;
	private String type;
	static AtomicInteger nextTrans = new AtomicInteger();
	private double amount;

	public Transaction(String date, String type, double amount) {
		this.date = date;
		this.type = type;
		this.amount = amount;
		transactionNo = nextTrans.incrementAndGet();
	}

	public String toString() {
		return "Transaction ID:" + transactionNo + "   " + date + ' ' + type + ": " + amount + "\n";

	}

}
