package com.ditheringllama.demo.ui.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GroupUI {

	@JsonProperty("id")
	private String id;

	@JsonProperty("groupName")
	private String groupName;

	@JsonProperty("groupDescription")
	private String groupDescription;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupDescription() {
		return groupDescription;
	}

	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}
}
