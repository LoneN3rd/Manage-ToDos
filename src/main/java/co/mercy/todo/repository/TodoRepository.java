package co.mercy.todo.repository;

import co.mercy.todo.model.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends MongoRepository<Todo, String> {

    @Query("{'todo': ?0, 'isDeleted': ?1}")
    List<Todo> findByTodoAndIsDeleted(String todo, int deleted);

    List<Todo> findByTodoAndCreatedOnBetween(String todo, Date from, Date to);

    List<Todo> findByCreatedOnBetween(Date from, Date to);

    @Query("{'isDeleted': ?0}")
    List<Todo> findAll(int deleted);

    @Query("{'completed': ?0}")
    List<Todo> findByCompleted(boolean status);
}
