package step.integrator.test.step_integrator_test.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.springframework.stereotype.Service;
import step.integrator.test.step_integrator_test.entity.user_model.User;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class HttpRestService {

    private final String GET_USERS_URL = "http://jsonplaceholder.typicode.com/users";
    private final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    private final ObjectMapper mapper = new ObjectMapper();

    public List<User> getUsers() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(GET_USERS_URL))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();
        CollectionType ListOfUsers = mapper.getTypeFactory().constructCollectionType(List.class, User.class);
        return mapper.readValue(body, ListOfUsers);
    }

}
