package DAO_Package;

import java.sql.*;

public class ClientDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/gym";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static boolean updatePaymentStatus(int clientId, boolean status) {
        String query = "UPDATE client SET statuPaiement = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBoolean(1, status);
            statement.setInt(2, clientId);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static int getNumberOfPaidClients() {
        int numberOfPaidClients = 0;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT COUNT(*) FROM client WHERE statuPaiement = true";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                numberOfPaidClients = resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return numberOfPaidClients;
    }

    public static double getRevenue() {
        double revenue = 0;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT SUM(prix) FROM pack INNER JOIN client ON pack.nom = client.pack WHERE client.statuPaiement = true";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                revenue = resultSet.getDouble(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return revenue;
    }
}