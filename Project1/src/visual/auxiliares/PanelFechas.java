package visual.auxiliares;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class PanelFechas
  extends JPanel
{
  private JComboBox dia;
  private JComboBox mes;
  private JComboBox year;

  private final int yearMinimo = 2016;
  private final int yearMaximo = 2050;

  public PanelFechas()
  {
    super();
    this.initComponents();
    this.setLayout(new BorderLayout());
    this.add(year, BorderLayout.WEST);
    this.add(mes, BorderLayout.CENTER);
    this.add(dia, BorderLayout.EAST);
  }

  private void initComponents()
  {
    this.dia = new JComboBox();
    for (int i = 1; i <= 31; i++)
    {
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
    this.mes.addActionListener(new ActionListener()
    {

      @Override
      public void actionPerformed(ActionEvent actionEvent)
      {
        PanelFechas.this.dia.setEnabled(true);
        PanelFechas.this.verificaDias(PanelFechas.this.yearBisiesto());
      }
    });
    this.mes.setEnabled(false);
    this.year = new JComboBox();
    for (int i = this.yearMinimo; i <= this.yearMaximo; i++)
    {
      year.addItem(i);
    }
    this.year.addActionListener(new ActionListener()
    {

      @Override
      public void actionPerformed(ActionEvent actionEvent)
      {
        if (PanelFechas.this.yearBisiesto() && PanelFechas.this.mes.isEnabled())
          PanelFechas.this.verificaDias(true);
        else
          PanelFechas.this.verificaDias(false);
        PanelFechas.this.mes.setEnabled(true);
      }
    });
  }

  public int getDia()
  {
    return this.dia.getSelectedIndex();
  }

  public int getMes()
  {
    return this.mes.getSelectedIndex();
  }

  public int getYear()
  {
    return (this.year.getSelectedIndex() + this.yearMinimo);
  }

  private void verificaDias(boolean bisiesto)
  {
    int i;
    switch (this.getMes())
    {
      case 1: //FEBRERO
        dia.removeAllItems();
        if (!bisiesto)
          for (i = 1; i <= 28; i++)
            dia.addItem(i);
        else
          for (i = 1; i <= 29; i++)
            dia.addItem(i);
        break;
      case 3:
      case 5:
      case 8:
      case 10: //ABRIL JUNIO SEPTIEMBRE NOVIEMBRE
        dia.removeAllItems();
        for (i = 1; i <= 30; i++)
          dia.addItem(i);
        break;
    }
  }

  private boolean yearBisiesto()
  {
    boolean ret = false;
    if (this.getYear() % 4 == 0)
      if (this.getYear() % 100 != 0)
        ret = true;
      else if (this.getYear() % 400 == 0)
        ret = true;
    return ret;
  }

}
