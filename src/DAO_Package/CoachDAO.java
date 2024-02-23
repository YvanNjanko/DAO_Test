package DAO_Package;

import javax.swing.*;
import java.sql.*;

public class CoachDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/gym";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static void fillStandardCoachComboBox(JComboBox<String> coachComboBox) {
        String query = "SELECT nom FROM coach WHERE categorie = ?";
        fillComboBox(coachComboBox, query, "standard");
    }

    public static void fillPremiumCoachComboBox(JComboBox<String> coachComboBox) {
        String query = "SELECT nom FROM coach WHERE categorie = ?";
        fillComboBox(coachComboBox, query, "premium");
    }

    private static void fillComboBox(JComboBox<String> comboBox, String query, String categorie) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, categorie);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String coachName = resultSet.getString("nom");
                comboBox.addItem(coachName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void insertCoach(String nom, String prenom, String sexe, String contact, String categorie) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "INSERT INTO coach(nom, prenom, sexe, contact, categorie) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nom);
            statement.setString(2, prenom);
            statement.setString(3, sexe);
            statement.setString(4, contact);
            statement.setString(5, categorie);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Le coach a été ajouté avec succès !");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Une erreur s'est produite lors de l'ajout du coach : " + ex.getMessage());
        }
    }

    public static int getNumberOfCoaches() {
        int numberOfCoaches = 0;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT COUNT(*) FROM coach";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                numberOfCoaches = resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return numberOfCoaches;
    }

    // Ajoutez d'autres méthodes DAO pour les opérations liées aux coachs si nécessaire
}
