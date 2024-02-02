package co.mercy.todo.service.impl;

import co.mercy.todo.model.Todo;
import co.mercy.todo.service.TodoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {
    @Override
    public List<Todo> getAll() {
        return null;
    }

    @Override
    public Todo getTodoById(String id) {
        return null;
    }

    @Override
    public List<Todo> getCompletedTodos() {
        return null;
    }

    @Override
    public List<Todo> getIncompleteTodos() {
        return null;
    }
}
