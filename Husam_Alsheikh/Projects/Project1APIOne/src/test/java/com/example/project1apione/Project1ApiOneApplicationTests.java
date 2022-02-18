package com.example.project1apione;

import com.example.project1apione.controllers.RequestController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class Project1ApiOneApplicationTests {
    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private RequestController requestController;

    @Test
    void contextLoads() {
    }

}
