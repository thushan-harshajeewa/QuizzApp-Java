import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CompleteCelebrate extends JFrame implements ActionListener {

    String name;
    String category;
    int marks;
    int level;
    private int user_id;

    private JLabel congra;
    private JLabel imageLabel1;
    private JLabel imageLabel2;
    private JButton play;
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

    public CompleteCelebrate(String name, String category, int marks, int level, int user_id) {
        this.name = name;
        this.category = category;
        this.marks = marks;
        this.level = level;
        this.user_id = user_id;
        initComponents(name, category, marks, level);
    }

    private void initComponents(String name, String category, int marks, int level) {
        jLabel1 = new JLabel();
        imageLabel1 = new JLabel();
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
        play = new JButton();
        imageLabel2 = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Load your image and set it to the imageLabel1
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\user\\IdeaProjects\\QuizApp\\images\\won.jpg");
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(325, 320, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImage);
        imageLabel1.setIcon(imageIcon);

        congra.setBackground(new java.awt.Color(51, 0, 51));
        congra.setFont(new java.awt.Font("Segoe UI", 2, 24));
        congra.setForeground(new java.awt.Color(255, 153, 0));
        congra.setText("Awsome Complete The Quizz....");

        marks1.setFont(new java.awt.Font("Sitka Small", 2, 20));
        marks1.setForeground(new java.awt.Color(204, 51, 0));
        marks1.setText("You Got Total " + marks );
        marks1.setPreferredSize(new Dimension(350, 50));

        marks2.setFont(new java.awt.Font("Sitka Small", 2, 20));
        marks2.setForeground(new java.awt.Color(204, 51, 0));
        marks2.setText("Out Of 15");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabel5.setForeground(new java.awt.Color(102, 0, 102));
        jLabel5.setText("Click To See Your Rank");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24));
        jLabel6.setForeground(new java.awt.Color(153, 0, 204));
        jLabel6.setText("=>");

        nextButton.setBackground(new java.awt.Color(153, 255, 51));
        nextButton.setFont(new java.awt.Font("Segoe UI", 0, 24));
        nextButton.setForeground(new java.awt.Color(204, 0, 204));
        nextButton.setText("ScoreBoard");
        nextButton.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        nextButton.addActionListener(this);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabel7.setText("--------------------------------------");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jLabel2.setText("Name");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabel3.setText(name);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jLabel4.setText("Category");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabel8.setText(category);

        play.setBackground(new java.awt.Color(255, 255, 0));
        play.setFont(new java.awt.Font("Segoe UI", 3, 18));
        play.setForeground(new java.awt.Color(51, 0, 255));
        play.setText("Play Again");
        play.addActionListener(this);

        imageLabel2.setText("imag2");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(congra, GroupLayout.PREFERRED_SIZE, 336, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(imageLabel2, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(61, 61, 61)
                                                .addComponent(imageLabel1, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel7)
                                                        .addComponent(marks2, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(marks1, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(21, 21, 21)
                                                .addComponent(play)
                                                .addGap(89, 89, 89)
                                                .addComponent(jLabel5, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
                                                .addGap(33, 33, 33)
                                                .addComponent(jLabel6)
                                                .addGap(37, 37, 37)
                                                .addComponent(nextButton, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(19, 19, 19)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel2)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel4)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jLabel8, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)))))
                                .addGap(0, 27, Short.MAX_VALUE))
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
                                                .addGap(214, 214, 214)
                                                .addComponent(marks1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel7)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(marks2, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                                .addGap(68, 68, 68))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(imageLabel1, GroupLayout.PREFERRED_SIZE, 320, GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(congra, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 4, Short.MAX_VALUE))
                                        .addComponent(imageLabel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(nextButton, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(play))
                                .addGap(23, 23, 23))
        );
        setVisible(true);
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton) {

            dispose();
            User user = new User(name,user_id);
            new ScoreBoard(user);

        }
        if (e.getSource() == play) {
            dispose();

            new CategoryNew(user_id,name);

        }
    }


}
