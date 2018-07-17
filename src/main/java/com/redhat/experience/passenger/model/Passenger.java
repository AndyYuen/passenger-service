package com.redhat.experience.passenger.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Passenger{



	private String prefix;
	private String firstName;
	private String middleName;
	private String surName;
	private long PCV;

	public Passenger() {
		
	}
	public Passenger(String prefix, String firstName, String middleName, String surName, long pcv) {
		this. prefix = prefix;
		this.firstName = firstName;
		this.middleName = middleName;
		this.surName = surName;
		this.PCV = pcv;
	}
	
	public String getPrefix() {
		return prefix;
	}


	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	@JsonProperty("PCV")
	public long getPCV() {
		return PCV;
	}

	@JsonProperty("PCV")
	public void setPCV(long pCV) {
		PCV = pCV;
	}

	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("prefix: "); builder.append(prefix); builder.append('\n');
		builder.append("firstName: "); builder.append(firstName); builder.append('\n');
		builder.append("middleName: "); builder.append(middleName); builder.append('\n');
		builder.append("surName: "); builder.append(surName); builder.append('\n');
		builder.append("PCV: "); builder.append(PCV); builder.append('\n');
		return builder.toString();
	}
}
