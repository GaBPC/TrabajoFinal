package visual;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Calendar;

import java.util.GregorianCalendar;

import javax.swing.*;

import visual.auxiliares.PanelFechas;

public class VentanaVentas extends VentanaBase {

    public VentanaVentas() {
        super("Ventas", JFrame.EXIT_ON_CLOSE, new Dimension(500, 500));
    }

    @Override
    protected void IniciarComponentes() {
        Container cp = this.getContentPane();


        /* Crea un panel donde se encuentra todo lo relacionado a agregar
         * un nuevo pedido al sistema desde el sector de ventas*/
        JPanel panelIngreso = new JPanel();
        panelIngreso.setLayout(new GridLayout(0, 2));

        /* Area para ingresar el numero de pedido*/
        panelIngreso.add(new JLabel("Numero de pedido: "));
        JTextField nPedido = new JTextField();
        panelIngreso.add(nPedido);

        /* Area para ingresar la fecha del pedido*/
        panelIngreso.add(new JLabel("Fecha de pedido: "));
        PanelFechas fechaPedido = new PanelFechas();
        panelIngreso.add(fechaPedido);

        /* Area para ingresar el tipo de maquina*/
        //TODO remplazar la entrada por una lista de las maquinas soportadas
        JComboBox maquinasSoportadas = new JComboBox();
        maquinasSoportadas.addItem("Consola 2 jugadores");
        maquinasSoportadas.addItem("Fliper");
        maquinasSoportadas.addItem("Consola individual");
        maquinasSoportadas.addItem("Simulador");
        panelIngreso.add(new JLabel("Tipo de maquina: "));
        panelIngreso.add(maquinasSoportadas);

        /* Area para ingresar la cantidad de maquinas a producir*/
        panelIngreso.add(new JLabel("Cantidad a producir: "));
        JTextField cantProducir = new JTextField();
        panelIngreso.add(cantProducir);

        /* Area para ingresar la fecha solicitada por ventas*/
        panelIngreso.add(new JLabel("Fecha solicitada por ventas: "));
        PanelFechas fechaVentas = new PanelFechas();
        panelIngreso.add(fechaVentas);


        /* Panel que ira en la parte inferior de la ventana. Este area esta destinada
         * a agregar nuevos pedidos al sistema*/
        JPanel panelSouth = new JPanel();
        panelSouth.setLayout(new BorderLayout());
        /* Agrega el panelIngreso al panelSouth*/
        panelSouth.add(panelIngreso, BorderLayout.CENTER);
        /* Agrega el titulo al panelSouth*/
        JLabel titulo = new JLabel("Ingrese los datos para el nuevo pedido");
        titulo.setFont(new Font("Arial", 0, 15));
        panelSouth.add(titulo, BorderLayout.NORTH);
        /*Agrega el boton que carga el nuevo pedido al sistema*/
        JButton nuevoPedido = new JButton("Cargar nuevo pedido");
        //TODO funcionalidad del boton
        nuevoPedido.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String pedido = "PED";
                pedido += nPedido.getText();

                int diaPedido = fechaPedido.getDia();
                int mesPedido = fechaPedido.getMes();
                int yearPedido = fechaPedido.getYear();

                Calendar calendarFechaPedido = new GregorianCalendar(yearPedido, mesPedido, diaPedido);

                String maquina = (String) maquinasSoportadas.getSelectedItem();

                int cantidadProducir = Integer.parseInt(cantProducir.getText());

                int diaVentas = fechaVentas.getDia();
                int mesVentas = fechaVentas.getMes();
                int yearVentas = fechaVentas.getYear();

                Calendar calendarFechaVentas = new GregorianCalendar(yearVentas, mesVentas, diaVentas);


                Controlador.crearNuevoLote(pedido, calendarFechaPedido, maquina, cantidadProducir, calendarFechaVentas);

            }
        });
        panelSouth.add(nuevoPedido, BorderLayout.SOUTH);

        /* Agrega el panelSouth a la ventana del programa*/
        cp.add(panelSouth, BorderLayout.SOUTH);
    }
}
