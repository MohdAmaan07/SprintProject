package com.sprint.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "address")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;
    
    @NotBlank(message = "Address is required")
    @Size(max = 50, message = "Address must not exceed 50 characters")
    @Column(name = "address", length = 50)
    private String address;
    
    @Size(max = 50, message = "Address2 must not exceed 50 characters")
    @Column(name = "address2", length = 50, nullable = true)
    private String address2;
    
    @NotBlank(message = "District is required")
    @Size(max = 20, message = "District must not exceed 20 characters")
    @Column(name = "district", length = 20)
    private String district;
    
    @NotNull(message = "City is required")
    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;
    
    @Size(max = 10, message = "Postal code must not exceed 10 characters")
    @Column(name = "postal_code", length = 10, nullable = true)
    private String postalCode;
    
    @Size(max = 20, message = "Phone must not exceed 20 characters")
    @Column(name = "phone", length = 20)
    private String phone;
    
    @Column(name = "last_update")
    private Timestamp lastUpdate;

    @OneToMany(mappedBy = "address")
    private List<Staff> staffList;

    @OneToMany(mappedBy = "address")
    private List<Customer> customers;
}
