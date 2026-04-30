package com.sprint.config;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import com.sprint.Entities.Film;
import com.sprint.Repository.FilmRepository;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

@Component
@RepositoryEventHandler(Film.class)
public class FilmEventHandler {

    @Autowired
    private Validator validator;

    @Autowired
    private FilmRepository filmRepository;

    @HandleBeforeCreate
    public void validateBeforeCreate(Film film) {
        // For POST, validate all fields strictly
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    @HandleBeforeSave
    public void validateBeforeSave(Film film) {
        if (film.getFilmId() != null) {
            Film existing = filmRepository.findById(film.getFilmId()).orElse(null);
            if (existing != null) {
                // @NotBlank — must merge
                if (film.getTitle() == null)
                    film.setTitle(existing.getTitle());

                // @NotNull — must merge
                if (film.getLanguage() == null)
                    film.setLanguage(existing.getLanguage());
                if (film.getRentalDuration() == null)
                    film.setRentalDuration(existing.getRentalDuration());
                if (film.getRentalRate() == null)
                    film.setRentalRate(existing.getRentalRate());
                if (film.getReplacementCost() == null)
                    film.setReplacementCost(existing.getReplacementCost());

                // Optional fields — still merge to preserve existing values
                if (film.getDescription() == null)
                    film.setDescription(existing.getDescription());
                if (film.getRating() == null)
                    film.setRating(existing.getRating());
                if (film.getLength() == null)
                    film.setLength(existing.getLength());
                if (film.getReleaseYear() == null)
                    film.setReleaseYear(existing.getReleaseYear());
                if (film.getOriginalLanguage() == null)
                    film.setOriginalLanguage(existing.getOriginalLanguage());
                if (film.getSpecialFeatures() == null)
                    film.setSpecialFeatures(existing.getSpecialFeatures());
                if (film.getActors() == null || film.getActors().isEmpty())
                    film.setActors(existing.getActors());
                if (film.getCategories() == null || film.getCategories().isEmpty())
                    film.setCategories(existing.getCategories());
            }
        }

        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
