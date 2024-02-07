package co.mercy.todo.service;

import co.mercy.todo.exception.TodoExceptionHandler;
import co.mercy.todo.model.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoService {
    List<Todo> getAll(int pageNumber, int pageSize);

    Optional<Todo> getTodoById(String id) throws TodoExceptionHandler;

    List<Todo> getTodoByName(String name, int pageNumber, int pageSize);

    List<Todo> getCompleteTodos(int pageNumber, int pageSize);

    List<Todo> getIncompleteTodos(int pageNumber, int pageSize);

    Todo createTodo(Todo todo) throws TodoExceptionHandler;

    Todo updateTodo(Todo todo, String id) throws TodoExceptionHandler;

    Todo completeTodo(String id) throws TodoExceptionHandler;

    String deleteTodo(String id) throws TodoExceptionHandler;

    Optional<Todo> findByTodoCreatedToday(String todo);

    List<Todo> createdToday(int pageNumber, int pageSize);
}
