package com.example.mymedia;

import com.example.mymedia.dao.MmPostRepository;
import com.example.mymedia.dao.MmUserRepository;
import com.example.mymedia.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class MyMediaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyMediaApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner runner() {
//		return args -> {
////			MmUser user = new MmUser();
////
////			user.setUsername("john.doe");
////			user.setHash("lkjdlajsuejkjdslkjldskja");
////			user.setRole(MmRole.ROLE_MANAGER);
////
////			MmProfile profile = new MmProfile();
////			profile.setEmail("john.doe@my-media.com");
////
////			user.setProfile(profile);
////
////			userRepository.save(user);
//
////			Optional<MmUser> user = userRepository.findMmUserByUsername("john.doe");
////
////			if(user.isPresent()) {
////				System.out.println(user.get());
////			} else {
////				System.out.println("User not found");
////			}
////
////			int managerCount = userRepository.countMmUserByRole(MmRole.ROLE_MANAGER);
////			System.out.println(managerCount);
//
////			Optional<MmUser> august = userRepository.findById(3);
////			Optional<MmUser> john = userRepository.findById(4);
////
////			if(august.isPresent() && john.isPresent()) {
////				System.out.println(String.format("%s is posting to %s", august.get().getUsername(), john.get().getUsername()));
////				Set<MmTag> tags = new LinkedHashSet<>();
////				MmTag tag1 = new MmTag();
////				tag1.setName("General");
////				tags.add(tag1);
////
////				MmPost post = new MmPost();
////				post.setPoster(august.get());
////				post.setReceiver(john.get());
////				post.setTags(tags);
////				post.setContent("Hello, John!");
////
////				postRepository.save(post);
//
////			}
////			Optional<MmPost> post = postRepository.findById(16);
////
////			if(post.isPresent()) {
////				System.out.println(post.get());
////			}
//
////			MmUser user = userRepository.getUserByProfileEmail("august.duet@revature.com");
////			System.out.println(user);
//
//			postRepository.likePost(16, 4);
//		};
//	}

}
