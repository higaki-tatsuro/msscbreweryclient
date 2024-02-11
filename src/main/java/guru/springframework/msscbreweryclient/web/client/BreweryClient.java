package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@ConfigurationProperties(prefix = "sfg.brewery", ignoreUnknownFields = false)
@Setter
@Component
public class BreweryClient {

    public final String BEER_PATH_V1 = "/api/v1/beer/";
    private String apihost;
    private RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public BeerDto getBeerById(UUID beerId){
        return restTemplate.getForObject(this.apihost + BEER_PATH_V1 + beerId.toString(), BeerDto.class);
    }

    public URI savedNewBeer(BeerDto beerDto){
        return restTemplate.postForLocation(this.apihost + BEER_PATH_V1,beerDto);
    }

    public void updateBeer(UUID beerId, BeerDto beerDto){
        restTemplate.put(this.apihost + BEER_PATH_V1 + beerId.toString(), beerDto);
    }

    public void deleteBeer(UUID beerId){
        restTemplate.delete(this.apihost + BEER_PATH_V1 + beerId.toString());
    }
}
