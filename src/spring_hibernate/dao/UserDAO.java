package spring_hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring_hibernate.entity.User;

@Repository
public class UserDAO {
	
	private static final String TEMPUSERNAME = "tempUsername";
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public boolean checkPassword(User user) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<String> query = currentSession.createQuery("select u.password from User u where u.username=:loginUser", String.class);
		query.setParameter("loginUser", user.getUsername());
		
		String password = query.getSingleResult();
		
		return password.equals(user.getPassword());
	}

	public User getUser(User tempUser) {
		
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Integer> queryId = currentSession.createQuery("select u.id from User u where u.username=:tempUsername", Integer.class);
		Query<String> queryFirstname = currentSession.createQuery("select u.firstname from User u where u.username=:tempUsername", String.class);
		Query<String> queryLastname = currentSession.createQuery("select u.lastname from User u where u.username=:tempUsername", String.class);
		Query<String> queryIsAdmin = currentSession.createQuery("select u.isAdmin from User u where u.username=:tempUsername", String.class);
		queryId.setParameter(TEMPUSERNAME, tempUser.getUsername());
		queryFirstname.setParameter(TEMPUSERNAME, tempUser.getUsername());
		queryLastname.setParameter(TEMPUSERNAME, tempUser.getUsername());
		queryIsAdmin.setParameter(TEMPUSERNAME, tempUser.getUsername());
		
		int id = queryId.getSingleResult();
		String firstname = queryFirstname.getSingleResult();
		String lastname = queryLastname.getSingleResult();
		String isAdmin = queryIsAdmin.getSingleResult();
		
		User user = new User(id, firstname, lastname, isAdmin);
		
		return user;
	}

	public User getUser(int userId) {

		Session currentSession = sessionFactory.getCurrentSession();
		
		User user = currentSession.get(User.class, userId);

		return user;
	}

	public void registerUser(User registerUser) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(registerUser);
		
	}

	public boolean isThereAUser(String username) {
		
		boolean isThereAUser = false;
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<User> query = currentSession.createQuery("from User where username=:username", User.class);
		query.setParameter("username", username);
		
		User user = null;
		
		try {
			user = query.getSingleResult();
		} catch (Exception e) {
			System.err.println("KEIN USER MIT DIESEM BENUTZERNAMEN GEFUNDEN");
		}
		
		if (user != null) {
			isThereAUser = true;
		}
		
		return isThereAUser;
	}
}
