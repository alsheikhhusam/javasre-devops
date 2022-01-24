package org.example.services;

import org.example.dao.Repository;

public class GreetingService {
    Repository<Integer, String> greetingRepository;

    public GreetingService(Repository<Integer, String> greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    public int save(String greeting){
        return greetingRepository.save(greeting);
    }
}
