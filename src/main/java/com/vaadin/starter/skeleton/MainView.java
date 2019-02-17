package com.vaadin.starter.skeleton;

import java.util.ArrayList;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

/**
 * The main view contains an interface for sending a user to a database via a
 * REST API, as well as retrieving users from the database and displaying them
 * in a grid.
 * 
 */
@Route("")
@PWA(name = "Project Base for Vaadin Flow", shortName = "Project Base")
public class MainView extends VerticalLayout {

	// The classes for viewing the users in the database and submitting a user.
	ViewUser viewUser = new ViewUser();
	SubmitUser submitUser = new SubmitUser();

	// Used for retrieving info from the fields in the web app.
	String textFieldValueName;
	String textFieldValueProfession;

	// Here is the code for the UI in the web app.
	public MainView() {

		ArrayList<User> userListArray = viewUser.viewUsersArrayList();

		Label appIntroduction = new Label(
				"Welcome to Kjell's Vaadin app. Either submit a user or view the users in the database in the list below.");

		// The grid for the userListArray
		Grid<User> grid = new Grid<>();
		grid.setItems(userListArray);
		grid.addColumn(user -> Integer.toString(user.getId())).setHeader("Id number");
		grid.addColumn(User::getName).setHeader("Name");
		grid.addColumn(User::getProfession).setHeader("Profession");

		// Text fields for user input
		TextField textFieldName = new TextField("Name field:");
		TextField textFieldProfession = new TextField("Profession field:");

		// Initial content in the boxes
		textFieldName.setValue("Enter name here.");
		textFieldProfession.setValue("Enter Profession here.");

		// Handles changes in the value
		textFieldName.addValueChangeListener(event -> {
			textFieldValueName = event.getValue();
			Notification.show("Name added!");

		});

		// Handles changes in the value
		textFieldProfession.addValueChangeListener(event -> {
			textFieldValueProfession = event.getValue();
			Notification.show("Profession added!");

		});

		// Button for sending the user.
		Button sendUser = new Button("Send user", event -> {
			submitUser.userSubmit(textFieldValueName, textFieldValueProfession);
			Notification.show("User sent!");
		});
		// Below are the components used in the web app which will be displayed on the
		// web page.
		add(appIntroduction, textFieldName, textFieldProfession, sendUser, grid);
	}

}
