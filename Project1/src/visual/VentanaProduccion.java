package visual;

import datos.Lote;

import exceptions.StateException;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import visual.auxiliares.DialogoAceptacion;
import visual.auxiliares.DialogoObservaciones;
import visual.auxiliares.MyList;

public class VentanaProduccion extends VentanaBase {
    public VentanaProduccion(Controlador control) {
        super(control, "Produccion", JFrame.DISPOSE_ON_CLOSE, new Dimension(700, 700));
    }

    @Override
    protected void IniciarComponentes() {
        Container cp = this.getContentPane();

        /* Crea un panel donde se encuentra todo lo relacionado a
         * ver y agregar observaciones a pedidos en etapa de revision*/
        JPanel panelListas = new JPanel();
        panelListas.setLayout(new BorderLayout());
        JPanel listas = new JPanel();
        listas.setLayout(new GridLayout(0, 2));
        /*Crea los titulos de cada lista a aparecer*/
        JPanel titulos = new JPanel();
        titulos.setLayout(new GridLayout(0, 2));
        JLabel tit1 = new JLabel("Lista con todos los lotes que estan en evaluacion");
        tit1.setFont(new Font("Arial", 0, 15));
        titulos.add(tit1);
        JLabel tit2 = new JLabel("Lista con todos los lotes que estan aceptados");
        tit2.setFont(new Font("Arial", 0, 15));
        titulos.add(tit2);
        /* Crea un modelo donde se agregaran y quitaran los itemas de la lista*/
        DefaultListModel listModelEv = new DefaultListModel();
        DefaultListModel listModelAc = new DefaultListModel();
        /* Crea la lista donde se veran todos los lotes que no han sido aun aceptados y sobre
         * los cuales se pueden agregar observaciones*/
        MyList lotesEv = new MyList(listModelEv);
        MyList lotesAc = new MyList(listModelAc);
        /* Agrega un JScrollPane que permite subir y bajar en la lista*/
        JScrollPane scrollLotesEv = new JScrollPane(lotesEv);
        JScrollPane scrollLotesAc = new JScrollPane(lotesAc);
        
        
        JPanel aux1 = new JPanel(new BorderLayout());
        JPanel aux2 = new JPanel(new BorderLayout());
        /*Agrega las listas al panel de listas*/
        aux1.add(scrollLotesEv, BorderLayout.CENTER);
        aux2.add(scrollLotesAc, BorderLayout.CENTER);


        JPanel aux1_botones = new JPanel(new GridLayout(0, 1));
        aux1.add(aux1_botones, BorderLayout.SOUTH);
        
        /* Carga por primera vez los datos de la lista*/
        this.actualizarListaAc(listModelAc);
        this.actualizarListaEv(listModelEv);

        /* Crea un boton que permite agregar una observacion sobre el elemento de la lista seleccionado*/
        JButton verObservaciones = new JButton("Ver observacion");
        verObservaciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int seleccionado = lotesEv.getSelectedIndex();
                VentanaProduccion.this.control.setLoteActual((Lote) listModelEv.getElementAt(seleccionado));
                new DialogoObservaciones(VentanaProduccion.this.control, VentanaProduccion.this);
            }
        });
        aux1_botones.add(verObservaciones);

        JButton aceptarLote = new JButton("Aceptar lote");
        aceptarLote.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                int seleccionado = lotesEv.getSelectedIndex();
                VentanaProduccion.this.control.setLoteActual((Lote) listModelEv.getElementAt(seleccionado));

                new DialogoAceptacion(VentanaProduccion.this.control, VentanaProduccion.this);

                VentanaProduccion.this.actualizarListaAc(listModelAc);
                VentanaProduccion.this.actualizarListaEv(listModelEv);

            }
        });
        aux1_botones.add(aceptarLote);

        listas.add(aux1);
        listas.add(aux2);
        /* Agrega la lista al panel de revisiones*/
        panelListas.add(listas, BorderLayout.CENTER);
        /* Agrega los titulos al panel de revisiones*/
        panelListas.add(titulos, BorderLayout.NORTH);

        cp.add(panelListas, BorderLayout.CENTER);
    }

    private void actualizarListaEv(DefaultListModel modelo) {
        modelo.removeAllElements();
        Iterator<Lote> it = this.control.getLotesEvaluacion();
        while (it.hasNext())
            modelo.addElement(it.next());
    }

    private void actualizarListaAc(DefaultListModel modelo) {
        modelo.removeAllElements();
        Iterator<Lote> it = this.control.getLotesAceptados();
        while (it.hasNext()) {
            modelo.addElement(it.next());
        }
    }
}
