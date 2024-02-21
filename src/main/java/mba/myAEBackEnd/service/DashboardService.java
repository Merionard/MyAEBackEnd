package mba.myAEBackEnd.service;

import lombok.AllArgsConstructor;
import mba.myAEBackEnd.dto.DashboardInfoDto;
import mba.myAEBackEnd.dto.UserDto;
import mba.myAEBackEnd.dto.WorkPeriodInfoDto;
import mba.myAEBackEnd.dto.invoice.InvoiceDto;
import mba.myAEBackEnd.dto.todoList.TaskDto;
import mba.myAEBackEnd.entity.Invoice;
import mba.myAEBackEnd.entity.User;
import mba.myAEBackEnd.entity.WorkDay;
import mba.myAEBackEnd.entity.WorkPeriod;
import mba.myAEBackEnd.enums.InvoiceStatus;
import mba.myAEBackEnd.mapper.InvoiceMapper;
import mba.myAEBackEnd.mapper.ToDoListMapper;
import mba.myAEBackEnd.repository.CustomerRepository;
import mba.myAEBackEnd.repository.InvoiceRepository;
import mba.myAEBackEnd.repository.TodoListRepository;
import mba.myAEBackEnd.repository.WorkPeriodRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class DashboardService {

    private InvoiceRepository invoiceRepository;
    private UserService userService;
    private WorkPeriodRepository workPeriodRepository;
    private CustomerRepository customerRepository;
    private InvoiceMapper invoiceMapper;
    private TodoListRepository todoListRepository;
    private ToDoListMapper toDoListMapper;


    public DashboardInfoDto getDashboardInfos(UserDto userDto) {
        User user = userService.findUserByEmail(userDto.getEmail());
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime startOfCurrentYear = now.withDayOfYear(1);
        ZonedDateTime endOfCurrentYear = now.withDayOfYear(now.toLocalDate().lengthOfYear());

        List<Invoice> invoices = invoiceRepository.getInvoicesByStatusIsAndPayedAtIsBetweenAndUser(
                InvoiceStatus.PAYED, startOfCurrentYear, endOfCurrentYear, user);

        double sum = invoices.stream()
                .mapToDouble(Invoice::getTotalTTC)
                .sum();

        return new DashboardInfoDto()
                .setCurrentCA(new BigDecimal(sum).setScale(2, RoundingMode.HALF_UP).doubleValue())
                .setLateInvoices(getLateInvoices(user))
                .setWorkPeriodInfos(getCurrentMonthWorkPeriod(user))
                .setCriticalTaskDto(getCriticalTasks(user))
                .setPlafondActivite(user.getActivity().getPlafond());

    }

    private List<WorkPeriodInfoDto> getCurrentMonthWorkPeriod(User user) {
        ZonedDateTime now = ZonedDateTime.now();
        List<WorkPeriod> currentMonthWorkPeriods = workPeriodRepository.findAllByMonthAndYearAndUser(now.getMonthValue() - 1, now.getYear(), user);

        return currentMonthWorkPeriods.stream()
                .map(WorkPeriod::getLines)
                .flatMap(Collection::stream)
                .map(line -> {
                    double nbDaysWorked = line.getWorkDays().stream()
                            .mapToDouble(WorkDay::getDuration)
                            .sum();

                    String customer = customerRepository.findById(line.getCustomerId()).orElseThrow().getBusinessName();
                    return new WorkPeriodInfoDto(customer, nbDaysWorked);
                })
                .toList();
    }

    private List<InvoiceDto> getLateInvoices(User user) {
        return invoiceRepository.getInvoicesByDueDateIsBeforeAndUserAndStatusIsNot(ZonedDateTime.now(), user, InvoiceStatus.PAYED)
                .stream()
                .map(i -> invoiceMapper.toDto(i))
                .toList();
    }

    private List<TaskDto> getCriticalTasks(User user) {
        return todoListRepository.findCriticalTask(user).stream()
                .map(t -> toDoListMapper.toTaskDto(t))
                .toList();
    }

}
