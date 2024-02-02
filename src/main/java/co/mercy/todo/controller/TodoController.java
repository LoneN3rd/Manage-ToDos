package co.mercy.todo.controller;

import co.mercy.todo.model.Todo;
import co.mercy.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    TodoService todoService;

    @GetMapping("all")
    private ResponseEntity<?> getAllTodos(){
        List<Todo> todos = todoService.getAll();
        if(!todos.isEmpty())
            return new ResponseEntity<List<Todo>>(todos, HttpStatus.OK);
        return new ResponseEntity<>("No todos found", HttpStatus.NOT_FOUND);
    }
}
