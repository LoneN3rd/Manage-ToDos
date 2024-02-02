package co.mercy.todo.service;

import co.mercy.todo.model.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> getAll();

    Todo getTodoById(String id);

    List<Todo> getCompletedTodos();

    List<Todo> getIncompleteTodos();
}
