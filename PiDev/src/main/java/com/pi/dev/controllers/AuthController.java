package com.pi.dev.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import com.pi.dev.mail.SimpleMailController;
import com.pi.dev.models.ERole;
import com.pi.dev.models.Role;
import com.pi.dev.models.User;
import com.pi.dev.payload.request.LoginRequest;
import com.pi.dev.payload.request.SignupRequest;
import com.pi.dev.payload.response.JwtResponse;
import com.pi.dev.payload.response.MessageResponse;
import com.pi.dev.repository.RoleRepository;
import com.pi.dev.repository.UserRepository;
import com.pi.dev.security.jwt.JwtUtils;
import com.pi.dev.security.services.UserDetailsImpl;
import com.pi.dev.service.UserServiceimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private JavaMailSender sender;
	
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());
if (userDetails.getState()==true){
    return ResponseEntity.ok(new JwtResponse(jwt, 
                         userDetails.getId(), 
                         userDetails.getUsername(), 
                         userDetails.getEmail(), 
                         roles));
}
else
	{	
    return ResponseEntity.ok(new MessageResponse("activate your account !"));
	}
  }
 
  @RequestMapping("/user/activate/{id}")
  public  ResponseEntity<?> activate(@PathVariable Long id){
	    User user = new User();
		user= userRepository.findById(id).get();
	    user.setState(true);
	    userRepository.save(user);
	    return ResponseEntity.ok(new MessageResponse("User activated successfully!"));
  }

  
  
  @GetMapping("/test/message")
  public  void test(){
      System.out.println("this rout works!"); 
  }
  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
      
      
    }
    
    // Create new user's account
    User user = new User(signUpRequest.getUsername(), 
               signUpRequest.getEmail(),
               encoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case "admin":
          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);

          break;
        case "mod":
          Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(modRole);

          break;
        default:
          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    
   
    MimeMessage message = sender.createMimeMessage();
	MimeMessageHelper helper = new MimeMessageHelper(message);

	try {
		helper.setTo(user.getEmail());
		
		
	    userRepository.save(user);

		Long uid = user.getId();
        String suid = String.valueOf(uid);

        
		helper.setText("http://localhost:8080/api/auth/user/activate/"+suid);
		helper.setSubject("confirm your womcity account creation");
	} catch (MessagingException e) {
		e.printStackTrace();
		//return "Error while sending mail ..";
	}
	sender.send(message);    
    	
    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
}
