package com.example.testingdemo.controllers;

import com.example.testingdemo.entities.Model;
import com.example.testingdemo.services.ModelService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("models")
public class ModelController {

    private ModelService modelService;

    @Autowired
    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Model> getModelById(@PathVariable Long id) {
        Model model = modelService.getById(id);
        return ResponseEntity.ok(model);
    }

    @PostMapping
    public ResponseEntity createNewModel(@RequestBody Model m) throws URISyntaxException {
        Model persisted = modelService.saveNewModel(m);
        return ResponseEntity.created(new URI("http://localhost:8080/models/" + persisted.getId())).build();
    }
}
