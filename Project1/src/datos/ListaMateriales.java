package datos;

import java.util.HashMap;
import java.util.Iterator;

public class ListaMateriales {
    private HashMap<String, Material> lista;

    public ListaMateriales() {
        super();
        this.lista = new HashMap();
    }

    public void agregarMaterial(Material nuevo) {
        this.lista.put(nuevo.getCodigo(), nuevo);
    }

    public void modificarMaterial(String codigo, float cantidad) throws Exception {
        if (this.lista.containsKey(codigo)) {
            Material aux = this.lista.get(codigo);
            aux.setCantidad(cantidad);
        } else
            throw new Exception("El material no existe");
    }

    public void borrarMaterial(String codigo) {
        if (this.lista.containsKey(codigo))
            this.lista.remove(codigo);
    }

    public Iterator<Material> getIterator() {
        Iterator<Material> it = this.lista.values().iterator();
        return it;
    }
}
