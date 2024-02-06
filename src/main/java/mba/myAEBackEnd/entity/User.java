package mba.myAEBackEnd.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import mba.myAEBackEnd.dto.CustomerDto;
import mba.myAEBackEnd.enums.RoleEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "app_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "first_name", nullable = false)
    @Size(max = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @Size(max = 100)
    private String lastName;

    @Column(nullable = false)
    @Size(max = 100)
    private String password;

    @Column(nullable = false)
    @Size(max = 100)
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    private Role role;

    @OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Customer> customers = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.getLabel()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void addCustomer (Customer customer){
        customers.add(customer);
    }

}
