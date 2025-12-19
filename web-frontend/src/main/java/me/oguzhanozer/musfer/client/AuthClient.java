package me.oguzhanozer.musfer.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class AuthClient {

    private final RestClient restClient;

    public AuthClient(@Value("${auth.service.url}") String baseUrl) {
        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public boolean login(String email, String password) {

        System.out.println("asd %s %s".formatted(email, password));
        return true;
    }

    public boolean signup(String email, String hash) {

        System.out.println("asd %s %s".formatted(email, hash));
        return true;
    }
}
