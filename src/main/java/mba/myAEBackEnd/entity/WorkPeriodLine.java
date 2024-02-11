package mba.myAEBackEnd.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Accessors(chain = true)
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
    private Set<WorkDay> workDays = new HashSet<>();

    public WorkPeriodLine addWorkDay(WorkDay workDay){
        workDays.add(workDay);
        return this;
    }
}
