package com.vaadin.starter.skeleton;

import java.awt.Event;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.xml.crypto.Data;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

/**
 * The main view contains a button and a click listener.
 * 
 */
@Route("")
@PWA(name = "Project Base for Vaadin Flow", shortName = "Project Base")
public class MainView extends VerticalLayout {

	ViewUser viewUser = new ViewUser();
	SubmitUser submitUser = new SubmitUser();

	String textFieldValueName;
	String textFieldValueProfession;

	public MainView() {

		ArrayList<User> userListArray = viewUser.viewUsersArrayList();

		// The grid for the userListArray
		Grid<User> grid = new Grid<>();
		grid.setItems(userListArray);
		grid.addColumn(user -> Integer.toString(user.getId())).setHeader("Id number");
		grid.addColumn(User::getName).setHeader("Name");
		grid.addColumn(User::getProfession).setHeader("Profession");

		// Textfields for user input
		TextField textFieldName = new TextField("Name field");
		TextField textFieldProfession = new TextField("Profession field");

		// Initial content in the boxes
		textFieldName.setValue("Enter name");
		textFieldProfession.setValue("Enter Profession");

		// Handle changes in the value
		textFieldName.addValueChangeListener(event -> {
			textFieldValueName = event.getValue();
			Notification.show("Name added");
			// submitUser.userSubmit(event.getValue());

		});

		// Handle changes in the value
		textFieldProfession.addValueChangeListener(event -> {
			textFieldValueProfession = event.getValue();
			Notification.show("Profession added");
			// submitUser.userSubmit(event.getValue());

		});

		Button button = new Button("The strings in the fields below",
				event -> Notification.show(textFieldValueName + textFieldValueProfession));
		Button sendUser = new Button("Send user", event -> {
			submitUser.userSubmit(textFieldValueName, textFieldValueProfession);
			Notification.show("User sent");
		});
		add(button, textFieldName, textFieldProfession, sendUser, grid);
	}

}
