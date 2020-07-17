package com.group18.asdc;

import java.util.List;

import com.group18.asdc.dao.DaoAbstractFactory;
import com.group18.asdc.dao.DaoAbstractFactoryImpl;
import com.group18.asdc.dao.IPasswordPolicyDB;
import com.group18.asdc.database.DataBaseAbstractFactory;
import com.group18.asdc.database.DataBaseAbstractFactoryImpl;
import com.group18.asdc.entities.ISurveyList;
import com.group18.asdc.entities.ISurveyMetaData;
import com.group18.asdc.entities.ModelAbstractFactory;
import com.group18.asdc.entities.ModelAbstractFactoryImpl;
import com.group18.asdc.groupformation.BaseGroupFormationBuilder;
import com.group18.asdc.groupformation.GroupFormationDirector;
import com.group18.asdc.groupformation.IGroupFormationBuilder;
import com.group18.asdc.groupformation.IGroupFormationDirector;
import com.group18.asdc.groupformation.SurveyAdapter;
import com.group18.asdc.passwordpolicy.BasePasswordPolicyFactory;
import com.group18.asdc.passwordpolicy.PasswordPolicyFactory;
import com.group18.asdc.security.SecurityAbstractFactory;
import com.group18.asdc.security.SecurityAbstractFactoryImpl;
import com.group18.asdc.service.ServiceAbstractFactory;
import com.group18.asdc.service.ServiceAbstractFactoryImpl;
import com.group18.asdc.util.UtilAbstractFactory;
import com.group18.asdc.util.UtilAbstractFactoryImpl;

public class SystemConfig {

	private static SystemConfig singleinstance = null;
	private ModelAbstractFactory modelAbstractFactory;
	private ServiceAbstractFactory serviceAbstractFactory;
	private DaoAbstractFactory daoAbstractFactory;
	private UtilAbstractFactory utilAbstractFactory;
	private BasePasswordPolicyFactory basePasswordPolicyFactory;
	private SecurityAbstractFactory securityAbstractFactory;
	private PasswordPolicyFactory passwordPolicyFactory;
	private DataBaseAbstractFactory dataBaseAbstractFactory;

	private SystemConfig() {

		this.modelAbstractFactory = new ModelAbstractFactoryImpl();
		this.daoAbstractFactory = new DaoAbstractFactoryImpl();
		this.serviceAbstractFactory = new ServiceAbstractFactoryImpl();
		this.utilAbstractFactory = new UtilAbstractFactoryImpl();
		this.basePasswordPolicyFactory = BasePasswordPolicyFactory.instance(daoAbstractFactory.getPasswordPolicyDB());
		this.securityAbstractFactory = new SecurityAbstractFactoryImpl();
		this.passwordPolicyFactory = PasswordPolicyFactory.instance(daoAbstractFactory.getPasswordPolicyDB());
		this.dataBaseAbstractFactory = new DataBaseAbstractFactoryImpl();
	}

	public static SystemConfig getSingletonInstance() {
		if (null == singleinstance) {
			singleinstance = new SystemConfig();
			return singleinstance;
		} else {
			return singleinstance;
		}
	}

	public ModelAbstractFactory getModelAbstractFactory() {
		return modelAbstractFactory;
	}

	public ServiceAbstractFactory getServiceAbstractFactory() {
		return serviceAbstractFactory;
	}

	public DaoAbstractFactory getDaoAbstractFactory() {
		return daoAbstractFactory;
	}

	public UtilAbstractFactory getUtilAbstractFactory() {
		return this.utilAbstractFactory;
	}

	public BasePasswordPolicyFactory getBasePasswordPolicyManager() {
		return this.basePasswordPolicyFactory;
	}

	public SecurityAbstractFactory getSecurityAbstractFactory() {
		return this.securityAbstractFactory;
	}

	public PasswordPolicyFactory getPasswordPolicyFactory() {
		return this.passwordPolicyFactory;
	}

	public void setBasePasswordPolicyManager(IPasswordPolicyDB passwordPolicyDB) {
		this.basePasswordPolicyFactory.resetInstance(passwordPolicyDB);
	}

	public void setPasswordPolicyManager(IPasswordPolicyDB passwordPolicyDB) {
		this.passwordPolicyFactory.resetInstance(passwordPolicyDB);
	}

	public ISurveyList createSurveyAdapter(ISurveyMetaData surveyQuestionData, List answerList) {
		return new SurveyAdapter(surveyQuestionData, answerList);
	}

	public IGroupFormationBuilder createGroupFormationBuilder() {
		return new BaseGroupFormationBuilder();
	}

	public IGroupFormationDirector createGroupFormationDirector(IGroupFormationBuilder groupBuilder) {
		return new GroupFormationDirector(groupBuilder);
	}

	public BasePasswordPolicyFactory getBasePasswordPolicyFactory() {
		return basePasswordPolicyFactory;
	}

	public void setBasePasswordPolicyFactory(BasePasswordPolicyFactory basePasswordPolicyFactory) {
		this.basePasswordPolicyFactory = basePasswordPolicyFactory;
	}

	public DataBaseAbstractFactory getDataBaseAbstractFactory() {
		return dataBaseAbstractFactory;
	}

	public void setDataBaseAbstractFactory(DataBaseAbstractFactory dataBaseAbstractFactory) {
		this.dataBaseAbstractFactory = dataBaseAbstractFactory;
	}

	public void setSecurityAbstractFactory(SecurityAbstractFactory securityAbstractFactory) {
		this.securityAbstractFactory = securityAbstractFactory;
	}

	public void setPasswordPolicyFactory(PasswordPolicyFactory passwordPolicyFactory) {
		this.passwordPolicyFactory = passwordPolicyFactory;
	}
}
