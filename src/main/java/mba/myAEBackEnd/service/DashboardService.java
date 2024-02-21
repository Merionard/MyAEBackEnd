package mba.myAEBackEnd.service;

import lombok.AllArgsConstructor;
import mba.myAEBackEnd.dto.UserDto;
import mba.myAEBackEnd.entity.Invoice;
import mba.myAEBackEnd.entity.User;
import mba.myAEBackEnd.enums.InvoiceStatus;
import mba.myAEBackEnd.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class DashboardService {

    private InvoiceRepository invoiceRepository;
    private UserService userService;


    public double getCurrentYearCA(UserDto userDto) {
        User user = userService.findUserByEmail(userDto.getEmail());
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime startOfCurrentYear = now.withDayOfYear(1);
        ZonedDateTime endOfCurrentYear = now.withDayOfYear(now.toLocalDate().lengthOfYear());

        List<Invoice> invoices = invoiceRepository.getInvoicesByStatusIsAndPayedAtIsBetweenAndUser(
                InvoiceStatus.PAYED, startOfCurrentYear, endOfCurrentYear, user);

        double sum =  invoices.stream()
                .mapToDouble(Invoice::getTotalTTC)
                .sum();

        BigDecimal bd = new BigDecimal(sum).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();

    }

}
