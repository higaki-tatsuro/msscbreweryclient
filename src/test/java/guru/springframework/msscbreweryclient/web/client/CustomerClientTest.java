package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerClientTest {

    @Autowired
    CustomerClient customerClient;

    @Test
    void getCustomerById() {
        CustomerDto dto = customerClient.getCustomerById(UUID.randomUUID());

        assertNotNull(dto);
    }

    @Test
    void savedNewCustomer() {
        CustomerDto dto = CustomerDto.builder().name("New Customer").build();
        URI uri = customerClient.savedNewCustomer(dto);

        assertNotNull(uri);
    }

    @Test
    void updateCustomer() {
        CustomerDto dto = CustomerDto.builder().name("New Customer").build();
        customerClient.updateCustomer(UUID.randomUUID(), dto);
    }

    @Test
    void deleteCustomer() {
        customerClient.deleteCustomer(UUID.randomUUID());
    }
}