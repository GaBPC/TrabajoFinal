package visual.auxiliares;

import java.awt.BorderLayout;

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
        this.add(dia, BorderLayout.WEST);
        this.add(mes, BorderLayout.CENTER);
        this.add(year, BorderLayout.EAST);
    }

    private void initComponents() {
        this.dia = new JComboBox();
        for (int i = 1; i <= 31; i++) {
            dia.addItem(i);
        }
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
        this.year = new JComboBox();
        for (int i = this.añoMinimo; i <= this.añoMaximo; i++) {
            year.addItem(i);
        }
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
}
