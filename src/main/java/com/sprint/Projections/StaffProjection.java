package com.sprint.Projections;

import org.springframework.data.rest.core.config.Projection;

import com.sprint.Entities.Staff;

@Projection(name = "staffProjection", types = Staff.class)
public interface StaffProjection {

    Long getStaffId();
    String getFirstName();
    String getLastName();
    String getEmail();
    Boolean getActive();
    String getUsername();

    AddressProjection getAddress();
    StoreProjection getStore();

    interface AddressProjection {
        Long getAddressId();
        CityProjection getCity();
        
        String getAddress();   // ADD THIS

        interface CityProjection {
            String getCity();
            
            interface CountryProjection {
                String getCountry();   // ADD THIS
            }

            CountryProjection getCountry();   // ADD THIS
        }
    }

    interface StoreProjection {
        Long getStoreId();
    }
    
}