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
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class cal9 extends JFrame {
    JTextField t1;
    String operator = "";
    double num1 = 0;

    cal9() {
        setTitle("계산기");
        setLayout(new BorderLayout(10, 10));
        makeMenu();
        showNorth();
        showCenter();
        showSouth();

        // 배경색을 흰색으로 설정
        getContentPane().setBackground(Color.WHITE);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(250, 300);
        setVisible(true);

        // 아이콘 설정
        try {
            BufferedImage icon = ImageIO.read(new File("C:/Users/ASUS/OneDrive - 청주대학교/바탕 화면/Note_icon.jpg"));
            setIconImage(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        menuBar.setBackground(Color.WHITE);

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.setBackground(Color.WHITE);

        JMenuItem newFileItem = new JMenuItem("새파일", KeyEvent.VK_N);
        newFileItem.addActionListener(e -> System.out.println("새파일"));
        newFileItem.setBackground(Color.WHITE);

        JMenuItem saveFileItem = new JMenuItem("파일 저장하기");
        saveFileItem.addActionListener(e -> saveToFile());
        saveFileItem.setBackground(Color.WHITE);

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
        exitItem.setBackground(Color.WHITE);

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
        p1.setBackground(Color.WHITE);
        t1 = new JTextField(10);

        // KeyListener 추가하여 한글과 영어 입력 제한
        t1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isLetter(c) || (c >= 0xAC00 && c <= 0xD7A3)) {
                    // 한글 또는 영어일 경우
                    e.consume(); // 입력 차단
                } else {
                }
            }
        });

        p1.add(t1);
        this.add(p1, BorderLayout.NORTH);
    }


    void showCenter() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new FlowLayout());

        Dimension buttonSize = new Dimension(50, 30);

        JButton clearButton = new JButton("C");
        clearButton.setPreferredSize(buttonSize);
        clearButton.setBackground(Color.GRAY);
        clearButton.addActionListener(e -> t1.setText(""));
        panel.add(clearButton);

        this.add(panel, BorderLayout.CENTER);
    }

    void showSouth() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 4, 10, 10));
        buttonPanel.setBackground(Color.WHITE);

        String[] buttonLabels = {
                "7", "8", "9", "+",
                "4", "5", "6", "-",
                "1", "2", "3", "*",
                "0", ".", "=", "%"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setBackground(Color.GRAY);
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }

        panel.add(buttonPanel);
        this.add(panel, BorderLayout.SOUTH);  // Add buttons to the south
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
        operator = "";
    }

    public static void main(String[] args) {
        new cal9();
    }
}

