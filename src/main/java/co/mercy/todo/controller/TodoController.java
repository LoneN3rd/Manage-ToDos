package co.mercy.todo.controller;

import co.mercy.todo.exception.TodoExceptionHandler;
import co.mercy.todo.model.Todo;
import co.mercy.todo.service.TodoService;
import jakarta.validation.ConstraintDeclarationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    TodoService todoService;

    @GetMapping("/all")
    private ResponseEntity<?> getAllTodos(@RequestParam Integer pageNumber, @RequestParam Integer pageSize){
        return new ResponseEntity<>(todoService.getAll(pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/all/complete")
    public ResponseEntity<List<Todo>> getCompleteTodos(@RequestParam Integer pageNumber, @RequestParam Integer pageSize){
        return new ResponseEntity<>(todoService.getCompleteTodos(pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/all/incomplete")
    public ResponseEntity<List<Todo>> getIncompleteTodos(@RequestParam Integer pageNumber, @RequestParam Integer pageSize){
        return new ResponseEntity<>(todoService.getIncompleteTodos(pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getTodById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(todoService.getTodoById(id), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/name")
    public ResponseEntity<?> getTodoByName(@RequestParam String name, @RequestParam Integer pageNumber, @RequestParam Integer pageSize){
        return new ResponseEntity<>(todoService.getTodoByName(name, pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/today")
    public ResponseEntity<?> getTodosCreatedToday(@RequestParam Integer pageNumber, @RequestParam Integer pageSize){
        return new ResponseEntity<>(todoService.createdToday(pageNumber, pageSize), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTodo(@RequestBody Todo todo){
        try {
            return new ResponseEntity<>(todoService.createTodo(todo), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    private ResponseEntity<?> updateTodo(@RequestBody Todo todo, @PathVariable String id){
        try {
            return new ResponseEntity<>(todoService.updateTodo(todo, id), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/complete/{id}")
    private ResponseEntity<?> completeTodo(@PathVariable String id){
        try {
            return new ResponseEntity<>(todoService.completeTodo(id), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/del/{id}")
    private ResponseEntity<?> deleteTodo(@PathVariable String id){
        try {
            return new ResponseEntity<>(todoService.deleteTodo(id), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
