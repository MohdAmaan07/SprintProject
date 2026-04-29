package com.sprint;

import com.sprint.Entities.Customer;
import com.sprint.Repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testFindByFirstNameAndLastName_returnsMatch() {
        List<Customer> customers = customerRepository.findByFirstNameAndLastName("MARY", "SMITH");

        assertFalse(customers.isEmpty());
        assertEquals("MARY", customers.get(0).getFirstName());
        assertEquals("SMITH", customers.get(0).getLastName());
    }

    @Test
    public void testFindByEmail_returnsMatch() {
        Optional<Customer> customer = customerRepository.findByEmail("MARY.SMITH@sakilacustomer.org");

        assertTrue(customer.isPresent());
        assertEquals("MARY.SMITH@sakilacustomer.org", customer.get().getEmail());
    }

    @Test
    public void testFindByActive_returnsActiveCustomers() {
        Page<Customer> page = customerRepository.findByActive(true, PageRequest.of(0, 5));

        assertFalse(page.isEmpty());
        assertTrue(page.getContent().get(0).getActive());
    }

    @Test
    public void testFindByAddressCityIgnoreCase_returnsMatchingCity() {
        Page<Customer> seed = customerRepository.findAll(PageRequest.of(0, 1));

        assertFalse(seed.isEmpty());

        String city = seed.getContent().get(0).getAddress().getCity().getCity();

        Page<Customer> page = customerRepository.findByAddress_City_CityIgnoreCase(
            city.toUpperCase(),
            PageRequest.of(0, 5)
        );

        assertFalse(page.isEmpty());
        assertTrue(page.getContent().stream()
            .allMatch(customer -> city.equalsIgnoreCase(customer.getAddress().getCity().getCity())));
    }

    @Test
    public void testFindByAddressCityIgnoreCase_noResults() {
        Page<Customer> page = customerRepository.findByAddress_City_CityIgnoreCase(
                "NoSuchCity",
                PageRequest.of(0, 5)
        );

        assertTrue(page.isEmpty());
    }
}
