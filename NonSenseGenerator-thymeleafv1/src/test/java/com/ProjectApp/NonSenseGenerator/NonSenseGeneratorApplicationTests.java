package com.ProjectApp.NonSenseGenerator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NonSenseGeneratorApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoads() {
        // Test vuoto che verifica solo il caricamento del contesto Spring
    }

    @Test
    public void homeEndpointShouldReturnBasicPage() {
        String response = this.restTemplate.getForObject("/", String.class);
        assertThat(response).contains("Nonsense Generator");
    }
}