import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCalculator {

    private JFrame frame;
    private JTextField textField;
    private StringBuilder input = new StringBuilder();
    private double firstNumber;
    private String operator;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SimpleCalculator::new);
    }

    public SimpleCalculator() {
        frame = new JFrame("Simple Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 500);
        frame.setLayout(new BorderLayout());

        textField = new JTextField();
        frame.add(textField, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 5));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.addActionListener(new ButtonClickListener());
            panel.add(button);
        }

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            String command = source.getText();

            switch (command) {
                case "=":
                    calculateResult();
                    break;
                case "C":
                    input.setLength(0);
                    textField.setText("");
                    operator = null;
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                    operator = command;
                    firstNumber = Double.parseDouble(input.toString());
                    input.setLength(0);
                    break;
                default:
                    input.append(command);
                    textField.setText(input.toString());
                    break;
            }
        }

        private void calculateResult() {
            double secondNumber = Double.parseDouble(input.toString());
            double result = 0;

            switch (operator) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "*":
                    result = firstNumber * secondNumber;
                    break;
                case "/":
                    if (secondNumber != 0) {
                        result = firstNumber / secondNumber;
                    } else {
                        JOptionPane.showMessageDialog(frame, "Error: Division by zero");
                        return;
                    }
                    break;
            }

            textField.setText(String.valueOf(result));
            input.setLength(0);
            operator = null;
        }
    }
}
