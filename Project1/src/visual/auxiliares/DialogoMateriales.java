package visual.auxiliares;

import exceptions.FaltantesException;
import exceptions.StateException;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JDialog;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JTextArea;

import listas.ListaMateriales;

import visual.Controlador;
import visual.VentanaProduccion;

/**Dialogo que se encarga de mostrar los materiales de los lotes o pedidos. Extiende de la clase JDialog
 * Invariantes: atributos distintos de null
 */
public class DialogoMateriales
  extends JDialog
{
  private Controlador control; //instancia del controlador
  private JButton aceptarLote = null; //boton de aceptar lote
  private JPanel panelIngreso = null; //panel de ingreso de los datos para aceptar el pedido

  /**Constructor de la clase
   * pre: control distinto de null
   * @param control
   * post: se crea la instancia de la clase o se informa el error
   */
  public DialogoMateriales(Controlador control)
  {
    super();
    assert control != null: "Controlador nulo";

    this.setLocationRelativeTo(null);
    this.control = control;
    this.setLayout(new BorderLayout());
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.setModal(true);
    this.setTitle("Materiales necesarios para aceptar el pedido");
    this.setMinimumSize(new Dimension(400, 300));
    this.initComponents();
    this.setVisible(true);

    this.verificarInvariantes();
  }

  /**Metodo en el cual se generan los componentes del dialogo
   * post: los componentes inicializados
   */
  public void initComponents()
  {
    Container cp = this.getContentPane();

    JPanel jp = new JPanel();
    jp.setLayout(new BorderLayout());

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
          new GregorianCalendar(panelFechas.getYear(), panelFechas.getMes(), panelFechas.getDia() + 1);
        if (calendarFechaProduccion == null)
          JOptionPane.showMessageDialog(DialogoMateriales.this, "Fecha mal guardada");
        else
        {
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
          catch (Exception e)
          {
            JOptionPane.showMessageDialog(DialogoMateriales.this, e.getMessage());
          }
        }
      }
    });
    this.aceptarLote.setEnabled(false);
    panelAceptar.add(aceptarLote, BorderLayout.SOUTH);

    this.actualizarMateriales(materiales);

    this.verificarInvariantes();
  }

  /**Metodo que muestra la lista de materiales a utilizar para aceptar el pedido o los faltantes
   * @param materiales
   * post: se actualiza la lista o se lanza una excepcion
   * @throws Exception
   */
  public void actualizarMateriales(JTextArea materiales)

  {
    try
    {
      String tipo = this.control
                        .getPedidoActual()
                        .getCodigoMaquina();
      ListaMateriales lista = this.control.verificaExistencias(tipo);
      this.control.actualizarExistencias(this.control.getProductoActual());
      materiales.append(lista.detalles());
      this.aceptarLote.setEnabled(true);
      this.panelIngreso.setVisible(true);
    }
    catch (FaltantesException e)
    {
      materiales.append(e.getMessage() + "\n\n");
      ListaMateriales listaFaltantes = e.getFaltantes();
      if (listaFaltantes != null)
        materiales.append(e.getFaltantes().detalles());
      else
        JOptionPane.showMessageDialog(DialogoMateriales.this, "Lista de faltantes nula");
    }
    catch (Exception e)
    {
      JOptionPane.showMessageDialog(DialogoMateriales.this, e.getMessage());
    }
    this.verificarInvariantes();
  }

  /**Metodo que verifica que los invariantes de clase se cumplan. Si algo falla lanza un AssertError
   */
  private void verificarInvariantes()
  {
    assert this.control != null: "Atributo controlador nulo";
    assert this.aceptarLote != null: "Atributo boton aceptar nulo";
    assert this.panelIngreso != null: "Atributo panel ingreso nulo";
  }
}
