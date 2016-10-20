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

public class DialogoDetalles extends JDialog
{
  private Controlador control;
  
  public DialogoDetalles(Controlador control)
  {
    super();
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
  
  public void initComponents()
  {
    Container cp = this.getContentPane();
    
    JTextArea area = new JTextArea();
    area.setEditable(false);
    area.append(this.control.detallesStock());
    JScrollPane detalles = new JScrollPane(area);
    
    cp.add(detalles,BorderLayout.CENTER);
  }
}
