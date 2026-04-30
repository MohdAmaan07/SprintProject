package com.sprint.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "actor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Actor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id")
    private Long actorId;
    
    @NotBlank(message = "First name is required")
    @Size(max = 45, message = "First name must not exceed 45 characters")
    @JsonProperty("firstName")
    @Column(name = "first_name", length = 45)
    private String firstName;
    
    @NotBlank(message = "Last name is required")
    @Size(max = 45, message = "Last name must not exceed 45 characters")
    @JsonProperty("lastName")
    @Column(name = "last_name", length = 45)
    private String lastName;
    
    
    @Column(name = "last_update")
    private Timestamp lastUpdate;
    
    @JsonIgnore
    @OneToMany(mappedBy = "actor")
    private List<FilmActor> filmActors;
    
    @PrePersist
    @PreUpdate
    public void setLastUpdate() {
        this.lastUpdate = new Timestamp(System.currentTimeMillis());
    }
}
