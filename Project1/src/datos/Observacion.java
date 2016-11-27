package datos;

import java.util.Calendar;

/**Clase que incluye todos los apartados que debe contener una observacion de un lote.
 * Es comparable ya que estas observaciones deben estar ordenadas segun un criterio.
 * Invariante: luego de la creacion de la instancia se asume en todo momento
 * - tema != null
 * - fechaObservacion != null
 * - legajoEmpleado != null y ademas es del a siguiente forma: LEGXXXXXX con X entre 0 y 9
 * - texto != null y ademas su longitud es menor a 500 caracteres
 */
public class Observacion
  implements Comparable
{
  // Atributo de la clase que contiene el tema de la observacion
  private String tema = null;
  // Atributo de la clase que contiene la fecha en la que se genera la observacion
  private Calendar fechaObservacion = null;
  // Atributo de la clase que contiene el numero de legajo del empleado que genero la observacion
  private String legajoEmpleado = null;
  // Atributo de la clase que contiene el texto de la observacion
  private String texto;

  /**Constructor principal de la clase. Si algun campo no esta completo (es decir, es null) o tiene
   * algun error lanza una excepcion
   * Pos-condicion:
   * - Si todos los parametros cumplen sus condicones, se crea una instancia de la clase. En caso contrario
   * se lanza una ArgumentoIlegalException
   * Invariante:
   * - Si la clase es instanciada correctamente, se puede asumir en todo momento que sus atributos no varian
   * en la utilizacion de metodos.
   * @param tema
   * @param fechaObservacion
   * @param legajoEmpleado
   * @param texto
   */
  public Observacion(String tema, Calendar fechaObservacion, String legajoEmpleado, String texto)
  {
    super();

    assert tema != null: "El tema es null";
    assert fechaObservacion != null: "La fecha de observacion es null";
    assert Verificaciones.verificaNumeroLegajo(legajoEmpleado) : "El legajo del empleado es null";
    assert Verificaciones.verificaTexto(texto): "El texto no cumple las condiciones";

    this.tema = tema;
    this.fechaObservacion = fechaObservacion;
    this.legajoEmpleado = legajoEmpleado;
    this.texto = texto;

    this.verificaInvariante();
  }

  /**Invariante de la clase, se asume que en todo momento luego de su instanciacion, sus atributos son
   * distuntos de null.
   * @return
   */
  public boolean verificacion()
  {
    return (this.tema != null && this.fechaObservacion != null && Verificaciones.verificaNumeroLegajo(this.legajoEmpleado) && this.texto != null);
  }

  /**Metodo que devuelve los datos de la instancia de clase en forma de String
   * Pos-condicion:
   * - El metodo devuelve un String con el legajo del empleado, el tema y el texto de la observacion
   * @return
   */
  public String toString()
  {
    return this.legajoEmpleado + " - " + this.tema + " - " + this.texto;
  }

  /**Metodo que compara dos instancias de esta clase.
   * Pre-condicion:
   * - Se asume que el parametro object es distinto de null
   * Pos-condicion:
   * - Devuelve un numero positivo si esta instancia es mayor a la instancia pasada como parametro, 0
   * si son iguales y un numero negativo si esta instancia es menor a la pasada como parametro
   * @param object
   * @return
   */
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

  /**Metodo que verifica los invariantes de la clase se cumplan. Si algo falla lanza un AssertError
   */
  private void verificaInvariante()
  {
    assert Verificaciones.verificaTexto(this.texto): "El texto de la observacion es incorrecto";
    assert this.fechaObservacion != null: "La fecha de observacion es nula";
    assert this.tema != null: "El tema es nulo";
    assert Verificaciones.verificaNumeroLegajo(this.legajoEmpleado): "El legajo del empleado no cumple las condiciones";
  }
}
