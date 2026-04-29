package com.sprint.Projections;

import org.springframework.data.rest.core.config.Projection;
import com.sprint.Entities.FilmActor;
import com.sprint.Entities.Actor;
import com.sprint.Entities.Film;

@Projection(name = "actorFilms", types = {FilmActor.class})
public interface ActorFilmProjection {

    Actor getActor();

    Film getFilm();
}