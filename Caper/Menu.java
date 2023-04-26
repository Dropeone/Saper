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


public class Menu {
    public static void main(String[] args) {
        Framemenu f = new Framemenu();
        f.setVisible(true);
    }
}

class Framemenu extends JFrame{

    boolean isVisible = true; //Видимость = да.
    JLabel lbl;
    private Point mouseOffset; // Отклик мыши.
    Color button = new Color(0, 0, 0); // Цвет текста у кнопок.

    Color text = new Color(175, 224, 248, 255); // Цвет кнопок.
    static JButton b; //Кнопка "Новичок".
    static JButton b2; //Кнопка "Любитель".
    static JButton b3; //Кнопка "Профессионал".
    static JButton b4; // Кнопка "Выход".
    public Framemenu(){
        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));

        setUndecorated(true);

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

        setContentPane(new BPanel()); //контейнер на панели.
        Container cont = getContentPane(); //Создание контейнера.
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

        b = new JButton("Играть."); //название кнопки.
        b.setLocation(180, 200); //Задаем располажение кнопки.
        b.setSize(150, 50); //Задаем размер кнопки(ширина-высота).
        b.setForeground(button); //Цвет текста у кнопки.
        b.setBackground(text); //Цвет кнопки.

        b2 = new JButton("Об Игре."); //название кнопки.
        b2.setLocation(180, 300); //Задаем располажение кнопки.
        b2.setSize(150, 50); //Задаем размер кнопки(ширина-высота).
        b2.setForeground(button); //Цвет текста у кнопки.
        b2.setBackground(text); //Цвет кнопки.

        b3 = new JButton("Выход."); //название кнопки.
        b3.setLocation(180, 400); //Задаем располажение кнопки.
        b3.setSize(150, 50); //Задаем размер кнопки(ширина-высота).
        b3.setForeground(button); //Цвет текста у кнопки.
        b3.setBackground(text); //Цвет кнопки.

        b4 = new JButton("Скрыть."); //название кнопки.
        b4.setLocation(0, 675); //Задаем располажение кнопки.
        b4.setSize(100, 25); //Задаем размер кнопки(ширина-высота).
        b4.setForeground(button); //Цвет текста у кнопки.
        b4.setBackground(text); //Цвет кнопки.

        cont.add(b); //присоединяем первую кнопку к контейнеру.
        cont.add(b2); //присоединяем вторую кнопку к контейнеру.
        cont.add(b3); //присоединяем третью кнопку к контейнеру.
        cont.add(b4); //присоединяем третью кнопку к контейнеру.
        lbl.setLayout(new FlowLayout()); //Создает нового менеджера по расположению потока.
        cont.add(lbl); //присоединяем лейбл к контейнеру.

        b.addActionListener(new ActionListener() { //слушатель на кнопку + ссылка на фрейм.
            @Override
            public void actionPerformed(ActionEvent e) {
                //Вызываем окно.
                FrameLevels levels = new FrameLevels(); // открытие выбора уровней игры.
                // видимость = да.
                levels.setVisible(true);
                // видимость у окна меню = нет.
                setVisible(false);
            }
        });
        b2.addActionListener(new ActionListener() { //слушатель на кнопку + ссылка на фрейм.
            @Override
            public void actionPerformed(ActionEvent e) {
                //Вызываем окно.
                AboutGame frame3 = new AboutGame(); //открытие об игре.
            }
        });

        b3.addActionListener(new ActionListener() { //слушатель на кнопку + ссылка на фрейм.
            @Override
            public void actionPerformed(ActionEvent e) {
                //Вызываем окно.
                if (e.getSource() == b3) System.exit(0);// Выход.
            }
        });

        b4.addActionListener(new ActionListener() { //слушатель на кнопку + ссылка на фрейм.
            @Override
            public void actionPerformed(ActionEvent e) {

                if (isVisible) { // При нажатии на кнопку 5, убрать все остальные кнопки по перечислению.
                    b.setVisible(false);
                    b2.setVisible(false);
                    b3.setVisible(false);
                    isVisible = false;
                }
                else { // При нажатии на кнопку 5, добавить все кнопки по перечислению.
                    b.setVisible(true);
                    b2.setVisible(true);
                    b3.setVisible(true);
                    isVisible = true;
                }
            }
        });
    }

    private void initComponents() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
    }
}

class BPanel extends JPanel { // Создаём класс.
    public void paintComponent(Graphics g) {
        Image im = null;
        try {
            im = ImageIO.read(new File("Foto.jpg")); // берём картинку по пути...
        } catch (IOException e) {
        }
        g.drawImage(im, 0, 0, null);

    }
}



