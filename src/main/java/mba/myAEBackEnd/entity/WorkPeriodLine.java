package mba.myAEBackEnd.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
    @ManyToOne
    @JoinColumn(name="workPeriod_id")
    private WorkPeriod workPeriod;
    @OneToMany(mappedBy = "workPeriodLine",cascade = CascadeType.ALL, orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    private Set<WorkDay> workDays = new HashSet<>();

    public void addWorkDay(WorkDay workDay){
        if(workDay!=null){
            if(workDays == null){
                workDays = new HashSet<>();
            }
            workDay.setWorkPeriodLine(this);
            workDays.add(workDay);

        }

    }
}
