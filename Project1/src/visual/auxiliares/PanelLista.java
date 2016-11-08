package visual.auxiliares;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**Clase compuesta por un panel de listas. Extiende de la clase JPanel
 * Invariante: atributo lista distinta de null
 */
public class PanelLista
  extends JPanel
{

  private MyList lista = null;   //lista que debe mostrarse por pantalla

  /**Constructor de la clase
   * pre: los parametros deben ser distintos de null
   * @param tituloPanel
   * @param listModel
   * post: se crea una instancia de la clase o se determina el error
   */
  public PanelLista(String tituloPanel, DefaultListModel listModel)
  {
    super();
    assert tituloPanel != null : "Titulo panel invalido";
    assert listModel != null : "Lista invalida";
    
    this.initComponents(tituloPanel, listModel);
    this.setVisible(true);
    
    this.verificarInvariantes();
  }

  /**Metodo que inicializa todos los componentes graficos del panel
   * @param tituloPanel
   * @param listModel
   * post: se generan todos los componentes
   */
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
    
    this.verificarInvariantes();
  }

  public MyList getLista()
  {
    return this.lista;
  }

  /**Metodo que verifica que los invariantes de clase se cumplan. Si algo falla lanza un AssertError
   */
  private void verificarInvariantes()
  {
    assert this.lista != null : "Atributo lista invalido";
  }
}
