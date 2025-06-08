import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Loging extends JFrame implements ActionListener {

    JLabel lab;
    JLabel username;
    JLabel password;
    JButton fp;
    JButton exit;
    JButton submit;
    JTextField userfield;
    JTextField passfield;
    public Loging(){
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lab = new JLabel("LOG-IN Page");
        fp = new JButton("Forget Password ?");
        exit = new JButton("EXIT");
        username = new JLabel("E-Mail");
        password = new JLabel("PASSWORD");
        submit = new JButton("SUBMIT");
        userfield = new JTextField();
        passfield = new JPasswordField();
        fp.setBounds(50,280,150,30);
        fp.setBackground(Color.white);
        fp.setForeground(Color.BLUE);
        exit.setBounds(290,200,100,40);
        exit.setBackground(Color.black);
        exit.setForeground(Color.white);
        lab.setBounds(250,0,100,50);
        lab.setForeground(Color.red);
        username.setBounds(150,100,80,30);
        password.setBounds(150,150,100,30);
        submit.setBounds(160,200,100,40);
        userfield.setBounds(300,100,200,30);
        passfield.setBounds(300,150,80,30);
        submit.setBackground(Color.black);
        submit.setForeground(Color.white);
        submit.addActionListener(this);
        fp.addActionListener(this);
        exit.addActionListener(this);
        add(username);
        add(exit);
        add(password);
        add(submit);
        add(userfield);
        add(passfield);
        add(lab);
        add(fp);
        setLayout(null);
        setSize(600,400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {

        if(e.getSource() == submit) {
            try {
                String url = "jdbc:mysql://localhost:3306/quizz";
                String username = "root";
                String password = "";
                Connection connection;
                connection = DriverManager.getConnection(url, username, password);
                String username1 = userfield.getText();
                String password1 = passfield.getText();
                String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, username1);
                statement.setString(2, password1);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    // Credentials are valid
                    String firstName = resultSet.getString("first_name");
                    int id =  Integer.parseInt(resultSet.getString("id"));
                    User loggedInUser = new User(firstName,id);
                    new LogPageUI(loggedInUser);
                    dispose();

                    System.out.println(resultSet.next());
                    System.out.println("you Loging Successfull");

                } else {
                    JOptionPane.showMessageDialog(Loging.this,
                            "Register First",
                            "Invalid Credentials",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception ee) {
                ee.printStackTrace();
            }

        }
    }
}
