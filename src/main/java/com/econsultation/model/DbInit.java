package com.econsultation.model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.econsultation.jpa.UserRepository;
import com.econsultation.jpa.UserRoleRespository;

@Service
public class DbInit implements CommandLineRunner {
	
    private UserRepository userRepository;
    private UserRoleRespository userRoleRepository;
    private PasswordEncoder passwordEncoder;
    
    

    public DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder, UserRoleRespository userRoleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void run(String... args) {
        // Delete all
        if(this.userRoleRepository.findAll().size() == 0)
        {
	        // Crete users
	        UserRole admin = new UserRole(1l,"Admin",new Date(),"ACCESS_1");
	        UserRole patient = new UserRole(2l, "Patient",new Date(),"ACCESS_1");
	        UserRole doctor = new UserRole(3l, "Doctor",new Date(),"ACCESS_1");
	
	        List<UserRole> userRoles = Arrays.asList(admin,patient,doctor);

	        // Save to db
	        this.userRoleRepository.saveAll(userRoles);
        }
        
        if(this.userRepository.findAll().size() == 0)
        {
	        // Crete users
	        User admin = new User("adminuser",  passwordEncoder.encode("password"), "Reheal", "Admin", "admin@reheal.com", "9998887777", userRoleRepository.findUserRoleByRoleId(1l), new Date());
	        User patient = new User("patientuser",  passwordEncoder.encode("password"), "Reheal", "Patient", "patient@reheal.com", "9998881111", userRoleRepository.findUserRoleByRoleId(2l), new Date());
	        User doctor = new User("doctoruser",  passwordEncoder.encode("password"), "Reheal", "Doctor", "doctor@reheal.com", "9998881111", userRoleRepository.findUserRoleByRoleId(3l), new Date());
	
	        List<User> users = Arrays.asList(admin,patient,doctor);

	        // Save to db
	        this.userRepository.saveAll(users);
        }
    }
    
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
