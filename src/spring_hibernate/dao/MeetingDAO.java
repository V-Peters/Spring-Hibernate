package spring_hibernate.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring_hibernate.entity.Meeting;

@Repository
public class MeetingDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public List<Meeting> getMeetings() {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Meeting> query = currentSession.createQuery("from Meeting", Meeting.class);
		
		List<Meeting> meetings = query.getResultList();
		
		return meetings;
	}

	public void saveMeeting(Meeting meeting) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(meeting);
		
		Query<?> query = currentSession.createQuery("update Meeting set last_updated=now() where id=:meetingId");
		query.setParameter("meetingId", meeting.getId());
		
		query.executeUpdate();
	}

	public Meeting getMeeting(int id) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Meeting meeting = currentSession.get(Meeting.class, id);

		return meeting;
	}

	public void deleteMeeting(int id) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<?> query = currentSession.createQuery("delete from Meeting where id=:meetingId");
		query.setParameter("meetingId", id);
		
		query.executeUpdate();
	}

	public void changeDisplay(int id, boolean display) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<?> query = currentSession.createQuery("update Meeting set last_updated=now(), display=:notDisplay where id=:meetingId");
		query.setParameter("meetingId", id);
		query.setParameter("notDisplay", !display);
		
		query.executeUpdate();
	}

}
