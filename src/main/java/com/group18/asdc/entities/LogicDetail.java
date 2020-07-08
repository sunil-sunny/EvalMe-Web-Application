package com.group18.asdc.entities;

public enum LogicDetail {

	Group_Similar("1"),
	Group_Disimilar("2"),
	Less_Than("4"),
	Greater_Than("3");
	
	private final String logicDetail;

	private LogicDetail(String logicDetail) {
		this.logicDetail = logicDetail;
	}

	@Override
	public String toString() {
		return logicDetail;
	}
}
