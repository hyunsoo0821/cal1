# cal1
![cal1 계산기](https://github.com/user-attachments/assets/7b9677b7-b991-4f2a-a8a8-00107a2be21f)
## 위 사진은 처음 책646쪽을 보고 계산기를 만들었습니다.
![계산기 설명 1](https://github.com/user-attachments/assets/1209f519-baa1-4f55-a0d4-cdf4facd7346)

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
**위사진 처럼 패널을 2개로 나누고 북쪽, 중앙으로 나누었습니다**
> ### 레이아웃 설정은 Frame 에는 BorderLayout 가로10,세로10
> > #### 북쪽 패널에는 GridLayout에는 가로줄 1줄, 세로 0 행으로 택스트 필드만 나오게 했고
> > > ##### 중앙패널에는 GridLayout에는 4개의 행, 4열, 수평10, 수직10입니다
> > > > ###### 맨위에 버튼 on,off 2개와 버튼이 16개 연산코드를 추가했습니다.

# cal2에는키보드 이벤트와 ActionListener를 추가했습니다
  ```
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
```
>### 0~9까지 숫자를 case에 넣었고 곱하기,나누기,빼기,더하기연산자도 case에 넣었습니다. 이걸가지고 택스트필드에 입력이 연속적으로 가능하게 구현했습니다
> >#### (Key press handling)->Keychar 변수에 눌린키의 문자를 저장하고 \b가 눌리면 현재 텍스트(t1)에서 마지막 문자를 제거하게 만들엇습니다.
~~~

            case KeyEvent.VK_BACK_SPACE:
                String text = t1.getText();
                if (!text.isEmpty()) {
                    t1.setText(text.substring(0, text.length() - 1));
                }
                break;
        }
~~~
>**백스페이스 키를 눌렀을 때,t1의 텍스트가 비어 있지 않다면,텍스트의 마지막 문자를 제거하게 헀습니다.**
> >**substring 메서드를 사용하여 현재 텍스트에서 마지막 문자를 제외한 부분을 설정합니다**
# cal3에는버튼클릭리스너를 추가했고 버튼을 누르면 텍스트필드에 텍스트가 추가되는 코드입니다.
    class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            t1.setText(t1.getText() + source.getText());
        }
    }

 **위에있는 버튼을 케이스에 넣어서 키보드로 버튼을 입력했을때 택스트필드에 입력이 되게 했습니다.**

# cal4에는 연산기능을 추가했습니다.

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
>**switch case문을 사용해 operator의 값에 넣어 계산되게 했습니다.**
> >**+, -, * 연산기능을 추가 했습니다.**
> > >**%(나누기연산)을 했을때, num2가 0일 경우에는 "0으로 나눌 수 없습니다."오류 메세지를 만들었습니다**
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
**"="버튼 클릭시 연산자 메소드 를 호출하고 연산자 버튼 클릭시 현재 입력된 숫자를 저장하게 했습니다.**
# cal5에는메뉴 이벤트를 추가했습니다.
 ![cal5메뉴이벤트 넣은거](https://github.com/user-attachments/assets/95dcee47-2015-4e58-b533-f1aca1a7f605)
![메뉴바 열기](https://github.com/user-attachments/assets/9dccb8ee-bc93-49ac-b54d-071fe942aacc)
![cal6 로컬파일 저장하기](https://github.com/user-attachments/assets/f1ee0801-9e03-4535-9778-266482b56638)
       
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.setBackground(Color.WHITE);

        JMenuItem newFileItem = new JMenuItem("새파일", KeyEvent.VK_N);
        newFileItem.addActionListener(e -> System.out.println("새파일"));
        newFileItem.setBackground(Color.WHITE);

        JMenuItem saveFileItem = new JMenuItem("파일 저장하기");
        saveFileItem.addActionListener(e -> saveToFile());
        saveFileItem.setBackground(Color.WHITE);
   **"새파일",'파일 저장하기", "종료"를 추가했습니다**
## cal6에는 파일 내컴퓨터 파일저장하기 기능을 추가했습니다. ### 여기에서 GUI애플리케이션 메뉴바를 설정하고 파일관련기능을 구현했습니다.
void saveToFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("파일 저장");

        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
  
   **저장할 파일의 경로를 선택하게 하고, 선택된 파일에 fileToSave 변수를 저장했습니다.**
  ```
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
                writer.write(t1.getText());
                JOptionPane.showMessageDialog(this, "파일이 저장되었습니다: " + fileToSave.getAbsolutePath());
            }
  ```
 **사용자가 선택한 파일을 BufferWritter를 사용하여 작성했습니다** 
~~~
           catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "파일 저장 중 오류가 발생했습니다: " + ex.getMessage());
            }
        }
    } 
 ~~~
