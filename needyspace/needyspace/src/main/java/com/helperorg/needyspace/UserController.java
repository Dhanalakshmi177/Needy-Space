package com.helperorg.needyspace;

import java.text.DecimalFormat;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.helperorg.needyspace.domain.User;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private JavaMailSender javaMailSender;

	@RequestMapping(method = RequestMethod.GET,value = "/login")
	public ModelAndView login(@RequestParam String username,@RequestParam String password) {
		ModelAndView modelAndView = new ModelAndView();
		if( username != null && password != null && 
				Database.credentialsMap.containsKey(username)
				&& Database.credentialsMap.get(username).getPassword().equals(password)) {
			modelAndView.setViewName("choice");
			modelAndView.addObject("firstName", Database.credentialsMap.get(username).getFirstName());
			return modelAndView;
		}
		modelAndView.addObject("message","Invalid Credentials !");
		modelAndView.addObject("isMsgAvailable",true);
		modelAndView.setViewName("index");
		return modelAndView;
	}


	@RequestMapping(method = RequestMethod.POST,value = "/register")
	public ModelAndView register(String mailid,String password,String firstName,String lastName) {
		User user = new User();
		user.setMailid(mailid);
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		ModelAndView modelAndView = new ModelAndView();
		if(user.getMailid() != null) {
			String userName = user.getMailid();
			if(Database.credentialsMap.containsKey(userName)) {
				modelAndView.setViewName("index");
				modelAndView.addObject("message","Mail ID already exists, Login to continue");
				modelAndView.addObject("isMsgAvailable",true);
				return modelAndView;
			}
			String otp= new DecimalFormat("000000").format(new Random().nextInt(999999));
			sendEmail(userName, otp);
			user.setOtp(otp);
			Database.credentialsMap.put(userName, user);
			modelAndView.setViewName("otp");
			modelAndView.addObject("userName",firstName);
			modelAndView.addObject("mailId",mailid);
			return modelAndView;
		}
		modelAndView.setViewName("register");
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET,value="/home")
	public ModelAndView home () {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;
	}

	void sendEmail(String toMail,String otp) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(toMail);
		msg.setSubject("Needy Space App - OTP");
		msg.setText("Your secure OTP to complete registration in NeedySpace app is "+otp);
		javaMailSender.send(msg);
	}
	@RequestMapping(method = RequestMethod.GET,value = "/verifyOtp")
	public ModelAndView verifyOtp(String mailId,String otp) {
		
		ModelAndView modelAndView = new ModelAndView();
		if(Database.credentialsMap.get(mailId) != null && Database.credentialsMap.get(mailId).getOtp().equals(otp)) {
			modelAndView.setViewName("index");
			modelAndView.addObject("message","Registered Successfully, Login to continue");
			modelAndView.addObject("isMsgAvailable",true);
			return modelAndView;
		}
		modelAndView.setViewName("otp");
		modelAndView.addObject("mailId",mailId);
		modelAndView.addObject("message","OTP wrong, Try again...");
		modelAndView.addObject("isMsgAvailable",true);
		return modelAndView;
	}
			
}
