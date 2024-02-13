package mba.myAEBackEnd.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@NoArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
public class InvoiceLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private float unitPrice;
    private int quantity;
    private int vatRate;
    private Float totalHT;
    private Float totalTTC;
    private Float VatAmount;
    @ManyToOne
    private Invoice invoice;
};
;