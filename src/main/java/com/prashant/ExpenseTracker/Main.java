package com.prashant.ExpenseTracker;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== Expense Tracker Menu =====");
            System.out.println("1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. Update Expense");
            System.out.println("4. Delete Expense");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter amount: ");
                    double amount = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Enter category: ");
                    String category = sc.nextLine();
                    System.out.print("Enter date (YYYY-MM-DD): ");
                    String date = sc.nextLine();
                    System.out.print("Enter note (optional): ");
                    String note = sc.nextLine();

                    Expense newExpense = new Expense(amount, category, date, note);
                    boolean added = ExpenseDAO.addExpense(newExpense);
                    if (added) {
                        System.out.println("✅ Expense added successfully.");
                    } else {
                        System.out.println("❌ Failed to add expense.");
                    }
                    break;

                case 2:
                    List<Expense> list = ExpenseDAO.getAllExpenses();
                    System.out.println("\n--- All Expenses ---");
                    for (Expense e : list) {
                        System.out.println("ID: " + e.getId() +
                                           " | Amount: " + e.getAmount() +
                                           " | Category: " + e.getCategory() +
                                           " | Date: " + e.getDate() +
                                           " | Note: " + e.getNote());
                    }
                    break;

                case 3:
                    System.out.print("Enter Expense ID to update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter new amount: ");
                    double newAmount = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Enter new category: ");
                    String newCategory = sc.nextLine();
                    System.out.print("Enter new date (YYYY-MM-DD): ");
                    String newDate = sc.nextLine();
                    System.out.print("Enter new note: ");
                    String newNote = sc.nextLine();

                    Expense updatedExpense = new Expense(updateId, newAmount, newCategory, newDate, newNote);
                    boolean updated = ExpenseDAO.updateExpense(updatedExpense);
                    if (updated) {
                        System.out.println("✅ Expense updated successfully.");
                    } else {
                        System.out.println("❌ Failed to update expense.");
                    }
                    break;

                case 4:
                    System.out.print("Enter Expense ID to delete: ");
                    int deleteId = sc.nextInt();
                    boolean deleted = ExpenseDAO.deleteExpense(deleteId);
                    if (deleted) {
                        System.out.println("✅ Expense deleted successfully.");
                    } else {
                        System.out.println("❌ Failed to delete expense.");
                    }
                    break;

                case 5:
                    System.out.println("Exiting... Thank you!");
                    break;

                default:
                    System.out.println("⚠️ Invalid choice. Try again.");
            }

        } while (choice != 5);

        sc.close();
    }
}
