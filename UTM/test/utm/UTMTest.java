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
public class UTMTest {
    
    public UTMTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Antes de la clase");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("Terminó la clase");
    }
    
    @Before
    public void setUp() {
        System.out.println("Iniciando nueva prueba");
    }
    
    @After
    public void tearDown() {
        
    }

    /**
     * Test of NewTape method, of class UTM.
     */
    @Test
    public void testNewTape() {
        System.out.println("NewTape");
        String TT = "100000010000000010000010000000001011111100000000";
        String Tape = "000000000000000000000000000000000000000000000000000000";
        int N = 1000;
        int P = 4;
        String expResult = "000011100000000000000000000000000000000000000000000000";
        String result = UTM.NewTape(TT, Tape, N, P);
        assertEquals(expResult, result);
        
        TT = "10000001010000101000001010000011110000000000000100000100101111111100001010000000";
        Tape = "000000000000000000000000000000000000000000000000000000";
        N = 100;
        P = 4;
        expResult = "001101110111010010101000000000000000000000000000000000";
        result = UTM.NewTape(TT, Tape, N, P);
        assertEquals(expResult, result);
        
        // Máquina del PDF de computabilidad
        TT = "10000001110000100100000001000011110000001111111111000001100001000000001100000001";
        Tape = "000000000000000000000000000000000000000000000000000000";
        N = 1000;
        P = 4;
        expResult = "111011110101010101010101010101010101010101010101111111";
        result = UTM.NewTape(TT, Tape, N, P);
        assertEquals(expResult, result);
    }

}
