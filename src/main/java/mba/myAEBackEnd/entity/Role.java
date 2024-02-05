package mba.myAEBackEnd.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mba.myAEBackEnd.enums.RoleEnum;
import org.springframework.security.core.GrantedAuthority;

@NoArgsConstructor
@Data
@Entity
public class Role  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleEnum value;

    public String getLabel(){
        return value.getLabel();
    }


}
