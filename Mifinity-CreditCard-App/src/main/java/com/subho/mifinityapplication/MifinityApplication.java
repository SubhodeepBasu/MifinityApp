package com.subho.mifinityapplication;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.subho.mifinityapplication.model.Role;
import com.subho.mifinityapplication.model.User;
import com.subho.mifinityapplication.repository.UserRepository;
import com.subho.mifinityapplication.repository.UserRoleRepository;

/**
 * This class has the main method which will start the main method
 * 
 * @author subasu
 * @version 1.0
 */
@SpringBootApplication
public class MifinityApplication extends SpringBootServletInitializer {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserRoleRepository userRoleRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Value("${user.role}")
	private String userRole;

	@Value("${admin.role}")
	private String adminRole;

	public static void main(String[] args) {
		SpringApplication.run(MifinityApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder) {
		return applicationBuilder.sources(MifinityApplication.class);
	}

	@Bean
	InitializingBean sendDatabase() {
		return () -> {
			Role adminRole = new Role(this.adminRole);
			userRoleRepository.save(adminRole);
			userRoleRepository.save(new Role(userRole));
			userRepository.save(new User("admin", encoder.encode("password"), adminRole));
		};
	}
}
