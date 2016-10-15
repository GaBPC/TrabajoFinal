package datos;

import java.util.ArrayList;
import java.util.Iterator;

/**Clase donde se almacenan todos los lotes que se van creando.
 * Es una clase singleton ya que "simula" ser una base de datos de los lotes, y todos los
 * cambios deben afectar sobre un solo conjunto de datos.
 */
public class ListaLotes {
    private static ListaLotes _instance = null;
    
    private ArrayList<Lote> lista;
    
    private ListaLotes() {
        super();
        this.lista = new ArrayList<Lote>();
    }

    /**Metodo que devuelve la referencia a la unica instancia posible de la lista de lotes
     * @return
     */
    public static ListaLotes getInstance(){
        if (_instance == null)
            _instance = new ListaLotes();
        return _instance;
    }

    /**Metodo que agrega un nuevo lote a la lista
     * @param nuevo
     */
    public void agregarNuevo(Lote nuevo) {
        this.lista.add(nuevo);
    }

    /**Metodo que devuelve todos los lotes que estan presentes en la lista
     * @return
     */
    public Iterator<Lote> getIterator(){
        Iterator<Lote> it = this.lista.iterator();
        return it;
    }
}
