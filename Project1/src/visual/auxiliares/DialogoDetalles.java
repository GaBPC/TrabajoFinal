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
import javax.swing.JTextField;

import visual.Controlador;

public class DialogoDetalles extends JDialog
{
  private Controlador control;
  
  public DialogoDetalles(Controlador control,TipoProducto producto)
  {
    super();
    this.control = control;
    this.setLayout(new BorderLayout());
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.setModal(true);
    this.setMinimumSize(new Dimension(300, 100));
    this.initComponents(producto);
    this.setVisible(true);
  }
  
  public void initComponents(TipoProducto producto)
  {
    Container cp = this.getContentPane();
    JPanel panel = new JPanel(new GridLayout(0,2));
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
        String codigo = "MAT";
        codigo += codMaterial.getText();
        producto.getListaMateriales().borrarMaterial(codigo);
        //DialogoBorrar.this.dispose();
      }
    });
    
    cp.add(panel,BorderLayout.CENTER);
    cp.add(agregar,BorderLayout.SOUTH);
       
    
  }
}
