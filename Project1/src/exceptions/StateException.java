package exceptions;

public class StateException
  extends Exception
{
  private String texto;

  public StateException(String mensaje)
  {
    super(mensaje);
  }
}
