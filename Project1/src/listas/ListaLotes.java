package listas;

import datos.Lote;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

/**Clase en la cual se almacenan todos los lotes que esperan ser generados. Implementa el patron Singleton, haciendo 
 * que solo se posea una instancia de la misma, actuando como una base de datos. Extiende de la clase Observable
 */
public class ListaLotes
  extends Observable
{
  private static ListaLotes _instance = null;   //atributo en el cual se guarda la instancia de la clase

  private ArrayList<Lote> lista;                //lista que contiene los lotes que estan esperando ser generados

  private int numeroLote = 0;                   //atributo que sirve como indice en la lista, aumenta con cada
                                                //lote que se le agrega a lista

  /**Constructor privado de la clase listaLotes, debido a que solo es accesible una vez mediante el metodo getInstance
   */
  private ListaLotes()
  {
    super();
    this.lista = new ArrayList<Lote>();
  }

  /**Metodo que devuelve la referencia a la unica instancia posible de la lista de lotes
   * @return ListaLotes
   */
  public static ListaLotes getInstance()
  {
    if (_instance == null)
      _instance = new ListaLotes();
    return _instance;
  }

  public int getProximoNumeroLote()
  {
    return this.numeroLote;
  }

  /**Metodo que agrega un nuevo lote a la lista
   * pre: nuevo distinto de null
   * @param nuevo
   * post: se agrega un lote a la lista
   */
  public void agregarNuevo(Lote nuevo)
  {
    assert nuevo != null : "Lote nulo";
    this.lista.add(nuevo);
    this.numeroLote++;
  }

  /**Metodo que devuelve todos los lotes que estan presentes en la lista
   * @return Iterator<Lote>
   * post: retorna un iterador de lote
   */
  public Iterator<Lote> getIterator()
  {
    Iterator<Lote> it = this.lista.iterator();
    return it;
  }

  /**Metodo que remueve un lote de la lista
   * pre: lot distinto de null
   * @param lot
   * post: se elimina un lote de la lista
   */
  public void borrarLote(Lote lot)
  {
    assert lot != null : "Lote nulo";
    this.lista.remove(lot);
  }
}
