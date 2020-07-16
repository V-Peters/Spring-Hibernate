package spring_hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring_hibernate.entity.Meeting;
import spring_hibernate.entity.User;

@Repository
public class UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public boolean checkPassword(User user) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<String> query = currentSession.createQuery("select u.password from User u where u.username=:loginUser", String.class);
		query.setParameter("loginUser", user.getUsername());
		
		String password = query.getSingleResult();
		System.out.println(password.equals(user.getPassword()));
		
		return password.equals(user.getPassword());
	}

	public User getUser(User tempUser) {
		
		Session currentSession = sessionFactory.getCurrentSession();

//		Query<User> query = currentSession.createQuery("from User", User.class);
//		query.setParameter("tempUsername", tempUser.getUsername());
		
//		List<User> user = query.getResultList();
		

		Query<Integer> queryId = currentSession.createQuery("select u.id from User u where u.username=:tempUsername", Integer.class);
		Query<String> queryFirstname = currentSession.createQuery("select u.firstname from User u where u.username=:tempUsername", String.class);
		Query<String> queryLastname = currentSession.createQuery("select u.lastname from User u where u.username=:tempUsername", String.class);
		Query<String> queryIsAdmin = currentSession.createQuery("select u.isAdmin from User u where u.username=:tempUsername", String.class);
//		Query query = currentSession.createQuery("from User u where u.username=:tempUsername");
		queryId.setParameter("tempUsername", tempUser.getUsername());
		queryFirstname.setParameter("tempUsername", tempUser.getUsername());
		queryLastname.setParameter("tempUsername", tempUser.getUsername());
		queryIsAdmin.setParameter("tempUsername", tempUser.getUsername());
//		
//		String test = query.getSingleResult().toString();
//		System.out.println(test);
		
		int id = queryId.getSingleResult();
		String firstname = queryFirstname.getSingleResult();
		String lastname = queryLastname.getSingleResult();
		String isAdmin = queryIsAdmin.getSingleResult();
		
		User user = new User(id, firstname, lastname, isAdmin);
//		List<User> user = query.getResultList();
		
//		List parameter = query.getResultList();
		
//		System.out.println(parameter.get(0).toString());
		
//		User user = new User(parameter.get(0).toString(), parameter.get(1).toString(), parameter.get(2).toString());
		
//		System.out.println("getUser: " + user.getFirstname());
		
		return user;
	}

//	public User getUser(int userId) {
//
//		Session currentSession = sessionFactory.getCurrentSession();
//		
//		User user = currentSession.get(User.class, userId);
//
//		return user;
//	}
}
