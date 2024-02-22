import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class PackDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/gym";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static void fillStandardPackComboBox(JComboBox<String> packComboBox) {
        String query = "SELECT nom FROM pack WHERE categorie = ?";
        fillComboBox(packComboBox, query, "standard");
    }

    public static void fillPremiumPackComboBox(JComboBox<String> packComboBox) {
        String query = "SELECT nom FROM pack WHERE categorie = ?";
        fillComboBox(packComboBox, query, "premium");
    }

    private static void fillComboBox(JComboBox<String> comboBox, String query, String categorie) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, categorie);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String packName = resultSet.getString("nom");
                comboBox.addItem(packName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void insertPack(String nom, String categorie, String description, String duree, int prix) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "INSERT INTO pack(nom, categorie, description, duree, prix) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nom);
            statement.setString(2, categorie);
            statement.setString(3, description);
            statement.setString(4, duree);
            statement.setInt(5, prix);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Le pack a été ajouté avec succès !");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Une erreur s'est produite lors de l'ajout du pack : " + ex.getMessage());
        }
    }

    // Ajoutez d'autres méthodes DAO pour les opérations liées aux packs si nécessaire
}
