package exceptions;

public class ArgumentoIlegalException extends Exception {

    private Object argumentoInvalido;

    public ArgumentoIlegalException(String mensaje, Object argumentoInvalido) {
        super(mensaje);
        this.argumentoInvalido = argumentoInvalido;
    }

    public Object gerArgumentoInvalido() {
        return this.argumentoInvalido;
    }
}
