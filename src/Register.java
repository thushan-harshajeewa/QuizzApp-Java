import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Register extends JFrame implements ActionListener {

    JButton btn_submit;
    JLabel labe;
    JButton Back;
    JButton btn_exit;
    JTextField tf_email;
    JPasswordField tf_password; // Change from JTextField to JPasswordField
    JLabel l_registration;
    JLabel l_firstName;
    JTextField tf_firstName;
    JLabel l_email;
    JLabel l_password;

    public Register(){
        setTitle("Registration");
        Back = new JButton("Back");
        Back.setBounds(330,200,100,30);
        Back.setForeground(Color.white);
        Back.setBackground(Color.black);
        labe = new JLabel("Note: Use numbers and special characters for a strong password");
        l_registration = new JLabel("REGISTRATION FORM");
        l_registration.setForeground(Color.red);
        labe.setBounds(50,280,400,40);
        labe.setForeground(Color.red);
        l_registration.setBounds(230,0,250,40);

        l_firstName = new JLabel("FirstName");
        l_firstName.setBounds(120,50,80,30);
        tf_firstName = new JTextField();
        tf_firstName.setBounds(300,50,80,30);

        l_email = new JLabel("E-Mail");
        l_email.setBounds(120,100,80,30);
        tf_email = new JTextField();
        tf_email.setBounds(300,100,200,30);

        l_password = new JLabel("CREATE PASSWORD");
        l_password.setBounds(100,150,80,30);
        tf_password = new JPasswordField(); // Change to JPasswordField
        tf_password.setBounds(300,150,200,30);

        btn_submit = new JButton("SUBMIT");
        btn_submit.setBounds(100,200,100,30);
        btn_submit.setForeground(Color.white);
        btn_submit.setBackground(Color.black);
        btn_submit.addActionListener(this);
        btn_exit = new JButton("EXIT");
        btn_exit.setBounds(225,200,80,30);
        btn_exit.setForeground(Color.white);
        btn_exit.setBackground(Color.black);
        btn_exit.addActionListener(this);
        Back.addActionListener(this);
        add(btn_submit);
        add(btn_exit);
        add(tf_email);
        add(tf_password);
        add(tf_firstName);

        add(l_email);
        add(l_firstName);
        add(l_password);
        add(labe);
        add(l_registration);
        add(Back);
        setLayout(null);
        setSize(600,600);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        String firstName = tf_firstName.getText();
        String email = tf_email.getText();
        String password = new String(tf_password.getPassword()); // Retrieve password as String

        if(e.getSource() == Back) {
            // Handle login button click
            dispose();
            new HomePageUI();
        }

        if(e.getSource() == btn_submit) {
            // Establish JDBC connection
            String url = "jdbc:mysql://localhost:3306/quizz";
            String user = "root";
            String dbPassword = "";

            try (Connection connection = DriverManager.getConnection(url, user, dbPassword)) {
                // Create and execute SQL INSERT query
                String sql = "INSERT INTO users (first_name, email, password) VALUES (?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, firstName);
                    statement.setString(2, email);
                    statement.setString(3, password);

                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(Register.this,
                                "Registration successful for " + firstName + ". Please Login Now",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        new HomePageUI();
                    } else {
                        JOptionPane.showMessageDialog(Register.this,
                                "Registration failed",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(Register.this,
                        "Registration failed: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        if(e.getSource() == btn_exit) {
            dispose();
        }

    }
}
