package spring_hibernate.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring_hibernate.dao.MeetingDAO;
import spring_hibernate.entity.Meeting;

@Service
public class MeetingService {

	@Autowired
	private MeetingDAO meetingDAO;
	
	@Transactional
	public List<Meeting> getMeetings() {
		return meetingDAO.getMeetings();
	}

	@Transactional
	public void saveMeeting(Meeting meeting) {
		meetingDAO.saveMeeting(meeting);
	}

	@Transactional
	public Meeting getMeeting(int id) {
		return meetingDAO.getMeeting(id);
	}

	@Transactional
	public void deleteMeeting(int id) {
		meetingDAO.deleteMeeting(id);
	}

	@Transactional
	public void changeDisplay(int id, boolean display) {
		meetingDAO.changeDisplay(id, display);
	}

}
