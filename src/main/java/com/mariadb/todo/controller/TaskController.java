package com.mariadb.todo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mariadb.todo.domain.Task;
import com.mariadb.todo.services.TaskService;
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
	@Autowired
	private TaskService service;
	// done	
	@GetMapping  
	public ResponseEntity<Iterable<Task>> get() {
		return ResponseEntity.ok(this.service.getAllTasks());
	}
	// add done
	
	@PostMapping()
	public ResponseEntity<Task> post(@RequestBody Task task) {
        if (service.isValid(task)) {
            return ResponseEntity.ok(this.service.createTask(task));
        }
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }
	 // Update a task
	
    @PutMapping()
    public ResponseEntity<Task> put(@RequestBody Task task) {
        if (service.isValid(task)) {
            return ResponseEntity.ok(this.service.updateTask(task));
        }
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }
    
 // Delete a task done
    @DeleteMapping()
    public ResponseEntity<Void> delete(@RequestParam int id) {
        if (id > 0) {
            this.service.deleteTask(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }
}
