package datos;

import exceptions.LengthException;

public class Material {
    private String codigo = null;
    private String descripcion = null;
    private float cantidad = 0.0f;
    
    public Material() {
        super();
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setDescripcion(String descripcion) throws LengthException {
        if(descripcion.length() <= 100)
            this.descripcion = descripcion;
        else
            throw new LengthException(descripcion,"El texto tiene mas de 100 caracteres");
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public float getCantidad() {
        return cantidad;
    }
}
