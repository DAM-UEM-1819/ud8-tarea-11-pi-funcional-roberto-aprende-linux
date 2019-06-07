package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import modelo.Modelo;

public class TestModelo {

	@Test
	public void testGenerarMD5() {
		Modelo modelo = new Modelo();
		String resultadoReal = modelo.generarMD5("12345");
		String resultadoEsperado = "gnzLDuqKcGxMNKFokfhOew==";
		assertEquals(resultadoEsperado, resultadoReal);
	}

}
