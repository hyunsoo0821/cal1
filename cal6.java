package exam;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class cal6 extends JFrame {
    JTextField t1;
    String operator = "";
    double num1 = 0;

    cal6() {
        setTitle("계산기");
        setLayout(new BorderLayout(10, 10));
        makeMenu();
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

    void makeMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        JMenuItem newFileItem = new JMenuItem("새파일", KeyEvent.VK_N);
        newFileItem.addActionListener(e -> System.out.println("새파일"));

        JMenuItem saveFileItem = new JMenuItem("파일 저장하기");
        saveFileItem.addActionListener(e -> saveToFile());

        JMenuItem exitItem = new JMenuItem("종료");
        exitItem.addActionListener(e -> {
            int confirmed = JOptionPane.showConfirmDialog(
                    this,
                    "정말 종료하시겠습니까?",
                    "종료 확인",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirmed == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        fileMenu.add(newFileItem);
        fileMenu.add(saveFileItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }

    void saveToFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("파일 저장");

        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
                writer.write(t1.getText());
                JOptionPane.showMessageDialog(this, "파일이 저장되었습니다: " + fileToSave.getAbsolutePath());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "파일 저장 중 오류가 발생했습니다: " + ex.getMessage());
            }
        }
    }

    void showNorth() {
        JPanel p1 = new JPanel();
        t1 = new JTextField(10);
        p1.add(t1);
        this.add(p1, BorderLayout.NORTH);
    }

    void showCenter() {
        JPanel panel = new JPanel();
        JPanel buttonPanel = new JPanel(new GridLayout(4, 4, 10, 10));

        String[] buttonLabels = {
                "7", "8", "9", "+",
                "4", "5", "6", "-",
                "1", "2", "3", "*",
                "0", ".", "=", "%"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }

        panel.add(buttonPanel);
        this.add(panel, BorderLayout.CENTER);
    }

    void handleKeyPress(KeyEvent e) {
        char keyChar = e.getKeyChar();
        switch (keyChar) {
            case '0': case '1': case '2': case '3':
            case '4': case '5': case '6': case '7':
            case '8': case '9': case '.':
                t1.setText(t1.getText() + keyChar);
                break;
            case '+': case '-': case '*': case '%':
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
            } else if ("+-*%".contains(text)) {
                operator = text;
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
        new cal6();
    }
}
