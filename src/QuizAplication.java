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


public class QuizAplication extends JFrame implements ActionListener {

    String url = "jdbc:mysql://localhost:3306/quizz";
    String username = "root";
    String password = "thushan";
    JLabel questionLabel;
    private JPanel mainPanel;
    JButton next;
    ArrayList<String> stringArrayList = new ArrayList<>();
    int i=1;

    JRadioButton quest1;
    JRadioButton quest2;
    JRadioButton quest3;
    JRadioButton quest4;


    public QuizAplication(){



        setTitle("Quiz Application");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        next = new JButton("Next");
        next.setBounds(400,300,150,30);
        next.addActionListener(this);

        mainPanel = new JPanel(new GridLayout(0, 2));
        add(mainPanel);
        mainPanel.add(next);

        questionLabel = new JLabel();

        quest1 = new JRadioButton();
        quest2 = new JRadioButton();
        quest3 = new JRadioButton();
        quest4 = new JRadioButton();

        setVisible(true);






        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT id FROM questions";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                stringArrayList.add(resultSet.getString("id"));
            }

            showNextQuestion();
            System.out.println(stringArrayList);
//            String questionDescription = resultSet.getString("question_description");
//            JLabel questionLabel = new JLabel(questionDescription);
//            questionLabel.setBounds(100, 50, 300, 80);
//            mainPanel.add(questionLabel);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void actionPerformed(ActionEvent e) {


        if(e.getSource() == next) {
            showNextQuestion();
        }








}
    public void showNextQuestion(){

        if (i<=stringArrayList.size()){

            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                String sql = "SELECT * FROM questions where id=?";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, Integer.toString(i));
                ResultSet resultSet = statement.executeQuery();


                if (resultSet.next()) {
                    questionLabel.setText(resultSet.getString("question_description"));
                    questionLabel.setBounds(100, 50, 300, 80);
                    mainPanel.add(questionLabel);

                    ButtonGroup buttonGroup = new ButtonGroup();

                     quest1.setText("1) " + resultSet.getString("answer1"));
                     quest2.setText("2) " + resultSet.getString("answer2"));
                     quest3.setText("3) " + resultSet.getString("answer3"));
                     quest4.setText("4) " + resultSet.getString("correct_answer"));

                    buttonGroup.add(quest1);
                    buttonGroup.add(quest2);
                    buttonGroup.add(quest3);
                    buttonGroup.add(quest4);

                    mainPanel.add(quest1);
                    mainPanel.add(quest2);
                    mainPanel.add(quest3);
                    mainPanel.add(quest4);
                }
                i++;


            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
    }


}
