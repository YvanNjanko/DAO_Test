import java.sql.*;
import javax.swing.JOptionPane;

public class CoachDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/gym";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

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

    // Ajoutez d'autres méthodes DAO pour les opérations liées aux coachs si nécessaire
}
