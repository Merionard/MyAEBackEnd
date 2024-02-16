package mba.myAEBackEnd.service;

import lombok.AllArgsConstructor;
import mba.myAEBackEnd.dto.UserDto;
import mba.myAEBackEnd.dto.invoice.InvoiceDto;
import mba.myAEBackEnd.entity.Compteur;
import mba.myAEBackEnd.entity.Invoice;
import mba.myAEBackEnd.entity.User;
import mba.myAEBackEnd.enums.ConditionReglement;
import mba.myAEBackEnd.enums.InvoiceStatus;
import mba.myAEBackEnd.enums.InvoiceType;
import mba.myAEBackEnd.enums.ParamEnum;
import mba.myAEBackEnd.exception.BusinessException;
import mba.myAEBackEnd.mapper.InvoiceMapper;
import mba.myAEBackEnd.repository.InvoiceLineRepository;
import mba.myAEBackEnd.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
@AllArgsConstructor
public class InvoiceService {

    private InvoiceRepository invoiceRepository;
    private InvoiceMapper invoiceMapper;
    private InvoiceLineRepository invoiceLineRepository;
    private UserService userService;
    private CompteurService compteurService;

    public InvoiceDto createInvoice(InvoiceDto invoiceDto, User user) {

        Invoice newInvoice = invoiceMapper.toEntity(invoiceDto, user);
        newInvoice.setStatus(InvoiceStatus.DRAFT);
        newInvoice.setNumber(generateDraftNumber());
        invoiceRepository.save(newInvoice);
        return invoiceMapper.toDto(newInvoice);
    }

    public InvoiceDto getInvoiceById(Long id) throws BusinessException {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Invoice not found with Id " + id));
        return invoiceMapper.toDto(invoice);
    }

    public InvoiceDto updateInvoice(InvoiceDto invoiceDto, User user) {


        Invoice updatedInvoice = invoiceMapper.toEntity(invoiceDto, user);
        invoiceLineRepository.deleteAllById(invoiceDto.getDeletedLines());
        invoiceRepository.save(updatedInvoice);

        return invoiceMapper.toDto(updatedInvoice);
    }

    public void deleteInvoice(Long invoiceId) {
        invoiceRepository.deleteById(invoiceId);
    }

    public List<InvoiceDto> fetchAllInvoiceByUser(UserDto userDto) {
        User user = userService.findUserByEmail(userDto.getEmail());
        return invoiceRepository.getInvoicesByUser(user).stream()
                .map(invoice -> invoiceMapper.toDto(invoice))
                .toList();
    }

    public InvoiceDto validateInvoice(Long invoiceId) throws BusinessException {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new BusinessException("Invoice not found with Id " + invoiceId));
        if(invoice.getStatus() != InvoiceStatus.DRAFT){
            throw new BusinessException("Statut incorect");
        }
        invoice.setStatus(InvoiceStatus.VALIDATED)
                .setNumber(generateInvoiceOrCreditNumber(invoice.getType()))
                .setValidatedAt(ZonedDateTime.now())
                        .setDueDate(defineDueDate(invoice.getConditionReglement()));
        invoiceRepository.save(invoice);
        return invoiceMapper.toDto(invoice);
    }

    public InvoiceDto payInvoice(Long invoiceId,ZonedDateTime payedAt) throws BusinessException {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new BusinessException("Invoice not found with Id " + invoiceId));
        invoice.setStatus(InvoiceStatus.PAYED)
                .setPayedAt(payedAt);
        invoiceRepository.save(invoice);
        return invoiceMapper.toDto(invoice);
    }

    private String generateDraftNumber() {
        Compteur compteur = compteurService.getParameterByCode(ParamEnum.DRAFT_COUNT);
        String draftNumber = "D_" + padLeftZeros( String.valueOf(compteur.getValue()),6);
        compteurService.incrementAndSave(compteur);
        return draftNumber;
    }

    private String generateInvoiceOrCreditNumber(InvoiceType invoiceType) {
        Compteur compteur = compteurService.getParameterByCode(invoiceType == InvoiceType.INVOICE ? ParamEnum.INVOICE_COUNT : ParamEnum.CREDIT_COUNT);
        String countValue = padLeftZeros( String.valueOf(compteur.getValue()),6);
        String invoiceNumber =  invoiceType == InvoiceType.INVOICE ? "F_" + countValue : "A_" + countValue;
        compteurService.incrementAndSave(compteur);
        return invoiceNumber;
    }

    private ZonedDateTime defineDueDate(ConditionReglement conditionReglement){
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime dueDate = now;
        switch (conditionReglement){
            case TRENTE_JOUR_FIN_MOIS -> dueDate = now.plusDays(30).with(TemporalAdjusters.lastDayOfMonth());
            case QUARANTE_CINQ_JOURS -> dueDate = now.plusDays(45);
            case QUARANTE_CINQ_JOURS_FIN_MOIS -> dueDate = now.plusDays(45).with(TemporalAdjusters.lastDayOfMonth());
            case SOIXANTE_JOURS -> dueDate = now.plusDays(60);
            case SOIXANTE_JOURS_FIN_MOIS -> dueDate = now.plusDays(60).with(TemporalAdjusters.lastDayOfMonth());
            case QUATRE_VINGT_DIX_JOURS -> dueDate = now.plusDays(90);
        }

        return dueDate;
    }

    public String padLeftZeros(String inputString, int length) {
        if (inputString.length() >= length) {
            return inputString;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length - inputString.length()) {
            sb.append('0');
        }
        sb.append(inputString);

        return sb.toString();
    }
}
