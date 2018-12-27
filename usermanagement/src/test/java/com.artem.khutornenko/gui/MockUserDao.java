package com.artem.khutornenko.gui;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.artem.khutornenko.User;
import com.artem.khutornenko.db.ConnectionFactory;
import com.artem.khutornenko.db.DatabaseException;
import com.artem.khutornenko.db.UserDao;

public class MockUserDao implements UserDao {
	private long id = 0;
	private Map users = new HashMap();

	@Override
	public User create(User user) throws DatabaseException {
		Long currentId = new Long(++id);
		user.setId(currentId);
		users.put(currentId, user);
		return user;
	}

	@Override
	public User update(User user) throws DatabaseException {
		Long currentId = user.getId();
		users.remove(currentId);
		users.put(currentId, user);
		return user;
	}

	@Override
	public User delete(User user) throws DatabaseException {
		Long currentId = user.getId();
		users.remove(currentId);
		return user;
	}

	@Override
	public User find(Long id) throws DatabaseException {
		return (User) users.get(id);
	}

	@Override
	public Collection findAll() throws DatabaseException {
		return users.values();
	}

	@Override
	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection findAll(String firstName, String lastName) throws DatabaseException {
		throw new UnsupportedOperationException();
	}

}
