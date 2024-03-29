import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizApplication extends JFrame implements ActionListener {
    private JLabel questionLabel, timerLabel, resultLabel;
    private JRadioButton[] optionButtons;
    private ButtonGroup optionGroup;
    private JButton submitButton;
    private Timer timer;
    private int timeLeft = 20; // 20 seconds for each question
    private int currentQuestionIndex = 0;
    private int score = 0;

    private String[] questions = {
            "What is the capital of France?",
            "Who painted the Mona Lisa?",
            "What is the powerhouse of the cell?",
            "Who wrote 'To Kill a Mockingbird'?",
            "What is the chemical symbol for water?"
    };

    private String[][] options = {
            {"Paris", "London", "Berlin", "Rome"},
            {"Leonardo da Vinci", "Vincent van Gogh", "Pablo Picasso", "Michelangelo"},
            {"Nucleus", "Mitochondria", "Ribosome", "Chloroplast"},
            {"Harper Lee", "J.K. Rowling", "George Orwell", "Charles Dickens"},
            {"H2O", "CO2", "O2", "NaCl"}
    };

    private int[] answers = {0, 0, 1, 0, 0}; // Index of correct answers

    public QuizApplication() {
        setTitle("Quiz Application");
        setSize(400, 300);
        setLayout(null);

        questionLabel = new JLabel();
        questionLabel.setBounds(20, 20, 360, 20);
        add(questionLabel);

        optionButtons = new JRadioButton[4];
        optionGroup = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton();
            optionGroup.add(optionButtons[i]);
            optionButtons[i].setBounds(20, 50 + i * 30, 360, 20);
            add(optionButtons[i]);
        }

        timerLabel = new JLabel();
        timerLabel.setBounds(300, 10, 100, 20);
        timerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        add(timerLabel);

        submitButton = new JButton("Submit");
        submitButton.setBounds(150, 200, 100, 30);
        submitButton.addActionListener(this);
        add(submitButton);

        resultLabel = new JLabel();
        resultLabel.setBounds(20, 240, 360, 20);
        add(resultLabel);

        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                timerLabel.setText("Time Left: " + timeLeft + "s");
                if (timeLeft == 0) {
                    timer.stop();
                    evaluateAnswer(-1); // Timeout
                }
            }
        });

        displayQuestion();
        setVisible(true);
    }

    private void displayQuestion() {
        questionLabel.setText(questions[currentQuestionIndex]);
        for (int i = 0; i < 4; i++) {
            optionButtons[i].setText(options[currentQuestionIndex][i]);
            optionButtons[i].setSelected(false);
        }
        timeLeft = 20;
        timerLabel.setText("Time Left: 20s");
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            int selectedOption = -1;
            for (int i = 0; i < 4; i++) {
                if (optionButtons[i].isSelected()) {
                    selectedOption = i;
                    break;
                }
            }
            timer.stop();
            evaluateAnswer(selectedOption);
        }
    }

    private void evaluateAnswer(int selectedOption) {
        if (selectedOption == answers[currentQuestionIndex]) {
            score++;
            resultLabel.setText("Correct! Score: " + score);
        } else {
            resultLabel.setText("Incorrect! Score: " + score);
        }
        currentQuestionIndex++;
        if (currentQuestionIndex < questions.length) {
            displayQuestion();
        } else {
            displayResult();
        }
    }

    private void displayResult() {
        questionLabel.setText("Quiz Completed!");
        timerLabel.setText("");
        for (int i = 0; i < 4; i++) {
            optionButtons[i].setVisible(false);
        }
        submitButton.setVisible(false);
        resultLabel.setText("Final Score: " + score + " out of " + questions.length);
    }

    public static void main(String[] args) {
        new QuizApplication();
    }
}
