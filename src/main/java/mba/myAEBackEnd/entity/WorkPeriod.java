package mba.myAEBackEnd.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Collection;
import java.util.HashSet;

@Data
@Entity
@Accessors(chain = true)
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

    public void addLine(Long customerId){
        WorkPeriodLine line = new WorkPeriodLine()
                .setCustomerId(customerId);
        line.setWorkPeriod(this);
        this.lines.add(line);
    }

}
