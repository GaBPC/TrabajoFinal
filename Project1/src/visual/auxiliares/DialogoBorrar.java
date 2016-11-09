package visual.auxiliares;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import visual.Controlador;

/**Dialogo desde el cual se puede borrar un material de la lista de materiales de un producto. Extiende de JDialog
 * Invariante: atributo control distinto de null
 */
public class DialogoBorrar
  extends JDialog
{
  private Controlador control;  //instancia de controlador

  /**Constructor de la clase
   * pre: control distinto de null
   * @param control
   * post: se crea instancia de la clase o se indica el error
   */
  public DialogoBorrar(Controlador control)
  {
    super();
    assert control != null : "Controlador nulo";
    
    this.control = control;
    this.setLayout(new BorderLayout());
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.setModal(true);
    this.setMinimumSize(new Dimension(300, 100));
    this.initComponents();
    this.setVisible(true);
    
    this.verificarInvariantes();
  }

  /**Metodo que inicializa todos los componentes del dialogo
   */
  public void initComponents()
  {
    Container cp = this.getContentPane();
    JPanel panel = new JPanel(new GridLayout(0, 2));
    JLabel material = new JLabel("Codigo material");
    JTextField codMaterial = new JTextField();

    panel.add(material);
    panel.add(codMaterial);

    JButton agregar = new JButton("Borrar");
    agregar.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent actionEvent)
      {
        try
        {
          String codigo = "MAT";
          codigo += codMaterial.getText();
          DialogoBorrar.this.control.borrarMaterial(codigo);
        }
        catch (Exception e)
        {
          JOptionPane.showMessageDialog(DialogoBorrar.this, e.getMessage());
        }
        DialogoBorrar.this.dispose();
      }
    });

    cp.add(panel, BorderLayout.CENTER);
    cp.add(agregar, BorderLayout.SOUTH);
    
    this.verificarInvariantes();

  }

  /**Metodo que verifica que los invariantes de clase se cumplan. Si algo falla lanza un AssertError
   */
  private void verificarInvariantes()
  {
    assert this.control != null : "Atributo controlador invalido";
  }
}
