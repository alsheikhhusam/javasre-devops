package com.example.testingdemo;

import com.example.testingdemo.controllers.ModelController;
import com.example.testingdemo.entities.Model;
import com.example.testingdemo.services.ModelService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ModelController.class)
public class ModelControllerTests {

    @MockBean
    private ModelService modelService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnModel() throws Exception {
        Long id = 1L;
        Model model = new Model(id, "test-value");

        when(modelService.getById(id)).thenReturn(model);

        mockMvc.perform(get("/models/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    public void shouldSaveModel() throws Exception {
        Model m = new Model(1, "test-value2");

        when(modelService.saveNewModel(any())).thenReturn(m);
        String location = "http://localhost:8080/models/" + m.getId();

        mockMvc.perform(
                post("/models")
                .contentType("application/json")
                .content("{\"value\": \"test-value2\"}"))
        .andExpect(status().isCreated())
        .andExpect(header().string("Location", containsString("models/"+m.getId())));
    }
}
