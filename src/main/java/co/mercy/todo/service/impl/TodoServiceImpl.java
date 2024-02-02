package co.mercy.todo.service.impl;

import co.mercy.todo.model.Todo;
import co.mercy.todo.repository.TodoRepository;
import co.mercy.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {
    @Autowired
    TodoRepository todoRepository;

    @Override
    public List<Todo> getAll() {
        return todoRepository.findAll();
    }

    @Override
    public Optional<Todo> getTodoById(String id) {
        return todoRepository.findById(id);
    }

    @Override
    public List<Todo> getCompleteTodos() {
        return todoRepository.findAll();
    }

    @Override
    public List<Todo> getIncompleteTodos() {
        return todoRepository.findAll();
    }

    @Override
    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public Todo updateTodo(Todo todo) {
        return todoRepository.save(todo);
    }
}
