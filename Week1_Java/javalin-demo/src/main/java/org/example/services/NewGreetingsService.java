package org.example.services;

import org.example.dao.Repository;

public class NewGreetingsService extends GreetingService{
    public NewGreetingsService(Repository<Integer, String> greetingRepository) {
        super(greetingRepository);
    }
}
