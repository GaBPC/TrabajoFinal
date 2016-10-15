package visual;

import datos.Lote;
import datos.Material;

import exceptions.ArgumentoIlegalException;

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

import java.util.Iterator;

import javax.swing.*;

import visual.auxiliares.DialogoObservaciones;
import visual.auxiliares.PanelFechas;

public class VentanaVentas extends VentanaBase {

    private String legajo;
    
    public VentanaVentas(String legajoEmpleado) {
        super("Ventas", JFrame.EXIT_ON_CLOSE, new Dimension(500, 500));
        this.legajo = legajoEmpleado;
    }

    @Override
    protected void IniciarComponentes() {
        Container cp = this.getContentPane();

        /* Crea un panel donde se encuentra todo lo relacionado a
         * ver y agregar observaciones a pedidos en etapa de revision*/
        JPanel panelRevision = new JPanel();
        panelRevision.setLayout(new BorderLayout());
        /* Crea un modelo donde se agregaran y quitaran los itemas de la lista*/
        DefaultListModel listModel = new DefaultListModel();
        /* Crea la lista donde se veran todos los lotes que no han sido aun aceptados y sobre
         * los cuales se pueden agregar observaciones*/
        JList lotes = new JList(listModel);
        /* Agrega un JScrollPane que permite subir y bajar en la lista*/
        JScrollPane scrollLotes = new JScrollPane(lotes);
        /* Carga por primera vez los datos de la lista*/
        this.actualizarLista(listModel);
        /* Agrega la lista al panel de revisiones*/
        panelRevision.add(scrollLotes, BorderLayout.CENTER);

        /* Crea un boton que permite agregar una observacion sobre el elemento de la lista seleccionado*/
        JButton verObservaciones = new JButton("Ver observacion");
        //TODO hacer que se agrege la observacion
        verObservaciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int seleccionado = lotes.getSelectedIndex();
                Lote aux = (Lote) listModel.getElementAt(seleccionado);
                System.out.println(aux);
                new DialogoObservaciones(aux, VentanaVentas.this.legajo);
            }
        });
        panelRevision.add(verObservaciones, BorderLayout.SOUTH);

        /* Agrega un titulo al panel de revisiones*/
        JLabel tituloRevision = new JLabel("Lista con todos los lotes que estan en revision");
        tituloRevision.setFont(new Font("Arial", 0, 15));
        panelRevision.add(tituloRevision, BorderLayout.NORTH);

        cp.add(panelRevision, BorderLayout.CENTER);

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
                try {
                    String pedido = "PED";
                    pedido += nPedido.getText();

                    Calendar calendarFechaPedido =
                        new GregorianCalendar(fechaPedido.getYear(), fechaPedido.getMes(), fechaPedido.getDia() + 1);

                    String maquina = (String) maquinasSoportadas.getSelectedItem();

                    int cantidadProducir = Integer.parseInt(cantProducir.getText());

                    Calendar calendarFechaVentas =
                        new GregorianCalendar(fechaVentas.getYear(), fechaVentas.getMes(), fechaVentas.getDia() + 1);

                    Controlador.crearNuevoLote(pedido, calendarFechaPedido, maquina, cantidadProducir,
                                               calendarFechaVentas);
                    
                    /* Cada vez que se agrega un nuevo lote al sistema se actualiza la lista
                     * con los datos del lote recien agregado*/
                    VentanaVentas.this.actualizarLista(listModel);
                } catch (ArgumentoIlegalException e) {
                    JOptionPane.showMessageDialog(VentanaVentas.this, e.getMessage());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(VentanaVentas.this, "Cantidad a producir solo puede ser un numero");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(VentanaVentas.this, "Falta completar algun campo");
                }
            }
        });
        panelSouth.add(nuevoPedido, BorderLayout.SOUTH);

        /* Agrega el panelSouth a la ventana del programa*/
        cp.add(panelSouth, BorderLayout.SOUTH);
    }

    private void actualizarLista(DefaultListModel modelo) {
        modelo.removeAllElements();
        Iterator it = Controlador.getLotesNoAceptados();
        while (it.hasNext())
            modelo.addElement(it.next());
    }
}
