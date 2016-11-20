package datos;

import listas.ListaEmpleados;
import listas.ListaMaterialesStock;

public abstract class Verificaciones
{
  /**Metodo que verifica si el numero cumple con las restricciones de longitud.
   * Pos-condiciones:
   * - Si el parametro cumple las restricciones, el metodo devuelve true sino false
   * @param str
   * @return
   */
  public static boolean verifica(String str)
  {
    boolean ret = false;
    if (str != null)
      if (str.length() == 9)
      {
        int num = Integer.parseInt(str.substring(3).trim());
        if (num >= 0 && num <= 999999)
          ret = true;
      }
    return ret;
  }

  /**Metodo que verifica si el numero de lote cumple con la restriccion de longitud y que ademas
   * incluya el prefijo LOT
   * Pos-condiciones:
   * - Si el parametro cumple las restricciones, el metodo devuelve true sino false
   * @param numeroLote
   * @return
   */
  public static boolean verificaNumeroLote(String numeroLote)
  {
    boolean ret = false;
    if (numeroLote != null)
    {
      String aux = numeroLote.substring(0, 3);
      if (aux.compareTo("LOT") == 0)
        ret = verifica(numeroLote);
    }
    return ret;
  }

  /**Metodo que se encarga de verificar que el codigo cumpla con las condiciones establecidas para el codigo
   * pre: codigo distinto de null
   * post: se determina si el codigo cumple o no con las condiciones
   * @param codigo
   * @return boolean
   */
  public static boolean verificaCodigo(String codigo)
  {
    boolean ret = false;
    assert codigo != null: "El codigo es nulo";
    if (codigo.length() == 8)
    {
      String aux = codigo.substring(0, 3);
      if (aux.compareTo("MAT") == 0)
      {
        int num = Integer.parseInt(codigo.substring(3).trim());
        if (num >= 0 && num <= 99999)
          ret = true;
      }
    }
    return ret;
  }

  /**Metodo que se encarga de verificar que la descripcion cumpla con las condiciones establecidas para la descripcion
   * pre: la descripcion es distinta de null
   * post: se determina si la descripcion cumple o no con las condiciones
   * @param descripcion
   * @return boolean
   */
  public static boolean verificaDescripcion(String descripcion)
  {
    assert descripcion != null: "Descripcion invalida";

    boolean ret = false;
    if (descripcion.length() <= 100 && descripcion.length() > 0)
      ret = true;
    return ret;
  }

  /**Metodo que se encarga de verificar que la cantidad cumpla con las condiciones establecidas para la cantidad
   * post: se determina si la cantidad cumple con las condiciones
   * @param cantidad
   * @return boolean
   */
  public static boolean verificaCantidad(float cantidad)
  {
    return (cantidad > 0.0 && cantidad <= 999.9999);
  }

  /**Metodo que verifica si el numero de pedido cumple con las condiciones
   * pre: numeroPedido distinto de null
   * post: se indica si el numero de pedido cumple o no con las condiciones
   * @param numeroPedido
   * @return boolean
   */
  public static boolean verificaNumeroPedido(String numeroPedido)
  {
    assert numeroPedido != null: "El numero de pedido es nulo";
    boolean ret = false;
    if (numeroPedido.length() == 9)
    {
      String aux = numeroPedido.substring(0, 3);
      if (aux.compareTo("PED") == 0)
      {
        ret = Verificaciones.verifica(numeroPedido);
      }
    }
    return ret;
  }

  /**Metodo que verifica que la cantidad a producir sea correcta segun las condiciones
   * Post: se indica si la cantidad es correcta o no
   * @param cantProduccion
   * @return boolean
   */
  public static boolean verificaCantProduccion(int cantProduccion)
  {
    boolean ret = false;
    if (cantProduccion > 0 && cantProduccion < 999)
      ret = true;
    return ret;
  }

  /**Metodo que verifica que el texto de la observacion tenga una longitud menor a 500 caracteres
   * Pre-condicion:
   * - Se asume que el argumento texto es distinto de null
   * @param texto
   * @return
   */
  public static boolean verificaTexto(String texto)
  {
    return (texto.length() <= 500);
  }

  /**Metodo que verifica que el legajo del empleado tenga la longitud correspondiente y ademas
   * tenga el prefijo LEG
   * pre: legajo distinto de null
   * @param legajo
   * post: se indica si el legajo es valido o no
   * @return boolean
   */
  public static boolean verificaNumeroLegajo(String codigo)
  {
    boolean ret = false;
    assert codigo != null: "El codigo es nulo";
    if (codigo.length() == 9)
    {
      String aux = codigo.substring(0, 3);
      if (aux.compareTo("LEG") == 0)
      {
        ret = Verificaciones.verifica(codigo);
      }
    }
    return ret;
  }

  /**Metodo que verifica que el nombre y apellido del empleado tenga menos de 100 caracteres
   * pre: nya distinto de null
   * @param nya
   * post: se verifica que nya sea valido
   * @return
   */
  public static boolean verificaNombreyApellido(String nya)
  {
    assert nya != null: "Nombre y apellido invalido";

    return (nya.length() > 0 && nya.length() <= 100);
  }

  public static boolean verificaTipoCodigo(String tipoCodigo)
  {
    assert tipoCodigo != null: "El numero de pedido es nulo";
    boolean ret = false;
    if (tipoCodigo.length() == 9)
    {
      String aux = tipoCodigo.substring(0, 3);
      if (aux.compareTo("TIP") == 0)
      {
        ret = Verificaciones.verifica(tipoCodigo);
      }
    }
    return ret;
  }

  public static boolean verificaTipoProducto(String tipoProducto)
  {
    return ListaMaterialesStock.getInstance()
                               .getCodigoProd()
                               .containsKey(tipoProducto);
  }

  public static boolean verificaSector(String sector)
  {
    boolean ret = false;
    if (sector == ListaEmpleados.VENTAS || sector == ListaEmpleados.CONTABILIDAD ||
        sector == ListaEmpleados.INSPECCION || sector == ListaEmpleados.PRODUCCION)
        ret = true;
    return ret;
  }
}
