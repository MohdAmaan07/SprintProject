package com.sprint.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;
    
    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;
    
    @Column(name = "first_name", length = 45)
    private String firstName;
    
    @Column(name = "last_name", length = 45)
    private String lastName;
    
    @Column(name = "email", length = 50, nullable = true)
    private String email;
    
    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
    
    @Column(name = "active")
    private Boolean active;
    
    @Column(name = "create_date")
    private LocalDate createDate;
    
    @Column(name = "last_update")
    private Timestamp lastUpdate;
}
