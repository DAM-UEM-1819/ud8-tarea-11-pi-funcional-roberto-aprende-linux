package enums;

public enum ActividadTipo {
	Escenario_complejo("Escenario complejo"), Taller_habilidades("Taller de habilidades");

	private String name;

	ActividadTipo(String name) {
		this.name = name;
	}


	// Optionally and/or additionally, toString.
	@Override
	public String toString() {
		return name;
	}
	

}
