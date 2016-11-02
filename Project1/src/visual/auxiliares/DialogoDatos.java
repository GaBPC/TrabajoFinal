package visual.auxiliares;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JTextArea;

public class DialogoDatos
  extends JDialog
{
  private String datos;

  public DialogoDatos(String datos)
  {
    super();
    this.setLocationRelativeTo(null);
    this.datos = datos;
    this.setLayout(new BorderLayout());
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.setModal(true);
    this.setMinimumSize(new Dimension(500, 250));
    this.initComponents();
    this.setVisible(true);
  }

  private void initComponents()
  {
    Container cp = this.getContentPane();
    JTextArea datos = new JTextArea(this.datos);
    datos.setEditable(false);
    cp.add(datos, BorderLayout.CENTER);
  }
}
