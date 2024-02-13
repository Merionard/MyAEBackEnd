package mba.myAEBackEnd.dto.invoice;

import lombok.Data;

@Data
public class InvoiceLineDto {
    private Long id;
    private String type;
    private float unitPrice;
    private int quantity;
    private int vatRate;
    private Float totalHT;
    private Float totalTTC;
    private Float VatAmount;
}
