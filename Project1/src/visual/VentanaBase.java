package visual;

import java.awt.*;

import javax.swing.*;

public abstract class VentanaBase extends JFrame {
    public VentanaBase(String nombreVentana, int accionCerrar, Dimension size) {
        super(nombreVentana);

        //Setea lo que ocurre al cerrar la ventana
        this.setDefaultCloseOperation(accionCerrar);
        //Se obtienen las dimensiones de la pantalla donde se esta ejecutando el programa
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //Se setean la dimension y ma dimension minima de la vnetana del programa
        this.setSize(size);
        this.setMinimumSize(size);
        //Se a�ade un BorderLayout a la ventana
        this.setLayout(new BorderLayout());
        //Inicia todas las componentes que contrendra la ventana
        this.IniciarComponentes();
        //Hace que la ventana aparezca en el centro de la pantalla
        this.setLocationRelativeTo(null);
        //Hace que la ventana sea visible
        this.setVisible(true);
    }

    protected abstract void IniciarComponentes();
}
