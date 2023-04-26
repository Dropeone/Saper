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
HEADER - Заголовок программы.
FLAG_SIGN - Значок флага.
BLOCK_SIZE - Размер блока.
FIELD_SIZE - Размер поля.
FIELD_DX - Размер поля по dx.
FIELD_DY - Размер поля по dy.
STARTING_PLACE - Стартовая позиция окна.
MOUSE_BUTTON_LEFT - ЛКМ.
MOUSE_BUTTON_RIGHT - ПКМ.
NUMBER_OF_MINES - Кол-во мин.
COLOR_OF_NUMBERS - цвета для цифр.
----------------------------------------
StudentMinesweeper - Основная часть Студенческого Сапёра.
OpeningEmptyCells - открытие пустых ячеек.
PlayingFieldInitialization - Инициализация игрового поля.
OpeningEmptyCells - Открытие пустых ячеек.
Cell - Клетка ( Игрового поля ).
Open - открытие.
PaintBomb - Рисовка бомбы.
LineDrawing - Рисовка строки.
Paint - Рисовка.
StopTimer - Остановка таймера.
TimerLabel - Панель Таймера.
----------------------------------------
   */

public class StudentMinesweeper extends JFrame {

    private JLabel label;

    Color mainColor = new Color(84, 148, 54, 255);
    int FIELD_SIZE = 12; // Размер нашего поля т.е. 12 на 12. т.е. 12 на 12, квадратиков.
    final int FIELD_DX = 6 + 6; // Горизонталь.
    final int FIELD_DY = 35 + 17; // +17 для таймера по вертикали.
    final int STARTING_PLACE = 200; //Стартовая позиция окна = 200 пикселей.
    final int BLOCK_SIZE = 30; // Размер блока(квадратиков).
    final String HEADER = "Sapper"; //  final - говорит, что данная переменная не будет изменяться, Константа пишется большими буквами. Mines - заголовок.
    final String FLAG_SIGN = "?"; //  Знак флага, т.е. ?.
    final int MOUSE_BUTTON_LEFT = 1;
    final int MOUSE_BUTTON_RIGHT = 3;
    final int NUMBER_OF_MINES = 20; //Кол-во мин, т.е. 20.
    final int[] COLOR_OF_NUMBERS = {0x0000FF, 0x008000, 0xFF0000, 0x800000, 0x0}; // массив содержит цвета. Первый цвет: Красный, Второй: Темно зелёный, Третий цвет: Синий, Четвёртый цвет: Тёмно Синий. Пятый Цвет: Чёрный.
    Cell[][] field = new Cell[FIELD_SIZE][FIELD_SIZE]; //Двухмерный массив.
    Random random = new Random(); //Случайным образом ставит бомбы.
    int countOpenedCells; //количество открытых ячеек.
    boolean youWon, bangMine; // переменная в "True" если выиграли, и в "True" подорвались на мине.
    int bangX, bangY; // для фиксации координаты взрыва.

