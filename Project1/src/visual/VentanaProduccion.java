package visual;

import datos.Lote;
import datos.Pedido;

import datos.TipoProducto;

import exceptions.StateException;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.util.Iterator;

import java.util.Observable;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import listas.ListaMateriales;
import listas.ListaMaterialesStock;

import visual.auxiliares.DialogoDetalles;
import visual.auxiliares.DialogoMateriales;
import visual.auxiliares.DialogoObservaciones;
import visual.auxiliares.DialogoListaMaterialesProd;
import visual.auxiliares.MyList;
import visual.auxiliares.PanelLista;

/**Clase que se encarga de la parte visual de la ventana de produccion. Extiende de VentanaBase
 */
public class VentanaProduccion
  extends VentanaBase
{
  private DefaultListModel listModelEv;   //DefaultListModel que se utiliza para mostrar en pantalla los pedidos en evaluacion
  private DefaultListModel listModelAc;   //DefaultListModel que se utiliza para mostrar en pantalla los pedidos aceptados

  /**Constructor de la ventana, se le pasa el controlador por parametro, el cual es pasado a la ventana padre
   * pre: control distinto de null
   * @param control
   * post: se crea instancia de la clase
   */
  public VentanaProduccion(Controlador control)
  {
    super(control, "Produccion", JFrame.DISPOSE_ON_CLOSE, new Dimension(700, 700));
    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
  }

  /**Metodo en el cual se establecen todos los componentes de la ventana
   * post: se inicializa la ventana con todos sus componentes graficos
   */
  @Override
  protected void IniciarComponentes()
  {
    Container cp = this.getContentPane();

    /* Instancia el modelo de la lista de pedidos en evaluacion*/
    this.listModelEv = new DefaultListModel();
    /* Crea el panel que contiene todo lo relacionado a la lista de pedidos en evaluacion*/
    PanelLista panelEvaluacion = new PanelLista("Pedidos que estan en evaluacion", this.listModelEv);
    /* Crea un panel donde iran todos los botones relacionados con el panelEvaluacin*/
    JPanel botonesEvaluacion = new JPanel(new GridLayout(0, 3));
    /* Crea un boton que permite cancelar y descartar un pedido*/
    JButton cancelarPedido = new JButton("Cancelar pedido");
    cancelarPedido.addActionListener(new ActionListener()
    {

      @Override
      public void actionPerformed(ActionEvent actionEvent)
      {
        int seleccionado = panelEvaluacion.getLista().getSelectedIndex();
        if (seleccionado != -1)
        {
          VentanaProduccion.this.control.setPedidoActual((Pedido) listModelEv.getElementAt(seleccionado));
          VentanaProduccion.this.control.removePedido();
          VentanaProduccion.this.actualizarListaEv();
          JOptionPane.showMessageDialog(VentanaProduccion.this, "El pedido ha sido cancelado");
        }
        else
          JOptionPane.showMessageDialog(VentanaProduccion.this, "No se ha seleccionado ningun pedido a cancelar");
      }
    });
    botonesEvaluacion.add(cancelarPedido);
    /* Crea un boton que permite agregar una observacion sobre el elemento de la lista seleccionado*/
    JButton verObservaciones = new JButton("Observaciones");
    verObservaciones.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent actionEvent)
      {
        int seleccionado = panelEvaluacion.getLista().getSelectedIndex();
        if (seleccionado != -1)
        {
          VentanaProduccion.this.control.setPedidoActual((Pedido) listModelEv.getElementAt(seleccionado));
          new DialogoObservaciones(VentanaProduccion.this.control);
        }
        else
          JOptionPane.showMessageDialog(VentanaProduccion.this, "No se ha seleccionado ningun pedido a observar");
      }
    });
    botonesEvaluacion.add(verObservaciones);
    /* Crea un boton que permita aceptar un pedido y cambiar su estado a aceptado*/
    JButton aceptarPedido = new JButton("Aceptar pedido");
    aceptarPedido.addActionListener(new ActionListener()
    {

      @Override
      public void actionPerformed(ActionEvent actionEvent)
      {
        int seleccionado = panelEvaluacion.getLista().getSelectedIndex();
        if (seleccionado != -1)
        {
          Pedido ped = (Pedido) listModelEv.getElementAt(seleccionado);
          VentanaProduccion.this.control.setPedidoActual(ped);
          VentanaProduccion.this
            .control.setProductoActual(VentanaProduccion.this.control.getProducto(ped.getCodigoMaquina()));

          new DialogoMateriales(VentanaProduccion.this.control);

          VentanaProduccion.this.actualizarListaAc();
          VentanaProduccion.this.actualizarListaEv();
        }
        else
          JOptionPane.showMessageDialog(VentanaProduccion.this, "No se ha seleccionado ningun pedido a aceptar");
      }
    });
    botonesEvaluacion.add(aceptarPedido);
    /* Agrega los botones al panelEvaluacion*/
    panelEvaluacion.add(botonesEvaluacion, BorderLayout.SOUTH);
    /* Actualiza la lista de lotes en evaluacion por primera vez*/
    this.actualizarListaEv();

    /* Instancia el modelo de la lsita de pedidos aceptados*/
    this.listModelAc = new DefaultListModel();
    /* Crea el panel que contiene todo lo relacionado a la lista de pedidos aceptados*/
    PanelLista panelAceptado = new PanelLista("Pedidos aceptados", this.listModelAc);
    /* Crea un boton que permite generar un lote a partir de un pedido aceptado*/
    JButton generarLote = new JButton("Generar lote");
    generarLote.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent actionEvent)
      {
        int seleccionado = panelAceptado.getLista().getSelectedIndex();
        if (seleccionado != -1)
        {
          Lote lot = (Lote) listModelAc.getElementAt(seleccionado);
          VentanaProduccion.this.control.setLoteActual(lot);
          VentanaProduccion.this.control.removeLote();
          VentanaProduccion.this.actualizarListaAc();
          JOptionPane.showMessageDialog(VentanaProduccion.this, "Ha comenzado a generarse el lote");
        }
        else
          JOptionPane.showMessageDialog(VentanaProduccion.this, "No se ha seleccionado ningun lote a generar");
      }
    });
    /* Agrega el boton de generar lote al panelAceptado*/
    panelAceptado.add(generarLote, BorderLayout.SOUTH);
    /* Actualiza la lista de lotes aceptados por primera vez*/
    this.actualizarListaAc();

    /* Crea el panel donde iran las dos listas para luego agregarlas al ContentPane*/
    JPanel panelListas = new JPanel(new GridLayout(0, 2));
    /* Agrega las listas al panelListas*/
    panelListas.add(panelEvaluacion);
    panelListas.add(panelAceptado);
    /* Agrega el panelLista al ContentPane*/
    cp.add(panelListas, BorderLayout.CENTER);

    /* Crea el menu para trabajar con las recetas de los productos*/
    JMenu recetaProductos = new JMenu("Recetas");
    /* Crea todos los items de cada uno de los productos*/
    JMenuItem recetaFlipper = new JMenuItem("Flipper");
    JMenuItem recetaSimulador = new JMenuItem("Simulador");
    JMenuItem recetaConsolaGrupal = new JMenuItem("Consola grupal");
    JMenuItem recetaConsolaIndividual = new JMenuItem("Consola individual");
    /* Crea un ActionListener que de acuerdo al item seleccionado muestra los materiales del
         * producto correspondiente*/
    ActionListener recetasListener = new ActionListener()
    {

      @Override
      public void actionPerformed(ActionEvent e)
      {
        ListaMaterialesStock aux = ListaMaterialesStock.getInstance();
        String codigoMaterial = null;
        if (e.getSource() == recetaFlipper)
          codigoMaterial = aux.getCodigo(ListaMaterialesStock.FLIPPER);
        else if (e.getSource() == recetaSimulador)
          codigoMaterial = aux.getCodigo(ListaMaterialesStock.SIMULADOR);
        else if (e.getSource() == recetaConsolaGrupal)
          codigoMaterial = aux.getCodigo(ListaMaterialesStock.CONSOLA_GRUPAL);
        else if (e.getSource() == recetaConsolaIndividual)
          codigoMaterial = aux.getCodigo(ListaMaterialesStock.CONSOLA_IND);
        if (codigoMaterial != null)
        {
          TipoProducto tp = ListaMaterialesStock.getInstance().getProducto(codigoMaterial);
          VentanaProduccion.this.control.setProductoActual(tp);
          new DialogoListaMaterialesProd(VentanaProduccion.this.control);
        }
      }
    };
    /* Agrega a todos los items el recetasListener*/
    recetaFlipper.addActionListener(recetasListener);
    recetaSimulador.addActionListener(recetasListener);
    recetaConsolaGrupal.addActionListener(recetasListener);
    recetaConsolaIndividual.addActionListener(recetasListener);
    /* Añade al menu recetaProductos todos los items*/
    recetaProductos.add(recetaFlipper);
    recetaProductos.add(recetaSimulador);
    recetaProductos.add(recetaConsolaGrupal);
    recetaProductos.add(recetaConsolaIndividual);

    /* Crea el menu para ver el stock de la empresa*/
    JMenuItem stock = new JMenuItem("Stock");
    /** En el action listener de aca abajo va lo del dialogo que tenes que hacer*/
    stock.addActionListener(new ActionListener()
    {

      @Override
      public void actionPerformed(ActionEvent actionEvent)
      {
        new DialogoDetalles(VentanaProduccion.this.control);
      }
    });

    /* Crea el supra menu para trabajar con todo lo relacionado a los materiales*/
    JMenu menuMateriales = new JMenu("Materiales");
    menuMateriales.add(recetaProductos);
    menuMateriales.add(stock);
    /* Agrega el menu a la JMenuBar de la ventana*/
    (this.getJMenuBar()).add(menuMateriales);
  }

  /**Metodo que se encarga de actualizar el atributo listModelEv, para que asi se reflejen en pantalla los cambios efectuados
   * post: en pantalla se actualiza la lista de pedidos en evaluacion
   */
  private void actualizarListaEv()
  {
    this.listModelEv.removeAllElements();
    Iterator<Pedido> it = this.control.getPedidosEvaluacion();
    while (it.hasNext())
      this.listModelEv.addElement(it.next());
  }

  /**Metodo que se encarga de actualizar el atributo listModelAc, para que asi se reflejen en pantalla los cambios efectuados
   * post: en pantalla se actualiza la lista de lotes en espera de ser generados
   */
  private void actualizarListaAc()
  {
    this.listModelAc.removeAllElements();
    Iterator<Lote> it = this.control.getLotes();
    while (it.hasNext())
    {
      this.listModelAc.addElement(it.next());
    }
  }

  /**Metodo que se activa cuando se realiza algun cambio en las clases a las cuales observa esta ventana. Se detecta el 
   * elemento que produjo el cambio y se adquiere una explicacion del cambio sufrido
   * pre: los parametros deben ser distintos de null
   * @param observable
   * @param object
   * post: se actualizan las listas o se lanza una excepcion
   */
  @Override
  public void update(Observable observable, Object object)
  {
    assert observable != null : "Observable nulo";
    assert object != null : "Object nulo";
    
    if (this.observados.contains(observable))
    {
      this.actualizarListaAc();
      this.actualizarListaEv();
    }
    else
      throw new IllegalArgumentException("Fatal error");
  }
}
