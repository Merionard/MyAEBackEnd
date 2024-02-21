package mba.myAEBackEnd.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
public enum ActiviteEnum {
    COMMERCIALES(188700,"commerciales"),SERVICE(77700,"service");

    private double plafond;
    private String label;

     ActiviteEnum(double plafond,String label){
        this.plafond = plafond;
        this.label = label;
    }

    public static ActiviteEnum findByLabel(String label){
        return Arrays.stream(ActiviteEnum.values())
                .filter(a-> Objects.equals(a.label, label))
                .findFirst()
                .orElse(ActiviteEnum.COMMERCIALES);
    }

}