    public StudentMinesweeper() {  // основная часть Сапёра.

        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));

        setTitle(HEADER); //Заголовок программы.
       // setDefaultCloseOperation(EXIT_ON_CLOSE);  // закрытие программы.
        setBounds(STARTING_PLACE, STARTING_PLACE, FIELD_SIZE * BLOCK_SIZE + FIELD_DX, FIELD_SIZE * BLOCK_SIZE + FIELD_DY); // устанавливает стартовую позицию окна. Размеры окна. X и Y, ширина и высота окна.

        setResizable(false); //Возможность маслтабировать окно.
        setLocationRelativeTo(null); // Местоположение.


        TimerLabel timeLabel = new TimerLabel(); // Метка таймера.
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER); //Выравнивание по горизонтале.

        Canvas canvas = new Canvas(); //создание Canvas.
        canvas.setBackground(Color.white); //Цвет фона белый.

        canvas.addMouseListener(new MouseAdapter() {   // прослушивание нажатие на мышь. MouseAdapter Ниже представлен код для него.
            @Override  // означает переопределяем метод MouseReleased.
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e); // вызываем метод MouseReleased его родителя.
                int x = e.getX()/BLOCK_SIZE; //Мы обращаемся к переменной е getX(вызываем абсолютный клик нашей мышки).
                int y = e.getY()/BLOCK_SIZE; //Мы обращаемся к переменной е getY(вызываем абсолютный клик нашей мышки). Т.е. по какой клетки мы тыкнули.

                //Отрабатывание кода при нажатие ЛКМ(Левой Кнопки Мыши).

                if (!bangMine && !youWon) {

                    if (e.getButton() == MOUSE_BUTTON_LEFT) // Левая кнопка мыши отжата, и мина не взорволась и не было победы, то...
                        if (field[y][x].isNotOpen()) {  // Если это ячейка не открыта, то...
                            OpeningEmptyCells(x, y);  // Я её открываю.
                            youWon = countOpenedCells == FIELD_SIZE*FIELD_SIZE - NUMBER_OF_MINES; // Первая проверка.
                            if (youWon) {
                                JFrame frame1 = new JFrame();
                                frame1.setBounds(670, 270, 600, 380);
                                frame1.setVisible(true);
                                frame1.setContentPane(new WinPanel());
                            }
                            //countOpenedCells - Кол-во открытых ячеек.
                            //FIELD_SIZE*FIELD_SIZE - Кол-во ячеек вообще.
                            //NUMBER_OF_MINES - Кол-во мин.
                            // Для countOpenedCells, Если я открыл все ячейки, и не взорвался (узнаём с помощью вычитания), то присваивается победа. Т.е. присваивается значение "true".
                            if (bangMine) {  // Вторая проверка. Если взорвалась мина, то...
                                bangX = x;  // Запоминается.
                                bangY = y;  // Место, где это произошло.
                                JFrame frame = new JFrame();
                                frame.setResizable(false);
                                frame.setBounds(670, 270, 210, 280);
                                frame.setVisible(true);
                                frame.setContentPane(new TrollPanel());
                            }
                        }



                    //Отрабатывания кода при нажатии ПКМ(Правой Кнопки Мыши).
                    if (e.getButton() == MOUSE_BUTTON_RIGHT) field[y][x].inverseFlag(); // Вставка или снятие флага, если уже имеется.
                    if (bangMine || youWon) timeLabel.StopTimer(); // Поражение. Таймер останавливается. :(
                    canvas.repaint(); // Перерисовывается экран.
                }
            }
        });

     /*   class TestActionListener implements ActionListener {  // Вызов сообщения.
            public void actionPerformed(ActionEvent e) {*/
             /*   String message = "\"Это уведомление\"\n"
                        + "...\n"
                        + "...";
                JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                        JOptionPane.ERROR_MESSAGE);
                //Код, который нужно выполнить при нажатии
            }
        }*/



        add(BorderLayout.CENTER, canvas); // Canvas ставится в центр экрана.
        add(BorderLayout.SOUTH, timeLabel); //Размещение таймера на панели.
        //add(BorderLayout.CENTER, button);
        setVisible(true); // Делает окно видимым.
        PlayingFieldInitialization(); // Выполняется initField.
    }

    private void initComponents() {
    }

    void OpeningEmptyCells(int x, int y) { // Циклическое открытие пустых ячеек. (Метод рекурсивный).
        if (x < 0 || x > FIELD_SIZE - 1 || y < 0 || y > FIELD_SIZE - 1) return; //Неправильные координаты.
        if (!field[y][x].isNotOpen()) return; //Ячейка уже открыта.
        field[y][x].Open(); //Условие возврата.

        //Если кол-во бомб " > 0 " или бомба взорвалась, то выход.
        if (field[y][x].getCountBomb() > 0 || bangMine) return; // ячейка не пуста.


        for (int dx = -1; dx < 2; dx++) //Двойной цикл, который вызывает метод выше.
            for (int dy = -1; dy < 2; dy++) OpeningEmptyCells(x + dx, y + dy); //Метод открывается сам себя.

    }

    void PlayingFieldInitialization() { //Инициализация игрового поля.

        int x, y, countMines = 0; //Счётчик мин установленных 0.

        // Создаёт каждую клеточку. Двухмерный массив объекта.
        for (x = 0; x < FIELD_SIZE; x++) // создётся игровое поле...
            for (y = 0; y < FIELD_SIZE; y++) // и заполняется объектами.
                field[y][x] = new Cell();


        // ГРПМ (Генератор рандомных поставленных мин).
        while (countMines < NUMBER_OF_MINES) { // В цикле: крутится, пока счётчик этих мин, меньше, мин сколько хотим поставить.
            do {
                x = random.nextInt(FIELD_SIZE);  //Рандомно размер поля по X.
                y = random.nextInt(FIELD_SIZE);  //Рандомно размер поля по Y.
            } while (field[y][x].isMined()); //Проверка, если = true, то цикл do While повторяется. До тех пор, пока не будет false, будет повторяться.
            field[y][x].mine();
            countMines++;
        }


        // Считаем бомбы. Считает мины вокруг, т.е соседей. В каждый объект.
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

    class Cell { // Клетка игрового поля.
         private int countBombNear; // Кол-во бомб в близи.
              private boolean isOpen, isMine, isFlag; // Открыта ли ячейка или нет, есть мина или нет, поставлен флаг.

                    void Open() {
                        isOpen = true; // Флаг открытия ячейки - Истина.
                        bangMine = isMine; // Глобальная переменная bangMine ( сапёр подорвался).
                        if (!isMine) countOpenedCells++;  // Если ячейка не заминированная, тогда счётчик открытых ячеек увел. на 1.
                    }

                    void mine() { isMine = true; } // Ставится мина. Ячейка таким образом минируется.

                    void setCountBomb(int count) { countBombNear = count; } // Устанавливает кол-во соседних бомб, у данного объекта. Классический сеттер.

                    int getCountBomb() { return countBombNear; } // Классический Геттер.

                    boolean isNotOpen() { return !isOpen; } // Проверяется, открыта или неоткрыта ячейка.

                    boolean isMined() { return isMine; } // Метод позволяет заминированных ли этот объект или нет.

                    void inverseFlag() { isFlag = !isFlag; } //Инверсия Флага.

                    void PaintBomb(Graphics g, int x, int y, Color color) { // Метод рисования бомбы. Прямоугольничками.
                        g.setColor(color); // Цвет бомбы у блоков.
                        g.fillRect(x*BLOCK_SIZE + 7, y*BLOCK_SIZE + 10, 18, 10);
                        g.fillRect(x*BLOCK_SIZE + 11, y*BLOCK_SIZE + 6, 10, 18);
                        g.fillRect(x*BLOCK_SIZE + 9, y*BLOCK_SIZE + 8, 14, 14);
                        g.setColor(Color.white); // рисуется блеск бомбы.
                        g.fillRect(x*BLOCK_SIZE + 11, y*BLOCK_SIZE + 10, 4, 4);
                    }

                    void LineDrawing(Graphics g, String str, int x, int y, Color color) { // Метод "рисования строки".
                        g.setColor(color);
                        g.setFont(new Font("УУУ", Font.BOLD, BLOCK_SIZE));
                        g.drawString(str, x*BLOCK_SIZE + 8, y*BLOCK_SIZE + 26);
                    }

                    void Paint(Graphics g, int x, int y) { //
                        g.setColor(Color.cyan); // цвет разреза.
                        g.drawRect(x*BLOCK_SIZE, y*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE); // Рисует прямоугольничек.
                        if (!isOpen) { // Если ячейка не открыта, то...
                            if ((bangMine || youWon) && isMine) PaintBomb(g, x, y, Color.red); // Бомба чёрного цвета + Если мины взорвались ||(Или) я победил и при этом ячейка заминированна, то рисуется бомба Чёрного цвета. В противном случае рисуется прямоугольничек.
                            else {
                                g.setColor(mainColor);
                                g.fill3DRect(x*BLOCK_SIZE, y*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE, true);
                                if (isFlag) LineDrawing(g, FLAG_SIGN, x, y, Color.red); // Если ячейка не открыта, и помеченна флагом, то , Рисуется флаг.
                            }
                        } else //иначе...
                        if (isMine) PaintBomb(g, x, y, bangMine? Color.red : Color.black); //Если ячейка открыта, то если есть мина, то рисуется бомба.

                        else if (countBombNear > 0) // Если в ближайших ячейках > 0, то
                            LineDrawing(g, Integer.toString(countBombNear), x, y, new Color(COLOR_OF_NUMBERS[countBombNear - 1])); // Цвет берётся из массива цветов.
                        //Integer.toString(countBombNear) - целое число превращаем в строку.
                    }
                }

    class TimerLabel extends JLabel { // Секундомер(Таймер).
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

class Canvas extends JPanel { // Холс для рисования.
        @Override // Переопределяем метод JPanel.
        public void paint(Graphics g) {  // Входной параметр графикс.
            super.paint(g);  // Родительский метод отрисовки.
            for (int x = 0; x < FIELD_SIZE; x++)  // Цикл:
                for (int y = 0; y < FIELD_SIZE; y++) field[y][x].Paint(g, x, y); // Обращаемся к объекту находиться в массиве Field, вызываем метод отрисовки paint.
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

