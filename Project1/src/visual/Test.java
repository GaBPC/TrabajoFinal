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
        
        Controlador controlUsuario1 = new Controlador();
        new VentanaLogin(controlUsuario1);

        Controlador controlUsuario2 = new Controlador();
        new VentanaLogin(controlUsuario2);
    }
}
