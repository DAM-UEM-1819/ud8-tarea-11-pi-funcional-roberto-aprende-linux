DROP TABLE actividad CASCADE CONSTRAINTS;

DROP TABLE actor CASCADE CONSTRAINTS;

DROP TABLE actua CASCADE CONSTRAINTS;

DROP TABLE alumno CASCADE CONSTRAINTS;

DROP TABLE asignatura CASCADE CONSTRAINTS;

DROP TABLE acad CASCADE CONSTRAINTS;

DROP TABLE matricula CASCADE CONSTRAINTS;

DROP TABLE participa CASCADE CONSTRAINTS;

DROP TABLE profesor CASCADE CONSTRAINTS;

DROP TABLE realiza CASCADE CONSTRAINTS;

DROP TABLE registro CASCADE CONSTRAINTS;

DROP TABLE sala CASCADE CONSTRAINTS;

DROP TABLE ocupa CASCADE CONSTRAINTS;

DROP TABLE Users CASCADE CONSTRAINTS;

CREATE TABLE actividad (
    nombre                  VARCHAR2(40) NOT NULL,
    tipo_actividad          VARCHAR2(30) NOT NULL,
    tipo_sala               VARCHAR2(30) NOT NULL,
    documentacion_tecnica   NUMBER NOT NULL,
    horas_actividad         NUMBER(2) NOT NULL,
    asignatura_codigo       NUMBER(5) NOT NULL,
    simulador               VARCHAR2(10),
    acad                    VARCHAR2(5)
);

ALTER TABLE actividad ADD CONSTRAINT actividad_pk PRIMARY KEY ( nombre );

CREATE TABLE actor (
    edad         VARCHAR2(20) NOT NULL,
    genero       VARCHAR2(15) NOT NULL,
    idioma       VARCHAR2(15) NOT NULL,
    nombre       VARCHAR2(50) NOT NULL,
    Cod_Actor    NUMBER(4) NOT NULL,
    complexion   VARCHAR2(30),
    activo        NUMBER(1) DEFAULT 1 NOT NULL
);

ALTER TABLE actor ADD CONSTRAINT actor_pk PRIMARY KEY ( Cod_Actor );

CREATE TABLE actua (
    Cod_Actor               NUMBER(4) NOT NULL,
    registro_cod_registro   NUMBER(5) NOT NULL,
    info                    VARCHAR2(100) NOT NULL,
    horas_actor             NUMBER(2) NOT NULL
);

ALTER TABLE actua ADD CONSTRAINT actua_pk PRIMARY KEY ( Cod_Actor,
                                                        registro_cod_registro );

CREATE TABLE alumno (
    exp      NUMBER(30) NOT NULL,
    nombre   VARCHAR2(50) NOT NULL,
    activo   NUMBER(1) DEFAULT 1 NOT NULL
);

ALTER TABLE alumno ADD CONSTRAINT alumno_pk PRIMARY KEY ( exp );

CREATE TABLE asignatura (
    codigo       NUMBER(5) NOT NULL,
    nombre       VARCHAR2(30),
    titulacion   VARCHAR2(30),
    curso        NUMBER(1)
);

ALTER TABLE asignatura ADD CONSTRAINT asignatura_pk PRIMARY KEY ( codigo );

CREATE TABLE acad (
    acad   VARCHAR2(5) NOT NULL,
	  sem1   DATE NOT NULL,
	  sem2   DATE NOT NULL
);

ALTER TABLE acad ADD CONSTRAINT acad_pk PRIMARY KEY ( acad );

CREATE TABLE matricula (
    alumno_exp          NUMBER(30) NOT NULL,
    asignatura_codigo   NUMBER(5) NOT NULL,
    acad                VARCHAR2(5) NOT NULL,
    cod_grupo           VARCHAR2(5) NOT  NULL

);

ALTER TABLE matricula
    ADD CONSTRAINT matricula_pk PRIMARY KEY ( alumno_exp,
                                              asignatura_codigo,
                                              acad );

CREATE TABLE participa (
    alumno_exp              NUMBER(30) NOT NULL,
    registro_cod_registro   NUMBER(5) NOT NULL,
    nota_alumnos            NUMBER(4, 2) NOT NULL

);

ALTER TABLE participa ADD CONSTRAINT participa_pk PRIMARY KEY ( alumno_exp,
                                                                registro_cod_registro );

CREATE TABLE profesor (
    relacion_laboral   VARCHAR2(10) NOT NULL,
    numero             NUMBER(5) NOT NULL,
    dni                VARCHAR2(9) NOT NULL,
    mail1              VARCHAR2(30) NOT NULL,
    mail2              VARCHAR2(30),
    tlf1               NUMBER(13),
    tlf2               NUMBER(13),
    nombre             VARCHAR2(15) NOT NULL,
    apellido1          VARCHAR2(15) NOT NULL,
    apellido2          VARCHAR2(15) NOT NULL,
    activo             NUMBER(1) DEFAULT 1,
    titulacion         VARCHAR2(30)
);

ALTER TABLE profesor ADD CONSTRAINT profesor_pk PRIMARY KEY ( numero );

CREATE TABLE realiza (
    registro_cod_registro   NUMBER(5) NOT NULL,
    profesor_numero         NUMBER(5) NOT NULL
);

ALTER TABLE realiza ADD CONSTRAINT realiza_pk PRIMARY KEY ( registro_cod_registro,
                                                            profesor_numero );

CREATE TABLE registro (
    cod_registro       NUMBER(5) NOT NULL,
    fecha              DATE NOT NULL,
    hora               VARCHAR2(5) NOT NULL,
    horas_profesor     NUMBER(2) NOT NULL,
    actividad_nombre   VARCHAR2(40) NOT NULL,
    cod_grupo          VARCHAR2(5) NOT  NULL
);

ALTER TABLE registro ADD CONSTRAINT registro_pk PRIMARY KEY ( cod_registro );


CREATE TABLE sala(
    cod_sala      NUMBER(5) NOT NULL,
    tipo_sala     VARCHAR2(30) NOT NULL,
    numero        NUMBER(5) NOT NULL,
    capacidad     NUMBER(30) NOT NULL
);

ALTER TABLE sala ADD CONSTRAINT sala_pk PRIMARY KEY ( cod_sala );

CREATE TABLE ocupa(
    cod_sala      NUMBER(5) NOT NULL,
    registro_cod_registro   NUMBER(5) NOT NULL

);

ALTER TABLE ocupa ADD CONSTRAINT ocupa_pk PRIMARY KEY ( registro_cod_registro,
                                                            cod_sala );

CREATE TABLE Users(
    usr 		VARCHAR2(20) NOT NULL,
    pwd 		VARCHAR2(100) NOT NULL,
    rol 		VARCHAR2(20) NOT NULL,
    email   VARCHAR2(50) NOT NULL
);

ALTER TABLE Users ADD CONSTRAINT users_pk PRIMARY KEY(usr);

--JFK--
ALTER TABLE actividad
    ADD CONSTRAINT actividad_asignatura_fk FOREIGN KEY ( asignatura_codigo )
        REFERENCES asignatura ( codigo );

ALTER TABLE actua
    ADD CONSTRAINT actua_actor_fk FOREIGN KEY ( Cod_Actor )
        REFERENCES actor ( Cod_Actor );

ALTER TABLE actua
    ADD CONSTRAINT actua_registro_fk FOREIGN KEY ( registro_cod_registro )
        REFERENCES registro ( cod_registro );

-- Error - Foreign Key Grupo_Matricula_FK has no columns

ALTER TABLE matricula
    ADD CONSTRAINT matricula_alumno_fk FOREIGN KEY ( alumno_exp )
        REFERENCES alumno ( exp );

ALTER TABLE matricula
    ADD CONSTRAINT matricula_asignatura_fk FOREIGN KEY ( asignatura_codigo )
        REFERENCES asignatura ( codigo );

ALTER TABLE matricula
    ADD CONSTRAINT matricula_acad_fk FOREIGN KEY ( acad)
        REFERENCES acad ( acad );

