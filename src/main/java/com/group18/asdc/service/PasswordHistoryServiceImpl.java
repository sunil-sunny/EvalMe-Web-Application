package com.group18.asdc.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.dao.PasswordHistoryDao;
import com.group18.asdc.entities.PasswordHistory;
import com.group18.asdc.security.IPasswordEncryption;
import com.group18.asdc.util.IQueryVariableToArrayList;

public class PasswordHistoryServiceImpl implements PasswordHistoryService {

	private IQueryVariableToArrayList queryVariableToArrayList;
	private PasswordHistoryDao passwordHistoryDao;
	private final String BANNER_ID = "bannerid", PASSWORD = "password";

	public PasswordHistoryServiceImpl(IQueryVariableToArrayList queryVariableToArrayList) {
		passwordHistoryDao = SystemConfig.getSingletonInstance().getDaoAbstractFactory().getPasswordHistoryDao();
		this.queryVariableToArrayList = queryVariableToArrayList;
	}

	@Override
	public Object insertPassword(PasswordHistory passwordHistory, IPasswordEncryption passwordEncryption) {
		ArrayList valuesList = queryVariableToArrayList.convertQueryVariablesToArrayList(passwordHistory.getBannerID(),
				passwordEncryption.encryptPassword(passwordHistory.getPassword()), passwordHistory.getDate());
		return passwordHistoryDao.insertPasswordHistory(valuesList);
	}

	@Override
	public ArrayList<PasswordHistory> getPasswordHistory(String bannerId, Integer numberOfRecords) {
		ArrayList criteriaList = queryVariableToArrayList.convertQueryVariablesToArrayList(bannerId, numberOfRecords);
		ArrayList<PasswordHistory> resultList = new ArrayList<PasswordHistory>();
		for (HashMap<String, Object> eachRow : passwordHistoryDao.getPasswordHistory(criteriaList)) {
			PasswordHistory passwordHistory = SystemConfig.getSingletonInstance().getModelAbstractFactory()
					.getPasswordHistory();
			passwordHistory.setBannerID((String) eachRow.get(BANNER_ID));
			passwordHistory.setPassword((String) eachRow.get(PASSWORD));
			resultList.add(passwordHistory);
		}
		return resultList;
	}
}