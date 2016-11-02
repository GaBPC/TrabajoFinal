package listas;

import exceptions.ArgumentoIlegalException;

import java.util.ArrayList;
import java.util.HashMap;

import personal.Empleado;

public class ListaEmpleados
{
  public static final String VENTAS = "Ventas";
  public static final String PRODUCCION = "Produccion";
  public static final String CONTABILIDAD = "Contabilidad";
  public static final String INSPECCION = "Inspeccion y Calidad";

  private static ListaEmpleados _instance = null;

  private HashMap<String, Empleado> empleados;

  private ListaEmpleados()
  {
    super();
    this.empleados = new HashMap<>();
    try
    {
      empleados.put("LEG123456", new Empleado("LEG123456", "Prieto Gabriel", VENTAS));
      empleados.put("LEG000000", new Empleado("LEG000000", "Cassanelli Rodrigo", VENTAS));
      empleados.put("LEG456789", new Empleado("LEG456789", "Colautti Bruno", PRODUCCION));
      empleados.put("LEG111111", new Empleado("LEG111111", "Coppes Lucia", PRODUCCION));
      empleados.put("LEG333333", new Empleado("LEG333333", "Ruiz Gonzalo", CONTABILIDAD));
      empleados.put("LEG888888", new Empleado("LEG888888", "Khun Franco", INSPECCION));
    }
    catch (ArgumentoIlegalException e)
    {
      System.out.println(e.getMessage());
    }
  }

  public static ListaEmpleados getInstance()
  {
    if (_instance == null)
      _instance = new ListaEmpleados();
    return _instance;
  }

  public Empleado buscar(String legajo)
    throws Exception
  {
    if (this.empleados.containsKey(legajo))
    {
      return this.empleados.get(legajo);
    }
    throw new Exception("El empleado no es parte de la empresa");
  }

}
