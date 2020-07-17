package spring_hibernate.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring_hibernate.dao.UserDAO;
import spring_hibernate.entity.User;

@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;

	@Transactional
	public boolean checkPassword(User user) {
		return userDAO.checkPassword(user);
	}

	@Transactional
	public User getUser(User tempUser) {
		return userDAO.getUser(tempUser);
	}

	@Transactional
	public User getUser(int userId) {
		return userDAO.getUser(userId);
	}
}
