import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class StudentGradeTrackerGUI {
    ArrayList<String> names = new ArrayList<>();
    ArrayList<Integer> marks = new ArrayList<>();

    JFrame frame;
    JTextField nameField, marksField;
    JTextArea outputArea;

    public StudentGradeTrackerGUI() {
        frame = new JFrame("Student Grade Tracker");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // Inputs
        frame.add(new JLabel("Student Name:"));
        nameField = new JTextField(15);
        frame.add(nameField);

        frame.add(new JLabel("Marks:"));
        marksField = new JTextField(5);
        frame.add(marksField);

        // Buttons
        JButton addButton = new JButton("Add Student");
        JButton reportButton = new JButton("Show Report");

        frame.add(addButton);
        frame.add(reportButton);

        // Output area
        outputArea = new JTextArea(15, 30);
        outputArea.setEditable(false);
        frame.add(new JScrollPane(outputArea));

       addButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        try {
            String name = nameField.getText();
            int mark = Integer.parseInt(marksField.getText());

            names.add(name);
            marks.add(mark);

            nameField.setText("");
            marksField.setText("");

            outputArea.setText("Student added successfully!\n");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Please enter valid numeric marks!");
        }
    }
});

        // Show report action
        reportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (marks.size() == 0) return;

                int total = 0;
                int highest = marks.get(0);
                int lowest = marks.get(0);

                for (int mark : marks) {
                    total += mark;
                    if (mark > highest) highest = mark;
                    if (mark < lowest) lowest = mark;
                }

                double avg = (double) total / marks.size();

                StringBuilder report = new StringBuilder();
                report.append("--- Student Report ---\n");

                for (int i = 0; i < names.size(); i++) {
                    report.append(names.get(i)).append(" : ").append(marks.get(i)).append("\n");
                }

                report.append("\nAverage: ").append(avg);
                report.append("\nHighest: ").append(highest);
                report.append("\nLowest: ").append(lowest);

                outputArea.setText(report.toString());
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new StudentGradeTrackerGUI();
    }
}
