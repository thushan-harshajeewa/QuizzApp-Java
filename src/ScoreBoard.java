import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ScoreBoard extends JFrame implements ActionListener {

    private JButton jButton1;
    private JLabel jLabel1;
    private JScrollPane jScrollPane1;
    private JTable jTable1;
    User user;

    public ScoreBoard(User user) {
        this.user=user;
        initComponents();
        populateTable();
    }

    private void populateTable() {
        try {
            String url = "jdbc:mysql://localhost:3306/quizz";
            String username = "root";
            String password = "thushan";
            Connection connection = DriverManager.getConnection(url, username, password);

            String sql = "SELECT users.first_name, scores.category, scores.total, scores.grade " +
                    "FROM scores JOIN users ON scores.user_id = users.id order by scores.total DESC";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

            while (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String category = resultSet.getString("category");
                int totalScore = resultSet.getInt("total");
                String grade = resultSet.getString("grade"); // New column
                // Add data to the table model
                model.addRow(new Object[]{firstName, category, totalScore, grade});
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }

    private void initComponents() {
        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();
        jLabel1 = new JLabel();
        jButton1 = new JButton();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jTable1.setBackground(new java.awt.Color(255, 204, 51));
        jTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable1.setFont(new java.awt.Font("Segoe UI", 3, 14));

        // Set table model with center aligned cells for specific columns
        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{},
                new String[]{
                        "First Name", "Category", "Total Score", "Grade" // New column
                }
        ) {
            Class[] types = new Class[]{
                    java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class // New column
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        jTable1.setModel(model);

        // Set cell renderer for specific columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        jTable1.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // First Name
        jTable1.getColumnModel().getColumn(1).setCellRenderer(centerRenderer); // Category
        jTable1.getColumnModel().getColumn(2).setCellRenderer(centerRenderer); // Total Score
        jTable1.getColumnModel().getColumn(3).setCellRenderer(centerRenderer); // Grade

        jTable1.setGridColor(new java.awt.Color(255, 0, 204));
        jScrollPane1.setViewportView(jTable1);

        // Set preferred column widths
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(150); // First Name column width
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(350); // Category column width
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(150); // Total Score column width
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(150); // Grade column width

        // Set row height
        jTable1.setRowHeight(50); // Increase row height to 30 pixels

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24));
        jLabel1.setText("Score Board");

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jButton1.setText("Exit");
        jButton1.addActionListener(this);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(200, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(211, 211, 211)
                                .addComponent(jButton1)
                                .addGap(41, 41, 41))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 488, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(123, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26))
        );
        setVisible(true);
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jButton1) {
            dispose();
            new LogPageUI(user);
        }
    }


}
