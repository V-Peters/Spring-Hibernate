package spring_hibernate.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring_hibernate.entity.MeetingUser;
import spring_hibernate.entity.User;

@Repository
public class MeetingUserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public List<User> getMeetings(int id) {
		
		Session currentSession = sessionFactory.getCurrentSession();

		Query<User> query = currentSession.createQuery("select u from User u join MeetingUser mu on u.id = mu.idUser where mu.idMeeting=:meetingId", User.class);
		query.setParameter("meetingId", id);
		
		List<User> participants = query.getResultList();
		
		return participants;
	}

	public List<Integer> getMeetingsForUser(int id) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Integer> query = currentSession.createQuery("select idMeeting from MeetingUser where idUser=:idUser order by idMeeting asc", Integer.class);
		query.setParameter("idUser", id);
		
		List<Integer> meetingsForUser = query.getResultList();
		
		return meetingsForUser;
	}

	public void signUp(int userId, int meetingId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		MeetingUser meetingUser = new MeetingUser(userId, meetingId);
		
		currentSession.saveOrUpdate(meetingUser);
		
	}

	public void signOut(int userId, int meetingId) {

		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<?> query = currentSession.createQuery("delete from MeetingUser where idUser=:userId and idMeeting=:meetingId");
		query.setParameter("userId", userId);
		query.setParameter("meetingId", meetingId);
		
		query.executeUpdate();
	}
}
