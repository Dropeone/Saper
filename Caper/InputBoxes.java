package Caper;
import javax.swing.JOptionPane;

public class InputBoxes {
    public static void main(String[] args) {

        String name;
        name = JOptionPane.showInputDialog("Ваше имя");

        String surname;
        surname = JOptionPane.showInputDialog("Ваша фамилия");

        String full;
        full = "Ваша фамилия " + name + " " + surname;

        JOptionPane.showMessageDialog(null, full);
        System.exit(0);
    }
}
