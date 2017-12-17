package pl.majorczyk.weatherapp.locator;

import org.springframework.stereotype.Component;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
public class IpLocator implements Locator {
    @Override
    public String locate(String ip) {
        Client client= ClientBuilder.newClient();
        Response response=client.target("https://api.ipdata.co/"+ip)
                .request(MediaType.TEXT_PLAIN_TYPE)
                .header("Accept","application/json")
                .get();

        return response.readEntity(String.class);
    }
}
