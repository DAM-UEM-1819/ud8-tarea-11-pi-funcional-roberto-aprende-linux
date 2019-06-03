package test;

import static org.junit.Assert.*;

import org.junit.Test;

import vista.Home;

public class TestHome {

	@Test
	public void testNumeroMes() {
		Home home = new Home();
		String resultadoReal = home.numeroMes("FEB");
		String resultadoEsperado = "02";
		assertEquals(resultadoEsperado, resultadoReal);
	}

}
