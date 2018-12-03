<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
	

	<link href="css/style.css" rel="stylesheet">
	<title>Tareas de Usuario</title>
</head>
<body>
	<div class="container">
		<c:if test="${not empty task}">
			<h1>Editar Tarea - ${user_name}</h1>
		</c:if>
		<c:if test="${empty task}">
			<h1>Crear Tarea - ${user_name}</h1>
		</c:if>

		<c:if test="${not empty error}">
			<div class="p-3 mb-2 bg-warning text-dark">${error}</div>
		</c:if>


		<form id="task" method="POST" action="/saveTask">
			<div class="form-group">
				<label for="taskTitle">Título</label> <input type="text"
					class="form-control" id="taskTitle" placeholder="Título"
					name="taskTitle" value="${task.title }">
			</div>
			<div class="form-group">
				<label for="taskDetail">Detalle</label>
				<textarea class="form-control" id="taskDetail" rows="3"
					name="taskDetail">${task.detail}</textarea>

			</div>
			<input type="hidden" id="id" name="id" value="${id}"> <input
				type="hidden" id="id" name="id_task" value="${task.id_task}">

			
			
			<c:if test="${empty task}">
				<div>
					<button type="submit" class="btn btn-secondary">Guardar</button>
				</div>
			</c:if>
			
			<c:if test="${not empty task && task.state != 'Completada'}">
				<div>
					<button type="submit" class="btn btn-secondary">Guardar</button>
					<c:if test="${not empty task}">
						<input type="button" class="btn btn-secondary"
							onclick="location.href='/completeTask?id=${id}&id_task=${task.id_task}'"
							value="Completar Tarea">
					</c:if>
				</div>
			</c:if>
			
		</form>

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