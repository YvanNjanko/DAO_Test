import DAO_Package.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddMember extends JFrame {
    private static JPanel rightPanel;
    private static JComboBox<String> packComboBox;
    private static JComboBox<String> coachComboBox;
    private static JComboBox<String> sexeComboBox;

    public AddMember() {
        JFrame frame = new JFrame("Dashboard Admin");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(10, 1));
        leftPanel.setBackground(new Color(50, 50, 50));

        JButton addStandardMember = createStyledButton("Add Standard Member", "./images/new member.png");
        JButton addPremiumMember = createStyledButton("Add Premium Member", "/path/to/pack.png");
        JButton addPaymentButton = createStyledButton("Payement", "/path/to/pack.png");
        JButton backButton = createBackButton();



        addPaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cacher la fenêtre actuelle
                setVisible(false);
                frame.dispose();

                // Ouvrir une nouvelle instance de la page de paiement
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new Payment().setVisible(true);
                    }
                });
            }
        });


        leftPanel.add(backButton);

        leftPanel.add(addStandardMember);
        leftPanel.add(addPremiumMember);
        leftPanel.add(addPaymentButton);

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
                            new Pack().setVisible(true);
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

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (text.equals("Add Standard Member")) {
                    rightPanel.removeAll();
                    addStandardMemberForm(rightPanel);
                    rightPanel.revalidate();
                    rightPanel.repaint();
                } else if (text.equals("Add Premium Member")) {
                    rightPanel.removeAll();
                    addPremiumMemberForm(rightPanel);
                    rightPanel.revalidate();
                    rightPanel.repaint();
                }
            }
        });

        return button;
    }

    private static void addStandardMemberForm(JPanel panel) {
        String[] labels = {"Nom:", "Prénom:", "Contact:"};
        JTextField[] textFields = new JTextField[labels.length];

        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            JTextField textField = new JTextField();
            panel.add(label);
            panel.add(textField);
            textFields[i] = textField;
        }

        // Liste déroulante pour le sexe
        JLabel sexeLabel = new JLabel("Sexe:");
        String[] sexeOptions = {"Masculin", "Féminin"};
        sexeComboBox = new JComboBox<>(sexeOptions);
        panel.add(sexeLabel);
        panel.add(sexeComboBox);

        // Liste déroulante pour les packs
        JLabel packLabel = new JLabel("Pack:");
        packComboBox = new JComboBox<>();
        PackDAO.fillStandardPackComboBox(packComboBox);
        panel.add(packLabel);
        panel.add(packComboBox);

        // Liste déroulante pour les coachs
        JLabel coachLabel = new JLabel("Coach:");
        coachComboBox = new JComboBox<>();
        CoachDAO.fillStandardCoachComboBox(coachComboBox);
        panel.add(coachLabel);
        panel.add(coachComboBox);

        // Bouton de soumission
        JButton submitButton = new JButton("Submit");
        panel.add(submitButton);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = textFields[0].getText();
                String prenom = textFields[1].getText();
                String sexe = (String) sexeComboBox.getSelectedItem();
                String contact = textFields[2].getText();
                String selectedPack = (String) packComboBox.getSelectedItem();
                String selectedCoach = (String) coachComboBox.getSelectedItem();

                MemberDAO.insertMember(nom, prenom, sexe, contact, "standard", selectedPack, selectedCoach);
            }
        });
    }

    private static void addPremiumMemberForm(JPanel panel) {
        String[] labels = {"Nom:", "Prénom:", "Contact:"};
        JTextField[] textFields = new JTextField[labels.length];

        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            JTextField textField = new JTextField();
            panel.add(label);
            panel.add(textField);
            textFields[i] = textField;
        }

        // Liste déroulante pour le sexe
        JLabel sexeLabel = new JLabel("Sexe:");
        String[] sexeOptions = {"Masculin", "Féminin"};
        sexeComboBox = new JComboBox<>(sexeOptions);
        panel.add(sexeLabel);
        panel.add(sexeComboBox);

        // Liste déroulante pour les packs
        JLabel packLabel = new JLabel("Pack:");
        packComboBox = new JComboBox<>();
        PackDAO.fillPremiumPackComboBox(packComboBox);
        panel.add(packLabel);
        panel.add(packComboBox);

        // Liste déroulante pour les coachs
        JLabel coachLabel = new JLabel("Coach:");
        coachComboBox = new JComboBox<>();
        CoachDAO.fillPremiumCoachComboBox(coachComboBox);
        panel.add(coachLabel);
        panel.add(coachComboBox);

        // Bouton de soumission
        JButton submitButton = new JButton("Submit");
        panel.add(submitButton);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = textFields[0].getText();
                String prenom = textFields[1].getText();
                String sexe = (String) sexeComboBox.getSelectedItem();
                String contact = textFields[2].getText();
                String selectedPack = (String) packComboBox.getSelectedItem();
                String selectedCoach = (String) coachComboBox.getSelectedItem();

                MemberDAO.insertMember(nom, prenom, sexe, contact, "premium", selectedPack, selectedCoach);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AddMember().setVisible(true);
            }
        });
    }

    public void setVisible(boolean b) {
    }
}