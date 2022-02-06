package com.example.mymedia;

import com.example.mymedia.dao.MmUserRepository;
import com.example.mymedia.models.MmProfile;
import com.example.mymedia.models.MmRole;
import com.example.mymedia.models.MmUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class MyMediaApplication {

	@Autowired
	MmUserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(MyMediaApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(){
		return args -> {
//			Optional<MmUser> user = userRepository.findById(2);
//
//			if(user.isPresent()){
//				System.out.println(user.get());
//			}
//			else{
//				System.out.println("User not found");
//			}

//			MmUser user = new MmUser();
//
//			user.setUsername("john.doe");
//			user.setHash("alsdkjfalskdjfalks");
//			user.setRole(MmRole.ROLE_MANAGER);
//
//			MmProfile profile = new MmProfile();
//			profile.setEmail("john.doe@my-media.com");
//
//			user.setProfile(profile);
//
//			userRepository.save(user);
		};
	}
}
