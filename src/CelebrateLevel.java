import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CelebrateLevel extends JFrame implements ActionListener {

    private JLabel congra;
    String name;
    String category;
    int marks;
    int level;
    private JLabel imageLabel;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel marks1;
    private JLabel marks2;
    private JButton nextButton;
    private int user_id;

    public CelebrateLevel(String name, String category, int marks, int level, int user_id) {
        this.name = name;
        this.category = category;
        this.marks = marks;
        this.level = level;
        this.user_id = user_id;
        initComponents(name, category, marks, level);

    }

    private void initComponents(String name, String category, int marks, int level) {
        jLabel1 = new JLabel();
        imageLabel = new JLabel();
        congra = new JLabel();
        marks1 = new JLabel();
        marks2 = new JLabel();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        nextButton = new JButton();
        jLabel7 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel8 = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Load your image and set it to the imageLabel
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\user\\IdeaProjects\\QuizApp\\images\\woohoo.jpg"); // Change "path_to_your_image.jpg" to your image path
        Image image = imageIcon.getImage(); // Transform it
        Image newImage = image.getScaledInstance(325, 320, Image.SCALE_SMOOTH); // Scale it
        imageIcon = new ImageIcon(newImage); // Transform it back to an ImageIcon
        imageLabel.setIcon(imageIcon);

        congra.setBackground(new java.awt.Color(51, 0, 51));
        congra.setFont(new java.awt.Font("Segoe UI", 2, 24));
        congra.setForeground(new java.awt.Color(255, 153, 0));
        congra.setText("Congratulations!! You Complete Level " + level + ". Let's Do This...");

        marks1.setFont(new java.awt.Font("Sitka Small", 2, 20));
        marks1.setForeground(new java.awt.Color(204, 51, 0));
        marks1.setText("You Got " + marks + " Marks");

        marks2.setFont(new java.awt.Font("Sitka Small", 2, 20));
        marks2.setForeground(new java.awt.Color(204, 51, 0));
        marks2.setText("Out Of 5");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabel5.setForeground(new java.awt.Color(102, 0, 102));
        jLabel5.setText("Click Here To Go Next Level ");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24));
        jLabel6.setForeground(new java.awt.Color(153, 0, 204));
        jLabel6.setText("=>");

        nextButton.setBackground(new java.awt.Color(153, 255, 51));
        nextButton.setFont(new java.awt.Font("Segoe UI", 0, 24));
        nextButton.setForeground(new java.awt.Color(204, 0, 204));
        nextButton.setText("Next");
        nextButton.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        nextButton.addActionListener(this);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabel7.setText("--------------------------------------");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jLabel2.setText("Name:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabel3.setText(name);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jLabel4.setText("Category:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabel8.setText(category);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(imageLabel, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel7)
                                                        .addComponent(marks1, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE))
                                                .addContainerGap(48, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(marks2, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(6, 6, 6)
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel4)
                                                                        .addComponent(jLabel2))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel8, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE))))
                                                .addGap(0, 0, Short.MAX_VALUE))))
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(congra)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(109, Short.MAX_VALUE)
                                .addComponent(jLabel5, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jLabel6)
                                .addGap(37, 37, 37)
                                .addComponent(nextButton, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
                                .addGap(71, 71, 71))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel8))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(43, 43, 43)
                                                .addComponent(marks1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel7)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(marks2, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(imageLabel, GroupLayout.PREFERRED_SIZE, 320, GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(congra, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(nextButton, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(23, Short.MAX_VALUE))
        );
        setVisible(true);
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
        new NewQuizzPage(user_id);
    }


}
