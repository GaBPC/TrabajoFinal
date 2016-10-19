package visual.auxiliares;

import exceptions.ArgumentoIlegalException;
import exceptions.FaltantesException;
import exceptions.StateException;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JScrollPane;

import javax.swing.JTextArea;

import listas.ListaMateriales;

import listas.ListaMaterialesStock;

import visual.Controlador;

public class DialogoMateriales
  extends JDialog
{
  private Controlador control;
  private JButton aceptarLote = null;
  private JPanel panelIngreso = null;

  public DialogoMateriales(Controlador control, Component relativeTo)
  {
    super();
    this.setLocationRelativeTo(relativeTo);
    this.control = control;
    this.setLayout(new BorderLayout());
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.setModal(true);
    this.setMinimumSize(new Dimension(400, 300));
    this.initComponents();
    this.setVisible(true);
  }

  public void initComponents()
  {
    Container cp = this.getContentPane();

    JPanel jp = new JPanel();
    jp.setLayout(new BorderLayout());

    JLabel titulo = new JLabel("Materiales necesarios para generar el lote");
    titulo.setFont(new Font("Arial", 0, 15));
    jp.add(titulo, BorderLayout.NORTH);

    JTextArea materiales = new JTextArea();
    materiales.setEditable(false);
    jp.add(materiales, BorderLayout.CENTER);
    cp.add(jp, BorderLayout.CENTER);

    JPanel panelAceptar = new JPanel(new BorderLayout());
    panelAceptar.setEnabled(false);
    cp.add(panelAceptar, BorderLayout.SOUTH);

    this.panelIngreso = new JPanel(new GridLayout(0, 2));

    panelIngreso.add(new JLabel("Fecha propuesta por produccion: "));
    PanelFechas panelFechas = new PanelFechas();
    this.panelIngreso.setVisible(false);
    this.panelIngreso.add(panelFechas);
    panelAceptar.add(panelIngreso, BorderLayout.CENTER);

    this.aceptarLote = new JButton("Aceptar pedido");
    this.aceptarLote.addActionListener(new ActionListener()
    {

      @Override
      public void actionPerformed(ActionEvent actionEvent)
      {
        Calendar calendarFechaProduccion =
          new GregorianCalendar(panelFechas.getYear(),
                                panelFechas.getMes(), panelFechas.getDia() + 1);
        try
        {
          DialogoMateriales.this.control.cambiarAAceptado(calendarFechaProduccion);
          DialogoMateriales.this.control.generarLote();
          DialogoMateriales.this.dispose();
        }
        catch (StateException e)
        {
          JOptionPane.showMessageDialog(DialogoMateriales.this, e.getMessage());
        }
        catch (ArgumentoIlegalException e)
        {
          JOptionPane.showMessageDialog(DialogoMateriales.this, e.getMessage());
        }

      }
    });
    this.aceptarLote.setEnabled(false);
    panelAceptar.add(aceptarLote, BorderLayout.SOUTH);

    try
    {
      this.actualizarMateriales(materiales);
    }
    catch (Exception e)
    {
    }
  }

  public void actualizarMateriales(JTextArea materiales)
    throws Exception
  {
    String tipo = this.control.getPedidoActual().getCodigoMaquina();
    ListaMateriales lista;
    try
    {
      lista = this.control.verificaExistencias(tipo);
      this.control.actualizarExistencias(this.control.getProductoActual());
      materiales.append(lista.detalles());
      this.aceptarLote.setEnabled(true);
      this.panelIngreso.setVisible(true);
    }
    catch (FaltantesException e)
    {
      materiales.append(e.getMessage() + "\n");
      materiales.append(e.getFaltantes().detalles());
    }
  }
}
