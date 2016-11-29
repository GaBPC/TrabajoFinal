package junit;

import datos.Material;
import datos.TipoProducto;

import listas.ListaMateriales;
import listas.ListaMaterialesStock;

public class FixtureListaMaterialesStockTestCajaBlanca
{
  public ListaMaterialesStock lista;
  
  public void setUp_2()
  {
    this.lista = ListaMaterialesStock.getInstance();
  }
  
  public void setUp_3()
  {
    this.setUp_2();
    TipoProducto tp = this.lista.getProducto("TIP000001");
    ListaMateriales materiales = new ListaMateriales();
    materiales.agregarMaterial(new Material(ListaMaterialesStock.COD_MADERA,"Es madera",5));
    tp.setListaMat(materiales);
  }
  
  public void setUp_4()
  {
    this.setUp_2();
    TipoProducto tp = this.lista.getProducto("TIP000001");
    ListaMateriales materiales = new ListaMateriales();
    tp.setListaMat(materiales);
  }
  
  public void tearDown()
  {
    this.lista = null;
  }
}
