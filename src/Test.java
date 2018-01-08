import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Cormac Buckley 15534413

public class Test implements Serializable {

	public static void main(String args[]) throws IOException, ClassNotFoundException {

		Scanner scan = new Scanner(System.in);

		ArrayList<Transaction> trans = new ArrayList();
		ArrayList<BankAccount> acc = new ArrayList();

		// Creating 2 bank accounts

		BankAccount b1 = new BankAccount("Jenny, Lee", "Open Account", 100, 100);

		BankAccount b2 = new BankAccount("Tony, Stark", "Open Account", 1000000, 100);

		// Inputting transactions

		b1.withdraw("22/08/2017", 200);
		b1.deposit("23/08/2017", 100);
		b1.withdraw("1/09/2017", 50);

		b2.deposit("22/08/2017", 1800000);
		b2.withdraw("23/08/2017", 10000000);

		// Adding to ArrayLists
		acc.add(b1);
		acc.add(b2);

		// Serializing data and writing to file
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("C:\\Users\\I342042\\Desktop/accountdetails.txt");
			for (int i = 0; i < acc.size(); i++) {
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(acc.get(i));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fos != null)
				fos.close();
		}

		ArrayList<BankAccount> bank = new ArrayList();

		FileInputStream fis = null;
		try {
			fis = new FileInputStream("C:\\Users\\I342042\\Desktop/accountdetails.txt");
			while (true) {
				ObjectInputStream ois = new ObjectInputStream(fis);
				bank.add((BankAccount) ois.readObject());
			}
		} catch (EOFException ignored) {
			// as expected
		} finally {
			if (fis != null)
				fis.close();
		}
		for (int i = 0; i < bank.size(); i++) {
			System.out.println(bank.get(i).toString());

		}
		
		//Reading in answer and appending
		System.out.println("Would you like to increase your overdraft? Please type Yes/No at the end of the line.");
		String data = scan.nextLine();

		writeToRandomAccessFile("C:\\Users\\I342042\\Desktop/overdraft.txt", 100, data);

		try (BufferedReader br = new BufferedReader(
				new FileReader("C:\\\\Users\\\\I342042\\\\Desktop/overdraft.txt"))) {
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		}

	}
//Write to file 
	public static void writeToRandomAccessFile(String file, int position, String record) {
		try {

			RandomAccessFile fileStore = new RandomAccessFile(file, "rwd");
			// moves file pointer to position specified
			fileStore.seek(position);
			// writing String to RandomAccessFile
			fileStore.writeUTF(record);

			fileStore.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}