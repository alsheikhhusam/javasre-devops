package com.example.testingdemo;

import com.example.testingdemo.entities.Model;
import com.example.testingdemo.repos.ModelRepository;
import com.example.testingdemo.services.ModelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class ModelServiceTests {
    private ModelRepository modelRepository;


    private ModelService modelService;

    @BeforeEach
    public void initBeforeTest() {
        modelRepository = mock(ModelRepository.class);
        modelService = new ModelService();
        modelService.setModelRepository(modelRepository);
    }

    @Test
    public void shouldReturnAllModels() {
        when(modelRepository.findAll()).thenReturn(Collections.emptyList());
        List<Model> models = modelService.getAllModels();
        assertTrue(models.isEmpty());
    }
}
