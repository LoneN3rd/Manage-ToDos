package co.mercy.todo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collation = "todos")
public class Todo {

    private String id;

    private String todo;

    private String description;

    private Boolean completed;

    //@CreationTimestamp
    private Date createdOn;

    private String createdBy;

    private Date updatedOn;

    private String updatedBy;
}
