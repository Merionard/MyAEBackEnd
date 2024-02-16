package mba.myAEBackEnd.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import mba.myAEBackEnd.enums.ConditionReglement;
import mba.myAEBackEnd.enums.InvoiceStatus;
import mba.myAEBackEnd.enums.InvoiceType;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    @Enumerated(EnumType.STRING)
    private InvoiceType type;
    @Enumerated(EnumType.STRING)
    private InvoiceStatus status;
    private String customerSociety;
    private String customerSiren;
    private String customerVatNumber;
    private String customerAddress;
    private String customerCountry;
    private String customerMail;
    @Enumerated(EnumType.STRING)
    private ConditionReglement conditionReglement;
    private String modeReglement;
    private ZonedDateTime createdAt;
    private String customerName;
    private ZonedDateTime validatedAt;
    private ZonedDateTime payedAt;
    private ZonedDateTime dueDate;
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<InvoiceLine> lines = new HashSet<>();
    private float totalHT;
    private float totalTTC;
    @ManyToOne
    private User user;
}
