package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
@ConfigurationProperties(prefix = "sfg.brewery")
@Setter
public class CustomerClient {

    private final String CUSTOMER_V1_PATH = "/api/v1/customer/";
    private String apihost;
    private RestTemplate restTemplate;

    public CustomerClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public CustomerDto getCustomerById(UUID customerId){
        return restTemplate.getForObject(this.apihost + CUSTOMER_V1_PATH + customerId.toString(), CustomerDto.class);
    }

    public URI savedNewCustomer(CustomerDto customerDto){
        return restTemplate.postForLocation(this.apihost + CUSTOMER_V1_PATH, customerDto);
    }

    public void updateCustomer(UUID customerId, CustomerDto customerDto){
        restTemplate.put(this.apihost + CUSTOMER_V1_PATH + customerId.toString(), customerDto);
    }

    public void deleteCustomer(UUID customerId){
        restTemplate.delete(this.apihost + CUSTOMER_V1_PATH + customerId.toString());
    }
}
