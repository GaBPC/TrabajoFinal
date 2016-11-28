package junit;

import datos.Material;

import listas.ListaMateriales;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;

public class ListaMaterialesTestCajaBlanca {

    private ListaMateriales lista = null;
    private Material mat = null;

    public ListaMaterialesTestCajaBlanca() {
        super();
    }

    @Before
    public void setUp() {
        this.lista = new ListaMateriales();
        this.mat = new Material("MAT12345", "Prueba", 54);
    }

    @After
    public void tearDown() {
        this.lista = null;
        this.mat = null;
    }

    @Test
    public void detallesTest_1() {
        String ret = this.lista.detalles();
        assertTrue("El metodo detalles no funciona correctamente", ret.equals(""));
    }

    @Test
    public void detallesTest_2() {
        this.lista.agregarMaterial(this.mat);
        String real = this.mat.detalles() + " unidades\n";
        String ret = this.lista.detalles();
        assertTrue("El metodo detalles no funciona correctamente", ret.equals(real));
    }

    @Test
    public void getMaterialTest_1() {
        this.lista.agregarMaterial(this.mat);
        try {
            Material buscado = this.lista.getMaterial("MAT12345");
            assertTrue("El metodo get no funciona correctamente", buscado == this.mat);
        } catch (Exception e) {
            fail("La excepcion no deberia saltar ya que existe el material");
        }
    }

    @Test
    public void getMaterialTest_2() {
        try {
            Material buscado = this.lista.getMaterial("MAT12345");
            fail("La excepcion  deberia saltar ya que no existe el material");
        } catch (Exception e) {
        }
    }

    @Test
    public void borrarMaterialTest_1() {
        this.lista.agregarMaterial(this.mat);
        try {
            Material buscado = this.lista.getMaterial("MAT12345");
        } catch (Exception e) {
            fail("La excepcion no deberia saltar ya que el material se encuentra");
        }
        try {
            this.lista.borrarMaterial("MAT12345");
        } catch (Exception e) {
            fail("La excepcion no deberia saltar ya que el material deberia ser borrado");
        }
        try {
            Material buscado = this.lista.getMaterial("MAT12345");
            fail("La excepcion  deberia saltar ya que el material fue borrado");
        } catch (Exception e) {
        }
    }

    @Test
    public void borrarMaterialTest_2() {
        try {
            Material buscado = this.lista.getMaterial("MAT12345");
            fail("La excepcion  deberia saltar ya que el material no se encuentra");
        } catch (Exception e) {
        }
        try {
            this.lista.borrarMaterial("MAT12345");
            fail("La excepcion  deberia saltar ya que el material no deberia ser borrado por que no existe");
        } catch (Exception e) {
        }
    }

    @Test
    public void agregarMaterialTest_1() {
        this.lista.agregarMaterial("MAT00000", "Prueba", 3);
        try {
            Material buscado = this.lista.getMaterial("MAT00000");
            assertTrue("El metodo no agrego el material correctamente", buscado.getCodigo().equals("MAT00000"));
        } catch (Exception e) {
            fail("El metodo no agrego el material correctamente");
        }
    }

    @Test
    public void agregarMaterialTest_2() {
        this.lista.agregarMaterial("MAT00000", "Prueba", 1);
        this.lista.agregarMaterial("MAT00000", "Cambiado", 6);
        try {
            Material buscado = this.lista.getMaterial("MAT00000");
            assertTrue("El metodo no agrego el material correctamente",
                       buscado.getCodigo().equals("MAT00000") && buscado.getDescripcion().equals("Cambiado") &&
                       buscado.getCantidad() == 6);
        } catch (Exception e) {
            fail("El metodo no agrego el material correctamente, ya que salta la excepcion");
        }
    }
}
