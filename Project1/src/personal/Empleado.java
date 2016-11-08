package personal;

import exceptions.ArgumentoIlegalException;

/**Clase que modela un empleado de la empresa. Almacena su legajo, nombre y apellido y el sector que ocupa
 * en la empresa.
 * Invariantes: los atributos deben cumplir ciertas invariantes
 *            legajo: String distinto de null de la forma LEGXXXXXX, siendo X un numero entero
 *            nya: String distinto de null
 *            sector: String distinto de null
 */
public class Empleado
{
  private String legajo = null;   //legajo del empleado
  private String nya = null;      //nombre y apellido del empleado
  private String sector = null;   //sector al que pertenece el empleado

  /**Constructor principal de la clase Empleado, que obtiene y verifica los datos, y en caso de que
   * algun parametro no cumpla la norma lanza una ArgumentoIlegalException
   * pre: los parametros deben cumplir las siguientes condiciones
   * @param legajo: String distinto de null de la forma LEGXXXXXX, siendo X un numero entero
   * @param nya: String distinto de null
   * @param sector: String distinto de null
   * post: se crea una instancia de la clase o se indica el error mediante una excepcion
   * @throws ArgumentoIlegalException excepcion de argumento no valido
   */
  public Empleado(String legajo, String nya, String sector)
    throws ArgumentoIlegalException
  {
    super();
    
    assert this.verificaLegajo(legajo) : "Legajo invalido";
    assert this.verificaNombreyApellido(nya) : "Nombre y apellido invalidos";
    assert sector != null : "Sector invalido";
    
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
    
    this.verificarInvariantes();
  }

  /**Metodo que verifica que el legajo del empleado tenga la longitud correspondiente y ademas
   * tenga el prefijo LEG
   * pre: legajo distinto de null
   * @param legajo
   * post: se indica si el legajo es valido o no
   * @return boolean
   */
  private boolean verificaLegajo(String legajo)
  {
    boolean ret = false;
    assert legajo != null : "Legajo nulo";
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
   * pre: nya distinto de null
   * @param nya
   * post: se verifica que nya sea valido
   * @return
   */
  private boolean verificaNombreyApellido(String nya)
  {
    assert nya != null : "Nombre y apellido invalido";
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

  /**Metodo que verifica que los invariantes de clase se cumplan. Si algo falla lanza un AssertError
   */
  private void verificarInvariantes()
  {
    assert this.verificaLegajo(this.legajo) : "Atributo legajo invalido";
    assert this.verificaNombreyApellido(nya) : "Atributo nombre y apellido invalido";
    assert this.sector != null : "Atributo sector invalido";
  }
}
