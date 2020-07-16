package spring_hibernate.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring_hibernate.dao.MeetingUserDAO;
import spring_hibernate.entity.User;

@Service
public class MeetingUserService {

	@Autowired
	private MeetingUserDAO meetingUserDAO;
	
	@Transactional
	public List<User> getParticipants(int id) {
		return meetingUserDAO.getMeetings(id);
	}

	@Transactional
	public List<Integer> getMeetingsForUser(int id) {
		return meetingUserDAO.getMeetingsForUser(id);
	}

	@Transactional
	public void signUp(int userId, int meetingId) {
		meetingUserDAO.signUp(userId, meetingId);
	}

	@Transactional
	public void signOut(int userId, int meetingId) {
		meetingUserDAO.signOut(userId, meetingId);
	}
}
