package com.mimacom.prueba.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mimacom.prueba.dao.TaskDao;
import com.mimacom.prueba.dao.UserDao;
import com.mimacom.prueba.dao.entity.TaskEntity;
import com.mimacom.prueba.dao.entity.UserEntity;

@Controller
public class WebController {

	@Autowired
	private UserDao userDao;
	@Autowired
	private TaskDao taskDao;


	@GetMapping("/")
	public String login() {
		return "index";
	}

	@RequestMapping(value = "/userTasks", method = RequestMethod.POST)
	public String listUserTasks(HttpServletRequest request, Model model) {
		try {
			String email = request.getParameter("email");
			UserEntity user = userDao.getUserByEmail(email);
			List<TaskEntity> tasks = taskDao.getAll(user);
			
			model.addAttribute("tasks", tasks);
			model.addAttribute("id", user.getId_user());
			model.addAttribute("user_name", user.getUsername());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		return "userTasks";
	}
	
	@GetMapping("/newTask")
	public String taskView(@RequestParam(name = "id", required = true) String id_user, Model model) {
		UserEntity user = userDao.getUser(id_user);

		model.addAttribute("id", user.getId_user());
		model.addAttribute("user_name", user.getUsername());
		return "taskView";
	}
	
	@RequestMapping(value = "/saveTask", method = RequestMethod.POST)
	public String newUserTask(HttpServletRequest request, Model model) {
		String resultPage = "userTasks";
		String id_user = request.getParameter("id");
		UserEntity user = userDao.getUser(id_user);
		String id_task = request.getParameter("id_task");
		try {
			
			String taskTitle= request.getParameter("taskTitle");
			String taskDetail= request.getParameter("taskDetail");
			if (null!=id_task && !id_task.isEmpty()) {//update task
				TaskEntity task = taskDao.getTaskById(id_task);
				task.setTitle(taskTitle);
				task.setDetail(taskDetail);
				taskDao.updateTask(task);
			}else {//New task
				taskDao.createTask(user, taskTitle, taskDetail);
			}
			
			List<TaskEntity> tasks = taskDao.getAll(user);
			model.addAttribute("tasks", tasks);
			model.addAttribute("id", user.getId_user());
			model.addAttribute("user_name", user.getUsername());
		} catch (Exception e) {
			System.err.println(e.getMessage());
			if (null!=id_task && !id_task.isEmpty()) {//update task
				model.addAttribute("error", "Error en la actualización, revise los campos");
				resultPage = editTaskView(id_task,id_user,model);
			}else {
				model.addAttribute("error", "No se ha podido crear la tarea");
				resultPage = taskView(id_user,model);
			}
			
		}
		return resultPage;
	}
	
	@GetMapping("/editTask")
	public String editTaskView(@RequestParam(name = "id_task", required = true) String id_task, @RequestParam(name = "id", required = true) String id_user, Model model) {

		UserEntity user = userDao.getUser(id_user);

		TaskEntity task = taskDao.getTaskById(id_task);
		
		model.addAttribute("id", user.getId_user());
		model.addAttribute("user_name", user.getUsername());
		model.addAttribute("task", task);
		return "taskView";
	}
	
	@GetMapping("/completeTask")
	public String completeTask(@RequestParam(name = "id_task", required = true) String id_task, @RequestParam(name = "id", required = true) String id_user, Model model) {
		try {
			UserEntity user = userDao.getUser(id_user);
			TaskEntity task = taskDao.completeTask(id_task);
			
			List<TaskEntity> tasks = taskDao.getAll(user);
			model.addAttribute("tasks", tasks);
			model.addAttribute("id", user.getId_user());
			model.addAttribute("user_name", user.getUsername());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return "userTasks";
	}
	
	@GetMapping("/deleteTask")
	public String deleteTask(@RequestParam(name = "id_task", required = true) String id_task, @RequestParam(name = "id", required = true) String id_user, Model model) {
		try {
			UserEntity user = userDao.getUser(id_user);
			taskDao.deleteTask(id_task);
			
			List<TaskEntity> tasks = taskDao.getAll(user);
			model.addAttribute("tasks", tasks);
			model.addAttribute("id", user.getId_user());
			model.addAttribute("user_name", user.getUsername());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return "userTasks";
	}
	
	@GetMapping("/testNewUser")
	public String newUser(@RequestParam(name = "name", required = true) String name, Model model) {
		try {
			userDao.createUser(name, "Mimacom", "Flowable", name + "@mimacom.com", "1234");

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		return "index";
	}

	@GetMapping("/testNewTask")
	public String newTask(@RequestParam(name = "idUser", required = true) String id_user, Model model) {
		try {
			UserEntity user = userDao.getUser(id_user);
			taskDao.createTask(user, "Tarea 1", "Esta es la primera tarea");

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		return "index";
	}

}
