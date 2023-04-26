package Caper;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


/*
--------------------------------------
HEADER - ��������� ���������.
FLAG_SIGN - ������ �����.
BLOCK_SIZE - ������ �����.
FIELD_SIZE - ������ ����.
FIELD_DX - ������ ���� �� dx.
FIELD_DY - ������ ���� �� dy.
STARTING_PLACE - ��������� ������� ����.
MOUSE_BUTTON_LEFT - ���.
MOUSE_BUTTON_RIGHT - ���.
NUMBER_OF_MINES - ���-�� ���.
COLOR_OF_NUMBERS - ����� ��� ����.
----------------------------------------
StudentMinesweeper - �������� ����� ������������� �����.
OpeningEmptyCells - �������� ������ �����.
PlayingFieldInitialization - ������������� �������� ����.
OpeningEmptyCells - �������� ������ �����.
Cell - ������ ( �������� ���� ).
Open - ��������.
PaintBomb - ������� �����.
LineDrawing - ������� ������.
Paint - �������.
StopTimer - ��������� �������.
TimerLabel - ������ �������.
----------------------------------------
   */

public class StudentMinesweeper extends JFrame {

    private JLabel label;

    Color mainColor = new Color(84, 148, 54, 255);
    int FIELD_SIZE = 12; // ������ ������ ���� �.�. 12 �� 12. �.�. 12 �� 12, �����������.
    final int FIELD_DX = 6 + 6; // �����������.
    final int FIELD_DY = 35 + 17; // +17 ��� ������� �� ���������.
    final int STARTING_PLACE = 200; //��������� ������� ���� = 200 ��������.
    final int BLOCK_SIZE = 30; // ������ �����(�����������).
    final String HEADER = "Sapper"; //  final - �������, ��� ������ ���������� �� ����� ����������, ��������� ������� �������� �������. Mines - ���������.
    final String FLAG_SIGN = "?"; //  ���� �����, �.�. ?.
    final int MOUSE_BUTTON_LEFT = 1;
    final int MOUSE_BUTTON_RIGHT = 3;
    final int NUMBER_OF_MINES = 20; //���-�� ���, �.�. 20.
    final int[] COLOR_OF_NUMBERS = {0x0000FF, 0x008000, 0xFF0000, 0x800000, 0x0}; // ������ �������� �����. ������ ����: �������, ������: ����� ������, ������ ����: �����, �������� ����: Ҹ��� �����. ����� ����: ׸����.
    Cell[][] field = new Cell[FIELD_SIZE][FIELD_SIZE]; //���������� ������.
    Random random = new Random(); //��������� ������� ������ �����.
    int countOpenedCells; //���������� �������� �����.
    boolean youWon, bangMine; // ���������� � "True" ���� ��������, � � "True" ����������� �� ����.
    int bangX, bangY; // ��� �������� ���������� ������.

