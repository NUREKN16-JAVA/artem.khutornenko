package com.artem.khutornenko.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.artem.khutornenko.User;
import com.artem.khutornenko.db.DaoFactory;
import com.artem.khutornenko.db.DatabaseException;

public class AddServlet extends EditServlet {

	@Override
	protected void processUser(User user) throws DatabaseException {
		DaoFactory.getInstance().getUserDao().create(user);
	}
	
	@Override
	protected void showPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/add.jsp").forward(req, resp);
	}

}
