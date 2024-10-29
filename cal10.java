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
/**
 * 계산기는 책 646쪽 예제를 기반하여 만들었습니다.
 * @see JFrame
 * @see JPanel
 * @see JButton
 * @see JTextField
 *   @author cho hyun soo (hyunsoo821cho@gmail.com)
 *   @version 24.2.3
 *  @since 24.10.8
 *
 *   @created 2024-10-8
 *   @lastModified 2024-10-29
 *
 *   @changelog
 *   <ul>
 *    <li>2024-10-8: 패널3개를 나누고 버튼을 16개를 추가한 책646쪽 계산기 예제를 참고하고  (cho hyun soo)</li>
 *    <li>2024-10-19:  버튼클릭리스너를 추가했습니다.(cho hyun soo)</li>
 *   <li>2024-10-23: 연산기능 추가, on,off를 파일종료하기로 바꾸었습니다,파일저장하기 메뉴 이벤트 추가, 계산기 디자인 구현  (cho hyun soo)</li>
 *   <li>2024-09-29: 텍스트필드에 영한타금지 (cho hyun soo)</li>
 *  </ul>
 */

public class cal10 extends JFrame {
    JTextField t1;
    String operator = "";
    double num1 = 0;
    /**
     * 계산기 프레임에 패널을 추가했습니다
     *
     * @see #makeMenu()
     * @see #showNorth()
     * @see #showCenter()
     * @see #showSouth()
     *   @author cho hyun soo (hyunsoo821cho@gmail.com)
     *   @version 24.2.3
     *  @since 24.10.8
     *
     *   @created 2024-10-8
     *   @lastModified 2024-10-29
     *
     *   @changelog
     *   <ul>
     *    <li>2024-10-8: 패널3개를 나누고 버튼을 16개를 추가한 책646쪽 계산기 예제를 참고하고  (cho hyun soo)</li>
     *    <li>2024-10-19:  버튼클릭리스너를 추가했습니다.(cho hyun soo)</li>
     *   <li>2024-10-23: 연산기능 추가, on,off를 파일종료하기로 바꾸었습니다,파일저장하기 메뉴 이벤트 추가, 계산기 디자인 구현  (cho hyun soo)</li>
     *   <li>2024-09-29: 텍스트필드에 영한타금지 (cho hyun soo)</li>
     *  </ul>
     */

