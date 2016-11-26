package exceptions;

/**Clase excepcion que se utiliza cuando surge alguna falla en un estado
 */
public class StateException
  extends Exception
{
  /**Constructor de la clase, le envia al padre Exception el mensaje
   * @param mensaje
   * post: se crea una instancia de la clase 
   */
  public StateException(String mensaje)
  {
    super(mensaje);
  }
}
