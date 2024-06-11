package com.flab.lemonticket.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl;

    @BeforeEach
    public void setUp() {
        baseUrl = "http://localhost:" + port + "/api/users";
    }

    @Test
    public void testRegister() throws Exception {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("user", new ClassPathResource("testdata/user.json"));
        body.add("file", new ClassPathResource("testimage/default_profile_img.png"));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.exchange(baseUrl + "/register", HttpMethod.POST, requestEntity, Map.class);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).containsKey("user");
        assertThat(((Map) response.getBody().get("user")).get("email")).isEqualTo("testuser@example.com");
    }

    @Test
    public void testLogin() throws Exception {
        Map<String, String> loginRequest = new HashMap<>();
        loginRequest.put("email", "testuser@example.com");
        loginRequest.put("password", "password");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(loginRequest, headers);

        ResponseEntity<Map> response = restTemplate.exchange(baseUrl + "/login", HttpMethod.POST, requestEntity, Map.class);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).containsKey("jwt");
        assertThat(response.getBody()).containsKey("userDetails");
    }

    @Test
    public void testLogout() throws Exception {
        // First, log in to get the token
        Map<String, String> loginRequest = new HashMap<>();
        loginRequest.put("email", "testuser@example.com");
        loginRequest.put("password", "password");

        HttpHeaders loginHeaders = new HttpHeaders();
        loginHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> loginRequestEntity = new HttpEntity<>(loginRequest, loginHeaders);
        ResponseEntity<Map> loginResponse = restTemplate.exchange(baseUrl + "/login", HttpMethod.POST, loginRequestEntity, Map.class);

        assertThat(loginResponse.getStatusCode().is2xxSuccessful()).isTrue();
        String jwtToken = (String) loginResponse.getBody().get("jwt");

        // Now, log out using the token
        HttpHeaders logoutHeaders = new HttpHeaders();
        logoutHeaders.set("Authorization", "Bearer " + jwtToken);

        HttpEntity<Void> logoutRequestEntity = new HttpEntity<>(logoutHeaders);
        ResponseEntity<Map> logoutResponse = restTemplate.exchange(baseUrl + "/logout", HttpMethod.POST, logoutRequestEntity, Map.class);

        assertThat(logoutResponse.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(logoutResponse.getBody()).containsKey("message");
        assertThat(logoutResponse.getBody().get("message")).isEqualTo("Successfully logged out");
    }

    @Test
    public void testTestNormal() throws Exception {
        // First, log in as normal user to get the token
        Map<String, String> loginRequest = new HashMap<>();
        loginRequest.put("email", "testuser@example.com");
        loginRequest.put("password", "password");

        HttpHeaders loginHeaders = new HttpHeaders();
        loginHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> loginRequestEntity = new HttpEntity<>(loginRequest, loginHeaders);
        ResponseEntity<Map> loginResponse = restTemplate.exchange(baseUrl + "/login", HttpMethod.POST, loginRequestEntity, Map.class);

        assertThat(loginResponse.getStatusCode().is2xxSuccessful()).isTrue();
        String jwtToken = (String) loginResponse.getBody().get("jwt");

        // Access the /test_normal endpoint using the token
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwtToken);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Map> response = restTemplate.exchange(baseUrl + "/test_normal", HttpMethod.GET, requestEntity, Map.class);

        System.out.println("requestEntity : " + requestEntity.toString() );
        System.out.println("response : " + response.getHeaders() );
        System.out.println("response : " + response.getBody() );

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).containsKey("message");
        assertThat(response.getBody().get("message")).isEqualTo("test world");
    }

    @Test
    public void testTestEventAdmin() throws Exception {
        // First, log in as event admin to get the token
        Map<String, String> loginRequest = new HashMap<>();
        loginRequest.put("email", "eventadmin@example.com");
        loginRequest.put("password", "password");

        HttpHeaders loginHeaders = new HttpHeaders();
        loginHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> loginRequestEntity = new HttpEntity<>(loginRequest, loginHeaders);
        ResponseEntity<Map> loginResponse = restTemplate.exchange(baseUrl + "/login", HttpMethod.POST, loginRequestEntity, Map.class);

        assertThat(loginResponse.getStatusCode().is2xxSuccessful()).isTrue();
        String jwtToken = (String) loginResponse.getBody().get("jwt");

        // Access the /test_event_admin endpoint using the token
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwtToken);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Map> response = restTemplate.exchange(baseUrl + "/test_event_admin", HttpMethod.GET, requestEntity, Map.class);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).containsKey("message");
        assertThat(response.getBody().get("message")).isEqualTo("test world");
    }
}