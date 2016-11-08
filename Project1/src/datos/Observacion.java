package datos;

import exceptions.ArgumentoIlegalException;

import java.util.Calendar;

/**Clase que incluye todos los apartados que debe contener una observacion de un lote.
 * Es comparable ya que estas observaciones deben estar ordenadas segun un criterio.
 */
public class Observacion implements Comparable {
    private String tema = null;
    private Calendar fechaObservacion = null;
    private String legajoEmpleado = null;
    private String texto;

    /**Constructor principal de la clase. Si algun campo no esta completo (es decir, es null) o tiene
     * algun error lanza una excepcion
     * Pos-condicion:
     * - Si todos los parametros cumplen sus condicones, se crea una instancia de la clase. En caso contrario
     * se lanza una ArgumentoIlegalException
     * Invariante:
     * - Si la clase es instanciada correctamente, se puede asumir en todo momento que sus atributos no varian
     * en la utilizacion de metodos.
     * @param tema
     * @param fechaObservacion
     * @param legajoEmpleado
     * @param texto
     * @throws ArgumentoIlegalException exception lanzada si algun parametro es incorrecto
     */
    public Observacion(String tema, Calendar fechaObservacion, String legajoEmpleado,
                       String texto) throws ArgumentoIlegalException {
        super();
        
        assert tema != null : "El tema es null";
        assert fechaObservacion != null : "La fecha de observacion es null";
        assert legajoEmpleado != null : "El legajo del empleado es null";
        assert this.verificaTexto(texto) : "El texto no cumple las condiciones";
        
        if (tema != null)
            this.tema = tema;
        else
            throw new ArgumentoIlegalException("El tema no esta correctamente completado", tema);
        if (fechaObservacion != null)
            this.fechaObservacion = fechaObservacion;
        else
            throw new ArgumentoIlegalException("La fecha de observacion no esta correctamente completada",
                                               fechaObservacion);
        if (legajoEmpleado != null)
            this.legajoEmpleado = legajoEmpleado;
        else
            throw new ArgumentoIlegalException("El legajo del empleado no esta correctamente completado",
                                               legajoEmpleado);
        if (this.verificaTexto(texto))
            this.texto = texto;
        else
            throw new ArgumentoIlegalException("El texto no esta correctamente completado", texto);
    }

    /**Metodo que verifica que el texto de la observacion tenga una longitud menor a 500 caracteres
     * Pre-condicion:
     * - Se asume que el argumento texto es distinto de null
     * @param texto
     * @return
     */
    private boolean verificaTexto(String texto) {
        return (texto.length() <= 500);
    }

    /**Invariante de la clase, se asume que en todo momento luego de su instanciacion, sus atributos son
     * distuntos de null.
     * @return
     */
    public boolean verificacion() {
        return (this.tema != null && this.fechaObservacion != null && this.legajoEmpleado != null &&
                this.texto != null);
    }

    /**Metodo que devuelve los datos de la instancia de clase en forma de String
     * Pos-condicion:
     * - El metodo devuelve un String con el legajo del empleado, el tema y el texto de la observacion
     * @return
     */
    public String toString() {
        return this.legajoEmpleado + " - " + this.tema + " - " + this.texto;
    }

    /**Metodo que compara dos instancias de esta clase.
     * Pre-condicion:
     * - Se asume que el parametro object es distinto de null
     * Pos-condicion:
     * - Devuelve un numero positivo si esta instancia es mayor a la instancia pasada como parametro, 0
     * si son iguales y un numero negativo si esta instancia es menor a la pasada como parametro
     * @param object
     * @return
     */
    @Override
    public int compareTo(Object object) {
        int ret;
        Observacion otra = (Observacion) object;
        int aux = this.tema.compareTo(otra.tema);
        if (aux != 0)
            ret = aux;
        else
            ret = this.fechaObservacion.compareTo(otra.fechaObservacion);
        return ret;
    }
}
