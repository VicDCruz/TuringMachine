/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utm;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author daniel
 */
public class StateTableTest {
    private static StateTable st;
    
    public StateTableTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Antes de la clase:");
        String mt = "10000001010000101000001010000011110000000000000100000100101111111100001010000000";
        st = new StateTable( mt );
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("Después de Clase");
        st = null;
    }
    
    @Before
    public void setUp() {
        System.out.println("Iniciando nueva prueba");
    }
    
    @After
    public void tearDown() {
        System.out.println("Finalizó prueba");
    }

    /**
     * Test of getState method, of class StateTable.
     */
    @Test
    public void testGetState() {
        System.out.println("getState");
        StateTable instance = st;
        String expResult =  "EA|O| M| SE|O| M| SE|\n" +
                            "---------------------\n" +
                            "0  1  R  1  0  I  2  \n" +
                            "1  1  R  2  1  R  3  \n" +
                            "2  1  I  0  0  R  1  \n" +
                            "3  0  R  4  1  R  H  \n" +
                            "4  1  I  2  1  R  0  \n";
        String result = instance.getState();
        assertEquals(expResult, result);
    }

    /**
     * Test of nextStep method, of class StateTable.
     */
    @Test
    public void testNextStep() {
        System.out.println("nextStep");
        char car = '1';
        int state = 0;
        StateTable instance = st;
        String[] expResult = { "0", "I", "2" };
        String[] result = instance.nextStep(car, state);
        assertArrayEquals(expResult, result);
    }
    
}
