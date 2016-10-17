package visual;

import datos.Pedido;
import datos.Material;

import exceptions.ArgumentoIlegalException;

import exceptions.StateException;

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

import java.util.Observable;

import javax.swing.*;

import visual.auxiliares.DialogoObservaciones;
import visual.auxiliares.MyList;
import visual.auxiliares.PanelFechas;

public class VentanaVentas extends VentanaBase {

    private DefaultListModel listModelEv;
    private DefaultListModel listModelIn;

    public VentanaVentas(Controlador control) {
        super(control, "Ventas", JFrame.DISPOSE_ON_CLOSE, new Dimension(700, 700));
    }

    @Override
    protected void IniciarComponentes() {
        Container cp = this.getContentPane();

        /* Crea un panel donde se encuentra todo lo relacionado a
         * ver y agregar observaciones a pedidos en etapa de revision*/
        JPanel panelRevision = new JPanel();
        panelRevision.setLayout(new BorderLayout());
        JPanel listas = new JPanel();
        listas.setLayout(new GridLayout(0, 2));
        /*Crea los titulos de cada lista a aparecer*/
        JPanel titulos = new JPanel();
        titulos.setLayout(new GridLayout(0, 2));
        JLabel tit1 = new JLabel("Lista con todos los pedidos que estan iniciados");
        tit1.setFont(new Font("Arial", 0, 15));
        titulos.add(tit1);
        JLabel tit2 = new JLabel("Lista con todos los pedidos que estan en evaluacion");
        tit2.setFont(new Font("Arial", 0, 15));
        titulos.add(tit2);
        /* Crea un modelo donde se agregaran y quitaran los itemas de la lista*/
        this.listModelEv = new DefaultListModel();
        this.listModelIn = new DefaultListModel();
        /* Crea la lista donde se veran todos los lotes que no han sido aun aceptados y sobre
         * los cuales se pueden agregar observaciones*/
        MyList lotesEv = new MyList(listModelEv);
        MyList lotesIn = new MyList(listModelIn);
        /* Agrega un JScrollPane que permite subir y bajar en la lista*/
        JScrollPane scrollLotesEv = new JScrollPane(lotesEv);
        JScrollPane scrollLotesIn = new JScrollPane(lotesIn);
        /* Carga por primera vez los datos de la lista*/
        this.actualizarListaEv(listModelEv);
        this.actualizarListaIn(listModelIn);
        JPanel aux1 = new JPanel(new BorderLayout());
        JPanel aux2 = new JPanel(new BorderLayout());
        /*Agrega las listas al panel de listas*/
        aux1.add(scrollLotesIn, BorderLayout.CENTER);
        aux2.add(scrollLotesEv, BorderLayout.CENTER);

        /* Crea un boton que permite agregar una observacion sobre el elemento de la lista seleccionado*/
        JButton verObservaciones = new JButton("Ver observacion");
        //TODO hacer que se agrege la observacion
        verObservaciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int seleccionado = lotesEv.getSelectedIndex();
                VentanaVentas.this.control.setLoteActual((Pedido) listModelEv.getElementAt(seleccionado));
                new DialogoObservaciones(VentanaVentas.this.control, VentanaVentas.this);
            }
        });
        aux2.add(verObservaciones, BorderLayout.SOUTH);

        /*Crea un boton que permite cambiar el estado de un lote de iniciado a en evaluacion*/
        JButton evaluar = new JButton("Evaluar pedido");
        evaluar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                try {
                    int seleccionado = lotesIn.getSelectedIndex();
                    VentanaVentas.this.control.setLoteActual((Pedido) listModelIn.getElementAt(seleccionado));
                    VentanaVentas.this.control.cambiarAEvaluacion();
                    VentanaVentas.this.actualizarListaIn(listModelIn);
                    VentanaVentas.this.actualizarListaEv(listModelEv);
                } catch (StateException e) {
                    JOptionPane.showMessageDialog(VentanaVentas.this, e.getMessage());
                }

            }
        });
        aux1.add(evaluar, BorderLayout.SOUTH);

        listas.add(aux1);
        listas.add(aux2);
        /* Agrega la lista al panel de revisiones*/
        panelRevision.add(listas, BorderLayout.CENTER);
        /* Agrega los titulos al panel de revisiones*/
        panelRevision.add(titulos, BorderLayout.NORTH);

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

                    /* Obtiene los datos del nuevo pedido de los campos de ingreso*/
                    String pedido = "PED";
                    pedido += nPedido.getText();

                    Calendar calendarFechaPedido =
                        new GregorianCalendar(fechaPedido.getYear(), fechaPedido.getMes(), fechaPedido.getDia() + 1);

                    String maquina = (String) maquinasSoportadas.getSelectedItem();

                    int cantidadProducir = Integer.parseInt(cantProducir.getText());

                    Calendar calendarFechaVentas =
                        new GregorianCalendar(fechaVentas.getYear(), fechaVentas.getMes(), fechaVentas.getDia() + 1);

                    /* Agrega el nuevo lote a la lista mediante el controlador*/
                    VentanaVentas.this.control.crearNuevoLote(pedido, calendarFechaPedido, maquina, cantidadProducir,
                                                              calendarFechaVentas);
                    /* Cada vez que se agrega un nuevo lote al sistema se actualiza la lista
                     * con los datos del lote recien agregado*/
                    VentanaVentas.this.actualizarListaIn(listModelIn);
                    /* Pone el blanco todos los campos de ingreso nuevamente*/
                    nPedido.setText("");
                    cantProducir.setText("");
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

    private void actualizarListaEv(DefaultListModel modelo) {
        modelo.removeAllElements();
        Iterator<Pedido> it = this.control.getPedidosEvaluacion();
        while (it.hasNext())
            modelo.addElement(it.next());
    }

    private void actualizarListaIn(DefaultListModel modelo) {
        modelo.removeAllElements();
        Iterator<Pedido> it = this.control.getPedidosIniciados();
        while (it.hasNext()) {
            modelo.addElement(it.next());
        }
    }

    @Override
    public void update(Observable observable, Object object) {
        if (this.observados.contains(observable)) {
            this.actualizarListaEv(this.listModelEv);
            this.actualizarListaIn(this.listModelIn);
        } else
            throw new IllegalArgumentException("Fatal error");
    }
}
