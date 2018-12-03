<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html lang="es">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">

<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<link href="css/style.css" rel="stylesheet">
<title>Tareas de Usuario</title>

</head>
<body>
	<div class="container">
		<h1>Tareas de Usuario - ${user_name}</h1>
		<input type="button" class="btn btn-secondary btn-sm" onclick="location.href='/newTask?id=${id}'" value="Crear Tarea" >
	</div>
	<div class="container">
		<table class="table">
			<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col">Título</th>
					<th scope="col">Destalle</th>
					<th scope="col">Creada</th>
					<th scope="col">Modificada</th>
					<th scope="col">Estado</th>
					<th scope="col">Operación</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="task" items="${tasks}">
					<tr>
						<th scope="row">${task.id_task}</th>
						<td>${task.title}</td>
						<td>${task.detail}</td>
						<td>${task.creation_date}</td>
						<td>${task.modification_date}</td>
						<td>${task.state}</td>
						<td>
							<a href="javascript:void(0)" style="color:black">
								<i class="material-icons"  title="Editar Tarea" onclick="location.href='/editTask?id=${id}&id_task=${task.id_task}'"  >edit</i>
							</a>
							<a href="javascript:void(0)" style="color:black">
								<i class="material-icons" title="Eliminar Tarea" onclick="location.href='/deleteTask?id=${id}&id_task=${task.id_task}'" >delete</i>
							</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
		integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
		crossorigin="anonymous"></script>
</body>
</html>