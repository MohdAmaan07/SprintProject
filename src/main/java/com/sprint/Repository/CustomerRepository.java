package com.sprint.Repository;

import com.sprint.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(collectionResourceRel = "customers", path = "customers")
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Override
    @RestResource(exported = false)
    void deleteById(Long id);

    @Override
    @RestResource(exported = false)
    void delete(Customer entity);

    @Override
    @RestResource(exported = false)
    <S extends Customer> S save(S entity);

    
}
