package test;

import static org.junit.Assert.*;

import org.junit.Test;

import vista.GestionAcad;
import vista.Home;

public class TestGestionAcad {

	@Test
	public void testNumeroMes() {
		GestionAcad gestionAcad = new GestionAcad();
		String resultadoReal = gestionAcad.numeroMes("FEB");
		String resultadoEsperado = "02";
		assertEquals(resultadoEsperado, resultadoReal);
	}

}
