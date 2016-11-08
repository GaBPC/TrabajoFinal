package visual.auxiliares;

import datos.TipoProducto;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import visual.Controlador;

/**Clase que muestra los detalles de stock de materiales. Extiende de JDialog
 * Invariantes: atributo control distinto de null
 */
public class DialogoDetalles
  extends JDialog
{
  private Controlador control;  //instancia de controlador

  /**Constructor de la clase
   * pre: control distinto de null
   * @param control
   * post: se crea la instancia de la clase o se informa el error
   */
  public DialogoDetalles(Controlador control)
  {
    super();
    assert control != null : "Controlador nulo";
    this.control = control;
    this.setLayout(new BorderLayout());
    this.setLocationRelativeTo(null);
    this.setTitle("Existencias en stock");
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.setModal(true);
    this.setMinimumSize(new Dimension(400, 200));
    this.initComponents();
    this.setVisible(true);
  }

  /**Metodo que inicializa los componentes del dialogo
   */
  public void initComponents()
  {
    Container cp = this.getContentPane();

    JTextArea area = new JTextArea();
    area.setEditable(false);
    area.append(this.control.detallesStock());
    JScrollPane detalles = new JScrollPane(area);

    cp.add(detalles, BorderLayout.CENTER);
  }

  /**Metodo que verifica que los invariantes de clase se cumplan. Si algo falla lanza un AssertError
   */
  private void verificarInvariantes()
  {
    assert this.control != null : "Atributo controlador nulo";
  }
}
