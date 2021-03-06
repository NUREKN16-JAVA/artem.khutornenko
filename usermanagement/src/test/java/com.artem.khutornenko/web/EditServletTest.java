package com.artem.khutornenko.web;

import java.text.DateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.artem.khutornenko.User;

public class EditServletTest extends MockServletTestCase {

	@Override
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		createServlet(EditServlet.class);
	}

	@Test
	public void testEdit() {
		Date date = new Date();
		User user = new User(1000L, "Artem", "Khutornenko", date);
		getMockUserDao().expectAndReturn("update", user, user);
		addRequestParameter("id", "1000");
		addRequestParameter("firstName", "Artem");
		addRequestParameter("lastName", "Khutornenko");
		addRequestParameter("dateOfBirth", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton", "Ok");
		doPost();
	}

	@Test
	public void testEditEmptyFirstName() {
		Date date = new Date();
		addRequestParameter("id", "1000");
		addRequestParameter("lastName", "Khutornenko");
		addRequestParameter("dateOfBirth", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton", "Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}

	@Test
	public void testEditEmptyLastName() {
		Date date = new Date();
		addRequestParameter("id", "1000");
		addRequestParameter("firstName", "Artem");
		addRequestParameter("dateOfBirth", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton", "Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}

	@Test
	public void testEditEmptyDateOfBirth() {
		Date date = new Date();
		addRequestParameter("id", "1000");
		addRequestParameter("firstName", "Artem");
		addRequestParameter("lastName", "Khutornenko");
		addRequestParameter("okButton", "Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}

	@Test
	public void testEditEmptyDateIncorrect() {
		Date date = new Date();
		addRequestParameter("id", "1000");
		addRequestParameter("firstName", "Artem");
		addRequestParameter("lastName", "Khutornenko");
		addRequestParameter("dateOfBirth", "sda");
		addRequestParameter("okButton", "Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}
}
