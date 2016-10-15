package visual;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Test {
    public Test() {
        super();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException |
                 ClassNotFoundException e) {
        }
        new VentanaVentas();
    }
}
