import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogPageUI extends JFrame implements ActionListener {

    private JButton StartQuizz;
    private JButton ScoreBoard;
    private JButton aboutButton;
    private JButton exitButton;
    JLabel welcomeLabel;
    User user;

    public LogPageUI(User user) {
        this.user=user;
        setTitle("Home Page");
        welcomeLabel = new JLabel("Welcome, " + user.getFirstName() + "!");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(welcomeLabel, BorderLayout.NORTH); // Add welcome label to the top

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 640);
        setLocationRelativeTo(null); // Center the frame on the screen

        // Add a label with the photo below the welcome label
        JLabel photoLabel = new JLabel(new ImageIcon("C:\\Users\\user\\OneDrive\\Desktop\\Quizz App\\QuizApp\\src\\quizz.jpg"));
        photoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(photoLabel, BorderLayout.CENTER);

        // Create a panel for holding the buttons
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 0, 10)); // 4 rows, 1 column

        // Add buttons
        StartQuizz = new JButton("Start Quizz");
        ScoreBoard = new JButton("Score Board");
        aboutButton = new JButton("About Us");
        exitButton = new JButton("Exit");

        Font buttonFont = new Font("Arial", Font.BOLD, 18);
        StartQuizz.setFont(buttonFont);
        StartQuizz.setForeground(Color.WHITE);
        ScoreBoard.setFont(buttonFont);
        ScoreBoard.setForeground(Color.WHITE);
        aboutButton.setFont(buttonFont);
        aboutButton.setForeground(Color.WHITE);
        exitButton.setFont(buttonFont);
        exitButton.setForeground(Color.WHITE);

        StartQuizz.setBackground(Color.MAGENTA);
        ScoreBoard.setBackground(Color.MAGENTA);
        aboutButton.setBackground(Color.MAGENTA);
        exitButton.setBackground(Color.MAGENTA);

        // Add action listeners to buttons
        StartQuizz.addActionListener(this);
        ScoreBoard.addActionListener(this);
        aboutButton.addActionListener(this);
        exitButton.addActionListener(this);

        // Add buttons to button panel
        buttonPanel.add(StartQuizz);
        buttonPanel.add(ScoreBoard);
        buttonPanel.add(aboutButton);
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.SOUTH); // Add button panel to the bottom

        setVisible(true); // Make the frame visible
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == StartQuizz) {
            dispose();
            new CategoryNew(user.getId(),user.getFirstName());
            System.out.println("quizz is Starting");
        } else if (e.getSource() == ScoreBoard) {
            dispose();
            new ScoreBoard(user);
        } else if (e.getSource() == aboutButton) {
            // Add code for about button action
            System.out.println("About Us button clicked");
        } else if (e.getSource() == exitButton) {
            // Exit the application
            dispose();
            new HomePageUI();
        }
    }
}
