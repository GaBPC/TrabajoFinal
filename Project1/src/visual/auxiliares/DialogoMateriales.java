package visual.auxiliares;

import exceptions.ArgumentoIlegalException;
import exceptions.StateException;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JDialog;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import visual.Controlador;

public class DialogoMateriales
  extends JDialog
{
  private Controlador control;

  public DialogoMateriales(Controlador control, Component relativeTo)
  {
    super();
    this.setLocationRelativeTo(relativeTo);
    this.control = control;
    this.setLayout(new BorderLayout());
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.setModal(true);
    this.setMinimumSize(new Dimension(400, 100));
    //this.initComponents();
    this.setVisible(true);
  }

 
}
