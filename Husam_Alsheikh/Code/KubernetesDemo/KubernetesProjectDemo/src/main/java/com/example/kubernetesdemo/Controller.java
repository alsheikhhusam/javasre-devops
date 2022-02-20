package com.example.kubernetesdemo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("k8s/demo")
public class Controller {
    @GetMapping
    public ResponseEntity<?> print(){
        System.out.println("Hello World");

        return ResponseEntity.ok("Look at me hosting a web app on a k8s cluster. YAY!");
    }
}
