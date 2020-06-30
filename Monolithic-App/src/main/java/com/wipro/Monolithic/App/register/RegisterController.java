package com.wipro.Monolithic.App.register;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegisterController {

	Map<String, User> users = new HashMap<String, User>();

	public RegisterController() {
		User userModel = new User("sachin", "sachin", "sachin@wipro.com");
		users.put("sachin", userModel);
	}

	@PostMapping(value = "/users/register")
	@ResponseBody
	public String registerUser(@ModelAttribute("userid") String userid, @ModelAttribute("password") String password,
			@ModelAttribute("email") String email) {
		User user = new User(userid, password, email);
		users.put(userid, user);
		return "<html><body bgcolor='coral'>" + userid
				+ " Registered Successfully <a href='http://localhost:8080/index.html'>Home to Login</a>"
				+ "<body><html>";
	}

	@GetMapping(value = "users/all")
	@ResponseBody
	public Map<String, User> getAllRegisteredUser() {
		return users;
	}

	@GetMapping(value = "users/{userid}")
	@ResponseBody
	public User getUser(@PathVariable("userid") String userid) {
		return users.get(userid);
	}

	@PostMapping(value = "users/login")
	public String loginUser(@ModelAttribute("userid") String userid, @ModelAttribute("password") String password,
			HttpServletRequest request) {
		User uu = users.get(userid);
		request.getSession().setAttribute("user", uu);

		if (users.get(userid) != null) {
			if (users.get(userid).getPassword().equals(password)) {
				return "Trade";
			} else {
				return "PasswordError";
			}
		} else {
			return "Sorry";
		}

	}

}
