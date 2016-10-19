package visual.auxiliares;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import java.awt.GridLayout;

import javax.swing.JDialog;

import javax.swing.JLabel;
import javax.swing.JTextField;

import visual.Controlador;

public class DialogoAgregar extends JDialog
{
  private Controlador control;
  
  public DialogoAgregar(Controlador control)
  {
    super();
    this.control = control;
    this.setLayout(new GridLayout(0,2));
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.setModal(true);
    this.setMinimumSize(new Dimension(200, 200));
    this.initComponents();
    this.setVisible(true);
  }
  
  public void initComponents()
  {
    Container cp = this.getContentPane();
    JLabel material = new JLabel("Codigo material");
    JLabel cantidad = new JLabel("Cantidad");
    JTextField codMaterial = new JTextField();
    JTextField cant = new JTextField();
    
    cp.add(material);
    cp.add(codMaterial);
    cp.add(cantidad);
    cp.add(cant);
    
    
    
    
  }
}
