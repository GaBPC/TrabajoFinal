package junit;

import datos.Verificaciones;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;

public class VerificacionesTestCajaBlanca
{  
  @Before
  public void setUp()
  {
  }
  
  @After
  public void tearDown()
  {
  }
  
  @Test
  public void testVerifica_Camino1()
  {
    boolean ret = Verificaciones.verifica("TIP000001");
    assertTrue("Verificacion erronea_1",ret);
  }
  
  @Test
  public void testVerifica_Camino3()
  {
    boolean ret = Verificaciones.verifica("TIP-000005");
    assertFalse("Verificacion erronea_3",ret);
  }
  
  @Test
  public void testVerifica_Camino4()
  {
    boolean ret = Verificaciones.verifica("TIP0000051");
    assertFalse("Verificacion erronea_4",ret);
  }
  
  @Test
  public void testVerifica_Camino5()
  {
    boolean ret = Verificaciones.verifica(null);
    assertFalse("Verificacion erronea_5",ret);
  }
  
  @Test
  public void testVerificaNumeroLote_Camino1_1()
  {
    boolean ret = Verificaciones.verificaNumeroLote("LOT0000001");
    assertFalse("Verificacion numeroLote erronea_1_2",ret);
  }
  
  @Test
  public void testVerificaNumeroLote_Camino1_2()
  {
    boolean ret = Verificaciones.verificaNumeroLote("LOT000001");
    assertTrue("Verificacion numeroLote erronea_1_2",ret);
  }
  
  @Test
  public void testVerificaNumeroLote_Camino2()
  {
    boolean ret = Verificaciones.verificaNumeroLote("PED000001");
    assertFalse("Verificacion numeroLote erronea_2",ret);
  }
  
  @Test
  public void testVerificaNumeroLote_Camino3()
  {
    boolean ret = Verificaciones.verificaNumeroLote(null);
    assertFalse("Verificacion numeroLote erronea_3",ret);
  }
  @Test
  public void testVerificaCodigo_Camino1()
  {
    boolean ret = Verificaciones.verificaCodigo(null);
    assertFalse("Verificaciones codigoMaterial erronea_1",ret);
  }
  
  @Test
  public void testVerificaCodigo_Camino2()
  {
    boolean ret = Verificaciones.verificaCodigo("MAT000001");
    assertFalse("Verificacion codigoMaterial erronea_2",ret);
  }
  
  @Test
  public void testVerificaCodigo_Camino3()
  {
    boolean ret = Verificaciones.verificaCodigo("PED00004");
    assertFalse("Verificacion codigoMaterial erronea_3",ret);
  }
  
  
  @Test
  public void testVerificaCodigo_Camino4()
  {
    boolean ret = Verificaciones.verificaCodigo("MAT-0004");
    assertFalse("Verificacion codigoMaterial erronea_4",ret);
  }
  
  @Test
  public void testVerificaCodigo_Camino6()
  {
    boolean ret = Verificaciones.verificaCodigo("MAT12345");
    assertTrue("Verificacion codigoMaterial erronea_6",ret);
  }  
  
  @Test
  public void testVerificaCantidad_Camino1()
  {
    boolean ret = Verificaciones.verificaCantidad(-1);
    assertFalse("Verificacion cantidad erronea_1",ret);
  }
  
  @Test
  public void testVerificaCantidad_Camino2()
  {
    boolean ret = Verificaciones.verificaCantidad(1000);
    assertFalse("Verificacion cantidad erronea_2",ret);
  }
  
  @Test
  public void testVerificaCantidad_Camino3()
  {
    boolean ret = Verificaciones.verificaCantidad(100);
    assertTrue("Verificacion cantidad erronea_3",ret);
  }
  
  @Test
  public void testVerificaDescripcion_Camino1()
  {
    boolean ret = Verificaciones.verificaDescripcion("hola");
    assertTrue("Verificacion descripcion erronea_1",ret);
  }
  
