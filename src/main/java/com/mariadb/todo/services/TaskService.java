package com.mariadb.todo.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mariadb.todo.domain.Task;
import com.mariadb.todo.repositories.TaskRepository;

@Service
public class TaskService {
	@Autowired
	private TaskRepository repository;
	
	public Boolean isValid(final Task task) {
		if(task != null && !task.getDescription().isEmpty()) {
			return true;
		}
		return false;
	}
	// Lay tat ca record tu bang
	public Iterable<Task> getAllTasks(){
		return this.repository.findAll();
	}
	public Task createTask(final Task task) {
		return this.repository.save(task);
	}
	
	@Transactional
	public Task updateTask(final Task task) {
		Optional<Task> optionTask = this.repository.findById(task.getId());
		Task t = optionTask.get();
		t.setCompleted(task.getCompleted());
		t.setDescription(task.getDescription());
		return this.repository.save(t);
	}
	@Transactional
	public void deleteTask(final int id) {
		Optional<Task> optionTask = this.repository.findById(id);
		Task t = optionTask.get();
		this.repository.delete(t);
	}
	
}
