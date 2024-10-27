![cal1 계산기](https://github.com/user-attachments/assets/7b9677b7-b991-4f2a-a8a8-00107a2be21f)
위 사진은 처음 책646쪽을 보고 계산기를 만들었으며 
![계산기 설명 1](https://github.com/user-attachments/assets/1209f519-baa1-4f55-a0d4-cdf4facd7346)

위사진 처럼 패널을 3개로 나누고 북쪽, 중앙으로 나누었습니다
레이아웃 설정은 Frame 에는 BorderLayout 가로10,세로10
북쪽 패널에는 GridLayout에는 가로줄 1줄, 세로 0 행으로 택스트 필드만 나오게 했고
중앙패널에는 GridLayout에는 4개의 행, 4열, 수평10, 수직10입니다
맨위에 버튼 on,off 2개와 버튼이 16개 연산코드를 추가했습니다.
cal2에는 ![스크린샷 2024-10-27 170900](https://github.com/user-attachments/assets/4bbd3dbd-a4dc-437e-a0bd-6aa456ea86f6)
![스크린샷 2024-10-27 170929](https://github.com/user-attachments/assets/849a72c3-2bfb-47ae-b262-e68838fe2bc8)
키보드 이벤트와 ActionListener를 추가했습니다
위에있는 버튼을 케이스에 넣어서 키보드로 버튼을 입력했을때 택스트필드에 입력이 되게 했습니다.(Key press handling)->Keychar 변수에 눌린키의 문자를 저장하고 \b가 눌리면 현재 텍스트(t1)에서 마지막 문자를 제거하게 만들엇습니다.
cal3에는 ![버튼](https://github.com/user-attachments/assets/3f072fc2-13df-4d08-abdf-eeb2993d2e29)
버튼클릭리스너를 추가했고 버튼을 누르면 텍스트필드에 텍스트가 추가되는 코드입니다.

cal4에는 ![스크린샷 2024-10-27 172240](https://github.com/user-attachments/assets/17a512a5-2ed4-4528-be75-f96c16532ef1)
버튼 레이블 배열에 계산기 버튼을 넣었습니다
각 버튼에 버튼클릭리스너를 추가했습니다.
![스크린샷 2024-10-27 172316](https://github.com/user-attachments/assets/12ff2f3c-93ae-4d9f-81b2-7b08818355f9)
두번쨰 숫자를 가져오고, 이전에 저장된 operator연산자에 따라 계산을 수행합니다
나누기 0으로 나누는 경우를 처리하여 에러 메시지를 표시했습니다.
결과를 택스트 필드에 표시하고, 연산자를 초기화 했습니다
![스크린샷 2024-10-27 172333](https://github.com/user-attachments/assets/bfdac423-493b-469d-9722-8e2ea6e82950)
"="버튼 클릭시 연산자 메소드 를 호출하고 연산자 버튼 클릭시 현재 입력된 숫자를 저장하게 했습니다.
cal5에는 ![cal5메뉴이벤트 넣은거](https://github.com/user-attachments/assets/95dcee47-2015-4e58-b533-f1aca1a7f605)
![메뉴바 열기](https://github.com/user-attachments/assets/9dccb8ee-bc93-49ac-b54d-071fe942aacc)
메뉴 이벤트를 추가했으며 "새파일",'파일 저장하기", "종료"를 추가했습니다.
cal6에는 ![cal6 로컬파일 저장하기](https://github.com/user-attachments/assets/f1ee0801-9e03-4535-9778-266482b56638)
파일 내컴퓨터 파일저장하기 기능을 추가했습니다. 여기에서 GUI애플리케이션 메뉴바를 설정하고 파일관련기능을 구현했습니다.
![파일저장하기 기능 ](https://github.com/user-attachments/assets/ee9c21e6-e0d0-4e4b-996d-4d20734b1f75)
사용자가 선택한 파일을 BufferWritter를 사용하여 작성했습니다
택스트필드의 내용을 파일에 쓰고, 파일저장 매세지를 보내게 했습니다, 오류가 뜨면 오류매세지가 뜨게 했습니다.
![메뉴바](https://github.com/user-attachments/assets/5082b429-7206-435e-a2ae-d3e522f12103)
fileMenu.setMnemonic(70); 70은 F이 아스키코드로 alt+F눌러 메뉴접근 가능하게 했습니다, JMenuItem newFileItem = new JMenuItem("새파일", 78); 78은 N이고 파일저장하는 레이블입니다. System.exit(0);를 종료 확인 상자를 표시하기 위해 레이블을 추가 했습니다.
fileMenu.add(newFileItem);, fileMenu.add(saveFileItem);, fileMenu.addSeparator();, fileMenu.add(exitItem);: 위에서 생성한 메뉴 항목들을 파일 메뉴에 추가했습니다. 
이때 addSeparator() 메서드는 항목들 사이에 구분선을 추가하여 시각적으로 구분했습니다.
cal7에는 ![Note_icon](https://github.com/user-attachments/assets/1e0a1eee-5a28-48cf-9772-eaa5f6c96e6d)
![아이콘 이미지 코드](https://github.com/user-attachments/assets/2e1a059d-83ff-49be-8baa-72fc263df1e1)
"C:/Users/ASUS/OneDrive - 청주대학교/바탕 화면/Note_icon.jpg"이미지 업로두 코드이고 
setIconImage(icon);을 통해 현재 프레임의 아이콘으로 설정하게 햇습니다.
예외 처리: IOException이 발생할 경우, catch 블록에서 예외를 잡아 e.printStackTrace();를 호출하여 콘솔에 에러 메시지를 출력합니다. 
![계산기 배경색 아이콘 바꾼거](https://github.com/user-attachments/assets/ca5b7723-15f4-45e8-85f5-062a767ac7bb)
GetContentPane()setbackground배경색 color를 하얀색으로 바꾸고 버튼을 회색으로 칠했습니다.
cal8에는![계산기에 최종적으로 C버튼 추가](https://github.com/user-attachments/assets/29beda10-f6e3-46c8-8eae-ab095b24bc29)
지우개 C버튼을 추가했습니다. 택스트필드에있는 내용을 지울수 있습니다.
