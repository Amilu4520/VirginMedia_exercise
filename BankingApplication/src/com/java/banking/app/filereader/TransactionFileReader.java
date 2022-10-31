package com.java.banking.app.filereader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.java.banking.app.transaction.dto.Transaction;

public class TransactionFileReader {

	public List<Transaction> transactionFileReader(String filename) throws IOException {

		final String EMPTY_CATEGORY = "Empty";
		String delimiter = ",";
		String currentLine;
		List<Transaction> transactions = new ArrayList<Transaction>();
		try {
			File file = new File(filename);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			for (int i = 0; (currentLine = br.readLine()) != null; i++) {
				if (i != 0) {
					List<String> innerLine = Arrays.asList(currentLine.split(delimiter));
					Transaction trans = new Transaction();
					trans.setTransactionDate(new SimpleDateFormat("dd/MMM/yyyy").parse(innerLine.get(0).trim()));
					trans.setVendor(innerLine.get(1).trim());
					trans.setType(innerLine.get(2).trim());
					trans.setAmount(Double.valueOf(innerLine.get(3).trim().substring(1)));
					trans.setCategory(innerLine.size() > 4 && !innerLine.get(4).isEmpty() ? innerLine.get(4).trim() : EMPTY_CATEGORY);
					transactions.add(trans);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return transactions;
	}
}
