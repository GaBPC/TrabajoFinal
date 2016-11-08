package visual;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**Clase principal del sistema, pone en funcionamiento al mismo
 */
public class Test
{
  /**Constuctor vacio de la clase
   */
  public Test()
  {
    super();
  }

  /**Metodo main que se encarga de crear el Thread que arranca el sistema
   * @param args
   * post: el programa se ejecuta
   */
  public static void main(String[] args)
  {
    try
    {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch (UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException | ClassNotFoundException e)
    {
    }

    Controlador controlUsuario1 = new Controlador();
    new VentanaLogin(controlUsuario1);

    Controlador controlUsuario2 = new Controlador();
    new VentanaLogin(controlUsuario2);
  }
}
