package visual.auxiliares;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PanelLista
  extends JPanel
{

  private MyList lista = null;

  public PanelLista(String tituloPanel, DefaultListModel listModel)
  {
    super();
    this.initComponents(tituloPanel, listModel);
    this.setVisible(true);
  }

  public void initComponents(String tituloPanel, DefaultListModel listModel)
  {
    this.setLayout(new BorderLayout());

    JLabel titulo = new JLabel(tituloPanel);
    titulo.setFont(new Font("Arial", 0, 15));
    /* Crea una nueva lista con el modelo indicado*/
    this.lista = new MyList(listModel);
    /* Agrega la nueva lista en un JScrollPane para poder moverse dentro de ella*/
    JScrollPane scrollLotesIn = new JScrollPane(this.lista);

    this.add(titulo, BorderLayout.NORTH);
    this.add(scrollLotesIn, BorderLayout.CENTER);
  }

  public MyList getLista()
  {
    return this.lista;
  }
}
