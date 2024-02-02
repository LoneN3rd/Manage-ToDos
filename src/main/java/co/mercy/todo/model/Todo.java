package co.mercy.todo.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "todos")
public class Todo {

    @Id
    private String id;

    @NotBlank(message = "Todo cannot be null")
    private String todo;

    @NotBlank(message = "Description cannot be null")
    private String description;

    private Boolean completed = false;

    //@CreationTimestamp
    private LocalDateTime createdOn;

    private String createdBy;

    //@UpdateTimestamp
    private LocalDateTime lastUpdatedOn;

    private String lastUpdatedBy;

    private Integer isDeleted = 0;
}
