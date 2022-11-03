package com.joeldavg.obrestdatajpa.controller;

import com.joeldavg.obrestdatajpa.model.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {

    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = this.restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
        testRestTemplate.delete("/api/books");
    }

    @DisplayName("Testing hello() method from HelloController")
    @Test
    void hello() {
        ResponseEntity<String> response =
                testRestTemplate.getForEntity("/hello", String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Hola Mundo que tal vamos!! Hasta luego!", response.getBody());
    }

    @Test
    void findAll() {
        ResponseEntity<Book[]> response =
                testRestTemplate.getForEntity("/api/books", Book[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());

        List<Book> books = Arrays.asList(response.getBody());
        assertTrue(books.size() == 0);
    }

    @Test
    void findById() {
        ResponseEntity<Book> response =
                testRestTemplate.getForEntity("/api/books/1", Book.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        assertEquals(200, response.getStatusCodeValue());

    }

    @Test
    void create() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = "{\n" +
                "    \"title\": \"Libro Nuevo\",\n" +
                "    \"author\": \"Joel G.\",\n" +
                "    \"pages\": 650,\n" +
                "    \"price\": 5.99,\n" +
                "    \"releaseDate\": \"1999-01-31\",\n" +
                "    \"online\": false\n" +
                "}";

        HttpEntity<String> request = new HttpEntity<>(json, headers);

        ResponseEntity<Book> response =
                testRestTemplate.exchange("/api/books", HttpMethod.POST, request, Book.class);

        Book result = response.getBody();

        assertEquals(1l, result.getId());
        assertEquals("Libro Nuevo", result.getTitle());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}