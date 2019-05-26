
REM ********************************* INFORMES ***************************************

--NÚMERO DE HORAS DE ACTIVIDAD 
--TOTALES
select count(cod_registro) * A.horas_actividad as "Horas actividad"
from registro R, actividad A
where A.nombre = R.actividad_nombre and R.fecha BETWEEN TO_DATE ('01/09/2018', 'dd/mm/yyyy')
AND TO_DATE ('01/07/2019', 'dd/mm/yyyy')
group by A.horas_actividad
order by 1

--TITULACION
select titulacion, curso, count(*) * ACT.horas_actividad as "Horas totales"
from registro R, asignatura ASI, actividad ACT
where ACT.asignatura_codigo=ASI.codigo and R.actividad_nombre = ACT.nombre and R.fecha BETWEEN TO_DATE ('01/09/2018', 'dd/mm/yyyy')
AND TO_DATE ('01/07/2019', 'dd/mm/yyyy')
group by titulacion,curso, ACT.horas_actividad
order by titulacion

--TITULACION y CURSO
select ASI.titulacion, ASI.nombre, count(cod_registro) * ACT.horas_actividad as "Horas totales"
from registro R, asignatura ASI, actividad ACT
where ACT.asignatura_codigo=ASI.codigo and R.actividad_nombre = ACT.nombre and R.fecha BETWEEN TO_DATE ('01/09/2018', 'dd/mm/yyyy')
AND TO_DATE ('01/07/2019', 'dd/mm/yyyy')
group by ASI.titulacion, ASI.nombre, ACT.horas_actividad
order by 1


--TITULACION Y ASIGNATURA
select P.numero, p.nombre, P.apellido1, sum(horas_profesor) as "Horas totales"
from registro R, actividad ACT, profesor P, realiza RE
where R.cod_registro=RE.registro_cod_registro and R.actividad_nombre = ACT.nombre and RE.PROFESOR_NUMERO = P.numero and R.fecha BETWEEN TO_DATE ('01/09/2018', 'dd/mm/yyyy')
AND TO_DATE ('01/07/2019', 'dd/mm/yyyy')
group by P.numero, p.nombre, P.apellido1 ,ACT.horas_actividad
order by 1

--PROFESOR---
select P.numero, p.nombre, P.apellido1, sum(horas_profesor) as "Horas totales"
from registro R, actividad ACT, profesor P, realiza RE
where R.cod_registro=RE.registro_cod_registro and R.actividad_nombre = ACT.nombre and RE.PROFESOR_NUMERO = P.numero and R.fecha BETWEEN TO_DATE ('01/09/2018', 'dd/mm/yyyy')
AND TO_DATE ('01/07/2019', 'dd/mm/yyyy')
group by P.numero, p.nombre, P.apellido1 ,ACT.horas_actividad
order by 1

--Salas---
select S.tipo_sala , S.cod_sala ,count(S.tipo_sala) * ACT.horas_actividad as Horas
from Sala S , actividad ACT , registro R , Ocupa O
Where R.actividad_nombre = ACT.nombre 
and  R.cod_registro = O.registro_cod_registro 
and O.cod_sala = S.cod_sala
group by S.tipo_sala ,S.cod_sala, ACT.horas_actividad

--TIPO DE ACTIVIDAD----
select tipo_actividad as "Tipo de actividad", count(cod_registro) * horas_actividad as "Horas totales"
from actividad, registro
where actividad_nombre = nombre and fecha BETWEEN TO_DATE ('01/09/2018', 'dd/mm/yyyy')
AND TO_DATE ('01/07/2019', 'dd/mm/yyyy')
group by tipo_actividad, horas_actividad
order by 1
--SEMESTRES----
select case when fecha BETWEEN TO_DATE ('01/09/2018', 'dd/mm/yyyy') 
and  TO_DATE ('01/02/2019', 'dd/mm/yyyy') then 'S1' when fecha BETWEEN TO_DATE ('02/02/2019', 'dd/mm/yyyy') 
and  TO_DATE ('31/08/2019', 'dd/mm/yyyy') then 'S2' else 'Nada' end as "Semestres", sum(Horas_actividad) as "Horas totales"
from actividad, registro
where actividad_nombre= nombre
group by case when fecha BETWEEN TO_DATE ('01/09/2018', 'dd/mm/yyyy') and  TO_DATE ('01/02/2019', 'dd/mm/yyyy') then 'S1' when fecha BETWEEN TO_DATE ('02/02/2019', 'dd/mm/yyyy') and  TO_DATE ('31/08/2019', 'dd/mm/yyyy') then 'S2' else 'Nada' end

