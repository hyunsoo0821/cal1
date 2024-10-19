package exam;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class cal1 extends JFrame {
    cal1() {
        this.setTitle("계산기");
        this.setLayout(new BorderLayout(10, 10));
        this.showNorth();
        this.showCenter();
        this.setDefaultCloseOperation(3);
        this.setSize(250, 300);
        this.setVisible(true);
    }

    void showNorth() {
        JPanel p1 = new JPanel();
        JPanel panel = new JPanel(new GridLayout(1, 0));
        JTextField t1 = new JTextField(10);
        p1.add(t1);
        panel.add(p1);
        this.add(panel, "North");
    }

    void showCenter() {
        JPanel panel = new JPanel();
        JPanel p = new JPanel(new GridLayout(4, 4, 10, 10));
        JPanel p2 = new JPanel();
        p.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
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

    public static void main(String[] args) {

        new cal1();
    }
}
