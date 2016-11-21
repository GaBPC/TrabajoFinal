package listas;

import datos.Material;
import datos.TipoProducto;

import datos.Verificaciones;

import exceptions.FaltantesException;

import java.util.HashMap;
import java.util.Iterator;

/**Clase donde se almacenan todas las existencias de los materiales disponibles.
 * Es una clase singleton ya que "simula" ser una base de datos de los materiales, y todos los
 * cambios deben afectar sobre un solo conjunto de datos.
 */
public class ListaMaterialesStock
{


  private static final String COD_MADERA = "MAT00000";
  private static final String DES_MADERA = "Madera de pino pulida";

  private static final String COD_VIDRIO = "MAT00001";
  private static final String DES_VIDRIO = "Vidrio de 5mm.";

  private static final String COD_METAL = "MAT00002";
  private static final String DES_METAL = "Metal liso anti oxidante";

  private static final String COD_PLASTICO = "MAT00003";
  private static final String DES_PLASTICO = "PVC negro liso";


  public static final String FLIPPER = "Flipper";
  public static final String CONSOLA_GRUPAL = "Consola grupal";
  public static final String CONSOLA_IND = "Consola individual";
  public static final String SIMULADOR = "Simulador";


  private static ListaMaterialesStock _instance = null;


  private HashMap<String, TipoProducto> recetas = null;
  private HashMap<String, String> codigoProd = null;

  private ListaMateriales listaExistencias = null;

  private ListaMaterialesStock()
  {
    super();
    this.listaExistencias = this.iniciarStock();
    this.recetas = this.iniciaRecetas();
  }

  private ListaMateriales iniciarStock()
  {
    ListaMateriales stock = null;

    stock = new ListaMateriales();
    stock.agregarMaterial(new Material(COD_MADERA, DES_MADERA, 10.0f));
    stock.agregarMaterial(new Material(COD_VIDRIO, DES_VIDRIO, 10.0f));
    stock.agregarMaterial(new Material(COD_METAL, DES_METAL, 35.0f));
    stock.agregarMaterial(new Material(COD_PLASTICO, DES_PLASTICO, 15.0f));


    return stock;
  }

  private HashMap<String, TipoProducto> iniciaRecetas()
  {
    HashMap<String, TipoProducto> recetas = null;

    ListaMateriales recetaFlipper = new ListaMateriales();
    recetaFlipper.agregarMaterial(new Material(COD_MADERA, DES_MADERA, 3.0f));
    recetaFlipper.agregarMaterial(new Material(COD_VIDRIO, DES_VIDRIO, 1.0f));
    recetaFlipper.agregarMaterial(new Material(COD_METAL, DES_METAL, 0.5f));

    ListaMateriales recetaConsola1 = new ListaMateriales();
    recetaConsola1.agregarMaterial(new Material(COD_PLASTICO, DES_PLASTICO, 4.0f));
    recetaConsola1.agregarMaterial(new Material(COD_METAL, DES_METAL, 1.0f));

    ListaMateriales recetaConsola2 = new ListaMateriales();
    recetaConsola2.agregarMaterial(new Material(COD_PLASTICO, DES_PLASTICO, 8.0f));
    recetaConsola2.agregarMaterial(new Material(COD_METAL, DES_METAL, 2.0f));

    ListaMateriales recetaSimulador = new ListaMateriales();
    recetaSimulador.agregarMaterial(new Material(COD_PLASTICO, DES_PLASTICO, 7.0f));
    recetaSimulador.agregarMaterial(new Material(COD_METAL, DES_METAL, 5.0f));
    recetaSimulador.agregarMaterial(new Material(COD_VIDRIO, DES_VIDRIO, 2.0f));

    TipoProducto flipper = new TipoProducto(recetaFlipper, "Producto flipper");
    TipoProducto consolaIndividual = new TipoProducto(recetaConsola1, "Producto consola individual");
    TipoProducto consolaGrupal = new TipoProducto(recetaConsola2, "Producto consola grupal");
    TipoProducto simulador = new TipoProducto(recetaSimulador, "Producto simulador");

    recetas = new HashMap<>();
    recetas.put(flipper.getCodigoProducto(), flipper);
    recetas.put(consolaIndividual.getCodigoProducto(), consolaIndividual);
    recetas.put(consolaGrupal.getCodigoProducto(), consolaGrupal);
    recetas.put(simulador.getCodigoProducto(), simulador);

    this.codigoProd = new HashMap<>();

    codigoProd.put(FLIPPER, flipper.getCodigoProducto());
    codigoProd.put(CONSOLA_IND, consolaIndividual.getCodigoProducto());
    codigoProd.put(CONSOLA_GRUPAL, consolaGrupal.getCodigoProducto());
    codigoProd.put(SIMULADOR, simulador.getCodigoProducto());

    return recetas;
  }


