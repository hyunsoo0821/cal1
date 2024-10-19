package exam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class cal3 extends JFrame {
    JTextField t1;

    cal3() {
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
        //처음 버튼리스너를 추가하면 되겠지 라고 생각했는데 런을 해보니 안됬었다
        //알고보니 리스너를 연결하는 코드인 액션리스너를 추가를 안했고 중앙 패널에 추가를 해야됬었다.
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

   // 패널에 버튼 추가 를 case로 묶었다.
    void handleKeyPress(KeyEvent e) {
        char keyChar = e.getKeyChar();
        switch (keyChar) {
            case '0': case '1': case '2': case '3':
            case '4': case '5': case '6': case '7':
            case '8': case '9': case '.':
            case '+': case '-': case 'X': case '%':
            case '=':
                t1.setText(t1.getText() + keyChar);
                break;
            // 키보드 이벤트 처리
            case KeyEvent.VK_BACK_SPACE:
                String text = t1.getText();
                if (!text.isEmpty()) {
                    t1.setText(text.substring(0, text.length() - 1));
                }
                break;
        }
    }

    // 버튼 클릭 리스너


    class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            t1.setText(t1.getText() + source.getText());
        }
    }

    public static void main(String[] args) {
        new cal3();
    }
}
