package junit;

import datos.Material;
import datos.Pedido;

import java.util.GregorianCalendar;

import java.util.Iterator;

import listas.ListaMateriales;
import listas.ListaPedidos;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;

public class ListaMaterialesTestCajaNegra {

    private ListaMateriales lista = null;
    private Material material = null;
    private Material materialInferior = null;
    private Material materialSuperior = null;

    public ListaMaterialesTestCajaNegra() {
        super();
    }

    @Before
    public void setUp() {
        this.lista = new ListaMateriales();
        this.material = new Material("MAT12345", "Esto es una prueba", 20);
        this.materialInferior = new Material("MAT00000", "Esto es una prueba", 20);
        this.materialSuperior = new Material("MAT99999", "Esto es una prueba", 20);
    }

    @After
    public void tearDown() {
        this.lista = null;
        this.material = null;
        this.materialInferior = null;
        this.materialSuperior = null;
    }

    @Test
    public void agregarMaterialTest_1() {
        boolean bool = false;
        this.lista.agregarMaterial(this.material);
        Iterator it = this.lista.getIterator();
        while (it.hasNext()) {
            if (it.next() == this.material)
                bool = true;
        }
        assertTrue("El material no fue agregado", bool == true);
    }
    
    @Test
    public void getMaterialTest_1() {
        this.lista.agregarMaterial(this.material);
        try {
            Material aux = this.lista.getMaterial("MAT12345");
            assertTrue("El metodo get no funciona correctamente", aux == this.material);
        } catch (Exception e) {
            fail("La excepcion no deberia producirse");
        }
    }

    @Test
    public void getMaterialTest_2() {
        this.lista.agregarMaterial(this.materialInferior);
        try {
            Material aux = this.lista.getMaterial("MAT00000");
            assertTrue("El metodo get no funciona correctamente", aux == this.materialInferior);
        } catch (Exception e) {
            fail("La excepcion no deberia producirse");
        }
    }

    @Test
    public void getMaterialTest_3() {
        this.lista.agregarMaterial(this.materialSuperior);
        try {
            Material aux = this.lista.getMaterial("MAT99999");
            assertTrue("El metodo get no funciona correctamente", aux == this.materialSuperior);
        } catch (Exception e) {
            fail("La excepcion no deberia producirse");
        }
    }

    @Test
    public void borrarMaterialTest_1() {
        boolean bool = false;
        this.lista.agregarMaterial(this.material);
        try {
            this.lista.borrarMaterial("MAT12345");
        } catch (Exception e) {
            fail("La excepcion no deberia producirse");
        }
        Iterator it = this.lista.getIterator();
        while(it.hasNext()){
            if(it.next() == this.material)
                bool = true;
        }
        assertTrue("El material no fue borrado", bool == false);
    }
    
    @Test
    public void borrarMaterialTest_2() {
        boolean bool = false;
        this.lista.agregarMaterial(this.materialInferior);
        try {
            this.lista.borrarMaterial("MAT00000");
        } catch (Exception e) {
            fail("La excepcion no deberia producirse");
        }
        Iterator it = this.lista.getIterator();
        while(it.hasNext()){
            if(it.next() == this.material)
                bool = true;
        }
        assertTrue("El material no fue borrado", bool == false);
    }
    
    @Test
    public void borrarMaterialTest_3() {
        boolean bool = false;
        this.lista.agregarMaterial(this.materialSuperior);
        try {
            this.lista.borrarMaterial("MAT99999");
        } catch (Exception e) {
            fail("La excepcion no deberia producirse");
        }
        Iterator it = this.lista.getIterator();
        while(it.hasNext()){
            if(it.next() == this.material)
                bool = true;
        }
        assertTrue("El material no fue borrado", bool == false);
    }
    
    @Test
    public void agregarMaterialTest_2(){
        this.lista.agregarMaterial("MAT12345", "Esto es una prueba", 20);
        try {
            this.lista.getMaterial("MAT12345");
        } catch (Exception e) {
            fail("El material no fue agregado ya que no se encuentra");
        }
    }
}
