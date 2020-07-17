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
	public String registerUser(@ModelAttribute("errorMessage") String errorMessage, @ModelAttribute("user") User user, Model model) {
		
		model.addAttribute("registerUser", new User());
		model.addAttribute("errorMessage", errorMessage);
		model.addAttribute("user", user);
		
		return "user/register-user";
	}
	
	@PostMapping("/registerUser")
	public String registerUser(@ModelAttribute("registerUser") User registerUser, RedirectAttributes redirectAttributes) {
		
		if (userService.getUser(registerUser).getId() != 0) {
			System.out.println("es gibt bereits einen Benutzer mit diesem Namen.");
			
			redirectAttributes.addFlashAttribute("errorMessage", "Der Benutzername \"" + registerUser.getUsername() + "\" ist bereits vergeben.");
			redirectAttributes.addFlashAttribute("user", registerUser);
		} else {
			userService.registerUser(registerUser);
			
			redirectAttributes.addFlashAttribute(registerUser);
			
			return "redirect:getUser";
		}
		return "redirect:showRegisterPage";
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
	public String getUser(@ModelAttribute("user") User tempUser, HttpServletRequest request) {
		
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
