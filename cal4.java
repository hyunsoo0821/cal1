package exam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class cal4 extends JFrame {
    JTextField t1;
    String operator = "";
    double num1 = 0;

    cal4() {
        setTitle("계산기");
        setLayout(new BorderLayout(10, 10));
        showNorth();
        showCenter();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(250, 300);
        setVisible(true);

        // 키보드 이벤트 추가
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }
        });
    }

    void showNorth() {
        JPanel p1 = new JPanel();
        t1 = new JTextField(10);
        p1.add(t1);
        this.add(p1, BorderLayout.NORTH);
    }

    void showCenter() {
        JPanel panel = new JPanel();
        JPanel p = new JPanel(new GridLayout(4, 4, 10, 10));
        JPanel p2 = new JPanel();
        JButton on = new JButton("on");
        JButton off = new JButton("off");
        p2.add(on);
        p2.add(off);

        String[] buttonLabels = {"7", "8", "9", "+",
                "4", "5", "6", "-",
                "1", "2", "3", "X",
                "0", ".", "=", "%"};
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(new ButtonClickListener());
            p.add(button);
        }

        this.pack();
        panel.add(p2);
        panel.add(p);
        this.add(panel);
    }

    void handleKeyPress(KeyEvent e) {
        char keyChar = e.getKeyChar();
        switch (keyChar) {
            case '0': case '1': case '2': case '3':
            case '4': case '5': case '6': case '7':
            case '8': case '9': case '.':
                t1.setText(t1.getText() + keyChar);
                break;
            case '+': case '-': case 'X': case '%':
                operator = String.valueOf(keyChar);
                num1 = Double.parseDouble(t1.getText());
                t1.setText("");
                break;
            case '=':
                calculateResult();
                break;
            case KeyEvent.VK_BACK_SPACE:
                String text = t1.getText();
                if (!text.isEmpty()) {
                    t1.setText(text.substring(0, text.length() - 1));
                }
                break;
        }
    }

    class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            String text = source.getText();

            if (text.equals("=")) {
                calculateResult();
            } else if ("+-X%".contains(text)) {
                operator = text.equals("X") ? "*" : text;
                num1 = Double.parseDouble(t1.getText());
                t1.setText("");
            } else {
                t1.setText(t1.getText() + text);
            }
        }
    }

    void calculateResult() {
        double num2 = Double.parseDouble(t1.getText());
        double result = 0;

        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "X":
            case "*":
                result = num1 * num2;
                break;
            case "%":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    t1.setText("0으로 나눌 수 없습니다.");
                    return;
                }
                break;
        }

        t1.setText(String.valueOf(result));
        operator = "";  // Reset operator for next calculation
    }

    public static void main(String[] args) {
        new cal4();
    }
}
