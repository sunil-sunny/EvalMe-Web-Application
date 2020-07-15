package com.group18.asdc.entities;

import java.util.ArrayList;
import java.util.List;

public class Group {
	
	private int groupId;
	private String groupName;
	private List<User> groupMembers= new ArrayList<User>();

	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public List<User> getGroupMembers() {
		return groupMembers;
	}
	public void setGroupMembers(List<User> groupMembers) {
		this.groupMembers = groupMembers;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}
