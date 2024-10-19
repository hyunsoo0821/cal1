package exam;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class cal2 extends JFrame {
    private JTextField t1;

    cal2() {
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

        JButton b1 = new JButton("7");
        JButton b2 = new JButton("8");
        JButton b3 = new JButton("9");
        JButton b4 = new JButton("+");
        JButton b5 = new JButton("4");
        JButton b6 = new JButton("5");
        JButton b7 = new JButton("6");
        JButton b8 = new JButton("-");
        JButton b9 = new JButton("1");
        JButton b10 = new JButton("2");
        JButton b11 = new JButton("3");
        JButton b12 = new JButton("X");
        JButton b13 = new JButton("0");
        JButton b14 = new JButton(".");
        JButton b15 = new JButton("=");
        JButton b16 = new JButton("%");
        p.add(b1);
        p.add(b2);
        p.add(b3);
        p.add(b4);
        p.add(b5);
        p.add(b6);
        p.add(b7);
        p.add(b8);
        p.add(b9);
        p.add(b10);
        p.add(b11);
        p.add(b12);
        p.add(b13);
        p.add(b14);
        p.add(b15);
        p.add(b16);
        this.pack();
        panel.add(p2);
        panel.add(p);
        this.add(panel);
    }
//키보드 이벤트 추가
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
            case KeyEvent.VK_BACK_SPACE:
                String text = t1.getText();
                if (!text.isEmpty()) {
                    t1.setText(text.substring(0, text.length() - 1));
                }
                break;
        }
    }
//버튼 클릭 리스너
    class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            t1.setText(t1.getText() + source.getText());
        }
    }

    public static void main(String[] args) {
        new cal2();
    }
}
