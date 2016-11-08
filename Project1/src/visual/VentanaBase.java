package visual;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import listas.ListaLotes;
import listas.ListaPedidos;

/**Ventana base de todas las demas ventanas del sistema. Extiende de JFrame
 * Invariante: atributos control y observados distintos de null
 */
public abstract class VentanaBase
  extends JFrame
  implements Observer
{
  protected Controlador control;                  //instancia de controlador
  protected ArrayList<Observable> observados;     //clases que observa esta ventana mediante el patron Observer

  /**Constructor de la clase
   * pre: los parametros deben cumplir las siguientes condiciones:
   * @param control: distinto de null 
   * @param nombreVentana: distinto de null
   * @param accionCerrar:
   * @param size: distinto de null
   * post: se establecen todos los valores de la ventana o se informa el error
   */
  public VentanaBase(Controlador control, String nombreVentana, int accionCerrar, Dimension size)
  {
    super(nombreVentana);
    
    assert control != null : "Controlador nulo";
    assert size != null : "Dimension nula";
    
    this.control = control;
    //Setea lo que ocurre al cerrar la ventana
    this.setDefaultCloseOperation(accionCerrar);
    //Se setean la dimension y la dimension minima de la vnetana del programa
    this.setSize(size);
    this.setMinimumSize(size);
    //Se añade un BorderLayout a la ventana
    this.setLayout(new BorderLayout());
    //Crea el menu basico de todas las ventanas
    this.setJMenuBar(this.crearMenuBasico());
    //Inicia todas las componentes que contrendra la ventana
    this.IniciarComponentes();
    //Hace que la ventana aparezca en el centro de la pantalla
    this.setLocationRelativeTo(null);
    //Hace que la ventana sea visible
    this.setVisible(true);

    this.definirObserverObservable();
    
    this.verificarInvariantes();
  }

  /**Metodo en el cual se definen las clases a observar y se les informa a las mismas que por quien estan siendo observadas
   */
  private void definirObserverObservable()
  {
    this.observados = new ArrayList<Observable>();
    ListaLotes lotes = ListaLotes.getInstance();
    lotes.addObserver(this);
    this.observados.add(lotes);

    ListaPedidos pedidos = ListaPedidos.getInstance();
    pedidos.addObserver(this);
    this.observados.add(pedidos);
    
    this.verificarInvariantes();
  }

  /**Metodo en el cual se crea la barra de menu
   * post: la barra es generada
   * @return
   */
  private JMenuBar crearMenuBasico()
  {
    JMenuItem cerrarSesion = new JMenuItem("Cerrar sesion");
    cerrarSesion.addActionListener(new ActionListener()
    {

      @Override
      public void actionPerformed(ActionEvent actionEvent)
      {
        VentanaBase.this.dispose();
        new VentanaLogin(new Controlador());
      }
    });

    JMenu sesion = new JMenu("Usuario");
    sesion.add(cerrarSesion);

    JMenuBar menuBar = new JMenuBar();
    menuBar.add(sesion);

    return menuBar;
  }

  protected abstract void IniciarComponentes();

  /**Metodo que se llama cuando alguna clase observada sufre un cambio
   * pre: los parametros distintos de null
   * @param observable
   * @param object
   * post: se realiza alguna accion
   */
  @Override
  public abstract void update(Observable observable, Object object);

  /**Metodo que verifica que los invariantes de clase se cumplan. Si algo falla lanza un AssertError
   */
  protected void verificarInvariantes()
  {
    assert this.control != null : "Atributo controlador nulo";
    assert this.observados != null : "Atributo observados nulo";
  }
}
