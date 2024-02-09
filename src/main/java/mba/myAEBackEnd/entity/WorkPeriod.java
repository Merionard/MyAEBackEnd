package mba.myAEBackEnd.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.HashSet;

@Data
@Entity
public class WorkPeriod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int month;
    private int year;
    @OneToMany(mappedBy = "workPeriod",cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<WorkPeriodLine> lines = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
