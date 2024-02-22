package DAO_Package;

import java.sql.*;
import javax.swing.*;
import java.awt.*;

public class PackListDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/gym";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static JScrollPane  getPackList() {
        JTextArea packListArea = new JTextArea(20, 40);
        packListArea.setEditable(false);
        Font font = new Font("Segoe UI Light", Font.ITALIC, 13);
        packListArea.setFont(font);

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT * FROM pack";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            StringBuilder packListText = new StringBuilder();
            while (resultSet.next()) {
                String nom = resultSet.getString("nom");
                String categorie = resultSet.getString("categorie");
                String description = resultSet.getString("description");
                String duree = resultSet.getString("duree");
                int prix = resultSet.getInt("prix");

                packListText.append("Nom: ").append(nom).append("\n");
                packListText.append("Catégorie: ").append(categorie).append("\n");
                packListText.append("Description: ").append(description).append("\n");
                packListText.append("Durée: ").append(duree).append("\n");
                packListText.append("Prix: ").append(prix).append("\n\n");
            }
            packListArea.setText(packListText.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Une erreur s'est produite lors de la récupération des packs : " + ex.getMessage());
        }
        JScrollPane scrollPane = new JScrollPane(packListArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        return scrollPane;
    }

}
