package com.java.banking.app.maintransaction;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.java.banking.app.filereader.TransactionFileReader;
import com.java.banking.app.transaction.category.TransactionCategory;
import com.java.banking.app.transaction.dto.Transaction;

public class BankingApplication {

	public static void main(String[] args) throws IOException {
		String csvFile ="C:\\Users\\Mr.Austin\\eclipse-workspace\\BankingApplication\\files\\TransactionDetails.csv";
		TransactionFileReader transFileReader = new TransactionFileReader();
		TransactionCategory transactionCategory = new TransactionCategory();
		List<Transaction> transactions = transFileReader.transactionFileReader(csvFile);
		List<String> categories = transactions.stream().map(txn -> txn.getCategory()).distinct().collect(Collectors.toList());
		Scanner input = new Scanner(System.in);
		int userChoice;
		String categoryChoice;
		String category = null;
		do {
			userChoice = displayMenu(input);
			switch (userChoice) {
			case 1:
				System.out.println("All transactions for a given category");
				categoryChoice = displayCategoryMenu(categories);
				transactionCategory.TransactionListPerCategory(transactions, categoryChoice);
				break;
			case 2:
				System.out.println("Total outgoing per category");
				categoryChoice = displayCategoryMenu(categories);
				transactionCategory.TotalOutgoingPerCatgory(transactions, categoryChoice);
				break;
			case 3:
				System.out.println("Monthly average spend in a given category");
				categoryChoice = displayCategoryMenu(categories);
				transactionCategory.MonthlyAvgPerCategory(transactions, categoryChoice);
				break;
			case 4:
				System.out.println("Highest spend in a given category, for a given year");
				categoryChoice = displayCategoryMenu(categories);
				transactionCategory.HighestSpendPerCategory(transactions, categoryChoice, receiveYear());
				break;
			case 5:
				System.out.println("Lowest spend in a given category, for a given year");
				categoryChoice = displayCategoryMenu(categories);
				transactionCategory.LowestSpendPerCategory(transactions, categoryChoice, receiveYear());
				break;
			case 6:
				System.out.println("Quit");
				break;
			default:
				System.out.println("Invalid Entry: Please make appropriate choice");
				break;
			}
		} while (userChoice != 6);
	}

	public static int displayMenu(Scanner input) {
		System.out.println("Choose the options");
		System.out.println("------------------------------------");
		System.out.println("| 1) All transactions for a given category");
		System.out.println("| 2) Total outgoing per category");
		System.out.println("| 3) Monthly average spend in a given category");
		System.out.println("| 4) Highest spend in a given category, for a given year");
		System.out.println("| 5) Lowest spend in a given category, for a given year");
		System.out.println("| 6) Quit");
		return input.nextInt();
	}

	public static String displayCategoryMenu(List<String> categories) {
		Scanner userCategory = new Scanner(System.in);
		int i = 1;
		if (categories != null || !categories.isEmpty()) {
			System.out.println("Choose the category from the options");
			for (String category : categories) {
				System.out.println("|" + i + ")" + category);
				i++;
			}
		}
		return categories.get((userCategory.nextInt()) - 1);
	}

	public static int receiveYear() {
		Scanner year = new Scanner(System.in);
		System.out.println("Provide an year for which data is required");
		return year.nextInt();
	}
}
