import DAO_Package.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class Payment extends JFrame {
    private JFrame frame;
    private static JPanel rightPanel;

    public Payment() {
        JFrame frame = new JFrame("Service de payement");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(10, 5));
        leftPanel.setBackground(new Color(50, 50, 50));


        JButton validerPayement = createStyledButton("Valider payement", "/path/to/pack.png");
        validerPayement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idClient = JOptionPane.showInputDialog(frame, "Entrez l'ID du client à valider :", "Validation de paiement", JOptionPane.QUESTION_MESSAGE);
                if (idClient != null && !idClient.isEmpty()) {
                    // Convertir l'ID du client en int
                    int clientId = Integer.parseInt(idClient);
                    // Mettre à jour le statut de paiement du client dans la base de données
                    boolean success = ClientDAO.updatePaymentStatus(clientId, true);
                    if (success) {
                        JOptionPane.showMessageDialog(frame, "Paiement validé pour le client ID : " + clientId, "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Échec de la validation du paiement pour le client ID : " + clientId, "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Veuillez entrer un ID de client valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton annulerpayment = createStyledButton("Annuler paiement", "/path/to/pack.png");
        annulerpayment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idClient = JOptionPane.showInputDialog(frame, "Entrez l'ID du client dont vous souhaitez annuler le paiement :", "Annulation de paiement", JOptionPane.QUESTION_MESSAGE);
                if (idClient != null && !idClient.isEmpty()) {
                    // Convertir l'ID du client en int
                    int clientId = Integer.parseInt(idClient);
                    // Mettre à jour le statut de paiement du client dans la base de données
                    boolean success = ClientDAO.updatePaymentStatus(clientId, false);
                    if (success) {
                        JOptionPane.showMessageDialog(frame, "Paiement annulé pour le client ID : " + clientId, "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Échec de l'annulation du paiement pour le client ID : " + clientId, "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Veuillez entrer un ID de client valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        JButton voirstatut = createStyledButton("Voir statut", "/path/to/pack.png");

        JButton backButton = createBackButton();
        leftPanel.add(backButton);


        leftPanel.add(validerPayement);
        leftPanel.add(annulerpayment);
        leftPanel.add(voirstatut);

        rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(8, 2));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private JButton createBackButton() {
        JButton backButton = new JButton();
        ImageIcon backIcon = new ImageIcon("./images/exit.png"); // Remplacez le chemin par le chemin réel de votre image de retour
        backButton.setIcon(backIcon);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setContentAreaFilled(false);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtenez la fenêtre parente de votre composant
                Container container = SwingUtilities.getAncestorOfClass(JFrame.class, (Component) e.getSource());

                if (container instanceof JFrame) {
                    JFrame frame = (JFrame) container;

                    // Fermez la fenêtre actuelle
                    frame.dispose();

                    // Ouvrez une nouvelle instance de Pack
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            new AddMember().setVisible(true);
                        }
                    });
                }
            }
        });


        return backButton;
    }

    private static JButton createStyledButton(String text, String imagePath) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(70, 70, 70));
        button.setFocusPainted(false);

        if (!imagePath.isEmpty()) {
            ImageIcon icon = new ImageIcon(imagePath);
            button.setIcon(icon);
        }
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Payment().setVisible(true);
            }
        });
    }

    public void setVisible(boolean b) {
    }

}
