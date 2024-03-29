import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GradeCalculatorGUI extends JFrame implements ActionListener {
    JLabel[] subjectLabels;
    JTextField[] subjectTextFields;
    JButton calculateButton;
    JLabel totalMarksLabel, averagePercentageLabel, gradeLabel;

    public GradeCalculatorGUI() {
        setTitle("Grade Calculator");
        setSize(400, 300);
        setLayout(null);

        JLabel headerLabel = new JLabel("Enter marks obtained in each subject (out of 100):");
        headerLabel.setBounds(20, 10, 300, 20);
        add(headerLabel);

        int numOfSubjects = 5;
        subjectLabels = new JLabel[numOfSubjects];
        subjectTextFields = new JTextField[numOfSubjects];
        for (int i = 0; i < numOfSubjects; i++) {
            subjectLabels[i] = new JLabel("Subject " + (i + 1) + ":");
            subjectLabels[i].setBounds(20, 40 + i * 30, 60, 20);
            add(subjectLabels[i]);

            subjectTextFields[i] = new JTextField();
            subjectTextFields[i].setBounds(90, 40 + i * 30, 50, 20);
            add(subjectTextFields[i]);
        }

        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(150, 180, 100, 30);
        calculateButton.addActionListener(this);
        add(calculateButton);

        totalMarksLabel = new JLabel();
        totalMarksLabel.setBounds(20, 220, 200, 20);
        add(totalMarksLabel);

        averagePercentageLabel = new JLabel();
        averagePercentageLabel.setBounds(20, 240, 200, 20);
        add(averagePercentageLabel);

        gradeLabel = new JLabel();
        gradeLabel.setBounds(20, 260, 200, 20);
        add(gradeLabel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            int totalMarks = 0;
            int numOfSubjects = subjectTextFields.length;
            for (int i = 0; i < numOfSubjects; i++) {
                try {
                    int marks = Integer.parseInt(subjectTextFields[i].getText());
                    totalMarks += marks;
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Please enter valid marks for all subjects.");
                    return;
                }
            }

            double averagePercentage = (double) totalMarks / numOfSubjects;

            char grade;
            if (averagePercentage >= 90) {
                grade = 'A';
            } else if (averagePercentage >= 80) {
                grade = 'B';
            } else if (averagePercentage >= 70) {
                grade = 'C';
            } else if (averagePercentage >= 60) {
                grade = 'D';
            } else if (averagePercentage >= 50) {
                grade = 'E';
            } else {
                grade = 'F';
            }

            totalMarksLabel.setText("Total Marks: " + totalMarks);
            averagePercentageLabel.setText("Average Percentage: " + String.format("%.2f%%", averagePercentage));
            gradeLabel.setText("Grade: " + grade);
        }
    }

    public static void main(String[] args) {
        new GradeCalculatorGUI();
    }
}
