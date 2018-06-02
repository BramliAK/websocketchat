package com.chat.chatwebsocket;

import com.chat.chatwebsocket.dao.RoleRepository;
import com.chat.chatwebsocket.dao.UserRepository;
import com.chat.chatwebsocket.model.AppRole;
import com.chat.chatwebsocket.model.AppUser;
import com.chat.chatwebsocket.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ChatwebsocketApplication implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;

	@Autowired
    private AccountService accountService;

	public static void main(String[] args) {
		SpringApplication.run(ChatwebsocketApplication.class, args);
	}


	@Bean
	public BCryptPasswordEncoder getpasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		return new JedisConnectionFactory();
	}

	@Bean
	public RedisTemplate<String, AppUser> redisTemplate() {
		RedisTemplate<String, AppUser> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		return redisTemplate;
	}


	@Override
	public void run(String... strings) throws Exception {

		/*
		accountService.saveUser(new AppUser("admin","admin@gmail.com","1234",null));
		accountService.saveUser(new AppUser("khalil","khalil@gmail.com","1234",null));
	    accountService.saveUser(new AppUser("user","user@gmail.com","1234",null));
	    accountService.addRoleToUser("admin","ROLE_ADMIN");
        accountService.addRoleToUser("admin","ROLE_USER");
        accountService.addRoleToUser("user","ROLE_USER");
        accountService.addRoleToUser("khalil","ROLE_USER");*/

	}
}
