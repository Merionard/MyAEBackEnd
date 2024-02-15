package mba.myAEBackEnd.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import mba.myAEBackEnd.enums.ParamEnum;

@Entity
@Getter
@Setter
@Accessors(chain = true)
public class Compteur {

    @Id
    @Enumerated(EnumType.STRING)
    private ParamEnum code;
    private int value;

    public void increment(){
        value ++;
    }

}
