package com.sprint.Repository;

import com.sprint.Entities.Staff;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.sprint.Projections.StaffProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "staff", path = "staff", excerptProjection = StaffProjection.class)
public interface StaffRepository extends JpaRepository<Staff, Long> {
	

    Page<Staff> findByStore_StoreId(
            @Param("storeId") Long storeId,
            Pageable pageable
    );

    Page<Staff> findByFirstNameContainingIgnoreCaseAndStore_StoreId(
            @Param("name") String name,
            @Param("storeId") Long storeId,
            Pageable pageable
    );
}
