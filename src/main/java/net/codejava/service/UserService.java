package net.codejava.service;

import lombok.RequiredArgsConstructor;
import net.codejava.repository.UserRepository;
import net.codejava.entity.enums.Provider;
import net.codejava.entity.User;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	
	public void processOAuthPostLogin(String email, String login, String oauth2ClientName) {

		Provider provider = Provider.valueOf(oauth2ClientName.toUpperCase());

		User existUser;

		if (Objects.nonNull(email))
			existUser = userRepository.getUserByUsername(email);
		else
			existUser = userRepository.getUserByUsername(login);



		if (Objects.isNull(existUser)) {
			User newUser = new User();
			newUser.setUsername(
				(Objects.nonNull(email)) ? email : login
			);
			newUser.setProvider(provider);
			newUser.setEnabled(true);			
			
			userRepository.save(newUser);
			
			System.out.println("Created new user: email - " + email + " // login - " + login);
		}
		
	}
	
}
