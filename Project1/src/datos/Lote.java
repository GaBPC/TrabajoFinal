package datos;

import exceptions.ArgumentoIlegalException;

public class Lote implements ResumenClase {
    private Pedido pedido = null;
    private String numeroLote = null;

    public Lote(Pedido pedido, String numeroLote) throws ArgumentoIlegalException {
        super();
        if (pedido != null)
            this.pedido = pedido;
        else
            throw new ArgumentoIlegalException("El pedido es null", pedido);
        if (this.verificaNumeroLote(numeroLote))
            this.numeroLote = numeroLote;
    }

    /**Metodo que verifica si el numero cumple con las restricciones de longitud
     * @param str
     * @return
     * @throws ArgumentoIlegalException
     */
    private boolean verifica(String str) throws ArgumentoIlegalException {
        boolean ret = false;
        if (str.length() == 9) {
            int num = Integer.parseInt(str.substring(3).trim());
            if (num >= 0 && num <= 999999)
                ret = true;
            else
                throw new ArgumentoIlegalException("El numero esta fuera de rango", num);
        } else
            throw new ArgumentoIlegalException("El numero debe tener 6 digitos", str);
        return ret;
    }

    /**Metodo que verifica si el numero de lote cumple con la restriccion de longitud y que ademas
     * incluya el prefijo LOT
     * @param numeroLote
     * @return
     * @throws ArgumentoIlegalException
     */
    private boolean verificaNumeroLote(String numeroLote) throws ArgumentoIlegalException {
        boolean ret = false;
        if (numeroLote == null)
            ret = false;
        else {
            String aux = numeroLote.substring(0, 3);
            if (aux.compareTo("LOT") == 0)
                ret = verifica(numeroLote);
            else
                throw new ArgumentoIlegalException("El numero de lote no contiene \"LOT\"", numeroLote);
        }
        return ret;
    }

    public String toString() {
        return this.pedido.toString();
    }

    public String detalles() {
        String ret = "";
        try {
            ret = this.pedido.detalles();
            ret += "\nNumero de lote: " + ((this.verificaNumeroLote(this.numeroLote)) ? this.numeroLote : " - ");
        } catch (ArgumentoIlegalException e) {
        }
        return ret;
    }
}