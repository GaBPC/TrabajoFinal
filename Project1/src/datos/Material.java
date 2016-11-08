package datos;

import exceptions.ArgumentoIlegalException;
import exceptions.LengthException;


/**Material, el cual posee un codigo para identificacion, una descripcion que otorga detalles del mismo, y la cantidad
 * que se posee del mismo
 * Invariante: los atributos cumpliran las siguientes condiciones en todo momento
 *            codigo: String que debe ser distinto de null y de la siguiente forma MAT00000
 *            descipcion: String distinto de null con una longitud menor a 100
 *            cantidad: float que debe ser mayor o igual que 0 y menor que 1000
 */
public class Material
{

  private String codigo = null;       //codigo que referencia a un material
  private String descripcion = null;  //descipcion breve sobre el material
  private float cantidad = 0.0f;      //cantidad del material disponible

  /**Constructor principal de la clase
   * Pre: condiciones que deben cumplir los parametros
   * @param codigo: String que debe ser distinto de null y de la siguiente forma MATXXXXX, siendo las X numeros enteros
   * @param descripcion: String distinto de null con una longitud menor a 100
   * @param cantidad: float que debe ser mayor o igual que 0 y menor que 1000
   * Post: se crea una instancia de la clase Material o se indica cual es el parametro invalido
   * @throws ArgumentoIlegalException
   * @throws LengthException
   */
  public Material(String codigo, String descripcion, float cantidad)
    throws ArgumentoIlegalException, LengthException
  {
    super();
    assert this.verificaCodigo(codigo): "Codigo invalido";
    assert this.verificaCantidad(cantidad): "Cantidad invalida";
    assert this.verificaDescripcion(descripcion): "Descripcion invalida";

    if (this.verificaCodigo(codigo))
      this.codigo = codigo;
    if (this.verificaDescripcion(descripcion))
      this.descripcion = descripcion;
    if (this.verificaCantidad(cantidad))
      this.cantidad = cantidad;

    this.verificarInvariante();
  }

  /**Metodo que se encarga de verificar que el codigo cumpla con las condiciones establecidas para el codigo
   * pre: codigo distinto de null
   * post: se determina si el codigo cumple o no con las condiciones
   * @param codigo
   * @return boolean
   * @throws ArgumentoIlegalException excepcion por argumento ilegal
   */
  private boolean verificaCodigo(String codigo)
    throws ArgumentoIlegalException
  {
    boolean ret = false;
    assert codigo != null: "El codigo es nulo";
    if (codigo.length() == 8)
    {
      String aux = codigo.substring(0, 3);
      if (aux.compareTo("MAT") == 0)
      {
        int num = Integer.parseInt(codigo.substring(3).trim());
        if (num >= 0 && num <= 999999)
          ret = true;
        else
          throw new ArgumentoIlegalException("Numero fuera de rango", num);
      }
      else
        throw new ArgumentoIlegalException("El codigo no contiene \"MAT\"", codigo);
    }
    else
      throw new ArgumentoIlegalException("El numero debe tener 6 digitos", codigo);
    return ret;
  }

  /**Metodo que se encarga de verificar que la descripcion cumpla con las condiciones establecidas para la descripcion
   * pre: la descripcion es distinta de null
   * post: se determina si la descripcion cumple o no con las condiciones
   * @param descripcion
   * @return boolean
   * @throws LengthException excepcion de longitud
   */
  private boolean verificaDescripcion(String descripcion)
    throws LengthException
  {
    assert descripcion != null : "Descripcion invalida";
    boolean ret = false;
    if (descripcion.length() <= 100)
      ret = true;
    else
      throw new LengthException(descripcion, "El texto tiene mas de 100 caracteres");
    return ret;
  }

  /**Metodo que se encarga de verificar que la cantidad cumpla con las condiciones establecidas para la cantidad
   * post: se determina si la cantidad cumple con las condiciones
   * @param cantidad
   * @return boolean
   */
  private boolean verificaCantidad(float cantidad)
  {
    return (cantidad > 0.0 && cantidad <= 999.9999);
  }

  public void setCodigo(String codigo)
  {
    try
    {
      assert this.verificaCodigo(codigo): "Codigo invalido";
    }
    catch (ArgumentoIlegalException e)
    {
    }
    this.codigo = codigo;
    this.verificarInvariante();
  }

  public String getCodigo()
  {
    return codigo;
  }

  public void setDescripcion(String descripcion)
  {
    try
    {
      assert this.verificaDescripcion(descripcion): "Descripcion invalida";
    }
    catch (LengthException e)
    {
    }
    this.descripcion = descripcion;
    this.verificarInvariante();
  }

  public String getDescripcion()
  {
    return descripcion;
  }

  public void setCantidad(float cantidad)
  {
    assert this.verificaCantidad(cantidad): "Cantidad invalida";
    this.cantidad = cantidad;
    this.verificarInvariante();
  }

  public float getCantidad()
  {
    return cantidad;
  }

  @Override
  public String toString()
  {
    return this.codigo;
  }

  /**Metodo que devuelve los detalles de un material
   * post: se obtiene un String que contiene los detalles necesarios posteriormente
   * @return String con los detalles
   */
  public String detalles()
  {
    String aux = "Material: " + this.codigo + " " + this.cantidad;
    return aux;
  }

  /**Metodo que verifica los invariantes de la clase se cumplan. Si algo falla lanza un AssertError
   * Captura las excepciones lanzadas por los metodos porque no interesa que se genere ningun aviso mediante interfaz
   */
  private void verificarInvariante()
  {
    try
    {
      assert this.verificaCodigo(this.codigo): "Atributo codigo invalido";
      assert this.verificaDescripcion(this.descripcion): "Atributo descripcion invalido";
    }
    catch (ArgumentoIlegalException e)
    {
    }
    catch (LengthException e)
    {
    }
    assert this.verificaCantidad(this.cantidad): "Atributo cantidad invalido";
  }
}
