package DAO_Package;

import javax.swing.*;
import java.sql.*;

public class TypePackDAO {
    public static void fillStandardPackComboBox(JComboBox<String> packComboBox) {
        fillPackComboBox(packComboBox, "standard");
    }

    public static void fillPremiumPackComboBox(JComboBox<String> packComboBox) {
        fillPackComboBox(packComboBox, "premium");
    }

    private static void fillPackComboBox(JComboBox<String> packComboBox, String category) {
        String url = "jdbc:mysql://localhost:3306/gym";
        String user = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT nom FROM pack WHERE categorie = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, category);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String packName = resultSet.getString("nom");
                packComboBox.addItem(packName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
