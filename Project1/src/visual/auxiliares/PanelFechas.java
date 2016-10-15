package visual.auxiliares;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class PanelFechas extends JPanel {
    private JComboBox dia;
    private JComboBox mes;
    private JComboBox year;

    private final int añoMinimo = 2010;
    private final int añoMaximo = 2050;

    public PanelFechas() {
        super();
        this.initComponents();
        this.setLayout(new BorderLayout());
        this.add(year, BorderLayout.WEST);
        this.add(mes, BorderLayout.CENTER);
        this.add(dia, BorderLayout.EAST);
    }

    private void initComponents() {
        this.dia = new JComboBox();
        for (int i = 1; i <= 31; i++) {
            dia.addItem(i);
        }
        this.dia.setEnabled(false);
        this.mes = new JComboBox();
        {
            mes.addItem("Enero");
            mes.addItem("Febrero");
            mes.addItem("Marzo");
            mes.addItem("Abril");
            mes.addItem("Mayo");
            mes.addItem("Junio");
            mes.addItem("Julio");
            mes.addItem("Agosto");
            mes.addItem("Septiembre");
            mes.addItem("Octubre");
            mes.addItem("Noviembre");
            mes.addItem("Diciembre");
        }
        this.mes.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                PanelFechas.this.dia.setEnabled(true);
                PanelFechas.this.verificaDias();
            }
        });
        this.mes.setEnabled(false);
        this.year = new JComboBox();
        for (int i = this.añoMinimo; i <= this.añoMaximo; i++) {
            year.addItem(i);
        }
        this.year.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                PanelFechas.this.mes.setEnabled(true);

            }
        });
    }

    public int getDia() {
        return this.dia.getSelectedIndex();
    }

    public int getMes() {
        return this.mes.getSelectedIndex();
    }

    public int getYear() {
        return (this.year.getSelectedIndex() + this.añoMinimo);
    }

    private void verificaDias() {
        //TODO te dejo esto de ejemplo asi mas o menos sabes como funciona
        switch (this.getMes()) {
        case 0: //ENERO
            dia.removeAllItems();
            for (int i = 1; i <= 31; i++) {
                dia.addItem(i);
            }
            break;
        case 1: //FEBRERO
            dia.removeAllItems();
            for (int i = 1; i <= 27; i++) {
                dia.addItem(i);
            }
        }
    }
}
