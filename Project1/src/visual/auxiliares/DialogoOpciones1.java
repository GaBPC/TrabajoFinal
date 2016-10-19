package visual.auxiliares;

import datos.TipoProducto;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

import java.awt.Font;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import listas.ListaMateriales;
import listas.ListaMaterialesStock;

import visual.Controlador;
import visual.VentanaProduccion;

public class DialogoOpciones1
  extends JDialog
{
  private Controlador control;

  public DialogoOpciones1(Controlador control, Component relativeTo)
  {
    super();
    this.setLocationRelativeTo(relativeTo);
    this.control = control;
    this.setLayout(new BorderLayout());
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.setModal(true);
    this.setMinimumSize(new Dimension(400, 300));
    this.initComponents();
    this.setVisible(true);
  }

  public void initComponents()
  {
    Container cp = this.getContentPane();

    JLabel titulo = new JLabel("Lista de materiales del producto");
    titulo.setFont(new Font("Arial", 0, 15));
    cp.add(titulo, BorderLayout.NORTH);

    JTextArea materiales = new JTextArea();
    materiales.setEditable(false);

    this.actualizarMatProductos(materiales);
    
    JPanel panelBotones = new JPanel(new GridLayout(0,2));
    
    JButton agregarMateriales = new JButton("Agregar material");
    agregarMateriales.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent actionEvent)
      {
        new DialogoAgregar(DialogoOpciones1.this.control,DialogoOpciones1.this.control.getProductoActual());
        DialogoOpciones1.this.actualizarMatProductos(materiales);
      }
    });
    JButton borrarMateriales = new JButton("Borrar material");
    borrarMateriales.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent actionEvent)
      {
        new DialogoBorrar(DialogoOpciones1.this.control,DialogoOpciones1.this.control.getProductoActual());
        DialogoOpciones1.this.actualizarMatProductos(materiales);
      }
    });
    
    panelBotones.add(agregarMateriales);
    panelBotones.add(borrarMateriales);
    cp.add(panelBotones, BorderLayout.SOUTH);
    cp.add(materiales, BorderLayout.CENTER);
  }

  public void actualizarMatProductos(JTextArea materiales)
  {
    materiales.setText("");
    String tipo = this.control
                      .getProductoActual()
                      .getCodigoProducto();
    ListaMateriales lista = ListaMaterialesStock.getInstance()
                                                .getProducto(tipo)
                                                .getListaMateriales();
    materiales.append(lista.detalles());
  }


}
