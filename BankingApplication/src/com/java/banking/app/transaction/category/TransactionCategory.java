package com.java.banking.app.transaction.category;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.java.banking.app.transaction.dto.Transaction;

public class TransactionCategory {

	// All transactions for a given category - latest first
	public void TransactionListPerCategory(List<Transaction> transactions, String category) {

		System.out.println(" All transactions for a given category - latest first :");
		transactions
				.stream().filter(t -> t.getCategory().trim().equals(category.trim()))
				.sorted(Comparator.comparing(Transaction::getTransactionDate, Comparator.nullsLast(Comparator.reverseOrder())))
				.forEach(itr -> {
					System.out.println(itr.toString());
				});
	}

	
	// Total outgoing per category
	public void TotalOutgoingPerCatgory(List<Transaction> transactions, String category) {
		System.out.println("Total outgoing per category : £" + transactions.stream()
									   .filter(t -> t.getCategory().trim().equals(category.trim()))
									   .mapToDouble(Transaction::getAmount).sum());
	}
	

	// Monthly average spend in a given category
	public void MonthlyAvgPerCategory(List<Transaction> transactions, String category) {

		Map<String, List<Transaction>> monthTransaction = transactions.stream()
																	  .filter(t -> t.getCategory().trim().equals(category.trim()))
																	  .collect(Collectors.groupingBy(Transaction::getMonth));

		monthTransaction.forEach((K, V) -> {
			Double avg = (V.stream().mapToDouble(Transaction::getAmount).sum())
						/ V.stream().mapToDouble(Transaction::getAmount).count();
			System.out.println("Monthly average spend in a given category," + K + ": £" + avg.toString());
		});
	}

	// Highest spend in a given category, for a given year
	public void HighestSpendPerCategory(List<Transaction> transactions, String category, int year) {

		System.out.println("Highest spend in a given category, for a given year: £"
			+	transactions.stream().filter(t -> t.getCategory().trim().equals(category.trim()))
									 .filter(txn -> {
										 				Calendar calendar = Calendar.getInstance();
										 				calendar.setTime(txn.getTransactionDate());
										 				System.out.println(calendar.get(Calendar.YEAR));
										 				return new Integer(calendar.get(Calendar.YEAR)).equals(year);
									 				}).mapToDouble(Transaction::getAmount).max().getAsDouble());

	}

	// Lowest spend in a given category, for a given year
	public void LowestSpendPerCategory(List<Transaction> transactions, String category, int year) {

		System.out.println("Lowest spend in a given category, for a given year: £"
			+	transactions.stream()
							.filter(t -> t.getCategory().trim().equals(category.trim()))
							.filter(txn -> {
												Calendar calendar = Calendar.getInstance();
												calendar.setTime(txn.getTransactionDate());
												System.out.println(calendar.get(Calendar.YEAR));
												return new Integer(calendar.get(Calendar.YEAR)).equals(year);
											}).mapToDouble(Transaction::getAmount).min().getAsDouble());
	}
}
