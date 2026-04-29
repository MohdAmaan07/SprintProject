package com.sprint.Projections;

import org.springframework.data.rest.core.config.Projection;
import com.sprint.Entities.Actor;

@Projection(name = "actorList", types = {Actor.class})
public interface ActorListProjection {

    String getFirstName();

    String getLastName();
}