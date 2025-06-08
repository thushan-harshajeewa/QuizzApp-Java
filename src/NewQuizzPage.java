import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.*;

public class NewQuizzPage extends JFrame implements ActionListener {

    private String url = "jdbc:mysql://localhost:3306/quizz";
    private String FirstName;
    private String Category;
    private String Level;

    private int user_id;
    private String username = "root";
    private String password = "thushan";
    private ArrayList<String> stringArrayList = new ArrayList<>();
    ArrayList<String> originalList = new ArrayList<>();
    private ArrayList<String> AnswerList = new ArrayList<>();
    private int currentQuestionIndex = 0;
    private boolean fiftyUsed = false;


    private JRadioButton quest1;
    private JRadioButton quest2;
    private JRadioButton quest3;
    private JRadioButton quest4;

    String Correct_answer;
    String answer1;
    String answer2;
    String answer3;
    int Score=0;
    int k=5;
    private JPanel answerPanel;
    private JButton skip;
    private JButton fifty;
    private JButton submit;
    private JButton exit;
    private JLabel category;
    private JLabel currentLevel;
    private JLabel imageLabel;
    private JLabel lifeLines;
    private JLabel myCategory;
    private JLabel myLevel;
    private JLabel myName;
    private JLabel name;
    private JLabel QuestionLabel;
    User user;

    public NewQuizzPage(int user_id) {


        try {
            String url = "jdbc:mysql://localhost:3306/quizz";
            String username = "root";
            String password = "thushan";
            Connection connection;
            connection = DriverManager.getConnection(url, username, password);

            String sql = "SELECT * FROM quizz.users join scores on users.id=scores.user_id where user_id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, Integer.toString(user_id));
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Credentials are valid
                this.FirstName = resultSet.getString("first_name");
                 this.Category=  resultSet.getString("category");
                 this.Level=  resultSet.getString("current_level");

                this.user=new User(resultSet.getString("first_name"),user_id);

            } else {
                JOptionPane.showMessageDialog(NewQuizzPage.this,
                        "",
                        "Invalid Credentials",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception ee) {
            ee.printStackTrace();
        }


        this.user_id=user_id;
        this.FirstName=FirstName;

        initComponents();
        loadQuestions();

    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        category = new JLabel();
        name = new JLabel();
        currentLevel = new JLabel();
        myCategory = new JLabel();
        myName = new JLabel();
        myLevel = new JLabel();
        imageLabel = new JLabel();
        QuestionLabel = new JLabel();
        answerPanel = new JPanel();
        lifeLines = new JLabel();
        skip = new JButton();
        fifty = new JButton();
        submit = new JButton();
        exit = new JButton();

        category.setText("Category:");

        name.setText("Name:");

        currentLevel.setText("Current Level:");

        myCategory.setText(Category);

        myName.setText(FirstName);

        myLevel.setText(Level);

        imageLabel.setText("jLabel7");
        imageLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));



