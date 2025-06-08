import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ModifyQuizzPage extends JFrame implements ActionListener {

    private String url = "jdbc:mysql://localhost:3306/quizz";
    private String FirstName;
    private int user_id;
    private String username = "root";
    private String password = "thushan";
    private JLabel questionLabel;
    private JLabel nameLabel;
    private JPanel mainPanel;
   private JButton next;
    private JButton submit;
    private ArrayList<String> stringArrayList = new ArrayList<>();
    private ArrayList<String> AnswerList = new ArrayList<>();
    private int currentQuestionIndex = 0;
    private JRadioButton quest1;
    private JRadioButton quest2;
    private JRadioButton quest3;
    private JRadioButton quest4;

    String Correct_answer;
    String answer1;
    String answer2;
    String answer3;
    int Score=0;

    public ModifyQuizzPage(String FirstName,int user_id) {
        setTitle("Quiz Application");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        this.user_id=user_id;
        this.FirstName=FirstName;

        initUI(FirstName);
        loadQuestions();

        setVisible(true);
    }

    private void initUI(String FirstName) {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        add(mainPanel, BorderLayout.CENTER);



        JLabel nameLabel = new JLabel(FirstName);
        nameLabel.setHorizontalAlignment(JLabel.LEFT);
        nameLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        add(nameLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        next = new JButton("Next");
        next.addActionListener(this);
        buttonPanel.add(next);

        submit = new JButton("Submit"); // Initialize the submit button
        submit.addActionListener(this); // Add ActionListener to the submit button
        buttonPanel.add(submit);

        add(buttonPanel, BorderLayout.SOUTH);



        questionLabel = new JLabel();
        questionLabel.setHorizontalAlignment(JLabel.LEFT);
        mainPanel.add(questionLabel, BorderLayout.NORTH);

        JPanel answerPanel = new JPanel();
        answerPanel.setLayout(new GridLayout(4, 1, 10, 10));

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
        mainPanel.add(answerPanel, BorderLayout.CENTER);
    }

    private void loadQuestions() {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT id FROM questions";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                stringArrayList.add(resultSet.getString("id"));
            }

            showQuestion(currentQuestionIndex);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showQuestion(int index) {
        if (index >= 0 && index < stringArrayList.size()) {
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
                String sql = "SELECT question_description,answer1, answer2, answer3, correct_answer FROM questions WHERE id=?";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, question_id);
                ResultSet resultSet = statement.executeQuery();


                if (resultSet.next()) {
                    questionLabel.setText(resultSet.getString("question_description"));
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
                        new ModifyQuizzPage("liyanage",6);

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
                        new ModifyQuizzPage("liyanage",6);


                    } else if (Current_level==3) {
                        sql="UPDATE scores SET level_3 = ? WHERE user_id = ?";
                        statement=connection.prepareStatement(sql);
                        statement.setString(1,Integer.toString(Score));
                        statement.setString(2,Integer.toString(user_id));
                        statement.executeUpdate();


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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == next) {

            currentQuestionIndex++;
            showQuestion(currentQuestionIndex);

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
                JOptionPane.showMessageDialog(ModifyQuizzPage.this,
                        "Well Done",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                currentQuestionIndex++;
                Score++;
                showQuestion(currentQuestionIndex);
            }
            else {
                JOptionPane.showMessageDialog(ModifyQuizzPage.this,
                        "You Wrong, Correct Answer is ** "+Correct_answer +" **",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                currentQuestionIndex++;
                showQuestion(currentQuestionIndex);
            }
            }




        }





}
