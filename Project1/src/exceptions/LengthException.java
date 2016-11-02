package exceptions;

public class LengthException
  extends Exception
{
  private String texto;

  public LengthException(String texto, String mensaje)
  {
    super(mensaje);
    this.texto = texto;
  }

  public String getObject()
  {
    return this.texto;
  }
}