        javax.swing.GroupLayout answerPanelLayout = new javax.swing.GroupLayout(answerPanel);
        answerPanel.setLayout(answerPanelLayout);
        answerPanelLayout.setHorizontalGroup(
                answerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 458, Short.MAX_VALUE)
        );
        answerPanelLayout.setVerticalGroup(
                answerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 232, Short.MAX_VALUE)
        );

        answerPanel.setLayout(new GridLayout(6, 2, 100, 10));
        answerPanel.setPreferredSize(new Dimension(500, 200));


        quest1 = new JRadioButton();
        quest2 = new JRadioButton();
        quest3 = new JRadioButton();
        quest4 = new JRadioButton();
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(quest1);
        buttonGroup.add(quest2);
        buttonGroup.add(quest3);
        buttonGroup.add(quest4);

        answerPanel.add(quest1);
        answerPanel.add(quest2);
        answerPanel.add(quest3);
        answerPanel.add(quest4);

        lifeLines.setText("Life Lines");

        skip.setText("Skip");

        fifty.setText("50/50");

        submit.setText("Submit");

        exit.setText("Exit");

        skip.addActionListener(this);
        fifty.addActionListener(this);
        submit.addActionListener(this);
        exit.addActionListener(this);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(75, 75, 75)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(category)
                                                        .addComponent(name))
                                                .addGap(31, 31, 31)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(myCategory)
                                                        .addComponent(myName)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(currentLevel)
                                                .addGap(10, 10, 10) // Adjusted gap between currentLevel and myLevel
                                                .addComponent(myLevel)
                                                .addGap(36, 36, 36)
                                                .addComponent(exit)
                                                .addGap(24, 24, 24))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(33, 33, 33)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(QuestionLabel)
                                                        .addComponent(answerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(163, 163, 163)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(skip)
                                                        .addComponent(lifeLines)
                                                        .addComponent(fifty)
                                                        .addComponent(submit))
                                                .addContainerGap(77, Short.MAX_VALUE))))
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(category)
                                        .addComponent(myCategory)
                                        .addComponent(currentLevel)
                                        .addComponent(myLevel) // No need to adjust vertical alignment
                                        .addComponent(exit))
                                .addGap(17, 17, 17)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(name)
                                        .addComponent(myName))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 152, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(QuestionLabel)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(42, 42, 42)
                                                                .addComponent(lifeLines)
                                                                .addGap(24, 24, 24)
                                                                .addComponent(skip)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(fifty)
                                                                .addGap(106, 106, 106)
                                                                .addComponent(submit))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(57, 57, 57)
                                                                .addComponent(answerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(20, 20, 20))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap())))
        );


        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(category)
                                        .addComponent(myCategory)
                                        .addComponent(currentLevel)
                                        .addComponent(myLevel) // No need to adjust vertical alignment
                                        .addComponent(exit))
                                .addGap(17, 17, 17)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(name)
                                        .addComponent(myName))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 152, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(QuestionLabel)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(42, 42, 42)
                                                                .addComponent(lifeLines)
                                                                .addGap(24, 24, 24)
                                                                .addComponent(skip)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(fifty)
                                                                .addGap(106, 106, 106)
                                                                .addComponent(submit))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(57, 57, 57)
                                                                .addComponent(answerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(20, 20, 20))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap())))
        );

        Font labelFont = new Font("Arial", Font.BOLD, 16);
        category.setFont(labelFont);
        name.setFont(labelFont);
        currentLevel.setFont(labelFont);
        myCategory.setFont(labelFont);
        myName.setFont(labelFont);
        myLevel.setFont(labelFont);
        lifeLines.setFont(labelFont);
        QuestionLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        // Set background color for labels
        category.setForeground(Color.BLUE);
        name.setForeground(Color.BLUE);
        currentLevel.setForeground(Color.BLUE);
        myCategory.setForeground(Color.BLACK);
        myName.setForeground(Color.BLACK);
        myLevel.setForeground(Color.BLACK);
        lifeLines.setForeground(Color.RED);

        // Set font and background color for buttons
        Font buttonFont = new Font("Arial", Font.BOLD, 14);
        skip.setFont(buttonFont);
        fifty.setFont(buttonFont);
        submit.setFont(buttonFont);
        exit.setFont(buttonFont);
        skip.setBackground(Color.GREEN);
        fifty.setBackground(Color.YELLOW);
        submit.setBackground(Color.ORANGE);
        exit.setBackground(Color.RED);




        pack();
        setVisible(true);
    }

    private void loadQuestions() {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {

            String sql = "SELECT id FROM questions where category=? and level=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, Category);
            statement.setString(2, Level);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                originalList.add(resultSet.getString("id"));
            }

            Random random = new Random();

            // Select 5 random integers from the original list
            for (int i = 0; i < 6; i++) {
                // Generate a random index within the range of the original list size
                int randomIndex = random.nextInt(originalList.size());

                // Add the element at the random index to the new list
                stringArrayList.add(originalList.get(randomIndex));

                // Remove the selected element from the original list to avoid duplicates
                originalList.remove(randomIndex);
            }
            System.out.println(originalList);
            System.out.println(stringArrayList);
            showQuestion(currentQuestionIndex);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showQuestion(int index) {
        quest1.setEnabled(true);
        quest2.setEnabled(true);
        quest3.setEnabled(true);
        quest4.setEnabled(true);

        if (index >= 0 && index < k) {
            String question_id = stringArrayList.get(index);
            //questionLabel.setText(question);

            ButtonGroup buttonGroup = new ButtonGroup();
            buttonGroup.add(quest1);
            buttonGroup.add(quest2);
            buttonGroup.add(quest3);
            buttonGroup.add(quest4);
            buttonGroup.clearSelection();

            // Load answers for the current question from the database and set them to radio buttons
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                String sql = "SELECT question_description,answer1, answer2, answer3, correct_answer,question_image_path FROM questions WHERE id=?";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, question_id);
                ResultSet resultSet = statement.executeQuery();


                if (resultSet.next()) {
                    System.out.println(resultSet.getString("question_image_path"));
                    //ImageIcon imageIcon = new ImageIcon(resultSet.getString("C:\\Users\\user\\IdeaProjects\\QuizApp\\images\\information1.jpg"));
                    ImageIcon imageIcon = new ImageIcon(resultSet.getString("question_image_path"));
                    // Resize the image to fit the label dimensions
                    Image scaledImage = imageIcon.getImage().getScaledInstance(345, 361, Image.SCALE_SMOOTH);
                    ImageIcon scaledIcon = new ImageIcon(scaledImage);
                    // Set the scaled image to the label
                    imageLabel.setIcon(scaledIcon);
                    QuestionLabel.setText(resultSet.getString("question_description"));
                    Correct_answer=resultSet.getString("correct_answer");
                    answer1=resultSet.getString("answer1");
                    answer2=resultSet.getString("answer2");
                    answer3=resultSet.getString("answer3");

                    AnswerList.clear();

                    AnswerList.add(answer1);
                    AnswerList.add(answer2);
                    AnswerList.add(answer3);
                    AnswerList.add(Correct_answer);

                    Collections.shuffle(AnswerList);

                    quest1.setText("1) " + AnswerList.get(0));
                    quest2.setText("2) " + AnswerList.get(1));
                    quest3.setText("3) " + AnswerList.get(2));
                    quest4.setText("4) " + AnswerList.get(3));

                    System.out.println("Radio Button 1 Text: " + quest1.getText());
                    System.out.println("Radio Button 2 Text: " + quest2.getText());
                    System.out.println("Radio Button 3 Text: " + quest3.getText());
                    System.out.println("Radio Button 4 Text: " + quest4.getText());

                    statement.close();
                    resultSet.close();
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        else {
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
                    int Current_level=Integer.parseInt(resultSet.getString("current_level"));

                    if (Current_level==1){
                        sql="UPDATE scores SET level_1 = ? WHERE user_id = ?";
                        statement=connection.prepareStatement(sql);
                        statement.setString(1,Integer.toString(Score));
                        statement.setString(2,Integer.toString(user_id));
                        statement.executeUpdate();

                        sql="UPDATE scores SET current_level = ? WHERE user_id = ?";
                        statement=connection.prepareStatement(sql);
                        statement.setString(1,Integer.toString(2));
                        statement.setString(2,Integer.toString(user_id));
                        statement.executeUpdate();
                        dispose();
                        new CelebrateLevel(FirstName,Category,Score,1,user_id);
                    } else if (Current_level==2) {
                        sql="UPDATE scores SET level_2 = ? WHERE user_id = ?";
                        statement=connection.prepareStatement(sql);
                        statement.setString(1,Integer.toString(Score));
                        statement.setString(2,Integer.toString(user_id));
                        statement.executeUpdate();


                        sql="UPDATE scores SET current_level = ? WHERE user_id = ?";
                        statement=connection.prepareStatement(sql);
                        statement.setString(1,Integer.toString(3));
                        statement.setString(2,Integer.toString(user_id));
                        statement.executeUpdate();
                        dispose();
                        new CelebrateLevel(FirstName,Category,Score,2,user_id);



                    } else if (Current_level==3) {
                        sql="UPDATE scores SET level_3 = ? WHERE user_id = ?";
                        statement=connection.prepareStatement(sql);
                        statement.setString(1,Integer.toString(Score));
                        statement.setString(2,Integer.toString(user_id));
                        statement.executeUpdate();

                        sql="UPDATE scores SET total = COALESCE(level_1, 0) + COALESCE(level_2, 0) + COALESCE(level_3, 0) where user_id=?";
                        statement=connection.prepareStatement(sql);
                        statement.setString(1,Integer.toString(user_id));
                        statement.executeUpdate();

                        sql="UPDATE scores SET grade = CASE WHEN total >= 12 THEN 'A+' WHEN total >= 9 AND total < 12 THEN 'B+' WHEN total >= 5 AND total < 9 THEN 'C+' ELSE 'D' END WHERE user_id = ?";
                        statement=connection.prepareStatement(sql);
                        statement.setString(1,Integer.toString(user_id));
                        statement.executeUpdate();



                        sql="SELECT total FROM scores WHERE user_id = ?";
                        statement=connection.prepareStatement(sql);
                        statement.setString(1,Integer.toString(user_id));
                        resultSet=statement.executeQuery();



                        dispose();

                        if (resultSet.next()){
                            new CompleteCelebrate(FirstName,Category,resultSet.getInt("total"),3,user_id);
                        }
                        else {
                            System.out.println("fail");
                        }


                        System.out.println("you Clompleted the Surevey");
                    }

                } else {
                    sql="INSERT INTO scores (category, current_level, user_id, level_1) VALUES (?,?,?,?)";
                    statement=connection.prepareStatement(sql);
                    statement.setString(1,"Information Technology");
                    statement.setString(2,Integer.toString(1));
                    statement.setString(3,Integer.toString(user_id));
                    statement.setString(4,Integer.toString(Score));
                    statement.executeUpdate();

                    sql="UPDATE scores SET current_level = ? WHERE user_id = ?";
                    statement=connection.prepareStatement(sql);
                    statement.setString(1,Integer.toString(2));
                    statement.setString(2,Integer.toString(user_id));
                    statement.executeUpdate();

                    dispose();
                    new ModifyQuizzPage("liyanage",6);

                    System.out.println("done");

                }
                resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception ee) {
                ee.printStackTrace();
            }

            System.out.println("score is: "+Score);
        }
    }

    private void disableTwoWrongAnswers() {

        String correctAnswer = Correct_answer;


        ArrayList<Integer> wrongIndices = new ArrayList<>();


        for (int i = 0; i < AnswerList.size(); i++) {
            if (!AnswerList.get(i).equals(correctAnswer)) {
                wrongIndices.add(i);
            }
        }


        Collections.shuffle(wrongIndices);
        int index1 = wrongIndices.get(0);
        int index2 = wrongIndices.get(1);


        switch (index1) {
            case 0:
                quest1.setEnabled(false);
                break;
            case 1:
                quest2.setEnabled(false);
                break;
            case 2:
                quest3.setEnabled(false);
                break;
            case 3:
                quest4.setEnabled(false);
                break;
        }

        switch (index2) {
            case 0:
                quest1.setEnabled(false);
                break;
            case 1:
                quest2.setEnabled(false);
                break;
            case 2:
                quest3.setEnabled(false);
                break;
            case 3:
                quest4.setEnabled(false);
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==exit){
            dispose();
            new LogPageUI(user);
        }
        if (e.getSource() == skip) {

            currentQuestionIndex++;
            k++;
            skip.setEnabled(false);
            skip.setBackground(Color.gray);
            showQuestion(currentQuestionIndex);

        }
        if (e.getSource()==fifty){
            if (!fiftyUsed) {

                disableTwoWrongAnswers();

                fiftyUsed = true;
                fifty.setBackground(Color.gray);
                fifty.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(NewQuizzPage.this,
                        "You've already used the 50/50 lifeline.",
                        "Info",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }

        if(e.getSource()==submit){
            String selectedAnswer = "";

            if (quest1.isSelected()) {
                selectedAnswer = quest1.getText().substring(3); // Extracting answer text (excluding the answer number)
            } else if (quest2.isSelected()) {
                selectedAnswer = quest2.getText().substring(3);
            } else if (quest3.isSelected()) {
                selectedAnswer = quest3.getText().substring(3);
            } else if (quest4.isSelected()) {
                selectedAnswer = quest4.getText().substring(3);
            }

            if (selectedAnswer.equals(Correct_answer)){
                JOptionPane.showMessageDialog(NewQuizzPage.this,
                        "Well Done",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                currentQuestionIndex++;
                Score++;
                showQuestion(currentQuestionIndex);
            }
            else {
                JOptionPane.showMessageDialog(NewQuizzPage.this,
                        "You Wrong, Correct Answer is ** "+Correct_answer +" **",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                currentQuestionIndex++;
                showQuestion(currentQuestionIndex);
            }
        }




    }



}
