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

import listas.ListaMaterialesStock;

import visual.auxiliares.DialogoObservaciones;
import visual.auxiliares.MyList;
import visual.auxiliares.PanelFechas;
import visual.auxiliares.PanelLista;

public class VentanaVentas
  extends VentanaBase
{

  private DefaultListModel listModelEv;
  private DefaultListModel listModelIn;

  public VentanaVentas(Controlador control)
  {
    super(control, "Ventas", JFrame.DISPOSE_ON_CLOSE, new Dimension(700, 700));
    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
  }

  @Override
  protected void IniciarComponentes()
  {
    Container cp = this.getContentPane();

    /* Agrega el panel desde donde se ingresaran los nuevos pedidos*/
    cp.add(this.creaPanelIngreso(), BorderLayout.SOUTH);

    /* Instancia el modelo de la lista de lotes iniciados*/
    this.listModelIn = new DefaultListModel();
    /* Crea el panel que contiene todo lo relacionado a la lista de lotes iniciados*/
    PanelLista panelIniciados = new PanelLista("Pedidos que estan iniciados", this.listModelIn);
    /* Crea un boton que permite cambiar de estado iniciado a estado evaluacion*/
    JButton evaluarPedido = new JButton("Evaluar pedido");
    evaluarPedido.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent actionEvent)
      {

        try
        {
          int seleccionado = panelIniciados.getLista().getSelectedIndex();
          if (seleccionado != -1)
          {
            VentanaVentas.this.control.setPedidoActual((Pedido) listModelIn.getElementAt(seleccionado));
            VentanaVentas.this.control.cambiarAEvaluacion();
            VentanaVentas.this.actualizarListaIn(listModelIn);
            VentanaVentas.this.actualizarListaEv(listModelEv);
          }
          else
            JOptionPane.showMessageDialog(VentanaVentas.this, "No se ha seleccionado ningun pedido para evaluar");
        }
        catch (StateException e)
        {
          JOptionPane.showMessageDialog(VentanaVentas.this, e.getMessage());
        }

      }
    });
    panelIniciados.add(evaluarPedido, BorderLayout.SOUTH);
    /* Actualiza la lista de lotes iniciados por primera vez*/
    this.actualizarListaIn(this.listModelIn);

    /* Instancia el modelo de la lista de lotes en evaluacion*/
    this.listModelEv = new DefaultListModel();
    /* Crea el panel que contiene todo lo relacionado a la lista de lotes en evaluacion*/
    PanelLista panelEvaluacion = new PanelLista("Pedidos que estan en evaluacion", this.listModelEv);
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
          VentanaVentas.this.control.setPedidoActual((Pedido) listModelEv.getElementAt(seleccionado));
          new DialogoObservaciones(VentanaVentas.this.control);
        }
        else
          JOptionPane.showMessageDialog(VentanaVentas.this, "No se ha seleccionado ningun pedido a observar");
      }
    });
    panelEvaluacion.add(verObservaciones, BorderLayout.SOUTH);
    /* Actualiza la lista de lotes en evaluacion por primera vez*/
    this.actualizarListaEv(this.listModelEv);

    /* Crea un panel donde iran las dos listas para luego agregarlas al ContentPane*/
    JPanel panelListas = new JPanel(new GridLayout(0, 2));
    /* Agrega las dos listas al panelListas*/
    panelListas.add(panelIniciados);
    panelListas.add(panelEvaluacion);
    /* Agrega el panel al ContentPane*/
    cp.add(panelListas, BorderLayout.CENTER);
  }

  private JPanel creaPanelIngreso()
  {
    /* Panel para la creacion de nuevos pedidos desde cero*/
    JPanel panelCreacion = new JPanel();
    panelCreacion.setLayout(new GridLayout(0, 2));
    /* Area para ingresar el tipo de maquina*/
    JComboBox maquinasSoportadas = new JComboBox();
    maquinasSoportadas.addItem(ListaMaterialesStock.CONSOLA_GRUPAL);
    maquinasSoportadas.addItem(ListaMaterialesStock.FLIPPER);
    maquinasSoportadas.addItem(ListaMaterialesStock.CONSOLA_IND);
    maquinasSoportadas.addItem(ListaMaterialesStock.SIMULADOR);
    panelCreacion.add(new JLabel("Tipo de maquina: "));
    panelCreacion.add(maquinasSoportadas);
    /* Area para ingresar la cantidad de maquinas a producir*/
    panelCreacion.add(new JLabel("Cantidad a producir: "));
    JTextField cantProducir = new JTextField();
    panelCreacion.add(cantProducir);
    /* Area para ingresar la fecha solicitada por ventas*/
    panelCreacion.add(new JLabel("Fecha solicitada por ventas: "));
    PanelFechas fechaVentas = new PanelFechas();
    panelCreacion.add(fechaVentas);
    /* Crea el titulo para la seccion de creacion*/
    JLabel titulo = new JLabel("Ingrese los datos para el nuevo pedido");
    titulo.setFont(new Font("Arial", 0, 15));
    /* Crea un boton que se encarga de llamar a los metodos necesarios para la
         * creacion de un nuevo pedido*/
    JButton nuevoPedido = new JButton("Cargar nuevo pedido");
    nuevoPedido.addActionListener(new ActionListener()
    {

      @Override
      public void actionPerformed(ActionEvent actionEvent)
      {
        try
        {
          Calendar calendarFechaPedido = GregorianCalendar.getInstance();
          String maquina = (String) maquinasSoportadas.getSelectedItem();

          int cantidadProducir = Integer.parseInt(cantProducir.getText());

          Calendar calendarFechaVentas =
            new GregorianCalendar(fechaVentas.getYear(), fechaVentas.getMes(), fechaVentas.getDia() + 1);

          /* Agrega el nuevo lote a la lista mediante el controlador*/
          VentanaVentas.this.control.crearNuevoPedido(calendarFechaPedido, maquina, cantidadProducir,
                                                      calendarFechaVentas);
          /* Cada vez que se agrega un nuevo lote al sistema se actualiza la lista
                     * con los datos del lote recien agregado*/
          VentanaVentas.this.actualizarListaIn(listModelIn);
          /* Pone el blanco todos los campos de ingreso nuevamente*/
          cantProducir.setText("");
        }
        catch (ArgumentoIlegalException e)
        {
          JOptionPane.showMessageDialog(VentanaVentas.this, e.getMessage());
        }
        catch (NumberFormatException e)
        {
          JOptionPane.showMessageDialog(VentanaVentas.this, "Cantidad a producir solo puede ser un numero");
        }
        catch (Exception e)
        {
          JOptionPane.showMessageDialog(VentanaVentas.this, "Falta completar algun campo");
        }
      }
    });
    /* Panel que contendra todo lo definido anteriormente, para que sea una sola unidad
         * en la ventana*/
    JPanel panelIntegral = new JPanel();
    panelIntegral.setLayout(new BorderLayout());
    /* Agrega el panelIntegral al panelSouth*/
    panelIntegral.add(panelCreacion, BorderLayout.CENTER);
    /* Agrega el titulo al panelIntegral*/
    panelIntegral.add(titulo, BorderLayout.NORTH);
    /*Agrega el boton que carga el nuevo pedido al sistema al panelIntegral*/
    panelIntegral.add(nuevoPedido, BorderLayout.SOUTH);
    return panelIntegral;
  }

  private void actualizarListaEv(DefaultListModel modelo)
  {
    modelo.removeAllElements();
    Iterator<Pedido> it = this.control.getPedidosEvaluacion();
    while (it.hasNext())
      modelo.addElement(it.next());
  }

  private void actualizarListaIn(DefaultListModel modelo)
  {
    modelo.removeAllElements();
    Iterator<Pedido> it = this.control.getPedidosIniciados();
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
      this.actualizarListaEv(this.listModelEv);
      this.actualizarListaIn(this.listModelIn);
    }
    else
      throw new IllegalArgumentException("Fatal error");
  }
}
