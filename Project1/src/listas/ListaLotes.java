package listas;

import datos.Lote;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

public class ListaLotes extends Observable{
    private static ListaLotes _instance = null;
    
    private ArrayList<Lote> lista;
    
    private int numeroLote = 0;
    
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
    
    public int getProximoNumeroLote(){
        return this.numeroLote;
    }

    /**Metodo que agrega un nuevo lote a la lista
     * @param nuevo
     */
    public void agregarNuevo(Lote nuevo) {
        this.lista.add(nuevo);
        this.numeroLote++;
    }

    /**Metodo que devuelve todos los lotes que estan presentes en la lista
     * @return
     */
    public Iterator<Lote> getIterator(){
        Iterator<Lote> it = this.lista.iterator();
        return it;
    }
    
    public void borrarLote(Lote lot)
    {
      this.lista.remove(lot);
    }
}
