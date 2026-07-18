package com.user.mgmt.security;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private AuthUtil authUtil;
	@Autowired
	private UserRespository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public LoginResponseDto login(LoginRequestDto loginRequestDto) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequestDto.getUserName(), loginRequestDto.getPassWord()));

		UserInfo user = (UserInfo) authentication.getPrincipal();
		String token = authUtil.generateJwtToken(user);
		return new LoginResponseDto(token, user.getId());

	}

	public SignupResponseDTO signup(LoginRequestDto signupRequestDto) {
		UserInfo user = (UserInfo) userRepository.findByUserName(signupRequestDto.getUserName()).orElse(null);

		if (user != null)
			throw new BadCredentialsException("User Already exist");
		UserInfo userCreated=userRepository.save(UserInfo.builder().userName(signupRequestDto.getUserName())
				.password(passwordEncoder.encode(signupRequestDto.getPassWord())).build());
		return new SignupResponseDTO(userCreated.getId(),userCreated.getUsername());
	}

}
