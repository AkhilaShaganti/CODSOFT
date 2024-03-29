import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private int enrolled;

    public Course(String code, String title, String description, int capacity) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.enrolled = 0;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getEnrolled() {
        return enrolled;
    }

    public void enrollStudent() {
        enrolled++;
    }

    public void dropStudent() {
        enrolled--;
    }
}

class Student {
    private int id;
    private String name;
    private List<String> registeredCourses;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(String courseCode) {
        registeredCourses.add(courseCode);
    }

    public void dropCourse(String courseCode) {
        registeredCourses.remove(courseCode);
    }
}

public class CourseRegistrationSystem extends JFrame implements ActionListener {
    private List<Course> courses;
    private List<Student> students;
    private JComboBox<String> courseComboBox;
    private JTextArea courseDetailsTextArea, studentDetailsTextArea;
    private JButton registerButton, dropButton;

    public CourseRegistrationSystem() {
        courses = new ArrayList<>();
        students = new ArrayList<>();

        setTitle("Course Registration System");
        setSize(600, 400);
        setLayout(new GridLayout(2, 2));

        JPanel coursePanel = new JPanel();
        coursePanel.setLayout(new BorderLayout());
        JLabel courseLabel = new JLabel("Available Courses:");
        coursePanel.add(courseLabel, BorderLayout.NORTH);
        courseComboBox = new JComboBox<>();
        coursePanel.add(courseComboBox, BorderLayout.CENTER);
        courseDetailsTextArea = new JTextArea();
        courseDetailsTextArea.setEditable(false);
        coursePanel.add(courseDetailsTextArea, BorderLayout.SOUTH);
        add(coursePanel);

        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(2, 1));
        registerButton = new JButton("Register");
        registerButton.addActionListener(this);
        actionPanel.add(registerButton);
        dropButton = new JButton("Drop");
        dropButton.addActionListener(this);
        actionPanel.add(dropButton);
        add(actionPanel);

        JPanel studentPanel = new JPanel();
        studentPanel.setLayout(new BorderLayout());
        JLabel studentLabel = new JLabel("Student Details:");
        studentPanel.add(studentLabel, BorderLayout.NORTH);
        studentDetailsTextArea = new JTextArea();
        studentDetailsTextArea.setEditable(false);
        studentPanel.add(studentDetailsTextArea, BorderLayout.CENTER);
        add(studentPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            String courseCode = (String) courseComboBox.getSelectedItem();
            Course selectedCourse = null;
            for (Course course : courses) {
                if (course.getCode().equals(courseCode)) {
                    selectedCourse = course;
                    break;
                }
            }
            if (selectedCourse != null && selectedCourse.getEnrolled() < selectedCourse.getCapacity()) {
                Student selectedStudent = students.get(0); // For simplicity, assuming only one student registered
                selectedStudent.registerCourse(courseCode);
                selectedCourse.enrollStudent();
                displayStudentDetails(selectedStudent);
                displayCourseDetails(selectedCourse);
                JOptionPane.showMessageDialog(this, "Registration successful!");
            } else {
                JOptionPane.showMessageDialog(this, "Course is full or invalid!");
            }
        } else if (e.getSource() == dropButton) {
            String courseCode = (String) courseComboBox.getSelectedItem();
            Course selectedCourse = null;
            for (Course course : courses) {
                if (course.getCode().equals(courseCode)) {
                    selectedCourse = course;
                    break;
                }
            }
            if (selectedCourse != null) {
                Student selectedStudent = students.get(0); // For simplicity, assuming only one student registered
                selectedStudent.dropCourse(courseCode);
                selectedCourse.dropStudent();
                displayStudentDetails(selectedStudent);
                displayCourseDetails(selectedCourse);
                JOptionPane.showMessageDialog(this, "Course dropped successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid course!");
            }
        }
    }

    public void addCourse(Course course) {
        courses.add(course);
        courseComboBox.addItem(course.getCode());
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    private void displayCourseDetails(Course course) {
        courseDetailsTextArea.setText("Course Code: " + course.getCode() + "\n"
                + "Title: " + course.getTitle() + "\n"
                + "Description: " + course.getDescription() + "\n"
                + "Capacity: " + course.getCapacity() + "\n"
                + "Enrolled: " + course.getEnrolled());
    }

    private void displayStudentDetails(Student student) {
        studentDetailsTextArea.setText("Student ID: " + student.getId() + "\n"
                + "Name: " + student.getName() + "\n"
                + "Registered Courses: " + student.getRegisteredCourses());
    }

    public static void main(String[] args) {
        CourseRegistrationSystem system = new CourseRegistrationSystem();

        // Adding sample courses
        Course course1 = new Course("CSCI101", "Introduction to Computer Science", "Fundamental concepts of programming", 50);
        Course course2 = new Course("MATH201", "Calculus I", "Basic calculus concepts", 40);
        system.addCourse(course1);
        system.addCourse(course2);

        // Adding sample student
        Student student = new Student(12345, "John Doe");
        system.addStudent(student);

        // Displaying initial course and student details
        system.displayCourseDetails(course1);
        system.displayStudentDetails(student);
    }
}
