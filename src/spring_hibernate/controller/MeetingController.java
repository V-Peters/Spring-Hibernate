package spring_hibernate.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.openqa.selenium.html5.SessionStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring_hibernate.entity.Meeting;
import spring_hibernate.entity.User;
import spring_hibernate.service.MeetingService;
import spring_hibernate.service.MeetingUserService;

@Controller
@RequestMapping("/meeting")
public class MeetingController {
	
	@Autowired
	private MeetingService meetingService;
	
	@Autowired
	private MeetingUserService meetingUserService;
	
	private UserController userController;
	private spring_hibernate.session_storage.SessionStorage sessionStorage;
	
//	@GetMapping("/list")
//	public String callListMeetings(@ModelAttribute("user") User user, Model model) {
//		System.out.println("test:  " + user.toString());
//		model.addAttribute("user", user);
//		return "meeting/call-list-meetings";
//	}
	
	@GetMapping("/list")
	public String listMeetings(@ModelAttribute("user") User user, Model model, RedirectAttributes redirectAttribute) {
//		System.out.println("SessionStorage.getID: " + sessionStorage.getItemFromSessionStorage("id"));
		System.out.println("userId: " + user.getId());
		System.out.println("userIsAdmin: " + user.getIsAdmin());
		List<Meeting> meetings = new ArrayList<Meeting>();
		List<Integer> meetingsSignedUpTo = new ArrayList<Integer>();
		System.out.println("vor if abfrage");
		if ("0".equals(user.getIsAdmin())) {
			System.out.println("GetMeetingsForUser wird aufgerufen");
			meetingsSignedUpTo = meetingUserService.getMeetingsForUser(user.getId());
//			for (int i = 0; i < meetingsSignedUpTo.size(); i++) {
//				Meeting tempMeeting = meetingService.getMeeting(meetingsSignedUpTo.get(i));
//				System.out.println(tempMeeting.toString());
//			}
		}
		meetings = meetingService.getMeetings();
//		User user = userController.getUser(userId);

		model.addAttribute("meetings", meetings);
		model.addAttribute("meetingsSignedUpTo", meetingsSignedUpTo);
//		model.addAttribute("user", user);
		redirectAttribute.addFlashAttribute("user", user);
		System.out.println("Name in list: " + user.getUsername());
		return "meeting/list-meetings";
	}
	
	@GetMapping("/showAddPage")
	public String addMeeting(Model model) {
		model.addAttribute("meeting", new Meeting());
		
		return "meeting/save-meeting";
	}
	
	@GetMapping("/showUpdatePage")
	public String updateMeeting(@RequestParam("meetingId") int id, Model model) {
		
		Meeting meeting = meetingService.getMeeting(id);
		
		model.addAttribute("meeting", meeting);
		
		return "meeting/save-meeting";
	}
	
	@PostMapping("/save")
	public String saveMeeting(@ModelAttribute("meeting") Meeting meeting) {
		
		meetingService.saveMeeting(meeting);
		
		return "redirect:/meeting/list";
	}
	
	@GetMapping("/delete")
	public String deleteMeeting(@RequestParam("meetingId") int id) {
		
		meetingService.deleteMeeting(id);
		
		return "redirect:/meeting/list";
	}
	
	@GetMapping("/changeDisplay")
	public String changeDisplay(@ModelAttribute("user") User user, @RequestParam("meetingId") int id, @RequestParam("displayValue") boolean display, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		System.out.println("Name in changeDisplay: " + user.getUsername());
		meetingService.changeDisplay(id, display);
		
		redirectAttributes.addAllAttributes(request.getParameterMap());
//		model.addAttribute("user", user);
		
		return "redirect:/meeting/list";
	}
	
}
