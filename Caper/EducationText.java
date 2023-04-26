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

public class EducationText {
    private Point mouseOffset;
    Color mainColor = new Color(1, 1, 43, 255); //Цвет фона.
    Color colortext = new Color(62, 230, 244); //Цвет текста.
    public EducationText() {
        
        JFrame frame = new JFrame("Обучение."); //название окна.
        frame.setResizable(false); //возможность масштабировать.
        JTextArea textArea = new JTextArea("Левой кнопкой мыши открывают квадраты, под которыми нет мин, а правой — ставят флажки на квадраты с минами.\n" + //Сам текст.
                "На более высоких уровнях сложности вам будет необходимо отмечать флажками те квадраты, под которыми потен -" + //Сам текст.
                "\nциально могут находиться мины, пока вы не сможете это проверить. " + //Сам текст.
                "\nЧисло на конкретном квадрате говорит о том, сколько мин расположено вокруг в смежных квадратах. Например, если" + //Сам текст.
                "\n вы видите два смежных квадрата и на одном из них стоит единица, знайте, что под смежным с цифрой квадратом" + //Сам текст.
                "\n прячется мина." ); //Сам текст.

        int begn = 1; //отступ.
        int end = 1; //отступ.

        textArea.replaceRange(null, begn, end);
        textArea.setForeground(colortext); // цвет текста.
        textArea.setBackground(mainColor); //цвет окна.

        frame.add(textArea); //добавление текста на фрейм.
        frame.setBounds(850, 300, 665, 150); //координаты фрейма + его размеры.
        frame.setLayout(new GridLayout(1, 1));
        // frame.setBackground(Color.cyan);
        frame.setVisible(true); //видимость окна.

    }


    public static void main(String args[]) {
        new EducationText();
    }

}
