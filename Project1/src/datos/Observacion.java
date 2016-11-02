package datos;

import exceptions.ArgumentoIlegalException;

import java.util.Calendar;

/**Clase que incluye todos los apartados que debe contener una observacion de un lote.
 * Es comparable ya que estas observaciones deben estar ordenadas segun un criterio.
 */
public class Observacion
  implements Comparable
{
  private String tema = null;
  private Calendar fechaObservacion = null;
  private String legajoEmpleado = null;
  private String texto;

  /**Constructor principal de la clase. Si algun campo no esta completo (es decir, es null) o tiene
   * algun error lanza una excepcion
   * @param tema
   * @param fechaObservacion
   * @param legajoEmpleado
   * @param texto
   * @throws ArgumentoIlegalException exception lanzada si algun parametro es incorrecto
   */
  public Observacion(String tema, Calendar fechaObservacion, String legajoEmpleado, String texto)
    throws ArgumentoIlegalException
  {
    super();
    if (tema != null)
      this.tema = tema;
    else
      throw new ArgumentoIlegalException("El tema no esta correctamente completado", tema);
    if (fechaObservacion != null)
      this.fechaObservacion = fechaObservacion;
    else
      throw new ArgumentoIlegalException("La fecha de observacion no esta correctamente completada", fechaObservacion);
    if (legajoEmpleado != null)
      this.legajoEmpleado = legajoEmpleado;
    else
      throw new ArgumentoIlegalException("El legajo del empleado no esta correctamente completado", legajoEmpleado);
    if (this.verificaTexto(texto))
      this.texto = texto;
    else
      throw new ArgumentoIlegalException("El texto no esta correctamente completado", texto);
  }

  /**Metodo que verifica que el texto de la observacion tenga una longitud menor a 500 caracteres
   * @param texto
   * @return
   */
  private boolean verificaTexto(String texto)
  {
    return (texto.length() <= 500);
  }

  public boolean verificacion()
  {
    return (this.tema != null && this.fechaObservacion != null && this.legajoEmpleado != null && this.texto != null);
  }

  public String toString()
  {
    return this.legajoEmpleado + " - " + this.tema + " - " + this.texto;
  }

  @Override
  public int compareTo(Object object)
  {
    int ret;
    Observacion otra = (Observacion) object;
    int aux = this.tema.compareTo(otra.tema);
    if (aux != 0)
      ret = aux;
    else
      ret = this.fechaObservacion.compareTo(otra.fechaObservacion);
    return ret;
  }
}
