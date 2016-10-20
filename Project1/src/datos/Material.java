package datos;

import exceptions.ArgumentoIlegalException;
import exceptions.LengthException;

public class Material
{

  private String codigo = null;
  private String descripcion = null;
  private float cantidad = 0.0f;

  public Material(String codigo, String descripcion, float cantidad)
    throws ArgumentoIlegalException, LengthException
  {
    super();
    if (this.verificaCodigo(codigo))
      this.codigo = codigo;
    if (this.verificaDescripcion(descripcion))
      this.descripcion = descripcion;
    if (this.verificaCantidad(cantidad))
      this.cantidad = cantidad;
  }

  private boolean verificaCodigo(String codigo)
    throws ArgumentoIlegalException
  {
    boolean ret = false;
    if (codigo.length() == 8)
    {
      String aux = codigo.substring(0, 3);
      if (aux.compareTo("MAT") == 0)
      {
        int num = Integer.parseInt(codigo.substring(3).trim());
        if (num >= 0 && num <= 999999)
          ret = true;
        else
          throw new ArgumentoIlegalException("Numero fuera de rango", num);
      }
      else
        throw new ArgumentoIlegalException("El codigo no contiene \"MAT\"", codigo);
    }
    else
      throw new ArgumentoIlegalException("El numero debe tener 6 digitos", codigo);
    return ret;
  }

  private boolean verificaDescripcion(String descripcion)
    throws LengthException
  {
    boolean ret = false;
    if (descripcion.length() <= 100)
      ret = true;
    else
      throw new LengthException(descripcion, "El texto tiene mas de 100 caracteres");
    return ret;
  }

  private boolean verificaCantidad(float cantidad)
  {
    return (cantidad > 0.0 && cantidad <= 999.9999);
  }

  public void setCodigo(String codigo)
    throws ArgumentoIlegalException
  {
    if (this.verificaCodigo(codigo))
      this.codigo = codigo;
  }

  public String getCodigo()
  {
    return codigo;
  }

  public void setDescripcion(String descripcion)
    throws LengthException
  {
    if (this.verificaDescripcion(descripcion))
      this.descripcion = descripcion;
  }

  public String getDescripcion()
  {
    return descripcion;
  }

  public void setCantidad(float cantidad)
  {
    if (this.verificaCantidad(cantidad))
      this.cantidad = cantidad;
  }

  public float getCantidad()
  {
    return cantidad;
  }

  public String toString()
  {
    return this.codigo;
  }

  public String detalles()
  {
    String aux = "Material: " + this.codigo + " " + this.cantidad;
    return aux;
  }
}
