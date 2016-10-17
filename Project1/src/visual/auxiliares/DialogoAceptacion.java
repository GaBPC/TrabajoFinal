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

import javax.swing.JTextField;

import visual.Controlador;

public class DialogoAceptacion extends JDialog {
    private Controlador control;

    public DialogoAceptacion(Controlador control, Component relativeTo) {
        super();
        this.setLocationRelativeTo(relativeTo);
        this.control = control;
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setModal(true);
        this.setMinimumSize(new Dimension(200, 200));
        this.initComponents();
        this.setVisible(true);
    }

    private void initComponents() {
        Container cp = this.getContentPane();

        JPanel panelIngreso = new JPanel(new GridLayout(0, 2));

        panelIngreso.add(new JLabel("Fecha propuesta por produccion: "));
        PanelFechas fechaProduccion = new PanelFechas();
        panelIngreso.add(fechaProduccion);

        panelIngreso.add(new JLabel("Numero de lote: "));
        JTextField numeroLote = new JTextField();
        panelIngreso.add(numeroLote);

        cp.add(panelIngreso, BorderLayout.CENTER);

        JButton aceptarLote = new JButton("Aceptar lote");
        aceptarLote.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Calendar calendarFechaProduccion =
                    new GregorianCalendar(fechaProduccion.getYear(), fechaProduccion.getMes(),
                                          fechaProduccion.getDia() + 1);
                String numero = "LOT" + numeroLote.getText();
                try {
                    DialogoAceptacion.this.control.cambiarAAceptado(numero, calendarFechaProduccion);
                    DialogoAceptacion.this.dispose();
                } catch (StateException e) {
                    JOptionPane.showMessageDialog(DialogoAceptacion.this, e.getMessage());
                } catch (ArgumentoIlegalException e) {
                    JOptionPane.showMessageDialog(DialogoAceptacion.this, e.getMessage());
                }

            }
        });
        cp.add(aceptarLote, BorderLayout.SOUTH);

    }
}
