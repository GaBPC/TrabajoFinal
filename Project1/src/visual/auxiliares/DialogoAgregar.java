package visual.auxiliares;

import datos.TipoProducto;

import exceptions.ArgumentoIlegalException;
import exceptions.LengthException;

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
import visual.VentanaLogin;

public class DialogoAgregar
  extends JDialog
{
  private Controlador control;

  public DialogoAgregar(Controlador control)
  {
    super();
    this.control = control;
    this.setLayout(new BorderLayout());
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.setModal(true);
    this.setMinimumSize(new Dimension(200, 150));
    this.initComponents();
    this.setVisible(true);
  }

  public void initComponents()
  {
    Container cp = this.getContentPane();
    JPanel panel = new JPanel(new GridLayout(0, 2));
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

    TipoProducto prod = this.control.getProductoActual();
    JButton agregar = new JButton("Agregar");
    agregar.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent actionEvent)
      {
        System.out.println(codMaterial.getText());

        if (codMaterial.getText().length() != 0 && descMaterial.getText().length() != 0 && cant.getText().length() != 0)
        {
          String codigo = "MAT";
          codigo += codMaterial.getText();
          String descripcion = descMaterial.getText();
          float cantidad = Float.parseFloat(cant.getText());
          try
          {
            prod.getListaMateriales().agregarMaterial(codigo, descripcion, cantidad);
          }
          catch (ArgumentoIlegalException | LengthException e)
          {
            JOptionPane.showMessageDialog(DialogoAgregar.this, e.getMessage());
          }
          DialogoAgregar.this.dispose();
        }
        else
          JOptionPane.showMessageDialog(DialogoAgregar.this, "Campos vacios");
      }
    });

    cp.add(panel, BorderLayout.CENTER);
    cp.add(agregar, BorderLayout.SOUTH);


  }
}
