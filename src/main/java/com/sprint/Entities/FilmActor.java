package com.sprint.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Entity
@Table(name = "film_actor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmActor {
    
    @EmbeddedId
    private FilmActorId id;
    
    @NotNull(message = "Film is required")
    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false)
    @MapsId("filmId")
    private Film film;
    
    @NotNull(message = "Actor is required")
    @ManyToOne
    @JoinColumn(name = "actor_id", nullable = false)
    @MapsId("actorId")
    private Actor actor;
    
    @Column(name = "last_update")
    private Timestamp lastUpdate;
}