--MESES----
select to_char(fecha,'MONTH') as "Mes", sum(Horas_actividad) as "Horas totales"
from registro, actividad
where actividad_nombre = nombre and fecha BETWEEN TO_DATE ('01/09/2018', 'dd/mm/yyyy')
AND TO_DATE ('01/07/2019', 'dd/mm/yyyy')
group by to_char(fecha,'MONTH')
order by 1


--NÚMERO DE HORAS DE ACTOR

--TOTALES EN UN CURSO ACADÉMICO----
select sum(horas_actor) "HORAS TOTALES DE ACTOR" , curso from actua , registro , actividad , asignado , asignatura 
where actua.cod_registro_actua = registro.cod_registro and registro.nombre_actividad_registro = actividad.nombre_actividad and actividad.nombre_actividad=asignado.nombre_actividad and asignado.codigo = asignatura.codigo
group by curso

--TITULACIÓN y MES
---
select titulacion,to_char(fecha,'MONTH'), sum(horas_actor) as "Horas totales"
from asignatura ASI, actividad ACT, registro R, Actua A
where ACT.asignatura_codigo=ASI.codigo and R.cod_registro = A.registro_cod_registro and R.actividad_nombre = ACT.nombre and R.fecha BETWEEN TO_DATE ('01/09/2018', 'dd/mm/yyyy')
AND TO_DATE ('01/07/2019', 'dd/mm/yyyy')
group by titulacion, to_char(fecha,'MONTH')

--TITULACIÓN y CURSO ACADEMICO---
select titulacion, to_char(R.fecha,'MONTH') as "Curso academico 18/19", sum(horas_actor) as "Horas totales"
from actua A, actividad ACT, Registro R, Asignatura ASI
where R.cod_registro = A.registro_cod_registro and R.actividad_nombre = ACT.nombre and ACT.asignatura_codigo=ASI.codigo and R.fecha BETWEEN TO_DATE ('01/09/2018', 'dd/mm/yyyy')
AND TO_DATE ('01/07/2019', 'dd/mm/yyyy')
group by titulacion, to_char(R.fecha,'MONTH')
order by titulacion

--LISTADO DE ALUMNOS

--ASIGNATURA Y GRUPO (ACTIVOS)---
select  A.nombre ,ASI.nombre as "Asignatura", M.cod_grupo as "Codigo Grupo", A.exp as "Expediente"
from alumno A, asignatura ASI, matricula M
where a.activo = 1 
and A.exp = M.alumno_exp 
and ASI.codigo = M.asignatura_codigo 
group by A.nombre, ASI.nombre, M.cod_grupo, A.exp
order by A.nombre , ASI.nombre, M.cod_grupo
--NOTAS POR NOMBRE DE ACTIVIDAD---

select  AL.exp as "Expediente", AL.nombre as "Nombre", AC.nombre as "Actividad", P.nota_alumnos as "Nota"
from alumno AL,  actividad AC, registro R, participa P
where AL.exp = P.alumno_exp and P.registro_cod_registro = R.cod_registro and R.actividad_nombre = AC.nombre and AL.activo=1
order by AC.nombre

--LISTADO DE PROFESORES

--TITULACION (ACTIVOS)---

select P.Titulacion as "Titulacion", P.numero as "numero", p.nombre || ' ' || P.apellido1 || ' ' || P.apellido2 as "Nombre y apellidos"
from profesor P
where P.activo = 1


REM ********************************* LOGIN ***************************************

	--selectPasswdUsuario  
	SELECT PWD, ROL FROM USERS WHERE USR = ? 
	--Login--


