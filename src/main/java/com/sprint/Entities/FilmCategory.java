package com.sprint.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Entity
@Table(name = "film_category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmCategory {
    
    @EmbeddedId
    private FilmCategoryId id;
    
    @NotNull(message = "Film is required")
    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false)
    @MapsId("filmId")
    private Film film;
    
    @NotNull(message = "Category is required")
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @MapsId("categoryId")
    private Category category;
    
    @Column(name = "last_update")
    private Timestamp lastUpdate;
}
