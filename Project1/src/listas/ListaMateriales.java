package listas;

import datos.Material;

import datos.Verificaciones;

import java.util.HashMap;
import java.util.Iterator;

public class ListaMateriales
{
  private HashMap<String, Material> lista;

  public ListaMateriales()
  {
    super();
    this.lista = new HashMap();
  }

  public Material getMaterial(String codigo)
    throws Exception
  {
    assert Verificaciones.verificaCodigo(codigo) : "Codigo invalido";
    
    Material ret = null;
    if (this.lista.containsKey(codigo))
      ret = this.lista.get(codigo);
    else
      throw new Exception("El material no se encuentra en la lista");
    return ret;
  }

  public void agregarMaterial(Material nuevo)
  {
    assert nuevo != null: "Material nulo";

    this.lista.put(nuevo.getCodigo(), nuevo);
  }

  public void borrarMaterial(String codigo)
    throws Exception
  {
    if (this.lista.containsKey(codigo))
      this.lista.remove(codigo);
    else
      throw new Exception("No contiene al material solicitado");
  }

  public Iterator<Material> getIterator()
  {
    Iterator<Material> it = this.lista
                                .values()
                                .iterator();
    return it;
  }

  public String detalles()
  {
    Iterator<Material> it = this.getIterator();
    String aux = "";
    while (it.hasNext())
    {
      Material mat = it.next();
      aux = aux + mat.detalles() + " unidades\n";
    }
    return aux;
  }

  public int size()
  {
    return this.lista.size();
  }

  public void agregarMaterial(String codigo, String descripcion, double cantidad)
  {
    if (!lista.containsKey(codigo))
    {
      Material mat = new Material(codigo, descripcion, cantidad);
      this.lista.put(mat.getCodigo(), mat);
    }
    else
    {
      Material mat = lista.get(codigo);
      mat.setCantidad(mat.getCantidad() + cantidad);
      mat.setDescripcion(descripcion);
    }
  }
}
