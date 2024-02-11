package mba.myAEBackEnd.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Data
@Entity
@Accessors(chain = true)
public class WorkDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy",timezone = "Europe/Paris")
    private ZonedDateTime date;
    private float duration;
    @ManyToOne
    @JoinColumn(name = "line_id")
    @EqualsAndHashCode.Exclude
    private WorkPeriodLine workPeriodLine;


}