**택스트필드의 내용을 파일에 쓰고, 파일저장 매세지를 보내게 했습니다, 오류가 뜨면 오류매세지가 뜨게 했습니다.**
~~~

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
~~~
**fileMenu.setMnemonic(70); 70은 F이 아스키코드로 alt+F눌러 메뉴접근 가능하게 했습니다, JMenuItem newFileItem = new JMenuItem("새파일", 78); 78은 N이고 파일저장하는 레이블입니다.**
```
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
```
**System.exit(0);를 종료 확인 상자를 표시하기 위해 레이블을 추가 했습니다.**
~~~
        });
        exitItem.setBackground(Color.WHITE);

        fileMenu.add(newFileItem);
        fileMenu.add(saveFileItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }
~~~
**fileMenu.add(newFileItem);, fileMenu.add(saveFileItem);, fileMenu.addSeparator();, fileMenu.add(exitItem);**: *위에서 생성한 메뉴 항목들을 파일 메뉴에 추가했습니다. 
이때 addSeparator() 메서드는 항목들 사이에 구분선을 추가하여 시각적으로 구분했습니다.*
# cal7는 이미지 업로드 코드입니다
![Note_icon](https://github.com/user-attachments/assets/455fde6a-b08a-40b6-9e23-bfe4c507ebd4)
```
 try {
            BufferedImage icon = ImageIO.read(new File("C:/Users/ASUS/OneDrive - 청주대학교/바탕 화면/Note_icon.jpg"));
            setIconImage(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
```
   **노트이미지 아이콘으로 변경, setIconImage(icon);을 통해 현재 프레임의 아이콘으로 설정하게 햇습니다.**
 ~~~
       
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }
        });
    }
 ~~~
**키 입력 이벤트를 감지하고 처리하는 기능을 추가하는 코드입니다 handleKeyPress 메서드에서 눌린 키에 따라 원하는 행동을 할수 있습니다.**

 ```
void showCenter() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new FlowLayout());
 ```
   **배경을 하얀색으로 바꿧다**
# cal8에는 C(지우개 버튼)을 추가했습니다
~~~
        Dimension buttonSize = new Dimension(50, 30);
        JButton clearButton = new JButton("C");
        clearButton.setPreferredSize(buttonSize);
        clearButton.setBackground(Color.GRAY);
        clearButton.addActionListener(e -> t1.setText(""));
        panel.add(clearButton);

        this.add(panel, BorderLayout.CENTER);
    }
 ~~~
**버튼을 회색으로 칠하고 C버튼을 만드는 과정**
>*택스트필드에있는 내용을 지울수 있습니다.*
> >setIconImage(icon);을 통해 현재 프레임의 아이콘으로 설정하게 햇습니다.
> > >예외 처리: IOException이 발생할 경우, catch 블록에서 예외를 잡아 e.printStackTrace();를 호출하여 콘솔에 에러 메시지를 출력합니다.
# cal9 Code keyListener(if else문 영한타 금지)

        void showNorth() {
        JPanel p1 = new  Panel();
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
> cal9에는 keyListener를 추가했습니다
> >if else문에 char c= e.getKeyChar();-> 문자를 입력하는 구문을 썻습니다.
> > >keytyped구문을 썻고 e.consume();을 써서 한영글자를 제한하는 코드를 사용했습니다
# 계산기 사진
![계산기에 최종적으로 C버튼 추가](https://github.com/user-attachments/assets/0c352faf-3f1a-4f03-9b84-98380660253b)
# cal10

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
>Backspace 버튼을 추가
>  >Backspace 이벤트 기능 추가
>  >  >택스트 필드에 Backspace버튼을 누르면 숫자가 하나사라지게했습니다(Backspace 버튼 액션리스너 사용)
# 최종 계산기 사진
![계산기 최종코드](https://github.com/user-attachments/assets/541dd55a-466d-4e0d-a065-f8f1bc0dddae)
