// TASK 2  STUDENT GRADE CALCULATOR

//Input: Take marks obtained (out of 100) in each subject.
// 1.Calculate Total Marks: Sum up the marks obtained in all subjects.
// 2.Calculate Average Percentage: Divide the total marks by the total number of subjects to get the
// average percentage.
// 3.Grade Calculation: Assign grades based on the average percentage achieved.
// 4.Display Results: Show the total marks, average percentage, and the corresponding grade to the user

import java.util.Scanner;

public class GradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input: Take marks obtained (out of 100) in each subject.
        System.out.print("Enter the number of subjects: ");
        int numOfSubjects = scanner.nextInt();

        int totalMarks = 0;
        for (int i = 1; i <= numOfSubjects; i++) {
            System.out.print("Enter marks obtained in subject " + i + ": ");
            int marks = scanner.nextInt();
            totalMarks += marks;
        }

        // Calculate Total Marks: Sum up the marks obtained in all subjects.
        // Calculate Average Percentage: Divide the total marks by the total number of subjects to get the average percentage.
        double averagePercentage = (double) totalMarks / numOfSubjects;

        // Grade Calculation: Assign grades based on the average percentage achieved.
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

        // Display Results: Show the total marks, average percentage, and the corresponding grade to the user
        System.out.println("\nTotal Marks: " + totalMarks);
        System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);
        System.out.println("Grade: " + grade);

        scanner.close();
    }
}
