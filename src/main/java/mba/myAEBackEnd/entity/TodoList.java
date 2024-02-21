package mba.myAEBackEnd.entity;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

@Enabled
@Getter
@Setter
@Accessors(chain = true)
@Entity
public class TodoList {
    @Id
    private String title;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "todoList" ,cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("priorityOrder ASC")
    private Set<Task> tasks = new HashSet<>();
}