REM ********************************* TABLAS ***************************************

	--Tablas home
	SELECT distinct sala.tipo_sala, SUBSTR(registro.hora, 10, 6) as inicio , SUBSTR(registro.hora,10,6) +2 as  fin, actividad.tipo_actividad, asignatura.titulacion, matricula.cod_grupo,profesor.nombre || profesor.apellido1 as "Profesor"
	from sala, registro, ocupa, actividad, asignatura, matricula, realiza, profesor 
	where registro.cod_registro = ocupa.registro_cod_registro 
	and ocupa.cod_sala = sala.cod_sala 
	and registro.actividad_nombre = actividad.nombre
	and actividad.asignatura_codigo = asignatura.codigo
	and asignatura.codigo = matricula.asignatura_codigo
	and  realiza.registro_cod_registro = registro.cod_registro 
	and realiza.profesor_numero  = profesor.numero

	--Tabla usuarios
	SELECT USR as Usuario, ROL as Rol FROM USERS
	--select Registros--
	SELECT cod_registro , fecha , horas_profesor , actividad_nombre FROM Registro
	--select Alumnos 
	SELECT * FROM alumno
	--selectTodasActividades--
	SELECT nombre , tipo_actividad , tipo_sala, simulador , documentacion_tecnica , horas_actividad , acad  FROM actividad
	--select Asignaturas--
	SELECT codigo , nombre , titulacion , curso FROM asignatura
	--select Profesores--
	SELECT numero , nombre || apellido1 || apellido2 as Nombre , titulacion , dni, activo, relacion_laboral,tlf1,tlf2 , mail1, mail2 from profesor
	--select Actores--
	 SELECT  cod_actor , nombre , edad , genero , idioma , complexion , activo FROM actor
	--select Salas--
	SELECT cod_sala , tipo_sala , numero , capacidad FROM sala
	--select Acad-- 
	SELECT * FROM acad
	--select Listado de Grupo--
	SELECT cod_grupo FROM matricula
	--selectListado PorGrupo-- 
	SELECT  cod_grupo , nombre FROM matricula , alumno where aexp = alumno_exp  and cod_grupo = ?

REM ********************************* BUSQUEDA ***************************************

    --selectBuscadorUsuarios-- 
	SELECT USR, ROL FROM HOSPITAL.USERS WHERE USR = ? or ROL= ?
    --selectBuscadorRegistros-- 
    SELECT cod_registro, fecha, horas_profesor, actividad_nombre FROM HOSPITAL.REGISTRO WHERE cod_registro = ? or fecha= ? or horas_profesor=? or actividad_nombre=?
    --selectBuscadorActividades-- 
    SELECT nombre , tipo_actividad , tipo_sala, simulador , documentacion_tecnica , horas_actividad , acad  FROM hospital.actividad WHERE nombre=? or tipo_actividad=? or tipo_sala=? or simulador=? or documentacion_tecnica=? or horas_actividad=? or acad=?
    --selectBuscadorAsignatura--
    SELECT codigo , nombre , titulacion , curso FROM HOSPITAL.asignatura WHERE codigo=? or nombre=? or titulacion=? or curso=?
    --selectBuscadorAlumnos-- 
    SELECT * FROM HOSPITAL.alumno WHERE exp=? or nombre=? or activo=?
    --selectBuscadorProfesores-- 
    SELECT * from HOSPITAL.profesor WHERE numero=? or nombre=? or apellido1=? or apellido2=? or titulacion=? or dni=? or activo=? or relacion_laboral=? or tlf1=? or tlf2=? or mail1=? or mail2=?
    --selectBuscadorActores-- 
    SELECT cod_actor, nombre, edad, genero, idioma, complexion, activo FROM HOSPITAL.actor WHERE cod_actor=? or nombre=? or edad=? or genero=? or idioma=? or complexion=? or activo=?
   --selectBuscadorSalas--
   SELECT cod_sala , tipo_sala , numero , capacidad FROM HOSPITAL.sala WHERE cod_sala=? or tipo_sala=? or numero=? or capacidad=?
   --selectBuscadorAcad--
    SELECT * FROM HOSPITAL.acad WHERE acad=? or sem1=? or sem2=?






