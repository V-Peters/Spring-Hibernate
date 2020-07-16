package spring_hibernate.controller;

import org.openqa.selenium.html5.SessionStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring_hibernate.entity.User;
import spring_hibernate.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	private SessionStorage sessionStorage;
//	private spring_hibernate.session_storage.SessionStorage sessionStorage;

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
			
//			sessionStorage.setItemInSessionStorage("wqertz", "hallo");
//			sessionStorage.setItem("qwertz", "Hallo");
			
			redirectAttribute.addFlashAttribute("user", loginUser);
			
			System.out.println("LoginUser: " + loginUser.getUsername());
			
//			this.getUser(loginUser);
			
			return "redirect:getUser";
//			return "redirect:../meeting/list";
		}
		return "user/login-user";
	}
	
//	public void getUser(User tempUser) {
//		
//	}
	
	@GetMapping("/getUser")
	public String getUser(@ModelAttribute("user") User tempUser, Model model, RedirectAttributes redirectAttribute) {
		
		System.out.println("tempUser: " + tempUser.getUsername());
		
		User user = userService.getUser(tempUser);
		
		System.out.println("userId: " + user.getId());
		
//		model.addAttribute("user", user);

		redirectAttribute.addFlashAttribute("user", user);
		redirectAttribute.addFlashAttribute("userId", user.getId());
//		sessionStorage.setItem("id", user.getId() + "");
		
		return "redirect:../meeting/list";
	}
	
	@PostMapping("/logout")
	public String logout() {
		return "user/login-user";
	}

//	public User getUser(int userId) {
//		return userService.getUser(userId);
//	}
	
}
