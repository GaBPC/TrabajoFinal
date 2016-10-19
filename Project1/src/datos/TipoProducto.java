package datos;

import exceptions.ArgumentoIlegalException;

import listas.ListaMateriales;
import listas.ListaMaterialesStock;

public class TipoProducto
{
  private String codigoProducto = null;
  private String descripcion = null;
  private ListaMateriales listaMat = null;
  
  private static int numeroProd = 0;
  
  public TipoProducto(ListaMateriales lista, String descripcion)
  {
    super();
    numeroProd ++;
    this.generarTipoProd();
    this.listaMat = lista;
    this.descripcion = descripcion;
  }
  
  public void generarTipoProd() {
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
  
  @Override
  public String toString()
  {
    return this.descripcion;
  }

}
