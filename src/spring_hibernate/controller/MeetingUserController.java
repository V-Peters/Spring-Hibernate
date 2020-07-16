package spring_hibernate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring_hibernate.entity.Meeting;
import spring_hibernate.entity.User;
import spring_hibernate.service.MeetingService;
import spring_hibernate.service.MeetingUserService;

@Controller
@RequestMapping("/meetingUser")
public class MeetingUserController {
	
	@Autowired
	private MeetingUserService meetingUserService;
	
	@Autowired
	private MeetingService meetingService;

	@GetMapping("/listParticipants")
	public String listMeetings(@RequestParam("meetingId") int id, Model model) {
		
		Meeting meeting = meetingService.getMeeting(id);
		List<User> participants = meetingUserService.getParticipants(id);

		model.addAttribute("meeting", meeting);
		model.addAttribute("participants", participants);
		
		return "meeting-user/list-participants";
	}
	
	@GetMapping("/signUp")
	public String signUp(@RequestParam("userId") int userId, @RequestParam("meetingId") int meetingId) {
		
		meetingUserService.signUp(userId, meetingId);
		
		return "redirect:../meeting/list";
	}
	
	@GetMapping("/signOut")
	public String signOut(@RequestParam("userId") int userId, @RequestParam("meetingId") int meetingId) {
		
		meetingUserService.signOut(userId, meetingId);
		
		return "redirect:../meeting/list";
	}
	
	
}
