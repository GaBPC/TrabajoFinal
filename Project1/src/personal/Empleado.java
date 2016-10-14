package personal;

public class Empleado {
    private String legajo = null;
    private String nya = null; //nombre y apellido
    private String sector = null;
    
    public Empleado(String legajo, String nya, String sector) {
        super();
        if(this.verificaLegajo(legajo))
          this.legajo = legajo;
        if(this.verificaNombreyApellido(nya))
          this.nya = nya;
        if(this.verificaSector(sector))
          this.sector = sector;
    }
    
    private boolean verificaLegajo(String legajo)
    {
      boolean ret = false;
      String aux = legajo.substring(0, 3);
      if (aux.compareTo("LEG") == 0)
      {
        int num = Integer.parseInt(legajo.substring(3).trim());
        if (num >= 0 && num <= 999999)
          ret = true;
      }
      return ret;
    }
    
    private boolean verificaNombreyApellido(String nya)
    {
      return (nya.length() <= 100);
    }
    
    private boolean verificaSector(String sector)
    {
      return (sector != null);
    }
}
