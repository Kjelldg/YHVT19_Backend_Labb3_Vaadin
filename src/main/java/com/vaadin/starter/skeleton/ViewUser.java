package com.vaadin.starter.skeleton;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

// This class contains the code for viewing the users in the database.
public class ViewUser {

	/*
	 * This method returns an arraylist from the database which is used to populate
	 * a grid in the "Mainview" class. The data is retrieved from the database.
	 * 
	 */
	public ArrayList<User> viewUsersArrayList() {

		ArrayList<User> userList = new ArrayList<>();

		// Note the port 8090.
		String url = "http://localhost:8090/YHVT19_Backend_Labb2_G/rest/UserService/viewusers";
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(url);
			NodeList nodeList = document.getElementsByTagName("user");

			Element docEle = document.getDocumentElement();
			NodeList nList = docEle.getChildNodes();
			if (nList != null) {
				int length = nList.getLength();
				for (int i = 0; i < length; i++) {
					if (nList.item(i).getNodeType() == Node.ELEMENT_NODE) {
						Element el = (Element) nList.item(i);
						if (el.getNodeName().contains("user")) {
							int id = Integer.parseInt(el.getElementsByTagName("id").item(0).getTextContent());
							String name = el.getElementsByTagName("name").item(0).getTextContent();
							String profession = el.getElementsByTagName("profession").item(0).getTextContent();
							userList.add(new User(id, name, profession));
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return userList;
	}

	// This method is used for testing the grid in an offline mode.
	public ArrayList<User> viewUsersArrayListTtest() {

		ArrayList<User> userList = new ArrayList<>();

		userList.add(new User(1, "Kjell", "Systemutvecklare"));
		userList.add(new User(2, "Generic user", "Testare"));
		userList.add(new User(3, "John", "Resurs"));
		userList.add(new User(4, "Meck", "Mekaniker"));
		userList.add(new User(5, "Kexet", "Salsadansare"));
		userList.add(new User(6, "Jane", "Painter"));
		userList.add(new User(7, "Pablo", "Manager"));
		userList.add(new User(8, "Generic user", "Testare"));
		userList.add(new User(9, "Generic user", "Testare"));
		userList.add(new User(10, "Generic user", "Testare"));
		userList.add(new User(11, "Generic user", "Testare"));
		userList.add(new User(12, "Generic user", "Testare"));

		return userList;
	}

}