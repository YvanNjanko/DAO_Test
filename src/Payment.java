import DAO_Package.CoachDAO;
import DAO_Package.MemberDAO;
import DAO_Package.PackDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Payment extends JFrame {
    private JFrame frame;
    private static JPanel rightPanel;

    public Payment() {
        JFrame frame = new JFrame("Dashboard Admin");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(10, 1));
        leftPanel.setBackground(new Color(50, 50, 50));


        JButton addPremiumMember = createStyledButton("Valider payement", "/path/to/pack.png");
        JButton addannulerpayment = createStyledButton("Anuler payement", "/path/to/pack.png");
        JButton addvoirstatut = createStyledButton("Voir statut", "/path/to/pack.png");
        JButton backButton = createBackButton();
        leftPanel.add(backButton);


        leftPanel.add(addPremiumMember);
        leftPanel.add(addannulerpayment);
        leftPanel.add(addvoirstatut);

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
