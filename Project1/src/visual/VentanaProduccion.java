package visual;

import datos.Lote;
import datos.Pedido;

import exceptions.StateException;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Iterator;

import java.util.Observable;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import visual.auxiliares.DialogoM;
import visual.auxiliares.DialogoMateriales;
import visual.auxiliares.DialogoObservaciones;
import visual.auxiliares.MyList;

public class VentanaProduccion
  extends VentanaBase
{
  private DefaultListModel listModelEv;
  private DefaultListModel listModelAc;

  public VentanaProduccion(Controlador control)
  {
    super(control, "Produccion", JFrame.DISPOSE_ON_CLOSE, new Dimension(700, 700));
    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
  }

  @Override
  protected void IniciarComponentes()
  {
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
    JLabel tit1 = new JLabel("Lista con todos los pedidos que estan en evaluacion");
    tit1.setFont(new Font("Arial", 0, 15));
    titulos.add(tit1);
    JLabel tit2 = new JLabel("Lista con todos los lotes que estan aceptados");
    tit2.setFont(new Font("Arial", 0, 15));
    titulos.add(tit2);
    /* Crea un modelo donde se agregaran y quitaran los itemas de la lista*/
    this.listModelEv = new DefaultListModel();
    this.listModelAc = new DefaultListModel();
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

    JPanel aux2_botones = new JPanel(new GridLayout(0, 1));
    aux2.add(aux2_botones, BorderLayout.SOUTH);

    /* Carga por primera vez los datos de la lista*/
    this.actualizarListaAc(listModelAc);
    this.actualizarListaEv(listModelEv);

    /* Crea un boton que permite agregar una observacion sobre el elemento de la lista seleccionado*/
    JButton verObservaciones = new JButton("Ver observacion");
    verObservaciones.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent actionEvent)
      {
        int seleccionado = lotesEv.getSelectedIndex();
        VentanaProduccion.this.control.setPedidoActual((Pedido) listModelEv.getElementAt(seleccionado));
        new DialogoObservaciones(VentanaProduccion.this.control);
      }
    });
    aux1_botones.add(verObservaciones);

    JButton aceptarLote = new JButton("Aceptar pedido");
    aceptarLote.addActionListener(new ActionListener()
    {

      @Override
      public void actionPerformed(ActionEvent actionEvent)
      {

        int seleccionado = lotesEv.getSelectedIndex();
        VentanaProduccion.this.control.setPedidoActual((Pedido) listModelEv.getElementAt(seleccionado));

        new DialogoM(VentanaProduccion.this.control, VentanaProduccion.this);

        VentanaProduccion.this.actualizarListaAc(listModelAc);
        VentanaProduccion.this.actualizarListaEv(listModelEv);

      }
    });
    aux1_botones.add(aceptarLote);

    JButton generarLote = new JButton("Generar lote");
    generarLote.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent actionEvent)
      {
        int seleccionado = lotesAc.getSelectedIndex();
        Lote lot = (Lote) listModelAc.getElementAt(seleccionado);
        VentanaProduccion.this.control.setPedidoActual(lot.getPedido());
        new DialogoMateriales(VentanaProduccion.this.control, VentanaProduccion.this);
        //FALTA VENTANA MATERIALES
        
          
      }
    });
    aux2_botones.add(generarLote);
    
    listas.add(aux1);
    listas.add(aux2);
    /* Agrega la lista al panel de revisiones*/
    panelListas.add(listas, BorderLayout.CENTER);
    /* Agrega los titulos al panel de revisiones*/
    panelListas.add(titulos, BorderLayout.NORTH);

    cp.add(panelListas, BorderLayout.CENTER);
  }

  private void actualizarListaEv(DefaultListModel modelo)
  {
    modelo.removeAllElements();
    Iterator<Pedido> it = this.control.getPedidosEvaluacion();
    while (it.hasNext())
      modelo.addElement(it.next());
  }

  private void actualizarListaAc(DefaultListModel modelo)
  {
    modelo.removeAllElements();
    Iterator<Lote> it = this.control.getLotes();
    while (it.hasNext())
    {
      modelo.addElement(it.next());
    }
  }

  @Override
  public void update(Observable observable, Object object)
  {
    if (this.observados.contains(observable))
    {
      this.actualizarListaAc(this.listModelAc);
      this.actualizarListaEv(this.listModelEv);
    }
    else
      throw new IllegalArgumentException("Fatal error");
  }
}
