package datos;

import listas.ListaMateriales;

/**Clase que contiene todos datos correspondientes a un producto
 * Invariantes: los siguientes atributos cumpliran las siguientes condiciones:
 *              codigoProducto: String distinto de null que debe ser de la forma TIPXXXXXX, siendo X un numero entero
 *              descripcion: String distinto de null
 *              listaMat: ListaMateriales distinta de null
 */
public class TipoProducto
{
  private String codigoProducto = null;     //codigo que identifica al tipo de producto
  private String descripcion = null;        //descripcion breve sobre el producto
  private ListaMateriales listaMat = null;  //lista con los materiales necesarios para desarrollar este producto

  private static int numeroProd = 0;

  /**Constructor de la clase. Como el codigo del producto se genera automaticamente, solo se asegura de recibir la lista
   * con los materiales que lo componen y la descripcion
   * pre: los siguientes parametros deben cumplir las siguientes condiciones:
   * @param lista: distinta de null
   * @param descripcion: distinta de null
   * post: se crea una instancia de la clase TipoProducto, o se indica porque no se ha podido realizar la instancia
   */
  public TipoProducto(ListaMateriales lista, String descripcion)
  {
    super();
    
    assert lista != null : "Lista de materiales nula";
    assert descripcion != null : "Descipcion nula";
    
    numeroProd++;
    this.generarTipoProd();
    this.listaMat = lista;
    this.descripcion = descripcion;
    
    this.verificarInvariantes();
  }
  
  public static void setNumeroProd(int numero){
      numeroProd = numero;
  }

  /**Metodo que genera el codigoProducto automaticamente basandose en un contador estatico de la clase
   * post: se genera el valor del atributo codigoProducto
   */
  public void generarTipoProd()
  {
    String aux = Integer.toString(numeroProd);
    int longitud = aux.length();
    String codigoTipo = "TIP";
    for (int i = 0; i < (6 - longitud); i++)
      codigoTipo += "0";
    codigoTipo += aux;
    this.codigoProducto = codigoTipo;
  }

  public ListaMateriales getListaMateriales()
  {
    return this.listaMat;
  }

  public String getCodigoProducto()
  {
    return codigoProducto;
  }

  public String getDescripcion()
  {
    return descripcion;
  }

  /**Metodo que otorga un String con una descripcion breve del producto
   * @return String
   */
  @Override
  public String toString()
  {
    return this.descripcion;
  }

  /**Metodo que verifica que los invariantes de clase se cumplan. Si algo falla lanza un AssertError
   */
  private void verificarInvariantes()
  {
    assert this.listaMat != null : "Atributo lista de materiales invalido";
    assert this.descripcion != null : "Atributo descripcion invalido";
  }

}
