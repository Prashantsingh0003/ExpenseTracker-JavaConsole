package com.prashant.ExpenseTracker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDAO {

    //----------- ADD EXPENSE-------------
    public static boolean addExpense(Expense expense) {
        String sql = "INSERT INTO expenses (amount, category, date, note) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, expense.getAmount());
            stmt.setString(2, expense.getCategory());
            stmt.setDate(3, Date.valueOf(expense.getDate())); // Date in YYYY-MM-DD format only.
            stmt.setString(4, expense.getNote());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    //---------------GET EXPENSE-------------------
    public static List<Expense> getAllExpenses() {
        List<Expense> list = new ArrayList<>();
        String sql = "SELECT * FROM expenses";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                double amount = rs.getDouble("amount");
                String category = rs.getString("category");
                String date = rs.getDate("date").toString();
                String note = rs.getString("note");

                Expense expense = new Expense(id, amount, category, date, note);
                list.add(expense);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    //---------------UPDATE EXPENSE------------------
    public static boolean updateExpense(Expense expense) {
        String sql = "UPDATE expenses SET amount = ?, category = ?, date = ?, note = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, expense.getAmount());
            stmt.setString(2, expense.getCategory());
            stmt.setDate(3, Date.valueOf(expense.getDate()));
            stmt.setString(4, expense.getNote());
            stmt.setInt(5, expense.getId());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    //---------------DELETE EXPENSE------------------
    public static boolean deleteExpense(int id) {
        String sql = "DELETE FROM expenses WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
