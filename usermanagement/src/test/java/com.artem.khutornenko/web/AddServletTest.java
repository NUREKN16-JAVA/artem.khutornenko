package com.artem.khutornenko.web;

import java.text.DateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.artem.khutornenko.User;

public class AddServletTest extends MockServletTestCase {

	@Override
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		createServlet(AddServlet.class);
	}

	@Test
	public void testAdd() {
		Date date = new Date();
		User newUser = new User("Artem", "Khutornenko", date);
		User user = new User(1000L, "Artem", "Khutornenko", date);
		getMockUserDao().expectAndReturn("create", user, user);
		addRequestParameter("id", "1000");
		addRequestParameter("firstName", "Artem");
		addRequestParameter("lastName", "Khutornenko");
		addRequestParameter("dateOfBirth", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton", "Ok");
		doPost();
	}

	@Test
	public void testAddEmptyFirstName() {
		Date date = new Date();
		addRequestParameter("lastName", "Khutornenko");
		addRequestParameter("dateOfBirth", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton", "Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}

	@Test
	public void testAddEmptyLastName() {
		Date date = new Date();
		addRequestParameter("firstName", "Artem");
		addRequestParameter("dateOfBirth", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton", "Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}

	@Test
	public void testAddEmptyDateOfBirth() {
		Date date = new Date();
		addRequestParameter("firstName", "Artem");
		addRequestParameter("lastName", "Khutornenko");
		addRequestParameter("okButton", "Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}

	@Test
	public void testAddEmptyDateIncorrect() {
		Date date = new Date();
		addRequestParameter("firstName", "Artem");
		addRequestParameter("lastName", "Khutornenko");
		addRequestParameter("dateOfBirth", "sda");
		addRequestParameter("okButton", "Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}
}
