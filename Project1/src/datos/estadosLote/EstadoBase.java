package datos.estadosLote;

import datos.Lote;

public abstract class EstadoBase implements Estado{
    protected Lote lote;
    
    public EstadoBase(Lote lote) {
        super();
        this.lote = lote;
    }

    @Override
    public boolean isModificable() {
        return false;
    }
    
    @Override
    public boolean isIniciado() 
    {
      return false;
    }
    
    @Override
    public boolean isAceptado()
    {
      return false;
    }
    
    @Override 
    public boolean isEvaluado()
    {
      return false;
    }
}
