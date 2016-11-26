package exceptions;

import listas.ListaMateriales;

/**Clase excepcion que contiene la informacion necesaria para expresar las cantidades faltantes de cierto material para 
 * producir el mismo
 */
public class FaltantesException
  extends Exception
{
  private ListaMateriales faltantes; //lista de la cual faltan ciertas cantidades para poder generar el pedido

  /**Constructor de la clase, en el cual se envia el mensaje a la clase padre Exception y se guarda la lista como atributo
   * pre: faltantes != null
   * post: se crea una instancia de la clase o se indica que fallo
   */
  public FaltantesException(String mensaje, ListaMateriales faltantes)
  {
    super(mensaje);
    assert faltantes != null : "Lista faltantes nula";
    
    this.faltantes = faltantes;
  }

  public ListaMateriales getFaltantes()
  {
    return this.faltantes;
  }
}