ALTER TABLE participa
    ADD CONSTRAINT participa_alumno_fk FOREIGN KEY ( alumno_exp )
        REFERENCES alumno ( exp );

ALTER TABLE participa
    ADD CONSTRAINT participa_registro_fk FOREIGN KEY ( registro_cod_registro )
        REFERENCES registro ( cod_registro );

ALTER TABLE realiza
    ADD CONSTRAINT realiza_profesor_fk FOREIGN KEY ( profesor_numero )
        REFERENCES profesor ( numero );

ALTER TABLE realiza
    ADD CONSTRAINT realiza_registro_fk FOREIGN KEY ( registro_cod_registro )
        REFERENCES registro ( cod_registro );

ALTER TABLE registro
    ADD CONSTRAINT registro_actividad_fk FOREIGN KEY ( actividad_nombre )
        REFERENCES actividad ( nombre );

ALTER TABLE ocupa
    ADD CONSTRAINT ocupa_sala_fk FOREIGN KEY ( cod_sala )
        REFERENCES sala ( cod_sala );

ALTER TABLE ocupa
    ADD CONSTRAINT ocupa_registro_fk FOREIGN KEY ( registro_cod_registro )
        REFERENCES registro ( cod_registro );

ALTER TABLE Actividad
    ADD CONSTRAINT actividad_acad_fk FOREIGN KEY ( acad )
        REFERENCES acad ( acad );


  REM ******* INSERTADO  ****

  REM ***1Âº Profesores***

  INSERT INTO Profesor(Numero, Relacion_Laboral, DNI, Mail1, Mail2, Tlf1, Tlf2, Nombre, Apellido1, Apellido2, Titulacion)
  VALUES(2224, 'MERCANTIL', '00000001F', 'lourdes@gmail.com', 'lourdes2@gmail.com', 654433891, 114433891, 'LOURDES', 'MARTIN', 'MENDEZ', 'MEDICO');

  INSERT INTO Profesor(Numero, Relacion_Laboral, DNI, Mail1, Mail2, Tlf1, Tlf2, Nombre, Apellido1, Apellido2, Titulacion)
  VALUES(3332, 'LABORAL', '00000002D', 'marta@gmail.com', 'marta2@gmail.com', 774433891, 224433891, 'MARTA', 'LABORDA', 'MARTÃ�N', 'CONSULTOR');

  INSERT INTO Profesor(Numero, Relacion_Laboral, DNI, Mail1, Mail2, Tlf1, Tlf2, Nombre, Apellido1, Apellido2, Titulacion)
  VALUES(2324, 'LABORAL', '00000003G', 'maria@gmail.com', 'maria2@gmail.com', 884433891, 334433891, 'MARIA', 'GILSANZ', 'MUÃ‘OZ', 'DESARROLLADOR');

  INSERT INTO Profesor(Numero, Relacion_Laboral, DNI, Mail1, Mail2, Tlf1, Tlf2, Nombre, Apellido1, Apellido2, Titulacion)
  VALUES(2325, 'LABORAL', '00000004C', 'santiago@gmail.com', 'santiago2@gmail.com', 994433891, 444433891, 'SANTIAGO', 'SEGURA', 'MARTIN', 'LEGADO');

  REM *** 2Âº Asignatura ***

  INSERT INTO Asignatura(Codigo, Nombre, Titulacion, Curso)
  VALUES(01412, 'HHCC', 'Medicina', 4);

  INSERT INTO Asignatura(Codigo, Nombre, Titulacion, Curso)
  VALUES(01411, 'SEGURIDAD PACIENTE', 'Medicina', 4);

  INSERT INTO Asignatura(Codigo, Nombre, Titulacion, Curso)
  VALUES(01401, 'BIOÃ‰TICA', 'Enfermeria', 4);

  INSERT INTO Asignatura(Codigo, Nombre, Titulacion, Curso)
  VALUES(01402, 'RESPIRATORIO', 'Enfermeria', 4);

  INSERT INTO Asignatura(Codigo, Nombre, Titulacion, Curso)
  VALUES(01405, 'NEFROLOGIA', 'Medicina', 4);

  INSERT INTO Asignatura(Codigo, Nombre, Titulacion, Curso)
  VALUES(01409, 'TALLERES', 'Medicina', 3);

  INSERT INTO Asignatura(Codigo, Nombre, Titulacion, Curso)
  VALUES(01403, 'DIGESTIVO', 'Enfermeria', 3);

  INSERT INTO Asignatura(Codigo, Nombre, Titulacion, Curso)
  VALUES(01404, 'UROLOGIA', 'Enfermeria', 3);

  INSERT INTO Asignatura(Codigo, Nombre, Titulacion, Curso)
  VALUES(01406, 'HEMATOLOGÃ�A', 'Medicina', 2);

  INSERT INTO Asignatura(Codigo, Nombre, Titulacion, Curso)
  VALUES(01407, 'INFECCIOSAS', 'Medicina', 1);

  INSERT INTO Asignatura(Codigo, Nombre, Titulacion, Curso)
  VALUES(01408, 'ENDOCRINO', 'Enfermeria', 1);

  INSERT INTO Asignatura(Codigo, Nombre, Titulacion, Curso)
  VALUES(01410, 'CARDIOLOGIA', 'Enfermeria', 2);

  REM *** 3Âº Alumno ***

  INSERT INTO Alumno(EXP, NOMBRE)
  VALUES(1, 'MARIA CARMEN');

  INSERT INTO Alumno(EXP, NOMBRE)
  VALUES(2, 'MARIA');

  INSERT INTO Alumno(EXP, NOMBRE)
  VALUES(3, 'CARMEN');

  REM *** 7Âº ACAD ***

  INSERT INTO acad ( acad, sem1 , sem2)
  VALUES ('18/19','09-01-2018','01-31-2019');
  INSERT INTO acad ( acad, sem1 , sem2)
  VALUES ('17/18','09-01-2017','01-31-2019');
  INSERT INTO acad ( acad, sem1 , sem2)
  VALUES ('16/17','09-01-2016','01-31-2017');
  INSERT INTO acad ( acad, sem1 , sem2)
  VALUES ('15/16','09-01-2015','01-31-2016');

  REM *** 4Âº Actor ***

  INSERT INTO Actor(EDAD, GENERO, IDIOMA, NOMBRE, Cod_Actor, COMPLEXION)
  VALUES('MEDIANA EDAD', 'Hombre', 'EspaÃ±ol', 'Juan', 1, 'Obesa');

  INSERT INTO Actor(EDAD, GENERO, IDIOMA, NOMBRE, Cod_Actor, COMPLEXION)
  VALUES('MAYOR 60 AÃ‘OS', 'Mujer', 'InglÃ©s', 'Pepa', 2, 'Normal');

  INSERT INTO Actor(EDAD, GENERO, IDIOMA, NOMBRE, Cod_Actor, COMPLEXION)
  VALUES('MEDIANA EDAD', 'Hombre', 'EspaÃ±ol', 'Anastasio', 3, 'Obesa');

  INSERT INTO Actor(EDAD, GENERO, IDIOMA, NOMBRE, Cod_Actor, COMPLEXION)
  VALUES('MAYOR 60 AÃ‘OS', 'Mujer', 'InglÃ©s', 'Gertrudis', 4, 'Delgada');

  INSERT INTO Actor(EDAD, GENERO, IDIOMA, NOMBRE, Cod_Actor, COMPLEXION)
  VALUES('MAYOR 60 AÃ‘OS', 'Hombre', 'EspaÃ±ol', 'Pepito', 5, 'Normal');

  REM *** 5Âº Actividad ***

  INSERT INTO Actividad(Nombre, Tipo_Actividad, Documentacion_Tecnica, Horas_Actividad, asignatura_Codigo, Simulador, acad, tipo_sala)
  VALUES('Demandante / desagradecido', 'Escenario Complejo', 1, 2, 01412, '', '18/19', 'consulta');

  INSERT INTO Actividad(Nombre, Tipo_Actividad, Documentacion_Tecnica, Horas_Actividad, asignatura_Codigo, Simulador, acad, tipo_sala)
  VALUES('Seg paciente 1', 'Taller de habilidades', 2, 2, 01411, 'ISTAN', '18/19', 'consulta');

  INSERT INTO Actividad(Nombre, Tipo_Actividad, Documentacion_Tecnica, Horas_Actividad, asignatura_Codigo, Simulador, acad, tipo_sala)
  VALUES('BioÃ©tica 1 LET/Rechazo al tto', 'Escenario Complejo', 3, 2, 01401, 'ISTAN', '18/19', 'consulta');

  INSERT INTO Actividad(Nombre, Tipo_Actividad, Documentacion_Tecnica, Horas_Actividad, asignatura_Codigo, Simulador, acad, tipo_sala)
  VALUES('BioÃ©tica 2 Ãºltima cama', 'Taller de habilidades', 4, 2, 01402, '', '18/19', 'consulta');

  INSERT INTO Actividad(Nombre, Tipo_Actividad, Documentacion_Tecnica, Horas_Actividad, asignatura_Codigo, Simulador, acad, tipo_sala)
  VALUES('EPOC-Neumonia', 'Escenario Complejo', 5, 2, 01405, 'ISTAN', '18/19', 'consulta');

  INSERT INTO Actividad(Nombre, Tipo_Actividad, Documentacion_Tecnica, Horas_Actividad, asignatura_Codigo, Simulador, acad, tipo_sala)
  VALUES('Asma-Ca PulmÃ³n', 'Taller de habilidades', 6, 2, 01409, '', '18/19', 'consulta');

  INSERT INTO Actividad(Nombre, Tipo_Actividad, Documentacion_Tecnica, Horas_Actividad, asignatura_Codigo, Simulador, acad, tipo_sala)
  VALUES('NefrologÃ­a FRA', 'Escenario Complejo', 7, 2, 01403, '', '18/19', 'consulta');

  INSERT INTO Actividad(Nombre, Tipo_Actividad, Documentacion_Tecnica, Horas_Actividad, asignatura_Codigo, Simulador, acad, tipo_sala)
  VALUES('NefrologÃ­a S NEFRÃ�TICO', 'Taller de habilidades', 8, 2, 01404, '', '18/19', 'consulta');

  INSERT INTO Actividad(Nombre, Tipo_Actividad, Documentacion_Tecnica, Horas_Actividad, asignatura_Codigo, Simulador, acad, tipo_sala)
  VALUES('VIA PERIFERICA', 'Escenario Complejo', 9, 2, 01406, '', '18/19', 'consulta');

  INSERT INTO Actividad(Nombre, Tipo_Actividad, Documentacion_Tecnica, Horas_Actividad, asignatura_Codigo, Simulador, acad, tipo_sala)
  VALUES('VIA CENTRAL', 'Taller de habilidades', 10, 2, 01407, '', '18/19', 'consulta');

  INSERT INTO Actividad(Nombre, Tipo_Actividad, Documentacion_Tecnica, Horas_Actividad, asignatura_Codigo, Simulador, acad, tipo_sala)
  VALUES('Expl abdominal', 'Escenario Complejo', 11, 2, 01408, '', '18/19', 'consulta');

  INSERT INTO Actividad(Nombre, Tipo_Actividad, Documentacion_Tecnica, Horas_Actividad, asignatura_Codigo, Simulador, acad, tipo_sala)
  VALUES('Expl abd RX', 'Taller de habilidades', 12, 2, 01410, '', '18/19', 'consulta');

  INSERT INTO Actividad(Nombre, Tipo_Actividad, Documentacion_Tecnica, Horas_Actividad, asignatura_Codigo, Simulador, acad, tipo_sala)
  VALUES('UrologÃ­a', 'Escenario Complejo', 13, 2, 01412, '', '18/19', 'consulta');

  INSERT INTO Actividad(Nombre, Tipo_Actividad, Documentacion_Tecnica, Horas_Actividad, asignatura_Codigo, Simulador, acad, tipo_sala)
  VALUES('Taller UrologÃ­a sv', 'Taller de habilidades', 14, 2, 01411, '', '18/19', 'consulta');

  INSERT INTO Actividad(Nombre, Tipo_Actividad, Documentacion_Tecnica, Horas_Actividad, asignatura_Codigo, Simulador, acad, tipo_sala)
  VALUES('Digestivo 1', 'HDA/Cirrosis', 15, 2, 01408,'', '18/19', 'consulta');

  INSERT INTO Actividad(Nombre, Tipo_Actividad, Documentacion_Tecnica, Horas_Actividad, asignatura_Codigo, Simulador, acad, tipo_sala)
  VALUES('Digestivo 2', ' Diarrea/Pancreatitis', 16, 2, 01406,'', '18/19', 'consulta');

  INSERT INTO Actividad(Nombre, Tipo_Actividad, Documentacion_Tecnica, Horas_Actividad, asignatura_Codigo, Simulador, acad, tipo_sala)
  VALUES('VIA AEREA', 'Escenario Complejo', 17, 2, 01405, '', '18/19', 'consulta');

  INSERT INTO Actividad(Nombre, Tipo_Actividad, Documentacion_Tecnica, Horas_Actividad, asignatura_Codigo, Simulador, acad, tipo_sala)
  VALUES('TORACOCENTESIS', 'Taller de habilidades', 18, 2, 01409, '', '18/19', 'consulta');

  INSERT INTO Actividad(Nombre, Tipo_Actividad, Documentacion_Tecnica, Horas_Actividad, asignatura_Codigo, Simulador, acad, tipo_sala)
  VALUES('HematologÃ­a 1', 'Escenario Complejo', 19, 2, 01403, '', '18/19', 'consulta');

  INSERT INTO Actividad(Nombre, Tipo_Actividad, Documentacion_Tecnica, Horas_Actividad, asignatura_Codigo, Simulador, acad, tipo_sala)
  VALUES('Pielonefritis', 'Taller de habilidades', 20, 2, 01404, '', '18/19', 'consulta');

  INSERT INTO Actividad(Nombre, Tipo_Actividad, Documentacion_Tecnica, Horas_Actividad, asignatura_Codigo, Simulador, acad, tipo_sala)
  VALUES('Neumonia', 'Escenario Complejo', 21, 2, 01406, '', '18/19', 'consulta');

  INSERT INTO Actividad(Nombre, Tipo_Actividad, Documentacion_Tecnica, Horas_Actividad, asignatura_Codigo, Simulador, acad, tipo_sala)
  VALUES('Diabetes', 'Taller de habilidades', 22, 2, 01407, 'SV', '18/19', 'consulta');

  INSERT INTO Actividad(Nombre, Tipo_Actividad, Documentacion_Tecnica, Horas_Actividad, asignatura_Codigo, Simulador, acad, tipo_sala)
  VALUES('Hipertiroidismo', 'Escenario Complejo', 23, 2, 01408, 'ISTAN', '18/19', 'consulta');

  INSERT INTO Actividad(Nombre, Tipo_Actividad, Documentacion_Tecnica, Horas_Actividad, asignatura_Codigo, Simulador, acad, tipo_sala)
  VALUES('CardiologÃ­a 1', 'Taller de habilidades', 24, 2, 01410, 'ISTAN', '18/19', 'consulta');

  INSERT INTO Actividad(Nombre, Tipo_Actividad, Documentacion_Tecnica, Horas_Actividad, asignatura_Codigo, Simulador, acad, tipo_sala)
  VALUES('CardiologÃ­a 2', 'Escenario Complejo', 25, 2, 01412, 'ISTAN', '18/19', 'consulta');

  INSERT INTO Actividad(Nombre, Tipo_Actividad, Documentacion_Tecnica, Horas_Actividad, asignatura_Codigo, Simulador, acad, tipo_sala)
  VALUES('CardiologÃ­a 3', 'Taller de habilidades', 26, 2, 01411, 'ISTAN', '18/19', 'consulta');

  INSERT INTO Actividad(Nombre, Tipo_Actividad, Documentacion_Tecnica, Horas_Actividad, asignatura_Codigo, Simulador, acad, tipo_sala)
  VALUES('CardiologÃ­a 4', 'Escenario Complejo', 27, 2, 01401, 'IOT', '18/19', 'consulta');

  INSERT INTO Actividad(Nombre, Tipo_Actividad, Documentacion_Tecnica, Horas_Actividad, asignatura_Codigo, Simulador, acad, tipo_sala)
  VALUES('Medicina interna 1', 'Taller de habilidades', 28, 2, 01402, 'OTRO', '18/19', 'consulta');

  INSERT INTO Actividad(Nombre, Tipo_Actividad, Documentacion_Tecnica, Horas_Actividad, asignatura_Codigo, Simulador, acad, tipo_sala)
  VALUES('Medicina interna 2', 'Escenario Complejo', 29, 2, 01405, 'ISTAN', '18/19', 'consulta');

  INSERT INTO Actividad(Nombre, Tipo_Actividad, Documentacion_Tecnica, Horas_Actividad, asignatura_Codigo, Simulador, acad, tipo_sala)
  VALUES('Fiebre exantematica', 'Taller de habilidades', 30, 2, 01409, 'ISTAN', '18/19', 'consulta');

  INSERT INTO Actividad(Nombre, Tipo_Actividad, Documentacion_Tecnica, Horas_Actividad, asignatura_Codigo, Simulador, acad, tipo_sala)
  VALUES('Meningitis', 'Escenario Complejo', 31, 2, 01403, '', '18/19', 'consulta');

  INSERT INTO Actividad(Nombre, Tipo_Actividad, Documentacion_Tecnica, Horas_Actividad, asignatura_Codigo, Simulador, acad, tipo_sala)
  VALUES('SUTURAS', 'Taller de habilidades', 32, 2, 01404, 'ISTAN', '18/19', 'consulta');

  INSERT INTO Actividad(Nombre, Tipo_Actividad, Documentacion_Tecnica, Horas_Actividad, asignatura_Codigo, Simulador, acad, tipo_sala)
  VALUES('ECOGRAFIA', 'Escenario Complejo', 33, 2, 01406, '', '18/19', 'consulta');

  INSERT INTO Actividad(Nombre, Tipo_Actividad, Documentacion_Tecnica, Horas_Actividad, asignatura_Codigo, Simulador, acad, tipo_sala)
  VALUES('Autoritario', 'Taller de habilidades', 34, 2, 01407, '', '18/19', 'consulta');

  INSERT INTO Actividad(Nombre, Tipo_Actividad, Documentacion_Tecnica, Horas_Actividad, asignatura_Codigo, Simulador, acad, tipo_sala)
  VALUES('Seg paciente 2', 'Escenario Complejo', 35, 2, 01408, 'ISTAN', '18/19', 'consulta');

  INSERT INTO Actividad(Nombre, Tipo_Actividad, Documentacion_Tecnica, Horas_Actividad, asignatura_Codigo, Simulador, acad, tipo_sala)
  VALUES('BioÃ©tica 3', 'Taller de habilidades', 36, 2, 01410, 'ISTAN', '18/19', 'consulta');

  INSERT INTO Actividad(Nombre, Tipo_Actividad, Documentacion_Tecnica, Horas_Actividad, asignatura_Codigo, Simulador, acad, tipo_sala)
  VALUES('BioÃ©tica 4', 'Escenario Complejo', 37, 2, 01412, '', '18/19', 'consulta');

  INSERT INTO Actividad(Nombre, Tipo_Actividad, Documentacion_Tecnica, Horas_Actividad, asignatura_Codigo, Simulador, acad, tipo_sala)
  VALUES('Viajero', 'Taller de habilidades', 38, 2, 01411, 'ISTAN', '18/19', 'consulta');

  INSERT INTO Actividad(Nombre, Tipo_Actividad, Documentacion_Tecnica, Horas_Actividad, asignatura_Codigo, Simulador, acad, tipo_sala)
  VALUES('VIH/TBC', 'Escenario Complejo', 39, 2, 01411, '', '18/19', 'consulta');

  REM *** 6Âº Registro ***

  INSERT INTO Registro(COD_REGISTRO, FECHA, HORA, HORAS_PROFESOR, ACTIVIDAD_NOMBRE, cod_grupo)
  VALUES(1,'02/24/2019', '18:30', 2, 'Demandante / desagradecido','MEM11');

  INSERT INTO Registro(COD_REGISTRO, FECHA, HORA, HORAS_PROFESOR, ACTIVIDAD_NOMBRE, cod_grupo)
  VALUES(2,'02/25/2019', '19:30', 2, 'Seg paciente 1','MEM11');

  INSERT INTO Registro(COD_REGISTRO, FECHA, HORA, HORAS_PROFESOR, ACTIVIDAD_NOMBRE, cod_grupo)
  VALUES(3,'02/24/2019', '20:30', 2, 'BioÃ©tica 1 LET/Rechazo al tto','MEM11');

  INSERT INTO Registro(COD_REGISTRO, FECHA, HORA, HORAS_PROFESOR, ACTIVIDAD_NOMBRE, cod_grupo)
  VALUES(4,'02/25/2019', '21:30', 2, 'BioÃ©tica 2 Ãºltima cama','MEM11');

  INSERT INTO Registro(COD_REGISTRO, FECHA, HORA, HORAS_PROFESOR, ACTIVIDAD_NOMBRE, cod_grupo)
  VALUES(5,'02/24/2019', '22:30', 2, 'EPOC-Neumonia','MEM11');

  INSERT INTO Registro(COD_REGISTRO, FECHA, HORA, HORAS_PROFESOR, ACTIVIDAD_NOMBRE, cod_grupo)
  VALUES(6,'02/25/2019', '23:30', 2, 'Asma-Ca PulmÃ³n','MEM11');

  INSERT INTO Registro(COD_REGISTRO, FECHA, HORA, HORAS_PROFESOR, ACTIVIDAD_NOMBRE, cod_grupo)
  VALUES(7,'02/24/2019', '0:30', 2, 'NefrologÃ­a FRA','MEM11');

  INSERT INTO Registro(COD_REGISTRO, FECHA, HORA, HORAS_PROFESOR, ACTIVIDAD_NOMBRE, cod_grupo)
  VALUES(8,'02/25/2019', '1:30', 2, 'NefrologÃ­a S NEFRÃ�TICO','MEM11');

  INSERT INTO Registro(COD_REGISTRO, FECHA, HORA, HORAS_PROFESOR, ACTIVIDAD_NOMBRE, cod_grupo)
  VALUES(9,'02/24/2019', '2:30', 2, 'VIA PERIFERICA','MEM11');

  INSERT INTO Registro(COD_REGISTRO, FECHA, HORA, HORAS_PROFESOR, ACTIVIDAD_NOMBRE, cod_grupo)
  VALUES(10,'02/25/2019', '3:30', 2, 'VIA CENTRAL','MEM11');

  INSERT INTO Registro(COD_REGISTRO, FECHA, HORA, HORAS_PROFESOR, ACTIVIDAD_NOMBRE, cod_grupo)
  VALUES(11,'02/24/2019', '4:30', 2, 'Expl abdominal','MEM11');

  INSERT INTO Registro(COD_REGISTRO, FECHA, HORA, HORAS_PROFESOR, ACTIVIDAD_NOMBRE, cod_grupo)
  VALUES(12, '02/25/2019', '5:30', 2, 'Expl abd RX','MEM11');

  INSERT INTO Registro(COD_REGISTRO, FECHA, HORA, HORAS_PROFESOR, ACTIVIDAD_NOMBRE, cod_grupo)
  VALUES(13, '02/24/2019', '6:30', 2, 'UrologÃ­a','MEM11');

  INSERT INTO Registro(COD_REGISTRO, FECHA, HORA, HORAS_PROFESOR, ACTIVIDAD_NOMBRE, cod_grupo)
  VALUES(14,'02/25/2019', '7:30', 2, 'Taller UrologÃ­a sv','MEM11');

  INSERT INTO Registro(COD_REGISTRO, FECHA, HORA, HORAS_PROFESOR, ACTIVIDAD_NOMBRE, cod_grupo)
  VALUES(15,'02/24/2019', '8:30', 2, 'Digestivo 1','MEM11');

  INSERT INTO Registro(COD_REGISTRO, FECHA, HORA, HORAS_PROFESOR, ACTIVIDAD_NOMBRE, cod_grupo)
  VALUES(16,'02/25/2019', '9:30', 2, 'Digestivo 2','MEM11');

  INSERT INTO Registro(COD_REGISTRO, FECHA, HORA, HORAS_PROFESOR, ACTIVIDAD_NOMBRE, cod_grupo)
  VALUES(17,'02/24/2019', '10:30', 2, 'VIA AEREA','MEM11');

  INSERT INTO Registro(COD_REGISTRO, FECHA, HORA, HORAS_PROFESOR, ACTIVIDAD_NOMBRE, cod_grupo)
  VALUES(18,'02/25/2019', '11:30', 2, 'TORACOCENTESIS','MEM11');

  INSERT INTO Registro(COD_REGISTRO, FECHA, HORA, HORAS_PROFESOR, ACTIVIDAD_NOMBRE, cod_grupo)
  VALUES(19, '02/24/2019', '12:30', 2, 'HematologÃ­a 1','MEM11');

  INSERT INTO Registro(COD_REGISTRO, FECHA, HORA, HORAS_PROFESOR, ACTIVIDAD_NOMBRE, cod_grupo)
  VALUES(20,'02/25/2019', '13:30', 2, 'Pielonefritis','MEM11');

  INSERT INTO Registro(COD_REGISTRO, FECHA, HORA, HORAS_PROFESOR, ACTIVIDAD_NOMBRE, cod_grupo)
  VALUES(21,'02/24/2019', '14:30', 2, 'Neumonia','FAM11');

  INSERT INTO Registro(COD_REGISTRO, FECHA, HORA, HORAS_PROFESOR, ACTIVIDAD_NOMBRE, cod_grupo)
  VALUES(22,'02/25/2019', '15:30', 2, 'Diabetes','FAM11');

  INSERT INTO Registro(COD_REGISTRO, FECHA, HORA, HORAS_PROFESOR, ACTIVIDAD_NOMBRE, cod_grupo)
  VALUES(23,'02/24/2019', '16:30', 2, 'Hipertiroidismo','FAM11');

  INSERT INTO Registro(COD_REGISTRO, FECHA, HORA, HORAS_PROFESOR, ACTIVIDAD_NOMBRE, cod_grupo)
  VALUES(24,'02/25/2019', '17:30', 2, 'CardiologÃ­a 1','FAM11');

  INSERT INTO Registro(COD_REGISTRO, FECHA, HORA, HORAS_PROFESOR, ACTIVIDAD_NOMBRE, cod_grupo)
  VALUES(25, '02/24/2019', '18:30', 2, 'CardiologÃ­a 2','FAM11');

  INSERT INTO Registro(COD_REGISTRO, FECHA, HORA, HORAS_PROFESOR, ACTIVIDAD_NOMBRE, cod_grupo)
  VALUES(26, '02/25/2019', '19:30', 2, 'CardiologÃ­a 3','FAM11');

  INSERT INTO Registro(COD_REGISTRO, FECHA, HORA, HORAS_PROFESOR, ACTIVIDAD_NOMBRE, cod_grupo)
  VALUES(27, '02/24/2019', '20:30', 2, 'CardiologÃ­a 4','FAM11');

  INSERT INTO Registro(COD_REGISTRO, FECHA, HORA, HORAS_PROFESOR, ACTIVIDAD_NOMBRE, cod_grupo)
  VALUES(28, '02/25/2019', '21:30', 2, 'Medicina interna 1','FAM11');

  INSERT INTO Registro(COD_REGISTRO, FECHA, HORA, HORAS_PROFESOR, ACTIVIDAD_NOMBRE, cod_grupo)
  VALUES(29, '02/24/2019', '22:30', 2, 'Medicina interna 2','FAM11');

  INSERT INTO Registro(COD_REGISTRO, FECHA, HORA, HORAS_PROFESOR, ACTIVIDAD_NOMBRE, cod_grupo)
  VALUES(30, '02/25/2019', '23:30', 2, 'Fiebre exantematica','FAM11');

  INSERT INTO Registro(COD_REGISTRO, FECHA, HORA, HORAS_PROFESOR, ACTIVIDAD_NOMBRE, cod_grupo)
  VALUES(31, '02/24/2019', '0:30', 2, 'Meningitis','FAM11');

  INSERT INTO Registro(COD_REGISTRO, FECHA, HORA, HORAS_PROFESOR, ACTIVIDAD_NOMBRE, cod_grupo)
  VALUES(32, '02/25/2019', '1:30', 2, 'SUTURAS','FAM11');

  INSERT INTO Registro(COD_REGISTRO, FECHA, HORA, HORAS_PROFESOR, ACTIVIDAD_NOMBRE, cod_grupo)
  VALUES(33, '02/24/2019', '2:30', 2, 'ECOGRAFIA','FAM11');

  INSERT INTO Registro(COD_REGISTRO, FECHA, HORA, HORAS_PROFESOR, ACTIVIDAD_NOMBRE, cod_grupo)
  VALUES(34, '02/25/2019', '3:30', 2, 'Autoritario','FAM11');

  INSERT INTO Registro(COD_REGISTRO, FECHA, HORA, HORAS_PROFESOR, ACTIVIDAD_NOMBRE, cod_grupo)
  VALUES(35, '02/24/2019', '4:30', 2, 'Seg paciente 2','FAM11');

  INSERT INTO Registro(COD_REGISTRO, FECHA, HORA, HORAS_PROFESOR, ACTIVIDAD_NOMBRE, cod_grupo)
  VALUES(36, '02/25/2019', '5:30', 2, 'BioÃ©tica 3','FAM11');

  INSERT INTO Registro(COD_REGISTRO, FECHA, HORA, HORAS_PROFESOR, ACTIVIDAD_NOMBRE, cod_grupo)
  VALUES(37, '02/24/2019', '6:30', 2, 'BioÃ©tica 4','FAM11');

  INSERT INTO Registro(COD_REGISTRO, FECHA, HORA, HORAS_PROFESOR, ACTIVIDAD_NOMBRE, cod_grupo)
  VALUES(38, '02/25/2019', '7:30', 2, 'Viajero','FAM11');

  INSERT INTO Registro(COD_REGISTRO, FECHA, HORA, HORAS_PROFESOR, ACTIVIDAD_NOMBRE, cod_grupo)
  VALUES(39, '02/24/2019', '8:30', 2, 'VIH/TBC','FAM11');

  REM *** 8Âº SALA ***

  INSERT INTO Sala(cod_sala,tipo_sala,numero,capacidad)
  VALUES (1,'Consulta', 1, 15);
  INSERT INTO Sala(cod_sala,tipo_sala,numero,capacidad)
  VALUES (2,'Consulta', 2, 15);
  INSERT INTO Sala(cod_sala,tipo_sala,numero,capacidad)
  VALUES (3,'Simulador', 1, 25);
  INSERT INTO Sala(cod_sala,tipo_sala,numero,capacidad)
  VALUES (4,'Simulador', 2, 25);

  REM *** 9Âº USERS ***

  INSERT INTO Users(Usr,pwd,rol, email)
  VALUES ('DAVID BUENAÑO','gnzLDuqKcGxMNKFokfhOew==','ADMINISTRADOR', 'PRUEBA@GMAIL.COM');
  INSERT INTO Users(Usr,pwd,rol, email)
  VALUES ('JAVIER PLAZA','gnzLDuqKcGxMNKFokfhOew==','ADMINISTRADOR', 'PRUEBA@GMAIL.COM');
  INSERT INTO Users(Usr,pwd,rol, email)
  VALUES ('JIMMY MERINO','gnzLDuqKcGxMNKFokfhOew==','LECTURA', 'PRUEBA@GMAIL.COM');
  INSERT INTO Users(Usr,pwd,rol, email)
  VALUES ('DEMO ADMIN','gnzLDuqKcGxMNKFokfhOew==','ADMINISTRADOR', 'ADMIN@GMAIL.COM');
    INSERT INTO Users(Usr,pwd,rol, email)
  VALUES ('DEMO LECTURA','gnzLDuqKcGxMNKFokfhOew==','LECTURA', 'LECTURA@GMAIL.COM');

  REM *** 10Âº REALIZA ***

  INSERT INTO Realiza(REGISTRO_COD_REGISTRO, PROFESOR_NUMERO)
  VALUES(1, 2224);

  INSERT INTO Realiza(REGISTRO_COD_REGISTRO, PROFESOR_NUMERO)
  VALUES(2, 3332);

  INSERT INTO Realiza(REGISTRO_COD_REGISTRO, PROFESOR_NUMERO)
  VALUES(3, 2324);

  INSERT INTO Realiza(REGISTRO_COD_REGISTRO, PROFESOR_NUMERO)
  VALUES(4, 2325);

  INSERT INTO Realiza(REGISTRO_COD_REGISTRO, PROFESOR_NUMERO)
  VALUES(5, 2224);

  INSERT INTO Realiza(REGISTRO_COD_REGISTRO, PROFESOR_NUMERO)
  VALUES(6, 3332);

  INSERT INTO Realiza(REGISTRO_COD_REGISTRO, PROFESOR_NUMERO)
  VALUES(7, 2324);

  INSERT INTO Realiza(REGISTRO_COD_REGISTRO, PROFESOR_NUMERO)
  VALUES(8, 2325);

  INSERT INTO Realiza(REGISTRO_COD_REGISTRO, PROFESOR_NUMERO)
  VALUES(9, 2224);


  INSERT INTO Realiza(REGISTRO_COD_REGISTRO, PROFESOR_NUMERO)
  VALUES(10, 3332);


  INSERT INTO Realiza(REGISTRO_COD_REGISTRO, PROFESOR_NUMERO)
  VALUES(11, 2324);


  INSERT INTO Realiza(REGISTRO_COD_REGISTRO, PROFESOR_NUMERO)
  VALUES(12, 2325);


  INSERT INTO Realiza(REGISTRO_COD_REGISTRO, PROFESOR_NUMERO)
  VALUES(13, 2224);


  INSERT INTO Realiza(REGISTRO_COD_REGISTRO, PROFESOR_NUMERO)
  VALUES(14, 3332);


  INSERT INTO Realiza(REGISTRO_COD_REGISTRO, PROFESOR_NUMERO)
  VALUES(15, 2324);


  INSERT INTO Realiza(REGISTRO_COD_REGISTRO, PROFESOR_NUMERO)
  VALUES(16, 2325);


  INSERT INTO Realiza(REGISTRO_COD_REGISTRO, PROFESOR_NUMERO)
  VALUES(17, 2224);


  INSERT INTO Realiza(REGISTRO_COD_REGISTRO, PROFESOR_NUMERO)
  VALUES(18, 3332);


  INSERT INTO Realiza(REGISTRO_COD_REGISTRO, PROFESOR_NUMERO)
  VALUES(19, 2324);


  INSERT INTO Realiza(REGISTRO_COD_REGISTRO, PROFESOR_NUMERO)
  VALUES(20, 2325);


  INSERT INTO Realiza(REGISTRO_COD_REGISTRO, PROFESOR_NUMERO)
  VALUES(21, 2224);


  INSERT INTO Realiza(REGISTRO_COD_REGISTRO, PROFESOR_NUMERO)
  VALUES(22, 3332);


  INSERT INTO Realiza(REGISTRO_COD_REGISTRO, PROFESOR_NUMERO)
  VALUES(23, 2324);


  INSERT INTO Realiza(REGISTRO_COD_REGISTRO, PROFESOR_NUMERO)
  VALUES(24, 2325);


  INSERT INTO Realiza(REGISTRO_COD_REGISTRO, PROFESOR_NUMERO)
  VALUES(25, 2224);


  INSERT INTO Realiza(REGISTRO_COD_REGISTRO, PROFESOR_NUMERO)
  VALUES(26, 3332);


  INSERT INTO Realiza(REGISTRO_COD_REGISTRO, PROFESOR_NUMERO)
  VALUES(27, 2324);


  INSERT INTO Realiza(REGISTRO_COD_REGISTRO, PROFESOR_NUMERO)
  VALUES(28, 2325);


  INSERT INTO Realiza(REGISTRO_COD_REGISTRO, PROFESOR_NUMERO)
  VALUES(29, 2224);


  INSERT INTO Realiza(REGISTRO_COD_REGISTRO, PROFESOR_NUMERO)
  VALUES(30, 3332);


  INSERT INTO Realiza(REGISTRO_COD_REGISTRO, PROFESOR_NUMERO)
  VALUES(31, 2324);


  INSERT INTO Realiza(REGISTRO_COD_REGISTRO, PROFESOR_NUMERO)
  VALUES(32, 2325);


  INSERT INTO Realiza(REGISTRO_COD_REGISTRO, PROFESOR_NUMERO)
  VALUES(33, 2224);


  INSERT INTO Realiza(REGISTRO_COD_REGISTRO, PROFESOR_NUMERO)
  VALUES(34, 3332);


  INSERT INTO Realiza(REGISTRO_COD_REGISTRO, PROFESOR_NUMERO)
  VALUES(35, 2324);


  INSERT INTO Realiza(REGISTRO_COD_REGISTRO, PROFESOR_NUMERO)
  VALUES(36, 2325);


  INSERT INTO Realiza(REGISTRO_COD_REGISTRO, PROFESOR_NUMERO)
  VALUES(37, 2224);


  INSERT INTO Realiza(REGISTRO_COD_REGISTRO, PROFESOR_NUMERO)
  VALUES(38, 3332);


  INSERT INTO Realiza(REGISTRO_COD_REGISTRO, PROFESOR_NUMERO)
  VALUES(39, 2324);

  REM *** 11Âº Actua ***

  INSERT INTO Actua(Cod_Actor, registro_cod_registro, info, horas_actor)
  VALUES(1,1,'Texto de prueba',2);

  INSERT INTO Actua(Cod_Actor, registro_cod_registro, info, horas_actor)
  VALUES(2,2,'Texto de prueba',2);

  INSERT INTO Actua(Cod_Actor, registro_cod_registro, info, horas_actor)
  VALUES(3,3,'Texto de prueba',2);

  INSERT INTO Actua(Cod_Actor, registro_cod_registro, info, horas_actor)
  VALUES(4,4,'Texto de prueba',2);

  INSERT INTO Actua(Cod_Actor, registro_cod_registro, info, horas_actor)
  VALUES(5,5,'Texto de prueba',2);

  INSERT INTO Actua(Cod_Actor, registro_cod_registro, info, horas_actor)
  VALUES(1,6,'Texto de prueba',2);

  INSERT INTO Actua(Cod_Actor, registro_cod_registro, info, horas_actor)
  VALUES(2,7,'Texto de prueba',2);

  INSERT INTO Actua(Cod_Actor, registro_cod_registro, info, horas_actor)
  VALUES(3,8,'Texto de prueba',2);

  INSERT INTO Actua(Cod_Actor, registro_cod_registro, info, horas_actor)
  VALUES(4,9,'Texto de prueba',2);

  INSERT INTO Actua(Cod_Actor, registro_cod_registro, info, horas_actor)
  VALUES(5,10,'Texto de prueba',2);

  INSERT INTO Actua(Cod_Actor, registro_cod_registro, info, horas_actor)
  VALUES(1,11,'Texto de prueba',2);

  INSERT INTO Actua(Cod_Actor, registro_cod_registro, info, horas_actor)
  VALUES(2,12,'Texto de prueba',2);

  INSERT INTO Actua(Cod_Actor, registro_cod_registro, info, horas_actor)
  VALUES(3,13,'Texto de prueba',2);

  INSERT INTO Actua(Cod_Actor, registro_cod_registro, info, horas_actor)
  VALUES(4,14,'Texto de prueba',2);

  INSERT INTO Actua(Cod_Actor, registro_cod_registro, info, horas_actor)
  VALUES(5,15,'Texto de prueba',2);

  INSERT INTO Actua(Cod_Actor, registro_cod_registro, info, horas_actor)
  VALUES(1,16,'Texto de prueba',2);

  INSERT INTO Actua(Cod_Actor, registro_cod_registro, info, horas_actor)
  VALUES(2,17,'Texto de prueba',2);

  INSERT INTO Actua(Cod_Actor, registro_cod_registro, info, horas_actor)
  VALUES(3,18,'Texto de prueba',2);

  INSERT INTO Actua(Cod_Actor, registro_cod_registro, info, horas_actor)
  VALUES(4,19,'Texto de prueba',2);

  INSERT INTO Actua(Cod_Actor, registro_cod_registro, info, horas_actor)
  VALUES(5,20,'Texto de prueba',2);

  INSERT INTO Actua(Cod_Actor, registro_cod_registro, info, horas_actor)
  VALUES(1,21,'Texto de prueba',2);

  INSERT INTO Actua(Cod_Actor, registro_cod_registro, info, horas_actor)
  VALUES(2,22,'Texto de prueba',2);

  INSERT INTO Actua(Cod_Actor, registro_cod_registro, info, horas_actor)
  VALUES(3,23,'Texto de prueba',2);

  INSERT INTO Actua(Cod_Actor, registro_cod_registro, info, horas_actor)
  VALUES(4,24,'Texto de prueba',2);

  INSERT INTO Actua(Cod_Actor, registro_cod_registro, info, horas_actor)
  VALUES(5,25,'Texto de prueba',2);

  INSERT INTO Actua(Cod_Actor, registro_cod_registro, info, horas_actor)
  VALUES(1,26,'Texto de prueba',2);

  INSERT INTO Actua(Cod_Actor, registro_cod_registro, info, horas_actor)
  VALUES(2,27,'Texto de prueba',2);

  INSERT INTO Actua(Cod_Actor, registro_cod_registro, info, horas_actor)
  VALUES(3,28,'Texto de prueba',2);

  INSERT INTO Actua(Cod_Actor, registro_cod_registro, info, horas_actor)
  VALUES(4,29,'Texto de prueba',2);

  INSERT INTO Actua(Cod_Actor, registro_cod_registro, info, horas_actor)
  VALUES(5,30,'Texto de prueba',2);

  INSERT INTO Actua(Cod_Actor, registro_cod_registro, info, horas_actor)
  VALUES(1,31,'Texto de prueba',2);

  INSERT INTO Actua(Cod_Actor, registro_cod_registro, info, horas_actor)
  VALUES(2,32,'Texto de prueba',2);

  INSERT INTO Actua(Cod_Actor, registro_cod_registro, info, horas_actor)
  VALUES(3,33,'Texto de prueba',2);

  INSERT INTO Actua(Cod_Actor, registro_cod_registro, info, horas_actor)
  VALUES(4,34,'Texto de prueba',2);

  INSERT INTO Actua(Cod_Actor, registro_cod_registro, info, horas_actor)
  VALUES(5,35,'Texto de prueba',2);

  INSERT INTO Actua(Cod_Actor, registro_cod_registro, info, horas_actor)
  VALUES(1,36,'Texto de prueba',2);

  INSERT INTO Actua(Cod_Actor, registro_cod_registro, info, horas_actor)
  VALUES(2,37,'Texto de prueba',2);

  INSERT INTO Actua(Cod_Actor, registro_cod_registro, info, horas_actor)
  VALUES(3,38,'Texto de prueba',2);

  INSERT INTO Actua(Cod_Actor, registro_cod_registro, info, horas_actor)
  VALUES(4,39,'Texto de prueba',2);

  REM *** 12Âº Participa ***

  INSERT INTO Participa(ALUMNO_EXP, REGISTRO_COD_REGISTRO, NOTA_ALUMNOS)
  VALUES(1, 1, 0);

  INSERT INTO Participa(ALUMNO_EXP, REGISTRO_COD_REGISTRO, NOTA_ALUMNOS)
  VALUES(2, 2, 1);

  INSERT INTO Participa(ALUMNO_EXP, REGISTRO_COD_REGISTRO, NOTA_ALUMNOS)
  VALUES(3, 3, 2);

  INSERT INTO Participa(ALUMNO_EXP, REGISTRO_COD_REGISTRO, NOTA_ALUMNOS)
  VALUES(1, 4, 3);

  INSERT INTO Participa(ALUMNO_EXP, REGISTRO_COD_REGISTRO, NOTA_ALUMNOS)
  VALUES(2, 5, 4);

  INSERT INTO Participa(ALUMNO_EXP, REGISTRO_COD_REGISTRO, NOTA_ALUMNOS)
  VALUES(3, 6, 5);

  INSERT INTO Participa(ALUMNO_EXP, REGISTRO_COD_REGISTRO, NOTA_ALUMNOS)
  VALUES(1, 7, 6);

  INSERT INTO Participa(ALUMNO_EXP, REGISTRO_COD_REGISTRO, NOTA_ALUMNOS)
  VALUES(2, 8, 7);

  INSERT INTO Participa(ALUMNO_EXP, REGISTRO_COD_REGISTRO, NOTA_ALUMNOS)
  VALUES(3, 9, 8);

  INSERT INTO Participa(ALUMNO_EXP, REGISTRO_COD_REGISTRO, NOTA_ALUMNOS)
  VALUES(1, 10, 9);

  INSERT INTO Participa(ALUMNO_EXP, REGISTRO_COD_REGISTRO, NOTA_ALUMNOS)
  VALUES(2, 11, 10);

  INSERT INTO Participa(ALUMNO_EXP, REGISTRO_COD_REGISTRO, NOTA_ALUMNOS)
  VALUES(3, 12, 0);

  INSERT INTO Participa(ALUMNO_EXP, REGISTRO_COD_REGISTRO, NOTA_ALUMNOS)
  VALUES(1, 13, 1);

  INSERT INTO Participa(ALUMNO_EXP, REGISTRO_COD_REGISTRO, NOTA_ALUMNOS)
  VALUES(2, 14, 2);

  INSERT INTO Participa(ALUMNO_EXP, REGISTRO_COD_REGISTRO, NOTA_ALUMNOS)
  VALUES(3, 15, 3);

  INSERT INTO Participa(ALUMNO_EXP, REGISTRO_COD_REGISTRO, NOTA_ALUMNOS)
  VALUES(1, 16, 4);

  INSERT INTO Participa(ALUMNO_EXP, REGISTRO_COD_REGISTRO, NOTA_ALUMNOS)
  VALUES(2, 17, 5);

  INSERT INTO Participa(ALUMNO_EXP, REGISTRO_COD_REGISTRO, NOTA_ALUMNOS)
  VALUES(3, 18, 6);

  INSERT INTO Participa(ALUMNO_EXP, REGISTRO_COD_REGISTRO, NOTA_ALUMNOS)
  VALUES(1, 19, 7);

  INSERT INTO Participa(ALUMNO_EXP, REGISTRO_COD_REGISTRO, NOTA_ALUMNOS)
  VALUES(2, 20, 8);

  INSERT INTO Participa(ALUMNO_EXP, REGISTRO_COD_REGISTRO, NOTA_ALUMNOS)
  VALUES(3, 21, 9);

  INSERT INTO Participa(ALUMNO_EXP, REGISTRO_COD_REGISTRO, NOTA_ALUMNOS)
  VALUES(1, 22, 10);

  INSERT INTO Participa(ALUMNO_EXP, REGISTRO_COD_REGISTRO, NOTA_ALUMNOS)
  VALUES(2, 23, 0);

  INSERT INTO Participa(ALUMNO_EXP, REGISTRO_COD_REGISTRO, NOTA_ALUMNOS)
  VALUES(3, 24, 1);

  INSERT INTO Participa(ALUMNO_EXP, REGISTRO_COD_REGISTRO, NOTA_ALUMNOS)
  VALUES(1, 25, 2);

  INSERT INTO Participa(ALUMNO_EXP, REGISTRO_COD_REGISTRO, NOTA_ALUMNOS)
  VALUES(2, 26, 3);

  INSERT INTO Participa(ALUMNO_EXP, REGISTRO_COD_REGISTRO, NOTA_ALUMNOS)
  VALUES(3, 27, 4);

  INSERT INTO Participa(ALUMNO_EXP, REGISTRO_COD_REGISTRO, NOTA_ALUMNOS)
  VALUES(1, 28, 5);

  INSERT INTO Participa(ALUMNO_EXP, REGISTRO_COD_REGISTRO, NOTA_ALUMNOS)
  VALUES(2, 29, 6);

  INSERT INTO Participa(ALUMNO_EXP, REGISTRO_COD_REGISTRO, NOTA_ALUMNOS)
  VALUES(3, 30, 7);

  INSERT INTO Participa(ALUMNO_EXP, REGISTRO_COD_REGISTRO, NOTA_ALUMNOS)
  VALUES(1, 31, 8);

  INSERT INTO Participa(ALUMNO_EXP, REGISTRO_COD_REGISTRO, NOTA_ALUMNOS)
  VALUES(2, 32, 9);

  INSERT INTO Participa(ALUMNO_EXP, REGISTRO_COD_REGISTRO, NOTA_ALUMNOS)
  VALUES(3, 33, 10);

  INSERT INTO Participa(ALUMNO_EXP, REGISTRO_COD_REGISTRO, NOTA_ALUMNOS)
  VALUES(1, 34, 0);

  INSERT INTO Participa(ALUMNO_EXP, REGISTRO_COD_REGISTRO, NOTA_ALUMNOS)
  VALUES(2, 35, 1);

  INSERT INTO Participa(ALUMNO_EXP, REGISTRO_COD_REGISTRO, NOTA_ALUMNOS)
  VALUES(3, 36, 2);

  INSERT INTO Participa(ALUMNO_EXP, REGISTRO_COD_REGISTRO, NOTA_ALUMNOS)
  VALUES(1, 37, 3);

  INSERT INTO Participa(ALUMNO_EXP, REGISTRO_COD_REGISTRO, NOTA_ALUMNOS)
  VALUES(2, 38, 4);

  INSERT INTO Participa(ALUMNO_EXP, REGISTRO_COD_REGISTRO, NOTA_ALUMNOS)
  VALUES(3, 39, 5);

  REM *** 13Âº Matricula ***

  INSERT INTO Matricula(Alumno_EXP, ASIGNATURA_CODIGO, acad, COD_GRUPO)
  VALUES(1, 01412, '18/19','M41');

  INSERT INTO Matricula(Alumno_EXP, ASIGNATURA_CODIGO, acad, COD_GRUPO)
  VALUES(2, 01412, '18/19','M41');

  INSERT INTO Matricula(Alumno_EXP, ASIGNATURA_CODIGO, acad, COD_GRUPO)
  VALUES(3, 01412, '18/19','M41');

  INSERT INTO Matricula(Alumno_EXP, ASIGNATURA_CODIGO, acad, COD_GRUPO)
  VALUES(1, 01411, '18/19','M11');

  INSERT INTO Matricula(Alumno_EXP, ASIGNATURA_CODIGO, acad, COD_GRUPO)
  VALUES(2, 01411, '18/19','M11');

  INSERT INTO Matricula(Alumno_EXP, ASIGNATURA_CODIGO, acad, COD_GRUPO)
  VALUES(3, 01411, '18/19','M11');

  INSERT INTO Matricula(Alumno_EXP, ASIGNATURA_CODIGO, acad, COD_GRUPO)
  VALUES(1, 01401, '18/19','M13');

  INSERT INTO Matricula(Alumno_EXP, ASIGNATURA_CODIGO, acad, COD_GRUPO)
  VALUES(2, 01401, '18/19','M13');

  INSERT INTO Matricula(Alumno_EXP, ASIGNATURA_CODIGO, acad, COD_GRUPO)
  VALUES(3, 01401, '18/19','M13');

  REM *** 14Âº ***

  INSERT INTO OCUPA(registro_cod_registro,cod_sala)
  VALUES(1, 1);

  INSERT INTO OCUPA(registro_cod_registro,cod_sala)
  VALUES(2, 1);

  INSERT INTO OCUPA(registro_cod_registro,cod_sala)
  VALUES(3, 1);

  INSERT INTO OCUPA(registro_cod_registro,cod_sala)
  VALUES(4, 1);

  INSERT INTO OCUPA(registro_cod_registro,cod_sala)
  VALUES(5, 1);

  INSERT INTO OCUPA(registro_cod_registro,cod_sala)
  VALUES(6, 1);

  INSERT INTO OCUPA(registro_cod_registro,cod_sala)
  VALUES(7, 1);

  INSERT INTO OCUPA(registro_cod_registro,cod_sala)
  VALUES(8, 1);

  INSERT INTO OCUPA(registro_cod_registro,cod_sala)
  VALUES(9, 1);

  INSERT INTO OCUPA(registro_cod_registro,cod_sala)
  VALUES(10,2);

  INSERT INTO OCUPA(registro_cod_registro,cod_sala)
  VALUES(11,2);

  INSERT INTO OCUPA(registro_cod_registro,cod_sala)
  VALUES(12,2);

  INSERT INTO OCUPA(registro_cod_registro,cod_sala)
  VALUES(13,2);

  INSERT INTO OCUPA(registro_cod_registro,cod_sala)
  VALUES(14,2);

  INSERT INTO OCUPA(registro_cod_registro,cod_sala)
  VALUES(15,2);

  INSERT INTO OCUPA(registro_cod_registro,cod_sala)
  VALUES(16,2);

  INSERT INTO OCUPA(registro_cod_registro,cod_sala)
  VALUES(17,2);

  INSERT INTO OCUPA(registro_cod_registro,cod_sala)
  VALUES(18,2);

  INSERT INTO OCUPA(registro_cod_registro,cod_sala)
  VALUES(19,3);

  INSERT INTO OCUPA(registro_cod_registro,cod_sala)
  VALUES(20,3);

  INSERT INTO OCUPA(registro_cod_registro,cod_sala)
  VALUES(21,3);

  INSERT INTO OCUPA(registro_cod_registro,cod_sala)
  VALUES(22,3);

  INSERT INTO OCUPA(registro_cod_registro,cod_sala)
  VALUES(23,3);

  INSERT INTO OCUPA(registro_cod_registro,cod_sala)
  VALUES(24,3);

  INSERT INTO OCUPA(registro_cod_registro,cod_sala)
  VALUES(25,3);

  INSERT INTO OCUPA(registro_cod_registro,cod_sala)
  VALUES(26,3);

  INSERT INTO OCUPA(registro_cod_registro,cod_sala)
  VALUES(27,3);

  INSERT INTO OCUPA(registro_cod_registro,cod_sala)
  VALUES(28,4);

  INSERT INTO OCUPA(registro_cod_registro,cod_sala)
  VALUES(29,4);

  INSERT INTO OCUPA(registro_cod_registro,cod_sala)
  VALUES(30,4);

  INSERT INTO OCUPA(registro_cod_registro,cod_sala)
  VALUES(31,4);

  INSERT INTO OCUPA(registro_cod_registro,cod_sala)
  VALUES(32,4);

  INSERT INTO OCUPA(registro_cod_registro,cod_sala)
  VALUES(33,4);

  INSERT INTO OCUPA(registro_cod_registro,cod_sala)
  VALUES(34,4);

  INSERT INTO OCUPA(registro_cod_registro,cod_sala)
  VALUES(35,4);

  INSERT INTO OCUPA(registro_cod_registro,cod_sala)
  VALUES(36,4);

  INSERT INTO OCUPA(registro_cod_registro,cod_sala)
  VALUES(37,4);

  INSERT INTO OCUPA(registro_cod_registro,cod_sala)
  VALUES(38,4);

  INSERT INTO OCUPA(registro_cod_registro,cod_sala)
  VALUES(39,4);
