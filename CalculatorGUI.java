import java.awt.*;
import java.awt.event.*;

public class CalculatorGUI extends Frame implements ActionListener {
    private TextField display;
    private String operand1 = "";
    private String operand2 = "";
    private char operator = ' ';
    
    public CalculatorGUI() {
        setLayout(new BorderLayout());
        
        display = new TextField();
        display.setEditable(false);
        display.setPreferredSize(new Dimension(300, 50)); // Set preferred size for display
        add(display, BorderLayout.NORTH);
        
        Panel buttonPanel = new Panel(new GridLayout(5, 4)); // Adjust grid layout to accommodate more buttons
        
        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };
        
        for (String label : buttonLabels) {
            Button button = new Button(label);
            button.addActionListener(this);
            buttonPanel.add(button);
        }
        
        // Adjust size of buttons
        adjustButtonSizes(buttonPanel.getComponents());
        
        add(buttonPanel, BorderLayout.CENTER);
        
        setTitle("Calculator");
        setSize(400, 400); // Adjust frame size
        setVisible(true);
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }
    
    private void adjustButtonSizes(Component[] components) {
        for (Component component : components) {
            if (component instanceof Button) {
                Button button = (Button) component;
                button.setFont(new Font("Arial", Font.PLAIN, 20)); // Set font size for buttons
                button.setPreferredSize(new Dimension(75, 75)); // Set preferred size for buttons
            }
        }
    }
    
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        if (Character.isDigit(command.charAt(0))) {
            if (operator == ' ') {
                operand1 += command;
                display.setText(operand1);
            } else {
                operand2 += command;
                display.setText(operand2);
            }
        } else if (command.equals("C")) {
            operand1 = "";
            operand2 = "";
            operator = ' ';
            display.setText("");
        } else if (command.equals("=")) {
            if (!operand1.equals("") && !operand2.equals("")) {
                double result = calculate();
                display.setText(Double.toString(result));
                operand1 = Double.toString(result);
                operand2 = "";
                operator = ' ';
            }
        } else {
            operator = command.charAt(0);
        }
    }
    
    private double calculate() {
        double num1 = Double.parseDouble(operand1);
        double num2 = Double.parseDouble(operand2);
        double result = 0;
        
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    display.setText("Error: Cannot divide by zero!");
                }
                break;
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        new CalculatorGUI();
    }
}
