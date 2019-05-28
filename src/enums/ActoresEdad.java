package enums;

public enum ActoresEdad {
	Adolescente("Adolescente"), Mediana_edad("Mediana edad"), Mayor_de_60_años("Mayor de 60 años");

	private String name;

	ActoresEdad(String name) {
		this.name = name;
	}


	// Optionally and/or additionally, toString.
	@Override
	public String toString() {
		return name;
	}

}
