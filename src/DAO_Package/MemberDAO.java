package DAO_Package;

import javax.swing.*;
import java.sql.*;

public class MemberDAO {
    public static void insertMember(String nom, String prenom, String sexe, String contact, String categorie, String selectedPack, String selectedCoach) {
        String url = "jdbc:mysql://localhost:3306/gym";
        String user = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO client (nom, prenom, sexe, contact, categorie, pack, coach, statuPaiement) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nom);
            statement.setString(2, prenom);
            statement.setString(3, sexe);
            statement.setString(4, contact);
            statement.setString(5, categorie);
            statement.setString(6, selectedPack);
            statement.setString(7, selectedCoach);
            statement.setBoolean(8, false);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Client ajouté avec succès !");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Une erreur s'est produite lors de l'ajout du client : " + e.getMessage());
        }
    }

    public static int getNumberOfMembers() {
        String url = "jdbc:mysql://localhost:3306/gym";
        String user = "root";
        String password = "";

        int numberOfMembers = 0;
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT COUNT(*) FROM client";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                numberOfMembers = resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return numberOfMembers;
    }
}
