package visual;

import java.awt.*;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import listas.ListaLotes;
import listas.ListaPedidos;

public abstract class VentanaBase extends JFrame implements Observer{
    protected Controlador control;
    protected ArrayList<Observable> observados;
    
    public VentanaBase(Controlador control, String nombreVentana, int accionCerrar, Dimension size) {
        super(nombreVentana);
        this.control = control;
        //Setea lo que ocurre al cerrar la ventana
        this.setDefaultCloseOperation(accionCerrar);
        //Se obtienen las dimensiones de la pantalla donde se esta ejecutando el programa
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //Se setean la dimension y ma dimension minima de la vnetana del programa
        this.setSize(size);
        this.setMinimumSize(size);
        //Se añade un BorderLayout a la ventana
        this.setLayout(new BorderLayout());
        //Inicia todas las componentes que contrendra la ventana
        this.IniciarComponentes();
        //Hace que la ventana aparezca en el centro de la pantalla
        this.setLocationRelativeTo(null);
        //Hace que la ventana sea visible
        this.setVisible(true);
        
        this.observados = new ArrayList<Observable>();
        ListaLotes lotes = ListaLotes.getInstance();
        lotes.addObserver(this);
        this.observados.add(lotes);
        
        ListaPedidos pedidos = ListaPedidos.getInstance();
        pedidos.addObserver(this);
        this.observados.add(pedidos);
    }

    protected abstract void IniciarComponentes();
    
    @Override
    public abstract void update(Observable observable, Object object);
}
