package Caper;
import javax.swing.JOptionPane;

public class InputBoxes {
    public static void main(String[] args) {

        String name;
        name = JOptionPane.showInputDialog("���� ���");

        String surname;
        surname = JOptionPane.showInputDialog("���� �������");

        String full;
        full = "���� ������� " + name + " " + surname;

        JOptionPane.showMessageDialog(null, full);
        System.exit(0);
    }
}
