package datos;

/**Material, el cual posee un codigo para identificacion, una descripcion que otorga detalles del mismo, y la cantidad
 * que se posee del mismo
 * Invariante: los atributos cumpliran las siguientes condiciones en todo momento
 *            codigo: String que debe ser distinto de null y de la siguiente forma MAT00000
 *            descipcion: String distinto de null con una longitud menor a 100
 *            cantidad: float que debe ser mayor o igual que 0 y menor que 1000
 */
public class Material
{

  private String codigo = null; //codigo que referencia a un material
  private String descripcion = null; //descipcion breve sobre el material
  private float cantidad = 0.0f; //cantidad del material disponible

  /**Constructor principal de la clase
   * Pre: condiciones que deben cumplir los parametros
   * @param codigo: String que debe ser distinto de null y de la siguiente forma MATXXXXX, siendo las X numeros enteros
   * @param descripcion: String distinto de null con una longitud menor a 100
   * @param cantidad: float que debe ser mayor o igual que 0 y menor que 1000
   * Post: se crea una instancia de la clase Material o se indica cual es el parametro invalido
   */
  public Material(String codigo, String descripcion, float cantidad)
  {
    super();
    assert Verificaciones.verificaCodigo(codigo): "Codigo invalido";
    assert Verificaciones.verificaCantidad(cantidad): "Cantidad invalida";
    assert Verificaciones.verificaDescripcion(descripcion): "Descripcion invalida";

    this.codigo = codigo;
    this.descripcion = descripcion;
    this.cantidad = cantidad;

    this.verificarInvariante();
  }

  public void setCodigo(String codigo)
  {
    assert Verificaciones.verificaCodigo(codigo): "Codigo invalido";

    this.codigo = codigo;
    this.verificarInvariante();
  }

  public String getCodigo()
  {
    return codigo;
  }

  public void setDescripcion(String descripcion)
  {
    assert Verificaciones.verificaDescripcion(descripcion): "Descripcion invalida";

    this.descripcion = descripcion;
    this.verificarInvariante();
  }

  public String getDescripcion()
  {
    return descripcion;
  }

  public void setCantidad(float cantidad)
  {
    assert Verificaciones.verificaCantidad(cantidad): "Cantidad invalida";
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
   */
  private void verificarInvariante()
  {
    assert Verificaciones.verificaCodigo(this.codigo): "Atributo codigo invalido";
    assert Verificaciones.verificaDescripcion(this.descripcion): "Atributo descripcion invalido";
    assert Verificaciones.verificaCantidad(this.cantidad): "Atributo cantidad invalido";
  }
}
