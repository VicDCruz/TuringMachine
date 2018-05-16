package mx.itam.fmc.automata;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HammingDistanceTest {
	@Test
	public void test() {
		assertEquals("0 caracteres inválidos", 0, HammingDistance.getHD("", ""));
		assertEquals("0 caracteres inválidos",
				0, HammingDistance.getHD("00000000", "00000000"));
		assertEquals("1 caracteres inválidos",
				1, HammingDistance.getHD("00000001", "00000000"));
		assertEquals("3 caracteres inválidos",
				3, HammingDistance.getHD("10010100", "00000000"));
		assertEquals("3 caracteres inválidos",
				3, HammingDistance.getHD("10010010", "00000000"));
		assertEquals("0 caracteres inválidos",
				0, HammingDistance.getHD("10010010", "10010010"));
		assertEquals("2 caracteres inválidos",
				2, HammingDistance.getHD("10010010", "10011110"));
	}

}
