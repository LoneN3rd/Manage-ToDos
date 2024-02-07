package co.mercy.todo.service.impl;

import co.mercy.todo.exception.TodoExceptionHandler;
import co.mercy.todo.model.Todo;
import co.mercy.todo.repository.TodoRepository;
import co.mercy.todo.service.TodoService;
import jakarta.validation.ConstraintDeclarationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {
    @Autowired
    private TodoRepository todoRepository;

    private final ZonedDateTime today = ZonedDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT, ZoneId.systemDefault());
    private final ZonedDateTime tomorrow = today.plusDays(1);
    private final Date from = Date.from(today.toInstant());
    private final Date to = Date.from(tomorrow.toInstant());

    @Override
    public List<Todo> getAll() {
       return todoRepository.findByIsDeleted(0);
    }

    @Override
    public Optional<Todo> getTodoById(String id) {
        return todoRepository.findByIdAndIsDeleted(id, 0);
    }

    @Override
    public List<Todo> getTodoByName(String name) {
        return todoRepository.findByTodoAndIsDeleted(name, 0);
    }

    @Override
    public List<Todo> getCompleteTodos() {
        return todoRepository.findByCompletedAndIsDeleted(true, 0);
    }

    @Override
    public List<Todo> getIncompleteTodos() {
        return todoRepository.findByCompletedAndIsDeleted(false, 0);
    }

    @Override
    public Todo createTodo(Todo todo) throws ConstraintDeclarationException, TodoExceptionHandler {
        // TODO: move this logic to the service layer
        List<Todo> existingTodos = this.findByTodoCreatedToday(todo.getTodo());
        System.out.println("Existing todos count: "+ existingTodos.size());
        if(existingTodos.isEmpty()){
            System.out.println("Creating todo with name '"+ todo.getTodo() +"'");
            todo.setCreatedOn(LocalDateTime.now());
            todo.setCreatedBy("mmutuku");
            todo.setLastUpdatedOn(LocalDateTime.now());
            todo.setLastUpdatedBy("mmutuku");
            return todoRepository.save(todo);
        } else {
            System.out.println("Throwing todo AlreadyExists exception");
            throw new TodoExceptionHandler(TodoExceptionHandler.AlreadyExists(todo.getTodo()));
        }
    }

    @Override
    public Todo updateTodo(Todo todo, String id) throws TodoExceptionHandler {
        Optional<Todo> existingTodo = this.getTodoById(id);
        if(existingTodo.isPresent()) {
            Todo existing = existingTodo.get();
            existing.setTodo(todo.getTodo() != null ? todo.getTodo() : existingTodo.get().getTodo());
            existing.setDescription(todo.getDescription() != null ? todo.getDescription() : existingTodo.get().getDescription());
            existing.setCompleted(todo.getCompleted() != null ? todo.getCompleted() : existingTodo.get().getCompleted());
            existing.setLastUpdatedOn(LocalDateTime.now());
            return todoRepository.save(existing);
        } else {
            System.out.println("Throwing IDNotFound exception");
            throw new TodoExceptionHandler(TodoExceptionHandler.IDNotFound(id));
        }
    }

    @Override
    public Todo completeTodo(String id) throws TodoExceptionHandler {
        Optional<Todo> existingTodo = this.getTodoById(id);
        if(existingTodo.isPresent()) {
            Todo existing = existingTodo.get();
            existing.setLastUpdatedOn(LocalDateTime.now());
            existing.setCompleted(true);
            return todoRepository.save(existing);
        } else {
            System.out.println("Throwing IDNotFound exception");
            throw new TodoExceptionHandler(TodoExceptionHandler.IDNotFound(id));
        }
    }

    @Override
    public String deleteTodo(String id) throws TodoExceptionHandler {
        Optional<Todo> existingTodo = this.getTodoById(id);
        if(existingTodo.isPresent()) {
            Todo existing = existingTodo.get();
            existing.setIsDeleted(1);
            existing.setLastUpdatedOn(LocalDateTime.now());
            todoRepository.save(existing);
            return "Todo with ID "+ id +" deleted";
        } else {
            System.out.println("Throwing IDNotFound exception");
            throw new TodoExceptionHandler(TodoExceptionHandler.IDNotFound(id));
        }
    }

    @Override
    public List<Todo> findByTodoCreatedToday(String todo) {
        System.out.println("Getting todos with name '"+ todo +"', AND created between " + Date.from(today.toInstant()) +" and "+ Date.from(tomorrow.toInstant()));
        return todoRepository.findByTodoAndIsDeletedAndCreatedOnBetween(todo, 0, from, to);
    }

    @Override
    public List<Todo> createdToday() {
        return todoRepository.findByCreatedOnBetween(from, to, 0);
    }
}
