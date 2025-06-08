import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.*;

public class CategoryNew extends JFrame implements ActionListener {
    private JButton historyButton;
    private JButton informationTechnologyButton;
    private JButton jButton3;
    private JButton jButton4;
    private JButton exit;
    private JLabel jLabel1;

    private int user_id;
    User user;

    public CategoryNew(int user_id,String first_name) {
        this.user=new User(first_name,user_id);
        this.user_id=user_id;
        initComponents();
    }

    private void initComponents() {
        informationTechnologyButton = new JButton();
        historyButton = new JButton();
        jLabel1 = new JLabel();
        jButton3 = new JButton();
        jButton4 = new JButton();
        exit = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        informationTechnologyButton.setBackground(new java.awt.Color(255, 102, 102));
        informationTechnologyButton.setFont(new java.awt.Font("Segoe UI", 1, 14));
        informationTechnologyButton.setText("INFORMATION TECHNOLOGY");
        informationTechnologyButton.addActionListener(this);

        historyButton.setBackground(new java.awt.Color(255, 255, 102));
        historyButton.setFont(new java.awt.Font("Segoe UI", 1, 14));
        historyButton.setText("HISTORY");
        historyButton.addActionListener(this);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 18));
        jLabel1.setText("Select Your Favorite Category");

        jButton3.setBackground(new java.awt.Color(102, 255, 102));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jButton3.setText("SCIENCE");
        jButton3.addActionListener(this);

        jButton4.setBackground(new java.awt.Color(0, 204, 102));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jButton4.setText("GEOGRAPHY");
        jButton4.addActionListener(this);

        exit.setBackground(new java.awt.Color(255, 102, 51));
        exit.setFont(new java.awt.Font("Segoe UI", 1, 12));
        exit.setText("Exit");
        exit.addActionListener(this);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(historyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(186, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jButton4)
                                                .addGap(232, 232, 232))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(informationTechnologyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(168, 168, 168))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(exit)
                                        .addComponent(jLabel1))
                                .addGap(85, 85, 85)
                                .addComponent(informationTechnologyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(historyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(94, 94, 94)
                                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(50, 50, 50))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(190, 190, 190))))
        );
        setVisible(true);
        pack();
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == informationTechnologyButton) {
            insertData("INFORMATION TECHNOLOGY",user_id);
            System.out.println("Information Technology button clicked.");
        } else if (e.getSource() == historyButton) {
            insertData("HISTORY",user_id);
            System.out.println("History button clicked.");
        } else if (e.getSource() == jButton3) {
            // Code to handle click on Science button
        } else if (e.getSource() == jButton4) {
            // Code to handle click on Geography button
        } else if (e.getSource() == exit) {
            dispose();
            new LogPageUI(user);
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
