package co.mercy.todo.controller;

import co.mercy.todo.model.Todo;
import co.mercy.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    TodoService todoService;

    @GetMapping("/all")
    private ResponseEntity<?> getAllTodos(){
        List<Todo> todos = todoService.getAll();
        if(!todos.isEmpty())
            return new ResponseEntity<List<Todo>>(todos, HttpStatus.OK);
        return new ResponseEntity<>("No todos found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all/complete")
    public ResponseEntity<List<Todo>> getCompleteTodos(){
        return new ResponseEntity<>(todoService.getCompleteTodos(), HttpStatus.OK);
    }

    @GetMapping("/all/incomplete")
    public ResponseEntity<List<Todo>> getIncompleteTodos(){
        return new ResponseEntity<>(todoService.getIncompleteTodos(), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getTodById(@PathVariable String id){
        Optional<Todo> todo = todoService.getTodoById(id);
        System.out.println(todo);
        if(todo.isPresent())
            return new ResponseEntity<>(todo, HttpStatus.OK);
        return new ResponseEntity<>("Todo with id "+ id +" not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTodo(@RequestBody Todo todo){
        try {
            todo.setCreatedOn(new Date(System.currentTimeMillis()));
            todo.setCreatedBy("mmutuku");
            todo.setLastUpdatedOn(new Date(System.currentTimeMillis()));
            todo.setLastUpdatedBy("mmutuku");
            todoService.createTodo(todo);
            return new ResponseEntity<>(todo, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    private ResponseEntity<?> updateTodo(@RequestBody Todo todo, @PathVariable String id){
        try {
            Optional<Todo> existingTodo = todoService.getTodoById(id);
            if(existingTodo.isPresent()) {
                Todo existing = existingTodo.get();
                existing.setTodo(todo.getTodo() != null ? todo.getTodo() : existingTodo.get().getTodo());
                existing.setDescription(todo.getDescription() != null ? todo.getDescription() : existingTodo.get().getDescription());
                existing.setCompleted(todo.getCompleted() != null ? todo.getCompleted() : existingTodo.get().getCompleted());
                existing.setLastUpdatedOn(new Date(System.currentTimeMillis()));
                todoService.updateTodo(existing);
                return new ResponseEntity<>(existingTodo, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Todo with id " + id + " not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/del/{id}")
    private ResponseEntity<?> deleteTodo(@PathVariable String id){
        try {
            Optional<Todo> existingTodo = todoService.getTodoById(id);
            String msg;
            HttpStatus httpStatus;
            if(existingTodo.isPresent()) {
                Todo existing = existingTodo.get();
                existing.setIsDeleted(1);
                todoService.updateTodo(existing);
                msg = "Todo with id "+ id +" deleted";
                httpStatus = HttpStatus.OK;
            } else {
                msg = "Todo with id " + id + " not found";
                httpStatus = HttpStatus.NOT_FOUND;
            }
            return new ResponseEntity<>(msg, httpStatus);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
