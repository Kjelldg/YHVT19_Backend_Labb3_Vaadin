package com.vaadin.starter.skeleton;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ViewUser {

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

}