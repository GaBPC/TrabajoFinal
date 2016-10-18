package visual.auxiliares;

import datos.Lote;

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

import javax.swing.JTextField;

import visual.Controlador;

public class DialogoM extends JDialog {
    private Controlador control;

    public DialogoM(Controlador control, Component relativeTo) {
        super();
        this.setLocationRelativeTo(relativeTo);
        this.control = control;
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setModal(true);
        this.setMinimumSize(new Dimension(400, 100));
        this.initComponents();
        this.setVisible(true);
    }

    private void initComponents() {
        Container cp = this.getContentPane();

        JPanel panelIngreso = new JPanel(new GridLayout(0, 2));

        panelIngreso.add(new JLabel("Fecha propuesta por produccion: "));
        PanelFechas fechaProduccion = new PanelFechas();
        panelIngreso.add(fechaProduccion);

        cp.add(panelIngreso, BorderLayout.CENTER);

        JButton aceptarLote = new JButton("Aceptar pedido");
        aceptarLote.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Calendar calendarFechaProduccion =
                    new GregorianCalendar(fechaProduccion.getYear(), fechaProduccion.getMes(),
                                          fechaProduccion.getDia() + 1);
                try {
                    DialogoM.this.control.cambiarAAceptado(calendarFechaProduccion);
                    DialogoM.this.control.generarLote();
                    DialogoM.this.dispose();
                } catch (StateException e) {
                    JOptionPane.showMessageDialog(DialogoM.this, e.getMessage());
                } catch (ArgumentoIlegalException e) {
                    JOptionPane.showMessageDialog(DialogoM.this, e.getMessage());
                }

            }
        });
        cp.add(aceptarLote, BorderLayout.SOUTH);

    }
}
