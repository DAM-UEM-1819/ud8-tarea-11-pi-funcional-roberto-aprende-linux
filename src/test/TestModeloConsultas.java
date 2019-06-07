package test;

import static org.junit.Assert.*;

import org.junit.Test;

import modelo.ModeloConsultas;
import vista.Home;

public class TestModeloConsultas {

	@Test
	public void test() {
		ModeloConsultas modeloConsultas = new ModeloConsultas();
		String resultadoReal = modeloConsultas.consultarPasswdUsuario("DEMO ADMIN", "gnzLDuqKcGxMNKFokfhOew==");
		String resultadoEsperado = "gnzLDuqKcGxMNKFokfhOew==";
		assertEquals(resultadoEsperado, resultadoReal);
	}

}
