package spring_hibernate.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@Autowired
	HttpServletRequest request;
	
	@GetMapping("/list")
	public String listMeetings(Model model, HttpServletRequest request) {
		
		User user = (User)request.getSession().getAttribute("user");
		
		List<Integer> meetingsSignedUpTo = new ArrayList<>();
		if ("0".equals(user.getIsAdmin())) {
			meetingsSignedUpTo = meetingUserService.getMeetingsForUser(user.getId());
		}
		List<Meeting> meetings = meetingService.getMeetings();

		model.addAttribute("meetings", meetings);
		model.addAttribute("meetingsSignedUpTo", meetingsSignedUpTo);
		model.addAttribute("user", user);
		
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
		meetingUserService.deleteMeeting(id);
		
		return "redirect:/meeting/list";
	}
	
	@GetMapping("/changeDisplay")
	public String changeDisplay( @RequestParam("meetingId") int id, @RequestParam("displayValue") boolean display) {
		meetingService.changeDisplay(id, display);
		
		return "redirect:/meeting/list";
	}
	
}
