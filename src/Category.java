import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Category extends JFrame implements ActionListener {
    private JButton informationTechnologyButton;
    private JButton historyButton;
    private JLabel jLabel1;
    private int user_id;
    User user;

    public Category(int user_id,String first_name) {
        new User(first_name,user_id);
        this.user_id=user_id;
        informationTechnologyButton = new JButton();
        historyButton = new JButton();
        jLabel1 = new JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        informationTechnologyButton.setText("INFORMATION TECHNOLOGY");
        informationTechnologyButton.addActionListener(this);

        historyButton.setText("HISTORY");
        historyButton.addActionListener(this);

        jLabel1.setText("Category");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(informationTechnologyButton)
                                        .addComponent(jLabel1))
                                .addGap(42, 42, 42)
                                .addComponent(historyButton)
                                .addContainerGap(107, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel1)
                                .addGap(150, 150, 150)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(informationTechnologyButton)
                                        .addComponent(historyButton))
                                .addContainerGap(92, Short.MAX_VALUE))
        );

        pack(); // Packs the components
        setVisible(true); // Sets the frame visible
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == informationTechnologyButton) {
           insertData("INFORMATION TECHNOLOGY",user_id);
            System.out.println("Information Technology button clicked.");
        } else if (e.getSource() == historyButton) {
            // Code for History button action
            insertData("HISTORY",user_id);
            System.out.println("History button clicked.");
        }
    }

    public void insertData(String category,int user_id){
        try {
            String url = "jdbc:mysql://localhost:3306/quizz";
            String username = "root";
            String password = "thushan";
            Connection connection;
            connection = DriverManager.getConnection(url, username, password);
            String sql = "SELECT * FROM scores WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, Integer.toString(user_id));

            ResultSet resultSet = statement.executeQuery();
            System.out.println(user_id);


            if (resultSet.next()) {
                // Credentials are valid



                sql = "UPDATE scores SET category = ?, current_level = ?, level_1 = ?, level_2 = ?, level_3 = ?, total = ? WHERE user_id = ?";
                statement = connection.prepareStatement(sql);
                statement.setString(1,category);
                statement.setString(2, Integer.toString(1));
                statement.setString(3, Integer.toString(0));
                statement.setString(4, Integer.toString(0));
                statement.setString(5, Integer.toString(0));
                statement.setString(6, Integer.toString(0));
                statement.setString(7, Integer.toString(user_id));
                statement.executeUpdate();
                dispose();
                new NewQuizzPage(user_id);





            } else {
                sql="INSERT INTO scores (category, current_level, user_id) VALUES (?,?,?)";
                statement=connection.prepareStatement(sql);
                statement.setString(1,category);
                statement.setString(2,Integer.toString(1));
                statement.setString(3,Integer.toString(user_id));
                statement.executeUpdate();
                dispose();
                new NewQuizzPage(user_id);

                System.out.println("done");

            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }
}
