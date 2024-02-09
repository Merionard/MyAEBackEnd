package mba.myAEBackEnd.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class WorkPeriodLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    private int nbDaysWorked;
    @ManyToOne
    @JoinColumn(name="workPeriod_id")
    private WorkPeriod workPeriod;
    @OneToMany(mappedBy = "workPeriodLine",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<WorkDay> workDays;
}
