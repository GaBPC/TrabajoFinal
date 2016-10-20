package listas;

import datos.Material;

import exceptions.ArgumentoIlegalException;
import exceptions.LengthException;

import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JOptionPane;

import visual.VentanaLogin;

public class ListaMateriales
{
  private HashMap<String, Material> lista;

  public ListaMateriales()
  {
    super();
    this.lista = new HashMap();
  }

  public void agregarMaterial(Material nuevo)
  {
    this.lista.put(nuevo.getCodigo(), nuevo);
  }

  public Material getMaterial(String codigo)
    throws Exception
  {
    if (this.lista.containsKey(codigo))
      return this.lista.get(codigo);
    else
      throw new Exception("El material no se encuentra en la lista");
  }

  public void modificarMaterial(String codigo, float cantidad)
    throws Exception
  {
    if (this.lista.containsKey(codigo))
    {
      Material aux = this.lista.get(codigo);
      aux.setCantidad(cantidad);
    }
    else
      throw new Exception("El material no existe");
  }

  public void borrarMaterial(String codigo)
    throws ArgumentoIlegalException
  {
    if (this.lista.containsKey(codigo))
      this.lista.remove(codigo);
    else
      throw new ArgumentoIlegalException("Campo vacio",codigo);
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

  public void agregarMaterial(String codigo, String descripcion, float cantidad)
    throws ArgumentoIlegalException, LengthException
  {
      if (!lista.containsKey(codigo))
      {
        Material mat = null;

        mat = new Material(codigo, descripcion, cantidad);
        this.agregarMaterial(mat);
      }

      else
      {
        Material mat = lista.get(codigo);
        mat.setCantidad(mat.getCantidad() + cantidad);
        mat.setDescripcion(descripcion);
      }
  }
}
