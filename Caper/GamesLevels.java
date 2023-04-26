package Caper;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;


public class GamesLevels {
    public static void main(String[] args) {
        FrameLevels f = new FrameLevels();
        f.setVisible(true);
    }
}

class FrameLevels extends JFrame{
    JLabel lbl;
    boolean isVisible = true; // Видимость = да.
    private Point mouseOffset; //Отклик мышки.
    Color button = new Color(0, 0, 0); //Цвет текста у кнопок.

    Color text = new Color(175, 224, 248, 255); //Цвет кнопок.

    static JButton education; //Кнопка "Обучения".
    static JButton b; // Кнопка "Новичок".
    static JButton b2; //Кнопка "Любитель".
    static JButton b3; //Кнопка "Профессионал".

    static JButton b4; // Кнопка "Выхода".
    static JButton b5; // Кнопка "Назад".
    static JButton b6; //Кнопка "Скрыть".
    public FrameLevels(){

        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));

        setUndecorated(true); //Убрать рамки окошка.

        addMouseListener(new MouseAdapter() { //Делаем так, чтобы можно было мышкой при нажатии, водить окно.
            @Override
            public void mousePressed(MouseEvent e) {
                mouseOffset = e.getPoint();
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                setLocation(e.getXOnScreen() - mouseOffset.x, e.getYOnScreen() - mouseOffset.y);
            }
        });

        setContentPane(new LevelPanel()); //контейнер на панели.
        Container cont = getContentPane(); //Создаём контейнер.
        cont.setLayout(new FlowLayout( FlowLayout.CENTER, 10, 100)); //Нахождение контейнера.
        cont.setLayout(new LayoutManager() {
            @Override
            public void addLayoutComponent(String name, Component comp) {

            }

            @Override
            public void removeLayoutComponent(Component comp) {

            }

            @Override
            public Dimension preferredLayoutSize(Container parent) {
                return null;
            }

            @Override
            public Dimension minimumLayoutSize(Container parent) {
                return null;
            }

            @Override
            public void layoutContainer(Container parent) {

            }
        });

        setTitle("Сапёр (Игра) в стиле киберпанк."); //название приложения.
        setBounds(500, 50, 500, 700); // координаты фрейма + его размеры.
        setResizable(false); //возможность масштабировать.


        lbl = new JLabel(); //новый лейбл.
        lbl.setFont(new Font("Arial", Font.BOLD, 24)); //шрифт + размеры шрифта.

        education = new JButton("Обучение."); //название кнопки.
        education.setLocation(180, 150); //Задаем располажение кнопки.
        education.setSize(150, 50); //Задаем размер кнопки(ширина-высота).
        education.setForeground(button);  //Цвет текста у кнопки.
        education.setBackground(text); //Цвет кнопки.

        b = new JButton("Новичок."); //название кнопки.
        b.setLocation(180, 250); //Задаем располажение кнопки.
        b.setSize(150, 50); //Задаем размер кнопки(ширина-высота).
        b.setForeground(button); //Цвет текста у кнопки.
        b.setBackground(text); //Цвет кнопки.

        b2 = new JButton("Любитель."); //название кнопки.
        b2.setLocation(180, 350); //Задаем располажение кнопки.
        b2.setSize(150, 50); //Задаем размер кнопки(ширина-высота).
        b2.setForeground(button); //Цвет текста у кнопки.
        b2.setBackground(text); //Цвет кнопки.

        b3 = new JButton("Профессионал."); //название кнопки.
        b3.setLocation(180, 450); //Задаем располажение кнопки.
        b3.setSize(150, 50); //Задаем размер кнопки(ширина-высота).
        b3.setForeground(button); //Цвет текста у кнопки.
        b3.setBackground(text); //Цвет кнопки.

        b4 = new JButton("Выход."); //название кнопки.
        b4.setLocation(180, 550); //Задаем располажение кнопки.
        b4.setSize(150, 50); //Задаем размер кнопки(ширина-высота).
        b4.setForeground(button); //Цвет текста у кнопки.
        b4.setBackground(text); //Цвет кнопки.

        b5 = new JButton("<--"); //название кнопки.
        b5.setLocation(0, 0); //Задаем располажение кнопки.
        b5.setSize(50, 50); //Задаем размер кнопки(ширина-высота).
        b5.setForeground(button); //Цвет текста у кнопки.
        b5.setBackground(text); //Цвет кнопки.

        b6 = new JButton("Скрыть."); //название кнопки.
        b6.setLocation(0, 675); //Задаем располажение кнопки.
        b6.setSize(100, 25); //Задаем размер кнопки(ширина-высота).
        b6.setForeground(button); //Цвет текста у кнопки.
        b6.setBackground(text); //Цвет кнопки.

        cont.add(education); //присоединяем первую кнопку к контейнеру.
        cont.add(b); //присоединяем первую кнопку к контейнеру.
        cont.add(b2); //присоединяем вторую кнопку к контейнеру.
        cont.add(b3); //присоединяем третью кнопку к контейнеру.
        cont.add(b4); //присоединяем третью кнопку к контейнеру.
        cont.add(b5); //присоединяем третью кнопку к контейнеру.
        cont.add(b6); //присоединяем третью кнопку к контейнеру.
        lbl.setLayout(new FlowLayout()); //Создает нового менеджера по расположению потока.
        cont.add(lbl); //присоединяем лейбл к контейнеру.

        education.addActionListener(new ActionListener() { //слушатель на кнопку + ссылка на фрейм.
            @Override
            public void actionPerformed(ActionEvent e) {
                //Вызываем два окна. Само окно обучения для игры, и обучение текст.
                EducationJava edu = new EducationJava(); // открытие маленького поля сапёра.
                EducationText edutext = new EducationText(); //открытие нового окна с текстом про обучение.
                setVisible(false); //Видимость (нет), для окна с выбором уровней.
            }
        });

        b.addActionListener(new ActionListener() { //слушатель на кнопку + ссылка на фрейм.
            @Override
            public void actionPerformed(ActionEvent e) {
                //Вызываем окно.
                StudentMinesweeper frame = new StudentMinesweeper(); // открытие маленького поля сапёра.
            }
        });

        b2.addActionListener(new ActionListener() { //слушатель на кнопку + ссылка на фрейм.
            @Override
            public void actionPerformed(ActionEvent e) {
                //Вызываем окно.
                StudentMinesweeper2 frame2 = new StudentMinesweeper2(); //открытие среднего поля сапёра.
            }
        });

        b3.addActionListener(new ActionListener() { //слушатель на кнопку + ссылка на фрейм.
            @Override
            public void actionPerformed(ActionEvent e) {
                //Вызываем окно.
                StudentMinesweeper3 frame3 = new StudentMinesweeper3(); // открытие большого поля сапёра.
            }
        });

        b4.addActionListener(new ActionListener() { //слушатель на кнопку + ссылка на фрейм.
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b4) System.exit(0); // кнопка выхода.
            }
        });

        b5.addActionListener(new ActionListener() { //слушатель на кнопку + ссылка на фрейм.
            @Override
            public void actionPerformed(ActionEvent e) {
                //Вызываем окно.
                Framemenu frame = new Framemenu(); //возвращаемся в окно "меню"
                frame.setVisible(true); // делаем его видимым.
                setVisible(false); // делаем окно с уровнями... видимость (нет).
            }
        });

        b6.addActionListener(new ActionListener() { //слушатель на кнопку + ссылка на фрейм.
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isVisible) { // При нажатии на кнопку 5, убрать все остальные кнопки по перечислению.
                    education.setVisible(false);
                    b.setVisible(false);
                    b2.setVisible(false);
                    b3.setVisible(false);
                    b4.setVisible(false);
                    b5.setVisible(false);
                    isVisible = false;
                }
                else { // При нажатии на кнопку 5, добавить все кнопки по перечислению.
                    education.setVisible(true);
                    b.setVisible(true);
                    b2.setVisible(true);
                    b3.setVisible(true);
                    b4.setVisible(true);
                    b5.setVisible(true);
                    isVisible = true;
                }
                
            }
            
        });
        
    }

    private void initComponents() {
    }

    class LevelPanel extends JPanel{ // Создаём класс.
        public void paintComponent(Graphics g){
            Image im = null;
            try {
                im = ImageIO.read(new File("Foto.jpg")); // берём картинку по пути...
            } catch (IOException e) {}
            g.drawImage(im, 0, 0, null);

        }
    }
}


