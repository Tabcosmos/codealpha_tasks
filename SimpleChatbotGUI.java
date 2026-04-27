import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleChatbotGUI {

    JFrame frame;
    JTextArea chatArea;
    JTextField inputField;
    JButton sendButton;

    public SimpleChatbotGUI() {
        // Frame setup
        frame = new JFrame("AI Chatbot");
        frame.setSize(450, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Chat display area
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(chatArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Input panel (bottom)
        JPanel inputPanel = new JPanel(new BorderLayout());

        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 14));

        sendButton = new JButton("Send");

        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        frame.add(inputPanel, BorderLayout.SOUTH);

        // Action: Enter key
        inputField.addActionListener(e -> processInput());

        // Action: Button click
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                processInput();
            }
        });

        frame.setVisible(true);
    }

    // Handle user input
    public void processInput() {
        String userText = inputField.getText().trim();

        if (userText.isEmpty()) return;

        chatArea.append("You: " + userText + "\n");

        String botResponse = getResponse(userText);
        chatArea.append("Bot: " + botResponse + "\n\n");

        inputField.setText("");
    }

    // Chatbot logic (basic NLP)
    public String getResponse(String input) {
        input = input.toLowerCase();

        if (input.contains("hello") || input.contains("hi")) {
            return "Hello! How can I help you?";
        } 
        else if (input.contains("name")) {
            return "I am a Java-based chatbot.";
        } 
        else if (input.contains("marks") || input.contains("grade")) {
            return "You can track student grades using our system.";
        } 
        else if (input.contains("help")) {
            return "Try asking about students, grades, or general queries.";
        } 
        else if (input.contains("bye")) {
            return "Goodbye! Have a great day.";
        } 
        else {
            return "Sorry, I didn't understand that.";
        }
    }

    public static void main(String[] args) {
        new SimpleChatbotGUI();
    }
}