package co.mercy.todo.repository;

import co.mercy.todo.model.Todo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends MongoRepository<Todo, String> {

    List<Todo> findByTodoAndIsDeleted(String todo, int deleted, Pageable pages);

    Optional<Todo> findByIdAndIsDeleted(String id, int deleted);

    Optional<Todo> findByTodoAndIsDeletedAndCreatedOnBetween(String todo, int deleted, Date from, Date to);

    List<Todo> findByCreatedOnBetween(Date from, Date to, int deleted, Pageable pages);

    List<Todo> findByIsDeleted(int deleted, Pageable pages);

    List<Todo> findByCompletedAndIsDeleted(boolean status, int deleted, Pageable pages);
}
