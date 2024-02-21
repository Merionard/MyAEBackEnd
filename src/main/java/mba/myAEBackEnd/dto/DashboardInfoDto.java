package mba.myAEBackEnd.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import mba.myAEBackEnd.dto.invoice.InvoiceDto;
import mba.myAEBackEnd.dto.todoList.TaskDto;

import java.util.List;

@Data
@Accessors(chain = true)
public class DashboardInfoDto {

    private List<WorkPeriodInfoDto> workPeriodInfos;
    private double currentCA;
    private List<InvoiceDto> lateInvoices;
    private List<TaskDto> criticalTaskDto;
    private double plafondActivite;
}