  @Test
  public void testVerificaDescripcion_Camino2()
  {
    String descripcion = "esta es una descripcion de longitud 100 para verificar que funcione bien la verificacion de la descripcion valga la redundancia";
    boolean ret = Verificaciones.verificaDescripcion(descripcion);
    assertFalse("Verificacion descripcion erronea_2",ret);
  }
  
  @Test
  public void testVerificaDescripcion_Camino3()
  {
    boolean ret = Verificaciones.verificaDescripcion("");
    assertFalse("Verificacion descripcion erronea_3",ret);
  }
  
  @Test
  public void testVerificaDescripcion_Camino4()
  {
    boolean ret = Verificaciones.verificaDescripcion(null);
    assertFalse("Verificacion descripcion erronea_4",ret);
  }
  
  @Test
  public void testVerificaSector_Camino1()
  {
    boolean ret = Verificaciones.verificaSector("Ventas");
    assertTrue("Verificacion sector erronea_1",ret);
  }
  
  @Test
  public void testVerificaSector_Camino2()
  {
    boolean ret = Verificaciones.verificaSector("Manufactura");
    assertFalse("Verificacion sector erronea_2",ret);
  }
  
  @Test
  public void testVerificaSector_Camino3()
  {
    boolean ret = Verificaciones.verificaSector(null);
    assertFalse("Verificacion sector erronea_3",ret);
  }
  
  @Test
  public void testVerificaNumeroPedido_Camino1()
  {
    boolean ret = Verificaciones.verificaNumeroPedido(null);
    assertFalse("Verificacion numeroPedido erronea_1",ret);
  }
  
  @Test
  public void testVerificaNumeroPedido_Camino2()
  {
    boolean ret = Verificaciones.verificaNumeroPedido("PED0000000");
    assertFalse("Verificacion numeroPedido erronea_2",ret);
  }
  
  @Test
  public void testVerificaNumeroPedido_Camino3()
  {
    boolean ret = Verificaciones.verificaNumeroPedido("TIP000000");
    assertFalse("Verificacion numeroPedido erronea_3",ret);
  }
  
  @Test
  public void testVerificaNumeroPedido_Camino4()
  {
    boolean ret = Verificaciones.verificaNumeroPedido("PED000000");
    assertTrue("Verificacion numeroPedido erronea_4",ret);
  }
  
  @Test
  public void testVerificaCantProduccion_Camino1()
  {
    boolean ret = Verificaciones.verificaCantProduccion(-4);
    assertFalse("Verificacion cantProduccion erronea_1",ret);
  }
  
  @Test
  public void testVerificaCantProduccion_Camino2()
  {
    boolean ret = Verificaciones.verificaCantProduccion(1000);
    assertFalse("Verificacion cantProduccion erronea_2",ret);
  }
  
  @Test
  public void testVerificaCantProduccion_Camino3()
  {
    boolean ret = Verificaciones.verificaCantProduccion(100);
    assertTrue("Verificacion cantProduccion erronea_3",ret);
  }
  
  @Test
  public void testVerificaTexto_Camino1()
  {
    boolean ret = Verificaciones.verificaTexto(null);
    assertFalse("Verificacion texto erronea_1",ret);
  }
  
  @Test
  public void testVerificaTexto_Camino2()
  {
    String texto = "este es un texto que debe tener longitud mayor a 500 para poder verificar que se cumpla que el verificador de verificaciones verifique correctamente que la verificacion" +
      "de este texto se correcta y que funcione todo bien para que el programa tenga robustez y que todo funione joya porque sino todo andaria mal si los verificadores de verificaciones no" +
      "verificaran correctamente todas las verificaciones seria incorrectas a tal punto que nada pudiera funcionar bien debido a las verificaciones invalidadas, todavia faltan demasiadas  " +
      "palabras para llegar a las 500 por eso debo seguir escribiendo mucho, este texto es muy largo y espero que haga que todo funcione bien y nada falle porque sino hay que arreglar algo ";
    boolean ret = Verificaciones.verificaTexto(texto);
    assertFalse("Verificacion texto erronea_2",ret);
  }
  
