package spring_hibernate.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring_hibernate.entity.User;
import spring_hibernate.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/showLoginPage")
	public String loginUser(Model model) {
		
		model.addAttribute("loginUser", new User());
		
		return "user/login-user";
	}
	
	@GetMapping("/showRegisterPage")
	public String registerUser(Model model) {
		
		model.addAttribute("user", new User());
		
		return "user/register-user";
	}
	
	@PostMapping("/loginUser")
	public String loginUser(@ModelAttribute("loginUser") User loginUser, RedirectAttributes redirectAttribute) {
		
		if (userService.checkPassword(loginUser)) {
			
			redirectAttribute.addFlashAttribute("user", loginUser);
			
			return "redirect:getUser";
		}
		return "user/login-user";
	}
	
	@GetMapping("/getUser")
	public String getUser(@ModelAttribute("user") User tempUser, Model model, RedirectAttributes redirectAttribute, HttpServletRequest request) {
		
		User user = userService.getUser(tempUser);
		
		request.getSession().setAttribute("user", user);
		
		return "redirect:../meeting/list";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		
		request.getSession().removeAttribute("user");
		
		return "redirect:showLoginPage";
	}

	public User getUser(int userId) {
		return userService.getUser(userId);
	}
	
}
