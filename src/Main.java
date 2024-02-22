import DAO_Package.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    public Main() {
        // Créer une fenêtre pour l'interface
        JFrame frame = new JFrame("Bienvenue dans votre salle de sport LETT");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500); // Ajustez la taille selon vos besoins

        // Créer un panneau pour contenir les composants
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Ajouter un panneau pour le bouton image en haut à gauche
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

// Spécifier les dimensions souhaitées pour l'icône
        int iconWidth =15; // Largeur de l'icône en pixels
        int iconHeight = 15; // Hauteur de l'icône en pixels

// Ajouter une image à l'étiquette
        ImageIcon closeButtonIcon = new ImageIcon("./images/close.png"); // Remplacez le chemin de votre image
        Image image = closeButtonIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(image);
        JLabel closeButtonLabel = new JLabel(scaledIcon);

// Ajouter l'étiquette au panneau
        buttonPanel.add(closeButtonLabel);


        // Créer un panneau pour contenir l'image et le texte
        JPanel imageTextPanel = new JPanel(new BorderLayout());

// Ajouter l'image à gauche
        ImageIcon imageIcon = new ImageIcon("./images/OIP (1).jpg");
        Image img = imageIcon.getImage();
        Image newImg = img.getScaledInstance(540, 340, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(newImg);
        JLabel imageLabel = new JLabel(scaledImageIcon);
        imageTextPanel.add(imageLabel, BorderLayout.WEST);

// Créer le JLabel avec le texte
        JLabel aboveImageLabel = new JLabel("Bienvenu dans votre salle de sport");
        aboveImageLabel.setHorizontalAlignment(SwingConstants.CENTER); // Centrer le texte horizontalement

// Définir les caractéristiques de la police pour le texte
        aboveImageLabel.setFont(new Font("Segoe UI Light", Font.ITALIC, 40));

// Ajouter le JLabel au panneau contenant l'image et le texte
        imageTextPanel.add(aboveImageLabel, BorderLayout.NORTH);

// Ajouter le panneau contenant l'image et le texte à gauche
        mainPanel.add(imageTextPanel, BorderLayout.WEST);


        // Créer un panneau pour le titre centré et le reste du contenu
        JPanel contentPanel = new JPanel(new GridBagLayout());

        // Ajouter les étiquettes et les champs de texte pour l'utilisateur et le mot de passe
        JLabel usernameLabel = new JLabel("USERNAME");
        JTextField usernameField = new JTextField(15);
        JLabel passwordLabel = new JLabel("PASSWORD");
        JPasswordField passwordField = new JPasswordField(15);

        // Ajouter les étiquettes et les champs de texte au panneau
        contentPanel.add(usernameLabel, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        contentPanel.add(usernameField, new GridBagConstraints(1, 1, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        contentPanel.add(passwordLabel, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        contentPanel.add(passwordField, new GridBagConstraints(1, 2, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

        // Créer un bouton de connexion
        JButton loginButton = new JButton("CONNEXION");

        // Ajouter un écouteur d'événements au bouton de connexion
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Vérification des informations d'identification dans la base de données
                if (AdminDAO.validateLogin(username, password)) {
                    JOptionPane.showMessageDialog(frame, "Connexion réussie !");
                    frame.dispose(); // Ferme la fenêtre actuelle
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            // Crée et affiche une nouvelle fenêtre Pack
                            new Pack().setVisible(true);
                        }
                    });
                } else {
                    JOptionPane.showMessageDialog(frame, "Nom d'utilisateur ou mot de passe incorrect !");
                }
            }
        });

        // Ajouter le bouton de connexion au panneau
        contentPanel.add(loginButton, new GridBagConstraints(1, 3, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));


        // Ajouter le panneau du contenu à droite
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Ajouter les panneaux principaux à la fenêtre
        frame.add(mainPanel, BorderLayout.WEST);

        // Afficher la fenêtre
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    public void setVisible(boolean b){
}
}