package visual.auxiliares;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JTextArea;

/**Clase que muestra todos los datos respectivos a cierto pedido o lote. Extiende de JDialog
 * Invariante: datos distinto de null
 */
public class DialogoDatos
  extends JDialog
{
  private String datos;    //datos a mostar del pedido o lote

  /**Constructor de la clase
   * pre: datos distinto de null
   * @param datos
   * post: se crea la instancia de la clase o se informa el error
   */
  public DialogoDatos(String datos)
  {
    super();
    assert datos != null : "Datos nulo";
    this.setLocationRelativeTo(null);
    this.datos = datos;
    this.setLayout(new BorderLayout());
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.setModal(true);
    this.setMinimumSize(new Dimension(500, 250));
    this.initComponents();
    this.setVisible(true);
  }

  /**Metodo que inicializa los componentes del dialogo
   */
  private void initComponents()
  {
    Container cp = this.getContentPane();
    JTextArea datos = new JTextArea(this.datos);
    datos.setEditable(false);
    cp.add(datos, BorderLayout.CENTER);
  }

  /**Metodo que verifica que los invariantes de clase se cumplan. Si algo falla lanza un AssertError
   */
  private void verificarInvariantes()
  {
    assert this.datos != null : "Atributo datos nulo";
  }
}
