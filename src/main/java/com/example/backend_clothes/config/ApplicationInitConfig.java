//package com.example.backend_clothes.config;
//
//
//import com.example.backend_clothes.entity.User;
//import com.example.backend_clothes.enums.Role;
//import com.example.backend_clothes.repository.UserRepository;
//import lombok.AccessLevel;
//import lombok.RequiredArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.HashSet;
//
//@Configuration
//@RequiredArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//@Slf4j
//public class ApplicationInitConfig {
//
//    PasswordEncoder passwordEncoder;
//
//    @Bean
//    ApplicationRunner applicationRunner(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder){
//        return args -> {
//            if (userRepository.findByUsername("admin").isEmpty()) {
//                Role adminRole = roleRepository.findByName("ADMIN")
//                        .orElseGet(() -> roleRepository.save(new Role(null, "ADMIN")));
//
//                Set<Role> roles = new HashSet<>();
//                roles.add(adminRole);
//
//                User user = User.builder()
//                        .username("admin")
//                        .email("admin@example.com")
//                        .password(passwordEncoder.encode("admin"))
//                        .roles(roles)
//                        .build();
//
//                userRepository.save(user);
//            }
//        };
//    }
//
//}