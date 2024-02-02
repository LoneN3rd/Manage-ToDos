package co.mercy.todo.service.impl;

import co.mercy.todo.model.Todo;
import co.mercy.todo.repository.TodoRepository;
import co.mercy.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {
    @Autowired
    TodoRepository todoRepository;

    private final ZonedDateTime today = ZonedDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT, ZoneId.systemDefault());
    private final ZonedDateTime tomorrow = today.plusDays(1);
    private final Date from = Date.from(today.toInstant());
    private final Date to = Date.from(tomorrow.toInstant());

    @Override
    public List<Todo> getAll() {
        return todoRepository.findAll(0);
    }

    @Override
    public Optional<Todo> getTodoById(String id) {
        return todoRepository.findById(id);
    }

    @Override
    public List<Todo> getTodoByName(String name) {
        return todoRepository.findByTodoAndIsDeleted(name, 0);
    }

    @Override
    public List<Todo> getCompleteTodos() {
        return todoRepository.findByCompleted(true);
    }

    @Override
    public List<Todo> getIncompleteTodos() {
        return todoRepository.findByCompleted(false);
    }

    @Override
    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public Todo updateTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public List<Todo> findByTodoCreatedToday(String todo) {
        System.out.println(Date.from(today.toInstant()) +", "+ Date.from(tomorrow.toInstant()));
        return todoRepository.findByTodoAndCreatedOnBetween(todo, from, to);
    }

    @Override
    public List<Todo> createdToday() {
        return todoRepository.findByCreatedOnBetween(from, to);
    }
}
