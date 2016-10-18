package listas;

import datos.Material;
import datos.Pedido;
import datos.TipoProducto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Observable;

/**Clase donde se almacenan todas las existencias de los materiales disponibles.
 * Es una clase singleton ya que "simula" ser una base de datos de los materiales, y todos los
 * cambios deben afectar sobre un solo conjunto de datos.
 */
public class ListaMaterialesStock
{
  
  public static final String FLIPPER = "Flipper";
  public static final String CONSOLA_GRUPAL = "Consola grupal";
  public static final String CONSOLA_IND = "Consola individual";
  public static final String SIMULADOR = "Simulador";

  
  private static ListaMaterialesStock _instance = null;
  private HashMap<String,TipoProducto> tipoProductos = new HashMap<>();
  private HashMap<String,String> codigoProd = new HashMap<>();
  
  {
    ListaMateriales matConsolaGrup = new ListaMateriales();
    ListaMateriales matFliper = new ListaMateriales();
    ListaMateriales matConsolaInd = new ListaMateriales();
    ListaMateriales matSimulador = new ListaMateriales();
    
    TipoProducto prod1 = new TipoProducto(matConsolaGrup);
    TipoProducto prod2 = new TipoProducto(matFliper);
    TipoProducto prod3 = new TipoProducto(matConsolaInd);
    TipoProducto prod4 = new TipoProducto(matSimulador);
    
    this.tipoProductos.put(prod1.getCodigoProducto(), prod1); //Consola grupal
    this.tipoProductos.put(prod2.getCodigoProducto(), prod2); //Fliper
    this.tipoProductos.put(prod3.getCodigoProducto(), prod3); //Consola individual
    this.tipoProductos.put(prod4.getCodigoProducto(), prod4); //Simulador
    
    codigoProd.put(CONSOLA_GRUPAL, prod1.getCodigoProducto());
    codigoProd.put(FLIPPER,prod2.getCodigoProducto());
    codigoProd.put(CONSOLA_IND,prod3.getCodigoProducto());
    codigoProd.put(SIMULADOR,prod4.getCodigoProducto());
    
    //FALTA AGREGAR MATERIALES DE CADA TIPOOOOO
  }
  
  private ListaMateriales lista;
  
  {
    //FALTAN MATERIALES EN EXISTENCIA
  }
  
  private ListaMaterialesStock() {
      super();
  }

  /**Metodo que devuelve la referencia a la unica instancia posible de la lista de materiales
   * @return
   */
  public static ListaMaterialesStock getInstance() {
      if (_instance == null)
          _instance = new ListaMaterialesStock();
      return _instance;
  }

  /**Metodo que agrega un nuevo material a la lista
   * @param nuevo
   */
  public void agregarNuevo(Material nuevo) {
      this.lista.agregarMaterial(nuevo);
  }

  /**Metodo que devuelve todos los materiales que estan presentes en la lista
   * @return
   */
  public Iterator<Material> getIterator() {
      return this.lista.getIterator();
  }
  
  public ListaMateriales verificarExistencias(String tipo)
    throws Exception
  {
    boolean ret = true;
    ListaMateriales retorno = null;
    ListaMateriales lista = this.tipoProductos.get(tipo).getListaMateriales();
    Iterator<Material> it = lista.getIterator();
    while(it.hasNext() && ret)
    {
      Material mat = it.next();
      Material matExistencias = this.lista.getMaterial(mat.getCodigo());
      if(matExistencias.getCantidad() < mat.getCantidad())
          ret = false;
    }
    if(ret)
      retorno = lista;
    return retorno;
  }
  
  public String getCodigo(String tipoProducto)
  {
    return codigoProd.get(tipoProducto);
  }
}
