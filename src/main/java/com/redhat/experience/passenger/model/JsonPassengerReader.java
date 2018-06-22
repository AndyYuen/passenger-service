package com.redhat.experience.passenger.model;

import org.json.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;


public class JsonPassengerReader {
	
	private String resourceName;
	
	public List<Passenger> readFromFile() throws IOException, JSONException {
	    File file = new File(resourceName);
	    return read(FileUtils.readFileToString(file, "utf-8"));
	}
	
	public List<Passenger> readFromResource() throws IOException, JSONException {
//		System.out.println("***********" + resourceName);
	    InputStream is = getClass().getClassLoader().getResourceAsStream(resourceName);
	    String content = IOUtils.toString(is, "utf-8");
//		System.out.println("Content:" + content);
	    return read(content);
	}
	    
	public List<Passenger> read(String content) throws IOException, JSONException {
	    List<Passenger> list = new ArrayList<Passenger>();
	    
		JSONArray array = new JSONArray(content);
		for (int i = 0; i < array.length(); i++) {  
		     JSONObject obj = array.getJSONObject(i);

		list.add( new Passenger(obj.getString("prefix"),
				obj.getString("firstName"),
				obj.getString("middleName"),
				obj.getString("surName"),
				obj.getLong("PCV")
				));
		}
		
		return list;
	}
	
	public JsonPassengerReader(String resourceName) {
		this.resourceName = resourceName;
	}



	public List<Passenger> createEventFromJsonResource() throws IOException, JSONException {
		return readFromResource();
	}


	public List<Passenger> createEventFromJsonFile() throws IOException, JSONException {
		return readFromFile();
	}
	
	
}
