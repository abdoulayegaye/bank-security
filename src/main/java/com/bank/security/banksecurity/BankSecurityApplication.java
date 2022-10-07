package com.bank.security.banksecurity;

import com.bank.security.banksecurity.domain.AppUser;
import com.bank.security.banksecurity.entity.Gender;
import com.bank.security.banksecurity.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = false)
public class BankSecurityApplication implements CommandLineRunner {

    private UserService userService;

    public BankSecurityApplication(UserService userService) {
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(BankSecurityApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        AppUser user = new AppUser();
        user.setLastName("Seck");
        user.setFirstName("Ngor");
        user.setEmail("nseck@seck.sn");
        user.setUsername("ndanane");
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String passwordcrip = bCryptPasswordEncoder.encode("passer22");
        user.setPassword(passwordcrip);
        user.setGender(Gender.M);
        user.setAddress("Dakar");
        user.setCity("Dakar");
        user.setCountry("Senegal");
       // userService.createUser(user);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
