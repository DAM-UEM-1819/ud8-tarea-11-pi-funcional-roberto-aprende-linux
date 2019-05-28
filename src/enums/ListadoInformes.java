package enums;

public enum ListadoInformes {
	Horas_Totales_Actividad("Horas totales actividad"),
	Horas_Actividad_Titulacion("Horas actividad titulacion"),
	Horas_Actividad_Titulacion_Y_Curso("Horas actividad titulacion y curso"),
	Horas_Actividad_Titulacion_Y_Asignatura("Horas actividad titulacion y asignatura"),
	Horas_Actividad_Profesor("Horas actividad profesor"),
	Horas_Actividad_Sala("Horas actividad sala"),
	Horas_Actividad_Tipo_Actividad("Horas actividad tipo actividad"),
	Horas_Actividad_Meses("Horas actividad meses"),
	Horas_Actividad_Semestres("Horas actividad semestres"),
	Horas_Actor_Totales_En_Acad("Horas actor totales en acad"),
	Horas_Actor_Titulacion_Y_Mes("Horas actor titulacion y mes"),
	Horas_Actor_Titulacion_Y_Acad("Horas actor titulacion y acad"),
	Listado_Alumnos_Segun_Asignatura_Y_Grupo_Activos("Listado alumnos segun asignatura y grupo activos"),
	Listado_Alumnos_Segun_Nombre_Actividad("Listado alumnos segun nombre actividad"),
	Listado_Profesores_Segun_Titulacion("Listado profesores segun titulacion");

	private String name;

	ListadoInformes(String name) {
		this.name = name;
	}

	// Optionally and/or additionally, toString.
	@Override
	public String toString() {
		return name;
	}

}
