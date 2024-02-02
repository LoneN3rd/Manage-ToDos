package co.mercy.todo.service;

import co.mercy.todo.model.Todo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TodoService {
    List<Todo> getAll();

    Optional<Todo> getTodoById(String id);

    List<Todo> getTodoByName(String name);

    List<Todo> getCompleteTodos();

    List<Todo> getIncompleteTodos();

    Todo createTodo(Todo todo);

    Todo updateTodo(Todo todo);

    List<Todo> findByTodoCreatedToday(String todo);

    List<Todo> createdToday();
}
