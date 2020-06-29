package com.group18.asdc.dao;

import java.util.ArrayList;
import java.util.HashMap;

public interface PasswordHistoryDao {

	public Object insertPasswordHistory(ArrayList valuesList);

	public ArrayList<HashMap> getPasswordHistory(ArrayList criteriaList);

}