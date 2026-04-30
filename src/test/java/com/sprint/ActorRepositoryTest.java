package com.sprint;

import com.sprint.Entities.Actor;
import com.sprint.Repository.ActorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ActorRepositorySearchTest {

    @Autowired
    private ActorRepository actorRepository;

    // Test 1
    @Test
    public void test01_validName() {
        Pageable pageable = PageRequest.of(0, 5);
        Page<Actor> result = actorRepository
            .findByFirstNameContainingIgnoreCase("PENELOPE", pageable);
        assertFalse(result.isEmpty());
    }

    // Test 2
    @Test
    public void test02_partialName() {
        Pageable pageable = PageRequest.of(0, 5);
        Page<Actor> result = actorRepository
            .findByFirstNameContainingIgnoreCase("PEN", pageable);
        assertFalse(result.isEmpty());
    }

    // Test 3
    @Test
    public void test03_noMatch() {
        Pageable pageable = PageRequest.of(0, 5);
        Page<Actor> result = actorRepository
            .findByFirstNameContainingIgnoreCase("ZZZZZ", pageable);
        assertTrue(result.isEmpty());
    }

    // Test 4
    @Test
    public void test04_caseInsensitive() {
        Pageable pageable = PageRequest.of(0, 5);
        Page<Actor> result = actorRepository
            .findByFirstNameContainingIgnoreCase("penelope", pageable);
        assertFalse(result.isEmpty());
    }

}
