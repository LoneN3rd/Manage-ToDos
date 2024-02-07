package co.mercy.todo.service;

import co.mercy.todo.exception.TodoExceptionHandler;
import co.mercy.todo.model.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoService {
    List<Todo> getAll();

    Optional<Todo> getTodoById(String id) throws TodoExceptionHandler;

    List<Todo> getTodoByName(String name);

    List<Todo> getCompleteTodos();

    List<Todo> getIncompleteTodos();

    Todo createTodo(Todo todo) throws TodoExceptionHandler;

    Todo updateTodo(Todo todo, String id) throws TodoExceptionHandler;

    Todo completeTodo(String id) throws TodoExceptionHandler;

    String deleteTodo(String id) throws TodoExceptionHandler;

    List<Todo> findByTodoCreatedToday(String todo);

    List<Todo> createdToday();
}