  /**Metodo que devuelve la referencia a la unica instancia posible de la lista de stock
   * @return
   */
  public static ListaMaterialesStock getInstance()
  {
    if (_instance == null)
      _instance = new ListaMaterialesStock();
    return _instance;
  }

  /**Metodo que agrega un nuevo material a la lista
   * @param nuevo
   */
  public void agregarNuevo(Material nuevo)
  {
    this.listaExistencias.agregarMaterial(nuevo);
  }

  /**Metodo que devuelve todos los materiales que estan presentes en la lista
   * @return
   */
  public Iterator<Material> getIterator()
  {
    return this.listaExistencias.getIterator();
  }

  public ListaMateriales verificarExistencias(String tipo, int cantidad)
    throws FaltantesException, Exception
  {
    assert Verificaciones.verificaTipoCodigo(tipo) : "Tipo invalido";
    assert Verificaciones.verificaCantProduccion(cantidad) : "Cantidad invalida";
    
    ListaMateriales listaFinal = new ListaMateriales();
    ListaMateriales listaFaltantes = new ListaMateriales();

    ListaMateriales receta = this.recetas.get(tipo).getListaMateriales();
    Iterator<Material> itReceta = receta.getIterator();

    while (itReceta.hasNext())
    {
      Material matReceta = itReceta.next();
      Material matExistente = this.listaExistencias.getMaterial(matReceta.getCodigo());
      double cantidadMaterialNecesaria = matReceta.getCantidad() * cantidad;
      if (matExistente.getCantidad() >= cantidadMaterialNecesaria)
        listaFinal.agregarMaterial(new Material(matReceta.getCodigo(), matReceta.getDescripcion(),
                                                cantidadMaterialNecesaria));
      else
        listaFaltantes.agregarMaterial(new Material(matReceta.getCodigo(), matReceta.getDescripcion(),
                                                    cantidadMaterialNecesaria - matExistente.getCantidad()));
    }
    if (listaFaltantes.size() <= 0)
      return listaFinal;
    else
      throw new FaltantesException("No se cuenta con los suficientes materiales\nEstas son las cantidades faltantes:",
                                   listaFaltantes);
  }

  public String getCodigo(String tipoProducto)
  {
    assert Verificaciones.verificaTipoProducto(tipoProducto) : "Tipo producto invalido";
    
    return codigoProd.get(tipoProducto);
  }

  public TipoProducto getProducto(String codigo)
  {
    assert Verificaciones.verificaTipoCodigo(codigo) : "Codigo invalido";
    
    return this.recetas.get(codigo);
  }

  public void actualizarExistencias(TipoProducto tipo)
  {
    assert tipo != null : "Producto nulo";
    
    ListaMateriales lista = tipo.getListaMateriales();
    Iterator<Material> it = lista.getIterator();
    while (it.hasNext())
    {
      Material mat = it.next();
      try
      {
        double cant1 = this.listaExistencias.getMaterial(mat.getCodigo()).getCantidad();
        this.listaExistencias.getMaterial(mat.getCodigo()).setCantidad(cant1 - mat.getCantidad());
      }
      catch (Exception e)
      {
      }
    }
  }

  public String detalles()
  {
    return this.listaExistencias.detalles();
  }
  
  public HashMap<String,String> getCodigoProd()
  {
    return this.codigoProd;
  }
                                  
  
}
