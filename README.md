# userTasks
Prueba: Tareas de Usuario

Proyecto implementado por Juan Luis Herrera.

Requisitos previos:
1-  Java, está desarrollado y provado con la versión 8 de Java.
2-  MySQL, se ha utilizado como base de datos MySQL Server version: 5.7.24.
3-  Maven
4-  Conexión a internet, ya que depende de algunas librerías

/**
* Opción 1
**/
Para poner en marcha esta aplicación, se debe:
1. Descargar el código en un IDE (Eclipse).
2. Crear una base de datos en MySQL:
    > create database db_mimacom;
3. Crear usuario para base de datos:
    > create user adminMimacom indentified by 'Admin.1234'
4. Dar permisos a este usuario en la base de datos:
    > grant all on db_mimacom.* to 'adminMimacom';
5. Crear tabla de usuarios:
CREATE TABLE users (
	id_user INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	username VARCHAR(15) NOT NULL,
	first_name VARCHAR(15) NOT NULL,
	last_name VARCHAR(15),
	email VARCHAR(64) NOT NULL,
	creation_date DATE NOT NULL,
	password VARCHAR(15) NOT NULL
);
6. Insertar los usuarios deseados de prueba. La aplicación valida el email, no el password.
insert into users (username, first_name, last_name, email, creation_date, password)
values ('User1', 'Mimacom', 'Flowable', 'user1@mimacom.com', '2018-12-01 10:47:00', '1234');

insert into users (username, first_name, last_name, email, creation_date, password)
values ('User2', 'Mimacom', 'Flowable', 'user2@mimacom.com', '2018-12-01 10:48:00', '1234');

insert into users (username, first_name, last_name, email, creation_date, password)
values ('User3', 'Mimacom', 'Flowable', 'user3@mimacom.com', '2018-12-01 10:49:00', '1234');
7. Crear tabla tareas:
create table tasks (
	id_task INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	id_user INT(6) UNSIGNED,
	title VARCHAR(32) NOT NULL,
	detail VARCHAR(64) NOT NULL,
	creation_date DATE NOT NULL,
	modification_date DATE NOT NULL,
	state varchar(16) not null default 'Pendiente' check (state in ('Pendiente', 'Completada')),
    FOREIGN KEY (id_user) REFERENCES users(id_user) ON DELETE CASCADE
);
Nota: LOS SCRIPTS DE CREACIÓN DE TABLAS, ESTÁN EN EL DIRECTORIO /prueba/scripts
8. Compilar el proyecto con Maven, descargando dependencias.
9. Si se han cambiado valores de base de datos, se deben configurar en el fichero:
/prueba/src/main/resources/application.properties

/**
* EJECUCIÓN
**/
Ejecutar como aplicación Java la clase:
com.mimacom.prueba.TasksApplication

Una vez ejecutada la clase, se accede a través del navegador: http://localhost:8080/

/**
* Opción 2
**/
Ejecutar la aplicación sin necesidad de tener el entorno instalado:
	- 	Descargar el codigo y descomprimir en un directorio.
	-	Realizar los pasos 2, 3 4, 5, 6 y 7 de la opción 1.
	-	Ir al directorio donde se ha descomprimido el proyecto (carpeta principal)
	-  Ejecutar:
		> mvn spring-boot:run



/**
* Funcionalidad
**/
Al acceder a localhost:8080/ nos aparece una página de login. Este proyecto no valida el password, con lo que basta para acceder meter el email de unos de los usuarios insertados en la tabla 'users' de base de datos.
La primera página que aparece es la vista global de tareas, donde podrá visualizar qué tareas tiene pendientes, y cuáles completadas.
Mediante el botón de 'Create tarea' accede a la vista, para este fin. Se debe insertar el título y el detalle. No se ha puesto validación. Al pulsar guardar, aparecerá en la pantalla de tareas de usuario, la nueva tarea.
Como opciones sobre las tareas se puede:
*Eliminar: borraría la tarea.
*Editar: podrá modificar los valores de la tarea o completarla.

