package com.sprint.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "staff")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Staff {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private Long staffId;
    
    @NotBlank(message = "First name is required")
    @Size(max = 45, message = "First name must not exceed 45 characters")
    @Column(name = "first_name", length = 45)
    private String firstName;
    
    @NotBlank(message = "Last name is required")
    @Size(max = 45, message = "Last name must not exceed 45 characters")
    @Column(name = "last_name", length = 45)
    private String lastName;
    
    @NotNull(message = "Address is required")
    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
    
    @Email(message = "Email must be a valid email address")
    @Size(max = 50, message = "Email must not exceed 50 characters")
    @Column(name = "email", length = 50, nullable = true)
    private String email;
    
    @NotNull(message = "Store is required")
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;
    
    @NotNull(message = "Active status is required")
    @Column(name = "active")
    private Boolean active;
    
    @NotBlank(message = "Username is required")
    @Size(max = 16, message = "Username must not exceed 16 characters")
    @Column(name = "username", length = 16)
    private String username;
    
    @Size(max = 40, message = "Password must not exceed 40 characters")
    @Column(name = "password", length = 40, nullable = true)
    private String password;
    
    @Column(name = "last_update")
    private Timestamp lastUpdate;

    @JsonIgnore
    @OneToMany(mappedBy = "staff")
    private List<Rental> rentals;

    @JsonIgnore
    @OneToMany(mappedBy = "staff")
    private List<Payment> payments;
}
