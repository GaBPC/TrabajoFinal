package visual.auxiliares;

import exceptions.ArgumentoIlegalException;
import exceptions.FaltantesException;
import exceptions.StateException;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JScrollPane;

import javax.swing.JTextArea;

import listas.ListaMateriales;

import listas.ListaMaterialesStock;

import visual.Controlador;

public class DialogoMateriales extends JDialog {
    private Controlador control;

    public DialogoMateriales(Controlador control, Component relativeTo) {
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

    public void initComponents() {
        Container cp = this.getContentPane();

        JPanel jp = new JPanel();
        jp.setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Materiales necesarios para generar el lote");
        titulo.setFont(new Font("Arial", 0, 15));
        jp.add(titulo, BorderLayout.NORTH);

        JTextArea materiales = new JTextArea();
        materiales.setEditable(false);

    try
    {
      this.actualizarMateriales(materiales);
    }
    catch (Exception e)
    {
    }
        jp.add(materiales, BorderLayout.CENTER);
        cp.add(jp, BorderLayout.CENTER);
    }

    public void actualizarMateriales(JTextArea materiales) throws Exception {
        String tipo = this.control.getPedidoActual().getCodigoMaquina();
        ListaMateriales lista;
        try {
            lista = this.control.verificaExistencias(tipo);
            materiales.append(lista.detalles());
        } catch (FaltantesException e) {
            materiales.append(e.getMessage() + "\n");
            materiales.append(e.getFaltantes().detalles());
        }
    }
    
    public void actualizarMatProductos(JTextArea materiales) 
    {
      String tipo = this.control.getPedidoActual().getCodigoMaquina();
      ListaMateriales lista = ListaMaterialesStock.getInstance().getProducto(tipo).getListaMateriales();
      materiales.append(lista.detalles());
    }


}
