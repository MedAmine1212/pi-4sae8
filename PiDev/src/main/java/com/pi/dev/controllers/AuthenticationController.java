package com.pi.dev.controllers;

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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@Api(tags = "authentication")
@RequestMapping("/authentication")
@Slf4j
public class AuthenticationController {
//    @Autowired
//    private JavaMailSender sender;
//
//    @Autowired
//    AuthenticationManager authenticationManager;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    RoleRepository roleRepository;
//
//    @Autowired
//    PasswordEncoder encoder;
//
//    @Autowired
//    JwtUtils jwtUtils;
//
//    @PostMapping("/userSignIn")
//    @ApiOperation(value = "Sign in auth")
//    @ResponseBody
//    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = jwtUtils.generateJwtToken(authentication);
//
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//        List<String> roles = userDetails.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.toList());
//        if (userDetails.getState()){
//            return ResponseEntity.ok(new JwtResponse(jwt,
//                    userDetails.getId(),
//                    userDetails.getUsername(),
//                    userDetails.getEmail(),
//                    roles));
//        }
//        else
//        {
//            return ResponseEntity.ok(new MessageResponse("activate your account !"));
//        }
//    }
//
//    @GetMapping("/activateUser/{id}")
//    @ApiOperation(value = "Activate user auth")
//    @ResponseBody
//    public  ResponseEntity<?> activateUser(@PathVariable Long id){
//
//        User user= userRepository.findById(id).get();
//        user.setState(true);
//        userRepository.save(user);
//        return ResponseEntity.ok(new MessageResponse("User activated successfully!"));
//    }
//
//    @PostMapping("/userSignUp")
//    @ApiOperation(value = "Sign up auth")
//    @ResponseBody
//    public ResponseEntity<?> registerUserAuth(@Valid @RequestBody SignupRequest signUpRequest) {
//        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
//            return ResponseEntity
//                    .badRequest()
//                    .body(new MessageResponse("Error: Username is already taken!"));
//        }
//
//        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
//            return ResponseEntity
//                    .badRequest()
//                    .body(new MessageResponse("Error: Email is already in use!"));
//
//
//        }
//
//        // Create new user's account
//        User user = new User(signUpRequest.getUsername(),
//                signUpRequest.getEmail(),
//                encoder.encode(signUpRequest.getPassword()));
//
//        Set<String> strRoles = signUpRequest.getRole();
//        Set<Role> roles = new HashSet<>();
//
//        if (strRoles == null) {
//            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//            roles.add(userRole);
//        } else {
//            strRoles.forEach(role -> {
//                switch (role) {
//                    case "admin":
//                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(adminRole);
//
//                        break;
//                    case "mod":
//                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(modRole);
//
//                        break;
//                    default:
//                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(userRole);
//                }
//            });
//        }
//
//        user.setRoles(roles);
//
//
//        MimeMessage message = sender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message);
//
//        try {
//            helper.setTo(user.getEmail());
//
//
//            user = userRepository.save(user);
//            Long uid = user.getId();
//            String suid = String.valueOf(uid);
//            helper.setText("http://localhost:8085/authentication/activateUser/"+suid);
//            helper.setSubject("confirm your womcity account creation");
//        } catch (MessagingException e) {
//            e.printStackTrace();
//            //return "Error while sending mail ..";
//        }
//        sender.send(message);
//
//        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
//    }
}