  @Test
  public void testVerificaTexto_Camino3()
  {
    boolean ret = Verificaciones.verificaTexto("hola");
    assertTrue("Verificacion texto erronea_3",ret);
  }
  
  @Test
  public void testVerificaNombreyApellido_Camino1()
  {
    boolean ret = Verificaciones.verificaNombreyApellido(null);
    assertFalse("Verificacion nya erronea_1",ret);
  }
  
  @Test
  public void testVerificaNombreyApellido_Camino2()
  {
    boolean ret = Verificaciones.verificaNombreyApellido("");
    assertFalse("Verificacion nya erronea_2",ret);
  }
  
  @Test
  public void testVerificaNombreyApellido_Camino3()
  {
    boolean ret = Verificaciones.verificaNombreyApellido("este es un nombre que debe tener una longitud 100 por lo cual debo alargar esto para poder probar que funcione bien esta verificacion");
    assertFalse("Verificacion nya erronea_3",ret);
  }
  
  @Test
  public void testVerificaNombreyApellido_Camino4()
  {
    boolean ret = Verificaciones.verificaNombreyApellido("hola");
    assertTrue("Verificacion nya erronea_4",ret);
  }
  
  @Test
  public void testVerificaTipoCodigo_Camino1()
  {
    boolean ret = Verificaciones.verificaTipoCodigo(null);
    assertFalse("Verificacion tipoCodigo erronea_1",ret);
  }  
  
  @Test
  public void testVerificaTipoCodigo_Camino2()
  {
    boolean ret = Verificaciones.verificaTipoCodigo("TIP2");
    assertFalse("Verificacion tipoCodigo erronea_2",ret);
  }
  
  @Test
  public void testVerificaTipoCodigo_Camino3()
  {
    boolean ret = Verificaciones.verificaTipoCodigo("PED000000");
    assertFalse("Verificacion tipoCodigo erronea_3",ret);
  }
  
  @Test
  public void testVerificaTipoCodigo_Camino4()
  {
    boolean ret = Verificaciones.verificaTipoCodigo("TIP123456");
    assertTrue("Verificacion tipoCodigo erronea_4",ret);
  }
  
  @Test
  public void testVerificaTipoProducto_Camino1()
  {
    boolean ret = Verificaciones.verificaTipoProducto(null);
    assertFalse("Verificacion tipoProducto erronea_1",ret);
  }
  
  @Test
  public void testVerificaTipoProducto_Camino2()
  {
    boolean ret = Verificaciones.verificaTipoProducto("Juego");
    assertFalse("Verificacion tipoProducto erronea_2",ret);
  }
  
  @Test
  public void testVerificaTipoProducto_Camino3()
  {
    boolean ret = Verificaciones.verificaTipoProducto("Flipper");
    assertTrue("Verificacion tipoProducto erronea_3",ret);
  }
  
  @Test
  public void testVerificaNumeroLegajo_Camino1()
  {
    boolean ret = Verificaciones.verificaNumeroLegajo(null);
    assertFalse("Verificacion numeroLegajo erronea_1",ret);
  }
  
  @Test
  public void testVerificaNumeroLegajo_Camino2()
  {
    boolean ret = Verificaciones.verificaNumeroLegajo("LEG");
    assertFalse("Verificacion numeroLegajo erronea_2",ret);
  }
  
  @Test
  public void testVerificaNumeroLegajo_Camino3()
  {
    boolean ret = Verificaciones.verificaNumeroLegajo("TIP000000");
    assertFalse("Verificacion numeroLegajo erronea_3",ret);
  }
  
  @Test
  public void testVerificaNumeroLegajo_Camino4()
  {
    boolean ret = Verificaciones.verificaNumeroLegajo("LEG000000");
    assertTrue("Verificacion numeroLegajo erronea_4",ret);
  }
}