    cal10() {
        setTitle("계산기");
        setLayout(new BorderLayout(10, 10));
        makeMenu();
        showNorth();
        showCenter();
        showSouth();

        getContentPane().setBackground(Color.WHITE);//배경색을 하얀색

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//아이콘 설정
        setSize(250, 300);
        setVisible(true);

        try {
            BufferedImage icon = ImageIO.read(new File("C:/Users/ASUS/OneDrive - 청주대학교/바탕 화면/Note_icon.jpg"));
            setIconImage(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
//키보드 이벤트 추가
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }
        });
    }
    /**
     * 패널안에 파일옵션과 메뉴바를 만들었습니다.
     *
     * @see JMenuBar
     * @see JMenu
     * @see JMenuItem
     *   @author cho hyun soo (hyunsoo821cho@gmail.com)
     *   @version 24.2.3
     *  @since 24.10.8
     *
     *   @created 2024-10-8
     *   @lastModified 2024-10-29
     *
     *   @changelog
     *   <ul>
     *    <li>2024-10-8: 패널3개를 나누고 버튼을 16개를 추가한 책646쪽 계산기 예제를 참고하고  (cho hyun soo)</li>
     *    <li>2024-10-19:  버튼클릭리스너를 추가했습니다.(cho hyun soo)</li>
     *   <li>2024-10-23: 연산기능 추가, on,off를 파일종료하기로 바꾸었습니다,파일저장하기 메뉴 이벤트 추가, 계산기 디자인 구현  (cho hyun soo)</li>
     *   <li>2024-09-29: 텍스트필드에 영한타금지 (cho hyun soo)</li>
     *  </ul>
     */
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
    /**
     * 사용자가 선택한 파일에 텍스트 필드의 현재 텍스트를 저장합니다.
     *
     * @see JFileChooser
     * @see BufferedWriter
     *   @author cho hyun soo (hyunsoo821cho@gmail.com)
     *   @version 24.2.3
     *  @since 24.10.8
     *
     *   @created 2024-10-8
     *   @lastModified 2024-10-29
     *
     *   @changelog
     *   <ul>
     *    <li>2024-10-8: 패널3개를 나누고 버튼을 16개를 추가한 책646쪽 계산기 예제를 참고하고  (cho hyun soo)</li>
     *    <li>2024-10-19:  버튼클릭리스너를 추가했습니다.(cho hyun soo)</li>
     *   <li>2024-10-23: 연산기능 추가, on,off를 파일종료하기로 바꾸었습니다,파일저장하기 메뉴 이벤트 추가, 계산기 디자인 구현  (cho hyun soo)</li>
     *   <li>2024-09-29: 텍스트필드에 영한타금지 (cho hyun soo)</li>
     *  </ul>
     */
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
    /**
     * 계산기 상단에 텍스트 필드를 표시합니다.
     *
     * @see JTextField
     * @see JPanel
     *   @author cho hyun soo (hyunsoo821cho@gmail.com)
     *   @version 24.2.3
     *  @since 24.10.8
     *
     *   @created 2024-10-8
     *   @lastModified 2024-10-29
     *
     *   @changelog
     *   <ul>
     *    <li>2024-10-8: 패널3개를 나누고 버튼을 16개를 추가한 책646쪽 계산기 예제를 참고하고  (cho hyun soo)</li>
     *    <li>2024-10-19:  버튼클릭리스너를 추가했습니다.(cho hyun soo)</li>
     *   <li>2024-10-23: 연산기능 추가, on,off를 파일종료하기로 바꾸었습니다,파일저장하기 메뉴 이벤트 추가, 계산기 디자인 구현  (cho hyun soo)</li>
     *   <li>2024-09-29: 텍스트필드에 영한타금지 (cho hyun soo)</li>
     *  </ul>
     */

    void showNorth() {
        JPanel p1 = new JPanel();
        p1.setBackground(Color.WHITE);
        t1 = new JTextField(10);

        t1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isLetter(c) || (c >= 0xAC00 && c <= 0xD7A3)) {
                    e.consume();
                }
            }
        });

        p1.add(t1);
        this.add(p1, BorderLayout.NORTH);
    }
    /**
     * 지우기 및 백스페이스 버튼을 포함하는 중앙 패널을 생성합니다.
     *
     * @see JButton
     * @see JPanel
     *   @author cho hyun soo (hyunsoo821cho@gmail.com)
     *   @version 24.2.3
     *  @since 24.10.8
     *
     *   @created 2024-10-8
     *   @lastModified 2024-10-29
     *
     *   @changelog
     *   <ul>
     *    <li>2024-10-8: 패널3개를 나누고 버튼을 16개를 추가한 책646쪽 계산기 예제를 참고하고  (cho hyun soo)</li>
     *    <li>2024-10-19:  버튼클릭리스너를 추가했습니다.(cho hyun soo)</li>
     *   <li>2024-10-23: 연산기능 추가, on,off를 파일종료하기로 바꾸었습니다,파일저장하기 메뉴 이벤트 추가, 계산기 디자인 구현  (cho hyun soo)</li>
     *   <li>2024-09-29: 텍스트필드에 영한타금지 (cho hyun soo)</li>
     *  </ul>
     */
    void showCenter() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new FlowLayout());

        Dimension buttonSize = new Dimension(100, 30);

        JButton clearButton = new JButton("C");
        clearButton.setPreferredSize(buttonSize);
        clearButton.setBackground(Color.GRAY);
        clearButton.addActionListener(e -> t1.setText(""));
        panel.add(clearButton);

        JButton backspaceButton = new JButton("Backspace");
        backspaceButton.setPreferredSize(buttonSize);
        backspaceButton.setBackground(Color.GRAY);
        backspaceButton.addActionListener(e -> {
            String text = t1.getText();
            if (!text.isEmpty()) {
                t1.setText(text.substring(0, text.length() - 1));
            }
        });
        panel.add(backspaceButton);

        add(panel, BorderLayout.CENTER);
    }
    /**
     * 숫자 및 연산자 버튼이 포함된 하단 패널을 생성합니다.
     *
     * @see JButton
     * @see JPanel
     *   @author cho hyun soo (hyunsoo821cho@gmail.com)
     *   @version 24.2.3
     *  @since 24.10.8
     *
     *   @created 2024-10-8
     *   @lastModified 2024-10-29
     *
     *   @changelog
     *   <ul>
     *    <li>2024-10-8: 패널3개를 나누고 버튼을 16개를 추가한 책646쪽 계산기 예제를 참고하고  (cho hyun soo)</li>
     *    <li>2024-10-19:  버튼클릭리스너를 추가했습니다.(cho hyun soo)</li>
     *   <li>2024-10-23: 연산기능 추가, on,off를 파일종료하기로 바꾸었습니다,파일저장하기 메뉴 이벤트 추가, 계산기 디자인 구현  (cho hyun soo)</li>
     *   <li>2024-09-29: 텍스트필드에 영한타금지 (cho hyun soo)</li>
     *  </ul>
     */
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
        this.add(panel, BorderLayout.SOUTH);
    }
    /**
     * 키보드 입력을 위한 키 누름을 처리합니다.
     *
     * @param e the key event
     *
     * @see KeyEvent
     *   @author cho hyun soo (hyunsoo821cho@gmail.com)
     *   @version 24.2.3
     *  @since 24.10.8
     *
     *   @created 2024-10-8
     *   @lastModified 2024-10-29
     *
     *   @changelog
     *   <ul>
     *    <li>2024-10-8: 패널3개를 나누고 버튼을 16개를 추가한 책646쪽 계산기 예제를 참고하고  (cho hyun soo)</li>
     *    <li>2024-10-19:  버튼클릭리스너를 추가했습니다.(cho hyun soo)</li>
     *   <li>2024-10-23: 연산기능 추가, on,off를 파일종료하기로 바꾸었습니다,파일저장하기 메뉴 이벤트 추가, 계산기 디자인 구현  (cho hyun soo)</li>
     *   <li>2024-09-29: 텍스트필드에 영한타금지 (cho hyun soo)</li>
     *  </ul>
     */
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
    /**
     * 숫자 및 연산자 버튼에 대한 액션 리스너 추가
     *
     * @see ActionListener
     *   @author cho hyun soo (hyunsoo821cho@gmail.com)
     *   @version 24.2.3
     *  @since 24.10.8
     *
     *   @created 2024-10-8
     *   @lastModified 2024-10-29
     *
     *   @changelog
     *   <ul>
     *    <li>2024-10-8: 패널3개를 나누고 버튼을 16개를 추가한 책646쪽 계산기 예제를 참고하고  (cho hyun soo)</li>
     *    <li>2024-10-19:  버튼클릭리스너를 추가했습니다.(cho hyun soo)</li>
     *   <li>2024-10-23: 연산기능 추가, on,off를 파일종료하기로 바꾸었습니다,파일저장하기 메뉴 이벤트 추가, 계산기 디자인 구현  (cho hyun soo)</li>
     *   <li>2024-09-29: 텍스트필드에 영한타금지 (cho hyun soo)</li>
     *  </ul>
     */
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
    /**
     * 현재 연산자와 피연산자를 기반으로 결과를 계산합니다.
     *
     * @see #operator
     * @see #num1
     * @see #t1
     *   @author cho hyun soo (hyunsoo821cho@gmail.com)
     *   @version 24.2.3
     *  @since 24.10.8
     *
     *   @created 2024-10-8
     *   @lastModified 2024-10-29
     *
     *   @changelog
     *   <ul>
     *    <li>2024-10-8: 패널3개를 나누고 버튼을 16개를 추가한 책646쪽 계산기 예제를 참고하고  (cho hyun soo)</li>
     *    <li>2024-10-19:  버튼클릭리스너를 추가했습니다.(cho hyun soo)</li>
     *   <li>2024-10-23: 연산기능 추가, on,off를 파일종료하기로 바꾸었습니다,파일저장하기 메뉴 이벤트 추가, 계산기 디자인 구현  (cho hyun soo)</li>
     *   <li>2024-09-29: 텍스트필드에 영한타금지 (cho hyun soo)</li>
     *  </ul>
     */
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
        new cal10();
    }
}
