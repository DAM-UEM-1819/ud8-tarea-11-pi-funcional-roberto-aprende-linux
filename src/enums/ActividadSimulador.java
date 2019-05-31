package enums;

public enum ActividadSimulador {
	Vacio("-"),ISTAN("ISTAN"),OTRO("OTRO"),SV("SV"),IOT("IOT");

	private String name;

	ActividadSimulador(String name) {
		this.name = name;
	}


	// Optionally and/or additionally, toString.
	@Override
	public String toString() {
		return name;
	}
	

}
