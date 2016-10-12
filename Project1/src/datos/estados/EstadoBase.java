package datos.estados;

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
}