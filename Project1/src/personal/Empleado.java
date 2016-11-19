package personal;

import datos.Verificaciones;

/**Clase que modela un empleado de la empresa. Almacena su legajo, nombre y apellido y el sector que ocupa
 * en la empresa.
 * Invariantes: los atributos deben cumplir ciertas invariantes
 *            legajo: String distinto de null de la forma LEGXXXXXX, siendo X un numero entero
 *            nya: String distinto de null
 *            sector: String distinto de null
 */
public class Empleado
{
  private String legajo = null; //legajo del empleado
  private String nya = null; //nombre y apellido del empleado
  private String sector = null; //sector al que pertenece el empleado

  /**Constructor principal de la clase Empleado, que obtiene y verifica los datos, y en caso de que
   * algun parametro no cumpla la norma lanza una ArgumentoIlegalException
   * pre: los parametros deben cumplir las siguientes condiciones
   * @param legajo: String distinto de null de la forma LEGXXXXXX, siendo X un numero entero
   * @param nya: String distinto de null
   * @param sector: String distinto de null
   * post: se crea una instancia de la clase o se indica el error mediante una excepcion
   */
  public Empleado(String legajo, String nya, String sector)
  {
    super();

    assert Verificaciones.verificaNumeroLegajo(legajo): "Legajo invalido";
    assert Verificaciones.verificaNombreyApellido(nya): "Nombre y apellido invalidos";
    assert Verificaciones.verificaSector(sector) : "Sector invalido";


    this.legajo = legajo;
    this.nya = nya;
    this.sector = sector;

    this.verificarInvariantes();
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
    assert Verificaciones.verificaNumeroLegajo(this.legajo): "Atributo legajo invalido";
    assert Verificaciones.verificaNombreyApellido(nya): "Atributo nombre y apellido invalido";
    assert this.sector != null: "Atributo sector invalido";
  }
}
