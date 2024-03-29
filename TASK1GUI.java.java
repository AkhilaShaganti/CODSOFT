import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

class GuessTheNumberGUI extends JFrame {

    private JTextField guessField;
    private JTextArea feedbackArea;
    private JButton guessButton, playAgainButton;
    private int randomNumber;
    private int attempts;
    private int maxAttempts = 5;
    private int score;

    public GuessTheNumberGUI() {
        setTitle("Guess The Number");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel for guess input
        JPanel inputPanel = new JPanel();
        JLabel guessLabel = new JLabel("Enter your guess:");
        guessField = new JTextField(10);
        guessButton = new JButton("Guess");
        inputPanel.add(guessLabel);
        inputPanel.add(guessField);
        inputPanel.add(guessButton);

        // Panel for feedback
        JPanel feedbackPanel = new JPanel();
        feedbackArea = new JTextArea(5, 20);
        feedbackArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(feedbackArea);
        feedbackPanel.add(scrollPane);

        // Panel for play again button
        JPanel playAgainPanel = new JPanel();
        playAgainButton = new JButton("Play Again");
        playAgainButton.setVisible(false); // Initially hidden
        playAgainPanel.add(playAgainButton);

        add(inputPanel, BorderLayout.NORTH);
        add(feedbackPanel, BorderLayout.CENTER);
        add(playAgainPanel, BorderLayout.SOUTH);

        generateRandomNumber();
        setupListeners();
    }

    private void generateRandomNumber() {
        Random random = new Random();
        randomNumber = random.nextInt(100) + 1; // Generates a number between 1 and 100
    }

    private void setupListeners() {
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
    }

    private void checkGuess() {
        int guess;
        try {
            guess = Integer.parseInt(guessField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }

        attempts++;

        if (guess == randomNumber) {
            feedbackArea.append("Congratulations! You've guessed the correct number in " + attempts + " attempts.\n");
            score += attempts;
            guessButton.setEnabled(false);
            playAgainButton.setVisible(true);
        } else if (guess < randomNumber) {
            feedbackArea.append("Too low! Try again.\n");
        } else {
            feedbackArea.append("Too high! Try again.\n");
        }

        if (attempts >= maxAttempts) {
            feedbackArea.append("You've reached the maximum number of attempts. The correct number was: " + randomNumber + "\n");
            guessButton.setEnabled(false);
            playAgainButton.setVisible(true);
        }
    }

    private void resetGame() {
        generateRandomNumber();
        guessField.setText("");
        feedbackArea.setText("");
        attempts = 0;
        guessButton.setEnabled(true);
        playAgainButton.setVisible(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GuessTheNumberGUI game = new GuessTheNumberGUI();
                game.setVisible(true);
            }
        });
    }
}
