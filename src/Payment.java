import javax.swing.*;
import java.awt.*;

public class Payment {
    private JFrame frame;
    private JTextField nameField;
    private JTextField amountField;
    private JComboBox<String> packField;
    private JTextField item1Field;
    private JTextField item2Field;
    private JTextField item3Field;
    private JTextField item4Field;
    private JTextField item5Field;

    public Payment() {
        createView();
    }

    private void createView() {
        frame = new JFrame("Bienvenue dans votre service de paie");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setResizable(false); // Empêche le redimensionnement de la fenêtre

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 10, 5)); // GridLayout avec espacement horizontal de 10 et vertical de 5

        // Ajout des composants d'entrée avec des étiquettes
        inputPanel.add(new JLabel("Nom & Prénom"));
        nameField = new JTextField(20);
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Montant"));
        amountField = new JTextField(20);
        inputPanel.add(amountField);

        inputPanel.add(new JLabel("Pack"));
        String[] packOptions = {"Pack 1", "Pack 2", "Pack 3"};
        packField = new JComboBox<>(packOptions);
        inputPanel.add(packField);

        // Ajout du panneau d'entrée au panneau principal
        mainPanel.add(inputPanel, BorderLayout.NORTH);





        // Création du panneau pour le bouton
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // FlowLayout centré
        JButton buyButton = new JButton("Acheter");
        buttonPanel.add(buyButton);

        // Ajout du panneau du bouton au panneau principal
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Ajout du panneau principal à la fenêtre
        frame.getContentPane().add(mainPanel);
        frame.setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Payment payrollService = new Payment();
            payrollService.frame.setVisible(true);
        });
    }
}
