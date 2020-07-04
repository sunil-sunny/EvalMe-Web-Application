package com.group18.asdc.service;

import java.util.ArrayList;
import com.group18.asdc.entities.PasswordHistory;
import com.group18.asdc.security.IPasswordEncryption;

public interface PasswordHistoryService {

	public Object insertPassword(PasswordHistory passwordHistory, IPasswordEncryption passwordEncryption);

	public ArrayList<PasswordHistory> getPasswordHistory(String bannerId, Integer numberOfRecords);
}