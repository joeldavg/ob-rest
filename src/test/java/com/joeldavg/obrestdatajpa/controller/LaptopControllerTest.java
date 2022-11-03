package com.joeldavg.obrestdatajpa.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joeldavg.obrestdatajpa.model.entity.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = this.restTemplateBuilder.rootUri("http://localhost:" + port + "/api/laptops");
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
        testRestTemplate.delete("/");
    }

    @Test
    void findAll() {
        ResponseEntity<Laptop[]> response =
                testRestTemplate.getForEntity("/", Laptop[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void findOneById_NotFound() {
        ResponseEntity<Laptop> response =
                testRestTemplate.getForEntity("/1", Laptop.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void create() throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        Laptop hp = Laptop.builder()
                .model("2000")
                .brand("HP")
                .build();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(hp);

        HttpEntity request = new HttpEntity(json, headers);

        ResponseEntity<Laptop> response =
                testRestTemplate.exchange("/", HttpMethod.POST, request, Laptop.class);

        Laptop result = response.getBody();
        hp.setId(result.getId());

        assertEquals(1l, result.getId());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(hp, result);
    }

    @Test
    void deleteById() {

        Laptop hp = Laptop.builder()
                .model("2000")
                .brand("HP")
                .build();

        ResponseEntity<Laptop> response = testRestTemplate.postForEntity("/", hp, Laptop.class);


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity request = new HttpEntity(headers);
        Long id = response.getBody().getId();
        ResponseEntity<Void> deleteResponse =
                testRestTemplate.exchange("/" + id, HttpMethod.DELETE, request, Void.class);

        assertEquals(HttpStatus.NO_CONTENT, deleteResponse.getStatusCode());

    }
}