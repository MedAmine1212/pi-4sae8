package com.pi.dev.controllers;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pi.dev.models.User;
import com.pi.dev.repository.UserRepository;
import com.pi.dev.service.UserNotFoundException;
import com.pi.dev.serviceInterface.IUserService;
import com.sun.mail.imap.Utility;

import io.swagger.annotations.Api;
import io.swagger.models.Model;
import net.bytebuddy.utility.RandomString;



@RestController
@Api(tags = "Forgot Password")
@RequestMapping("/password")
public class ForgotPasswordController {
	
	
	 @Autowired
	    private JavaMailSender mailSender;
	     
	    @Autowired
		IUserService userService;	

	    @Autowired
	    UserRepository userRepository;
	    
	    @PostMapping("/forgot_password")
	    public String processForgotPassword( Long userId ,  String password) throws UserNotFoundException {
	    		User user = new User();
	    		user = userRepository.getById(userId);
	    		String email = user.getEmail();
	    		
	    		String token = RandomString.make(30);
	    		userService.updateResetPasswordToken(token, email);
	    		MimeMessage message = mailSender.createMimeMessage();
	    		MimeMessageHelper helper = new MimeMessageHelper(message);

	    		try {
	    			helper.setTo(email);
	    			helper.setText( "http://localhost:8087/password/reset_password/"+token+"/"+password);
	    			helper.setSubject("Reset Password");
	    		} catch (MessagingException e) {
	    			e.printStackTrace();
	    			return "Error while sending mail ..";
	    		}
	    		mailSender.send(message);
	    		return "Mail Sent Success!";
	    }
	    
	    
	     @GetMapping("/reset_password/{token}/{password}")
	    public String processResetPassword( @PathVariable("token") String token, @PathVariable("password") String password) {
//	        String token = request.getParameter("token");
//	        String password = request.getParameter("password");
	         
	        User user = userService.getByResetPasswordToken(token);
	         
	        if (user == null) {
	            return "Invalid Token";
	        } else {           
	        	userService.updatePassword(user, password);
	            return "You have successfully changed your password.";
	        }
	         
	    }
	    
	    
}
	    



