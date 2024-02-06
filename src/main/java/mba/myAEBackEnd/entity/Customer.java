package mba.myAEBackEnd.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String businessName;
    private String vatNumber;
    private String siren;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CustomerAddress> addresses = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CustomerContact> contacts = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void addAddress(CustomerAddress address){
        addresses.add(address);
    }


}
