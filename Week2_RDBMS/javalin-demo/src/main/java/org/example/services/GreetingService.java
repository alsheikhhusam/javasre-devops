package org.example.services;

import org.example.dao.Repository;
import org.example.dto.CreateGreetingDTO;
import org.example.dto.GreetingDTO;
import org.example.dto.ListGreetingDTO;
import org.example.dto.UserProfileDTO;
import org.example.models.Greeting;
import org.example.models.User;

import java.util.ArrayList;
import java.util.List;

public class GreetingService {
    Repository<Integer, Greeting> greetingRepository;


    public GreetingService(Repository<Integer, Greeting> greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

//    public int save(CreateGreetingDTO createGreeting) {
//        return greetingRepository.save(greeting);
//    }

    public int save(String greeting) {
        return 0;
    }

    public ListGreetingDTO getAllGreetings() {
        // don't return the pure entity form of your data from the database
        // convert it to some kind of DTO
        List<Greeting> greetings = greetingRepository.getAll();
        ListGreetingDTO listGreetingDTO = new ListGreetingDTO();
        listGreetingDTO.setGreetings(new ArrayList<>());

        for(Greeting greeting : greetings) {
            User user = greeting.getUser();
            GreetingDTO dto = new GreetingDTO(greeting.getId(), greeting.getGreeting(),
                    new UserProfileDTO(user.getId(), user.getUsername()));
            listGreetingDTO.getGreetings().add(dto);
        }
        return listGreetingDTO;

    }
}
