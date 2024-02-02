package co.mercy.todo.service;

import co.mercy.todo.model.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoService {
    List<Todo> getAll();

    Optional<Todo> getTodoById(String id);

    List<Todo> getCompleteTodos();

    List<Todo> getIncompleteTodos();

    Todo createTodo(Todo todo);

    Todo updateTodo(Todo todo);
}
