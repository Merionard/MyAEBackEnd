package mba.myAEBackEnd.mapper;

import mba.myAEBackEnd.dto.invoice.InvoiceDto;
import mba.myAEBackEnd.entity.Invoice;
import mba.myAEBackEnd.entity.User;
import mba.myAEBackEnd.enums.ConditionReglement;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {


    @Mapping(target = "user", source = "user")
    @Mapping(target = "conditionReglement",source = "dto.conditionReglement", qualifiedByName = "mapConditionReglement")
    Invoice toEntity(InvoiceDto dto, User user);

    @AfterMapping
    default Invoice  complete(@MappingTarget Invoice invoice){
        invoice.getLines().forEach(l->l.setInvoice(invoice));
        return invoice;
    }

    @Mapping(target = "user", source = "user.userId")
    @Mapping(target = "deletedLines", ignore = true)
    @Mapping(target = "conditionReglement",source = "conditionReglement.label")
    InvoiceDto toDto(Invoice invoice);

    @Named("mapConditionReglement")
    default ConditionReglement mapConditionReglement(String label){
        return ConditionReglement.findByLabel(label);
    }


}