    public StudentMinesweeper() {  // �������� ����� �����.

        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));

        setTitle(HEADER); //��������� ���������.
       // setDefaultCloseOperation(EXIT_ON_CLOSE);  // �������� ���������.
        setBounds(STARTING_PLACE, STARTING_PLACE, FIELD_SIZE * BLOCK_SIZE + FIELD_DX, FIELD_SIZE * BLOCK_SIZE + FIELD_DY); // ������������� ��������� ������� ����. ������� ����. X � Y, ������ � ������ ����.

        setResizable(false); //����������� �������������� ����.
        setLocationRelativeTo(null); // ��������������.


        TimerLabel timeLabel = new TimerLabel(); // ����� �������.
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER); //������������ �� �����������.

        Canvas canvas = new Canvas(); //�������� Canvas.
        canvas.setBackground(Color.white); //���� ���� �����.

        canvas.addMouseListener(new MouseAdapter() {   // ������������� ������� �� ����. MouseAdapter ���� ����������� ��� ��� ����.
            @Override  // �������� �������������� ����� MouseReleased.
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e); // �������� ����� MouseReleased ��� ��������.
                int x = e.getX()/BLOCK_SIZE; //�� ���������� � ���������� � getX(�������� ���������� ���� ����� �����).
                int y = e.getY()/BLOCK_SIZE; //�� ���������� � ���������� � getY(�������� ���������� ���� ����� �����). �.�. �� ����� ������ �� �������.

                //������������� ���� ��� ������� ���(����� ������ ����).

                if (!bangMine && !youWon) {

                    if (e.getButton() == MOUSE_BUTTON_LEFT) // ����� ������ ���� ������, � ���� �� ���������� � �� ���� ������, ��...
                        if (field[y][x].isNotOpen()) {  // ���� ��� ������ �� �������, ��...
                            OpeningEmptyCells(x, y);  // � � ��������.
                            youWon = countOpenedCells == FIELD_SIZE*FIELD_SIZE - NUMBER_OF_MINES; // ������ ��������.
                            if (youWon) {
                                JFrame frame1 = new JFrame();
                                frame1.setBounds(670, 270, 600, 380);
                                frame1.setVisible(true);
                                frame1.setContentPane(new WinPanel());
                            }
                            //countOpenedCells - ���-�� �������� �����.
                            //FIELD_SIZE*FIELD_SIZE - ���-�� ����� ������.
                            //NUMBER_OF_MINES - ���-�� ���.
                            // ��� countOpenedCells, ���� � ������ ��� ������, � �� ��������� (����� � ������� ���������), �� ������������� ������. �.�. ������������� �������� "true".
                            if (bangMine) {  // ������ ��������. ���� ���������� ����, ��...
                                bangX = x;  // ������������.
                                bangY = y;  // �����, ��� ��� ���������.
                                JFrame frame = new JFrame();
                                frame.setResizable(false);
                                frame.setBounds(670, 270, 210, 280);
                                frame.setVisible(true);
                                frame.setContentPane(new TrollPanel());
                            }
                        }



                    //������������� ���� ��� ������� ���(������ ������ ����).
                    if (e.getButton() == MOUSE_BUTTON_RIGHT) field[y][x].inverseFlag(); // ������� ��� ������ �����, ���� ��� �������.
                    if (bangMine || youWon) timeLabel.StopTimer(); // ���������. ������ ���������������. :(
                    canvas.repaint(); // ���������������� �����.
                }
            }
        });

     /*   class TestActionListener implements ActionListener {  // ����� ���������.
            public void actionPerformed(ActionEvent e) {*/
             /*   String message = "\"��� �����������\"\n"
                        + "...\n"
                        + "...";
                JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                        JOptionPane.ERROR_MESSAGE);
                //���, ������� ����� ��������� ��� �������
            }
        }*/



        add(BorderLayout.CENTER, canvas); // Canvas �������� � ����� ������.
        add(BorderLayout.SOUTH, timeLabel); //���������� ������� �� ������.
        //add(BorderLayout.CENTER, button);
        setVisible(true); // ������ ���� �������.
        PlayingFieldInitialization(); // ����������� initField.
    }

    private void initComponents() {
    }

    void OpeningEmptyCells(int x, int y) { // ����������� �������� ������ �����. (����� �����������).
        if (x < 0 || x > FIELD_SIZE - 1 || y < 0 || y > FIELD_SIZE - 1) return; //������������ ����������.
        if (!field[y][x].isNotOpen()) return; //������ ��� �������.
        field[y][x].Open(); //������� ��������.

        //���� ���-�� ���� " > 0 " ��� ����� ����������, �� �����.
        if (field[y][x].getCountBomb() > 0 || bangMine) return; // ������ �� �����.


        for (int dx = -1; dx < 2; dx++) //������� ����, ������� �������� ����� ����.
            for (int dy = -1; dy < 2; dy++) OpeningEmptyCells(x + dx, y + dy); //����� ����������� ��� ����.

    }

    void PlayingFieldInitialization() { //������������� �������� ����.

        int x, y, countMines = 0; //������� ��� ������������� 0.

        // ������ ������ ��������. ���������� ������ �������.
        for (x = 0; x < FIELD_SIZE; x++) // ������� ������� ����...
            for (y = 0; y < FIELD_SIZE; y++) // � ����������� ���������.
                field[y][x] = new Cell();


        // ���� (��������� ��������� ������������ ���).
        while (countMines < NUMBER_OF_MINES) { // � �����: ��������, ���� ������� ���� ���, ������, ��� ������� ����� ���������.
            do {
                x = random.nextInt(FIELD_SIZE);  //�������� ������ ���� �� X.
                y = random.nextInt(FIELD_SIZE);  //�������� ������ ���� �� Y.
            } while (field[y][x].isMined()); //��������, ���� = true, �� ���� do While �����������. �� ��� ���, ���� �� ����� false, ����� �����������.
            field[y][x].mine();
            countMines++;
        }


        // ������� �����. ������� ���� ������, �.� �������. � ������ ������.
        for (x = 0; x < FIELD_SIZE; x++)
            for (y = 0; y < FIELD_SIZE; y++)
                if (!field[y][x].isMined()) {
                    int count = 0;

                    for (int dx = -1; dx < 2; dx++)
                        for (int dy = -1; dy < 2; dy++) {
                            int nX = x + dx;
                            int nY = y + dy;
                            if (nX < 0 || nY < 0 || nX > FIELD_SIZE - 1 || nY > FIELD_SIZE - 1) {
                                nX = x;
                                nY = y;
                            }
                            count += (field[nY][nX].isMined()) ? 1 : 0;
                        }
                    field[y][x].setCountBomb(count);
                }
    }

    class Cell { // ������ �������� ����.
         private int countBombNear; // ���-�� ���� � �����.
              private boolean isOpen, isMine, isFlag; // ������� �� ������ ��� ���, ���� ���� ��� ���, ��������� ����.

                    void Open() {
                        isOpen = true; // ���� �������� ������ - ������.
                        bangMine = isMine; // ���������� ���������� bangMine ( ���� ����������).
                        if (!isMine) countOpenedCells++;  // ���� ������ �� ��������������, ����� ������� �������� ����� ����. �� 1.
                    }

                    void mine() { isMine = true; } // �������� ����. ������ ����� ������� ����������.

                    void setCountBomb(int count) { countBombNear = count; } // ������������� ���-�� �������� ����, � ������� �������. ������������ ������.

                    int getCountBomb() { return countBombNear; } // ������������ ������.

                    boolean isNotOpen() { return !isOpen; } // �����������, ������� ��� ��������� ������.

                    boolean isMined() { return isMine; } // ����� ��������� �������������� �� ���� ������ ��� ���.

                    void inverseFlag() { isFlag = !isFlag; } //�������� �����.

                    void PaintBomb(Graphics g, int x, int y, Color color) { // ����� ��������� �����. �����������������.
                        g.setColor(color); // ���� ����� � ������.
                        g.fillRect(x*BLOCK_SIZE + 7, y*BLOCK_SIZE + 10, 18, 10);
                        g.fillRect(x*BLOCK_SIZE + 11, y*BLOCK_SIZE + 6, 10, 18);
                        g.fillRect(x*BLOCK_SIZE + 9, y*BLOCK_SIZE + 8, 14, 14);
                        g.setColor(Color.white); // �������� ����� �����.
                        g.fillRect(x*BLOCK_SIZE + 11, y*BLOCK_SIZE + 10, 4, 4);
                    }

                    void LineDrawing(Graphics g, String str, int x, int y, Color color) { // ����� "��������� ������".
                        g.setColor(color);
                        g.setFont(new Font("���", Font.BOLD, BLOCK_SIZE));
                        g.drawString(str, x*BLOCK_SIZE + 8, y*BLOCK_SIZE + 26);
                    }

                    void Paint(Graphics g, int x, int y) { //
                        g.setColor(Color.cyan); // ���� �������.
                        g.drawRect(x*BLOCK_SIZE, y*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE); // ������ ���������������.
                        if (!isOpen) { // ���� ������ �� �������, ��...
                            if ((bangMine || youWon) && isMine) PaintBomb(g, x, y, Color.red); // ����� ������� ����� + ���� ���� ���������� ||(���) � ������� � ��� ���� ������ �������������, �� �������� ����� ׸����� �����. � ��������� ������ �������� ���������������.
                            else {
                                g.setColor(mainColor);
                                g.fill3DRect(x*BLOCK_SIZE, y*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE, true);
                                if (isFlag) LineDrawing(g, FLAG_SIGN, x, y, Color.red); // ���� ������ �� �������, � ��������� ������, �� , �������� ����.
                            }
                        } else //�����...
                        if (isMine) PaintBomb(g, x, y, bangMine? Color.red : Color.black); //���� ������ �������, �� ���� ���� ����, �� �������� �����.

                        else if (countBombNear > 0) // ���� � ��������� ������� > 0, ��
                            LineDrawing(g, Integer.toString(countBombNear), x, y, new Color(COLOR_OF_NUMBERS[countBombNear - 1])); // ���� ������ �� ������� ������.
                        //Integer.toString(countBombNear) - ����� ����� ���������� � ������.
                    }
                }

    class TimerLabel extends JLabel { // ����������(������).
         Timer timer = new Timer();

             TimerLabel() { timer.scheduleAtFixedRate(timerTask, 0, 1000); }

                 TimerTask timerTask = new TimerTask() {
                        volatile int time;
                            Runnable refresher = new Runnable() {
                                public void run() {
                                    TimerLabel.this.setText(String.format("%02d:%02d", time / 60, time % 60));
                                }
                            };
                                    public void run() {
                                        time++;
                                        SwingUtilities.invokeLater(refresher);
                                    }
                                };

                                        void StopTimer() { timer.cancel(); }
                                    }

class Canvas extends JPanel { // ���� ��� ���������.
        @Override // �������������� ����� JPanel.
        public void paint(Graphics g) {  // ������� �������� �������.
            super.paint(g);  // ������������ ����� ���������.
            for (int x = 0; x < FIELD_SIZE; x++)  // ����:
                for (int y = 0; y < FIELD_SIZE; y++) field[y][x].Paint(g, x, y); // ���������� � ������� ���������� � ������� Field, �������� ����� ��������� paint.
        }
    }
    class TrollPanel extends JPanel{
        public void paintComponent(Graphics g){
            Image im = null;
            try {
                im = ImageIO.read(new File("Troll.jpg"));
            } catch (IOException e) {}
            g.drawImage(im, 0, 0, null);

        }
    }

    class WinPanel extends JPanel{
        public void paintComponent(Graphics g){
            Image im = null;
            try {
                im = ImageIO.read(new File("Win.jpg"));
            } catch (IOException e) {}
            g.drawImage(im, 0, 0, null);

        }
    }
}

