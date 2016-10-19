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

public class DialogoAgregar extends JDialog
{
  private Controlador control;
  
  public DialogoAgregar(Controlador control,TipoProducto producto)
  {
    super();
    this.control = control;
    this.setLayout(new BorderLayout());
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.setModal(true);
    this.setMinimumSize(new Dimension(200, 200));
    this.initComponents(producto);
    this.setVisible(true);
  }
  
  public void initComponents(TipoProducto producto)
  {
    Container cp = this.getContentPane();
    JPanel panel = new JPanel(new GridLayout(0,2));
    JLabel material = new JLabel("Codigo material");
    JLabel cantidad = new JLabel("Cantidad");
    JLabel descripcion = new JLabel("Descripcion");
    JTextField codMaterial = new JTextField();
    JTextField descMaterial = new JTextField();
    JTextField cant = new JTextField();
    
    panel.add(material);
    panel.add(codMaterial);
    panel.add(descripcion);
    panel.add(descMaterial);
    panel.add(cantidad);
    panel.add(cant);
    
    JButton agregar = new JButton("agregar");
    agregar.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent actionEvent)
      {
        String codigo = "MAT";
        codigo += codMaterial.getText();
        String descripcion = descMaterial.getText();
        float cantidad = Float.parseFloat(cant.getText());
        producto.getListaMateriales().agregarMaterial(codigo, descripcion, cantidad);
        DialogoAgregar.this.dispose();
      }
    });
    
    cp.add(panel,BorderLayout.CENTER);
    cp.add(agregar,BorderLayout.SOUTH);
    
    
  }
}
