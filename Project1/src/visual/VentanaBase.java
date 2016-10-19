package visual;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import listas.ListaLotes;
import listas.ListaPedidos;

public abstract class VentanaBase extends JFrame implements Observer {
    protected Controlador control;
    protected ArrayList<Observable> observados;

    public VentanaBase(Controlador control, String nombreVentana, int accionCerrar, Dimension size) {
        super(nombreVentana);
        this.control = control;
        //Setea lo que ocurre al cerrar la ventana
        this.setDefaultCloseOperation(accionCerrar);
        //Se setean la dimension y la dimension minima de la vnetana del programa
        this.setSize(size);
        this.setMinimumSize(size);
        //Se añade un BorderLayout a la ventana
        this.setLayout(new BorderLayout());
        //Crea el menu basico de todas las ventanas
        this.setJMenuBar(this.crearMenuBasico());
        //Inicia todas las componentes que contrendra la ventana
        this.IniciarComponentes();
        //Hace que la ventana aparezca en el centro de la pantalla
        this.setLocationRelativeTo(null);
        //Hace que la ventana sea visible
        this.setVisible(true);
        
        this.definirObserverObservable();
    }
    
    private void definirObserverObservable(){
        this.observados = new ArrayList<Observable>();
        ListaLotes lotes = ListaLotes.getInstance();
        lotes.addObserver(this);
        this.observados.add(lotes);

        ListaPedidos pedidos = ListaPedidos.getInstance();
        pedidos.addObserver(this);
        this.observados.add(pedidos);
    }
    
    private JMenuBar crearMenuBasico(){
        JMenuItem cerrarSesion = new JMenuItem("Cerrar sesion");        
        cerrarSesion.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                VentanaBase.this.dispose();
                new VentanaLogin(new Controlador());
            }
        });

        JMenu sesion = new JMenu("Sesion");
        sesion.add(cerrarSesion);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(sesion);
        
        return menuBar;
    }

    protected abstract void IniciarComponentes();

    @Override
    public abstract void update(Observable observable, Object object);
}
