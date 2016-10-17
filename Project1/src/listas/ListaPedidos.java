package listas;

import datos.Pedido;

import exceptions.ArgumentoIlegalException;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

/**Clase donde se almacenan todos los lotes que se van creando.
 * Es una clase singleton ya que "simula" ser una base de datos de los lotes, y todos los
 * cambios deben afectar sobre un solo conjunto de datos.
 */
public class ListaPedidos extends Observable implements Observer {
    private static ListaPedidos _instance = null;

    private ArrayList<Pedido> lista;

    private ArrayList<Observable> pedidosObservados;

    private ListaPedidos() {
        super();
        this.pedidosObservados = new ArrayList();
        this.lista = new ArrayList<Pedido>();
    }

    /**Metodo que devuelve la referencia a la unica instancia posible de la lista de lotes
     * @return
     */
    public static ListaPedidos getInstance() {
        if (_instance == null)
            _instance = new ListaPedidos();
        return _instance;
    }

    /**Metodo que agrega un nuevo lote a la lista
     * @param nuevo
     */
    public void agregarNuevo(Pedido nuevo) {
        this.lista.add(nuevo);
        nuevo.addObserver(this);
        this.pedidosObservados.add(nuevo);
        this.setChanged();
        this.notifyObservers();
    }

    /**Metodo que devuelve todos los lotes que estan presentes en la lista
     * @return
     */
    public Iterator<Pedido> getIterator() {
        Iterator<Pedido> it = this.lista.iterator();
        return it;
    }

    @Override
    public void update(Observable observable, Object object) {
        if (this.pedidosObservados.contains(observable)) {
            this.setChanged();
            this.notifyObservers();
        } else
            throw new IllegalArgumentException("Fatal error");
    }
}
