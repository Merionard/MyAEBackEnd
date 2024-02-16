package mba.myAEBackEnd.dto.invoice;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
public class InvoiceDto {
    private Long id;
    private String number;

    private String type;
    private String status;
    private String customerSociety;
    private String customerSiren;
    private String customerVatNumber;
    private String customerAddress;
    private String customerCountry;
    private String customerMail;
    private String conditionReglement;
    private String modeReglement;
    private ZonedDateTime createdAt;
    private String customerName;
    private ZonedDateTime validatedAt;
    private ZonedDateTime payedAt;
    private ZonedDateTime dueDate;
    private Set<InvoiceLineDto> lines = new HashSet<>();
    private float totalHT;
    private float totalTTC;
    private Long user;
    private Set<Long> deletedLines = new HashSet<>();
}
