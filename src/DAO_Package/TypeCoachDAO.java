package DAO_Package;

import javax.swing.*;
import java.sql.*;

public class TypeCoachDAO {
    public static void fillStandardCoachComboBox(JComboBox<String> coachComboBox) {
        fillCoachComboBox(coachComboBox, "standard");
    }

    public static void fillPremiumCoachComboBox(JComboBox<String> coachComboBox) {
        fillCoachComboBox(coachComboBox, "premium");
    }

    private static void fillCoachComboBox(JComboBox<String> coachComboBox, String category) {
        String url = "jdbc:mysql://localhost:3306/gym";
        String user = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT nom FROM coach WHERE categorie = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, category);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String coachName = resultSet.getString("nom");
                coachComboBox.addItem(coachName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
