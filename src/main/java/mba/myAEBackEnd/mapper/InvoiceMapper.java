package mba.myAEBackEnd.mapper;

import mba.myAEBackEnd.dto.invoice.InvoiceDto;
import mba.myAEBackEnd.entity.Invoice;
import mba.myAEBackEnd.entity.User;
import mba.myAEBackEnd.repository.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class InvoiceMapper {

    @Autowired
    private UserRepository userRepository;


    public abstract Invoice toEntity(InvoiceDto dto);

    @Mapping(target = "user", source = "user.userId")
    @Mapping(target = "linesToDelete",ignore = true)
    public abstract InvoiceDto toDto(Invoice invoice);

    public User mapUser(Long userId) throws Exception {
        return userRepository.findById(userId).orElseThrow(()->new Exception("User not found"));
    }
}
