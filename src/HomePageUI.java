import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.*;

public class HomePageUI extends JFrame implements ActionListener {
    private JButton registerButton;
    private JButton loginButton;
    private JButton aboutButton;
    private JButton exitButton;

    public HomePageUI() {
        setTitle("Home Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 640);
        setLocationRelativeTo(null); // Center the frame on the screen

        // Create a panel for holding components
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Add a label with the photo at the top
        JLabel photoLabel = new JLabel(new ImageIcon("C:\\Users\\user\\IdeaProjects\\QuizApp\\src\\quizz.jpg")); // Replace "your_photo.jpg" with the path to your photo
        panel.add(photoLabel, BorderLayout.NORTH);

        // Create a panel for holding the buttons
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 0, 10)); // 4 rows, 1 column
        panel.add(buttonPanel, BorderLayout.CENTER);

        // Add buttons
         registerButton = new JButton("Register");
         loginButton = new JButton("Login");
         aboutButton = new JButton("About Us");
         exitButton = new JButton("Exit");

        Font buttonFont = new Font("Arial", Font.BOLD, 18);
        registerButton.setFont(buttonFont);
        registerButton.setForeground(Color.WHITE);
        loginButton.setFont(buttonFont);
        loginButton.setForeground(Color.WHITE);
        aboutButton.setFont(buttonFont);
        aboutButton.setForeground(Color.WHITE);
        exitButton.setFont(buttonFont);
        exitButton.setForeground(Color.WHITE);

         registerButton.setBackground(Color.red);
         loginButton.setBackground(Color.red);
         aboutButton.setBackground(Color.red);
         exitButton.setBackground(Color.red);

        // Add action listeners to buttons
        registerButton.addActionListener(this);
        loginButton.addActionListener(this);
        aboutButton.addActionListener(this);
        exitButton.addActionListener(this);

        // Add buttons to button panel
        buttonPanel.add(registerButton);
        buttonPanel.add(loginButton);
        buttonPanel.add(aboutButton);
        buttonPanel.add(exitButton);

        // Add panel to frame
        add(panel);

        setVisible(true); // Make the frame visible
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            dispose();
            new Register();
        } else if (e.getSource() == loginButton) {
            dispose();
            new Loging();
        } else if (e.getSource() == aboutButton) {
            // Add code for about button action
            System.out.println("About Us button clicked");
        } else if (e.getSource() == exitButton) {
            // Exit the application
            System.exit(0);
        }
    }
}
