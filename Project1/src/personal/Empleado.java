package personal;

import exceptions.ArgumentoIlegalException;

/**Clase que modela un empleado de la empresa. Almacena su legajo, nombre y apellido y el sector que ocupa
 * en la empresa.
 */
public class Empleado
{
  private String legajo = null;
  private String nya = null; //nombre y apellido
  private String sector = null;

  /**Constructor principal de la clase Empleado, que obtiene y verifica los datos, y en caso de que
   * algun parametro no cumpla la norma lanza una ArgumentoIlegalException
   * @param legajo
   * @param nya
   * @param sector
   * @throws ArgumentoIlegalException
   */
  public Empleado(String legajo, String nya, String sector)
    throws ArgumentoIlegalException
  {
    super();
    if (this.verificaLegajo(legajo))
      this.legajo = legajo;
    else
      throw new ArgumentoIlegalException("El legajo del empleado no cumple la norma", legajo);
    if (this.verificaNombreyApellido(nya))
      this.nya = nya;
    else
      throw new ArgumentoIlegalException("El nombre del empleado no cumple la norma", nya);
    if (sector != null)
      this.sector = sector;
    else
      throw new ArgumentoIlegalException("El sector del empleado no cumple la norma", sector);
  }

  /**Metodo que verifica que el legajo del empleado tenga la longitud correspondiente y ademas
   * tenga el prefijo LEG
   * @param legajo
   * @return
   */
  private boolean verificaLegajo(String legajo)
  {
    boolean ret = false;
    String aux = legajo.substring(0, 3);
    if (aux.compareTo("LEG") == 0)
    {
      int num = Integer.parseInt(legajo.substring(3).trim());
      if (num >= 0 && num <= 999999)
        ret = true;
    }
    return ret;
  }

  /**Metodo que verifica que el nombre y apellido del empleado tenga menos de 100 caracteres
   * @param nya
   * @return
   */
  private boolean verificaNombreyApellido(String nya)
  {
    return (nya.length() <= 100);
  }

  public String getLegajo()
  {
    return legajo;
  }

  public String getNya()
  {
    return nya;
  }

  public String getSector()
  {
    return sector;
  }
}
