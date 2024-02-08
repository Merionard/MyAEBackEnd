package mba.myAEBackEnd.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Accessors(chain = true)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String businessName;
    private String vatNumber;
    private String siren;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CustomerAddress> addresses = new HashSet<>();
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private CustomerContact contact;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



}
