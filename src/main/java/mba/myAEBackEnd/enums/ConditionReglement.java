package mba.myAEBackEnd.enums;

import java.util.Arrays;
import java.util.Objects;

public enum ConditionReglement {

    TRENTE_JOUR_FIN_MOIS("30 jours fin de mois"),

    QUARANTE_CINQ_JOURS("45 jours"),
    QUARANTE_CINQ_JOURS_FIN_MOIS("45 jours fin de mois"),
    SOIXANTE_JOURS("60 jours"),
    SOIXANTE_JOURS_FIN_MOIS("60 jours fin de mois"),
    QUATRE_VINGT_DIX_JOURS("90 jours");


    private final String label;

    ConditionReglement(String label) {
        this.label = label;
    }

    public static ConditionReglement findByLabel(String label){
        return Arrays.stream(ConditionReglement.values()).filter(c-> Objects.equals(c.label, label))
                .findFirst()
                .orElse(ConditionReglement.TRENTE_JOUR_FIN_MOIS);
    }

    public String getLabel(){
        return label;
    }
}
