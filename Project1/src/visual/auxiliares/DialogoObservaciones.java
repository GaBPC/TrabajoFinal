package visual.auxiliares;

import datos.Observacion;

import exceptions.ArgumentoIlegalException;

import java.awt.BorderLayout;

import java.awt.Component;
import java.awt.Container;


import java.awt.Dimension;

import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.GregorianCalendar;

import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import visual.Controlador;

/**Dialogo desde el cual se observan las observaciones de los pedidos. Extiende de JDialog
 * Invariantes: atributo control distinto de null
 */
public class DialogoObservaciones
  extends JDialog
{

  private Controlador control;   //instancia de la clase controlador

  /**Constructor de la clase, en el cual se crea la instancia de la clase y todos sus componentes
   * pre: control distinto de null
   * @param control
   * post: se crea la instancia de la clase o se informa el error
   */
  public DialogoObservaciones(Controlador control)
  {
    super();
    assert control != null : "Controlador invalido";
    
    this.setLocationRelativeTo(null);
    this.control = control;
    this.setTitle("Observaciones");
    this.setLayout(new BorderLayout());
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.setModal(true);
    this.setMinimumSize(new Dimension(400, 300));
    this.initComponents();
    this.setVisible(true);
    
    this.verificarInvariantes();
  }

  /**Metodo que inicializa todas las componentes del dialogo
   * post: se generan todos los componentes
   */
  private void initComponents()
  {
    Container cp = this.getContentPane();

    /* Crea un modelo donde se agregaran y quitaran los itemas de la lista*/
    DefaultListModel listModel = new DefaultListModel();
    /* JList donde se veran todas las observaciones que ya tiene el lote*/
    JList observaciones = new JList(listModel);
    /* Agrega un JScrollPane que permite subir y bajar en la lista*/
    JScrollPane scrollObservaciones = new JScrollPane(observaciones);
    /* Actualiza la lista por primera vez*/
    this.actualizarLista(listModel);
    /* Agrega el scroll pane al dialogo*/
    cp.add(scrollObservaciones, BorderLayout.CENTER);

    /* Panel donde iran todo lo referido a agregar nuevas observaciones
         * a un lote*/
    JPanel panelSouth = new JPanel();
    panelSouth.setLayout(new GridLayout(0, 2));

    panelSouth.add(new JLabel("Tema: "));
    JTextField tema = new JTextField();
    panelSouth.add(tema);

    panelSouth.add(new JLabel("Observacion: "));
    JTextField observacion = new JTextField();
    panelSouth.add(observacion);

    /* Boton que añade la nueva observacion al lote*/
    JButton nuevoIngreso = new JButton("+");
    nuevoIngreso.addActionListener(new ActionListener()
    {

      @Override
      public void actionPerformed(ActionEvent actionEvent)
      {
        String temaIngresado = tema.getText();
        String observacionIngresada = observacion.getText();
        Observacion nueva;
        try
        {
          nueva =
            new Observacion(temaIngresado, GregorianCalendar.getInstance(), DialogoObservaciones.this.control
                                                                                                           .getEmpeladoActual()
                                                                                                           .getLegajo(),
                            observacionIngresada);
          DialogoObservaciones.this.control
                                   .getPedidoActual()
                                   .agregarObservacion(nueva);
          DialogoObservaciones.this.actualizarLista(listModel);
        }
        catch (ArgumentoIlegalException e)
        {
          JOptionPane.showMessageDialog(DialogoObservaciones.this, e.getMessage());
        }
        catch (Exception e)
        {
          JOptionPane.showMessageDialog(DialogoObservaciones.this, e.getMessage());
        }
      }
    });
    //TODO implementar la logica de añadir
    /* Se añaden todos los componentes al panel*/
    panelSouth.add(new JLabel(""));
    panelSouth.add(nuevoIngreso);
    /* Se añade el panel al contenedor del dialogo*/
    cp.add(panelSouth, BorderLayout.SOUTH);
    
    this.verificarInvariantes();
  }

  /**Metodo que actualiza la lista en la que se encuentran las observaciones
   * pre: modelo distinto de null
   * @param modelo
   * post: se actualiza la lista que se muestra por pantalla
   */
  private void actualizarLista(DefaultListModel modelo)
  {
    assert modelo != null : "Modelo nulo";
    
    modelo.removeAllElements();

    Iterator<Observacion> it = this.control
                                   .getPedidoActual()
                                   .getListaObservaciones()
                                   .iterator();
    while (it.hasNext())
      modelo.addElement(it.next().toString());
  }

  /**Metodo que verifica que los invariantes de clase se cumplan. Si algo falla lanza un AssertError
   */
  private void verificarInvariantes()
  {
    assert this.control != null : "Atributo controlador invalido";
  }
}
