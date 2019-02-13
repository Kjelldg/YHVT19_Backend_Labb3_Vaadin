package com.vaadin.starter.skeleton;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class SubmitUser {

	public void userSubmit(String name, String profession) {

		String newUrl = "http://localhost:8090/YHVT19_Backend_Labb2_G/rest/UserService/addusersURL/" + name + "/"
				+ profession;

		URL myURL;
		try {
			myURL = new URL(newUrl);

			URLConnection myURLConnection = myURL.openConnection();
			System.out.println(myURL.getPath());
			myURLConnection.getContent();
			myURLConnection.connect();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}