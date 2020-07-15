package com.group18.asdc;

import com.group18.asdc.dao.DaoAbstractFactory;
import com.group18.asdc.dao.DaoAbstractFactoryImpl;
import com.group18.asdc.entities.ModelAbstractFactory;
import com.group18.asdc.entities.ModelAbstractFactoryImpl;
import com.group18.asdc.errorhandling.ExceptionAbstractFactory;
import com.group18.asdc.errorhandling.ExceptionAbstractionFactoryImpl;
import com.group18.asdc.service.ServiceAbstractFactory;
import com.group18.asdc.service.ServiceAbstractFactoryImpl;

public class SystemConfig {

	private static SystemConfig singleinstance = null;
	private ModelAbstractFactory modelAbstractFactory;
	private ServiceAbstractFactory serviceAbstractFactory;
	private DaoAbstractFactory daoAbstractFactory;
	private ExceptionAbstractFactory exceptionAbstractFactory;

	private SystemConfig() {

		this.modelAbstractFactory = new ModelAbstractFactoryImpl();
		this.daoAbstractFactory = new DaoAbstractFactoryImpl();
		this.serviceAbstractFactory = new ServiceAbstractFactoryImpl();
		this.exceptionAbstractFactory = new ExceptionAbstractionFactoryImpl();
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

	public void setModelAbstractFactory(ModelAbstractFactory modelAbstractFactory) {
		this.modelAbstractFactory = modelAbstractFactory;
	}

	public ServiceAbstractFactory getServiceAbstractFactory() {
		return serviceAbstractFactory;
	}

	public void setServiceAbstractFactory(ServiceAbstractFactory serviceAbstractFactory) {
		this.serviceAbstractFactory = serviceAbstractFactory;
	}

	public DaoAbstractFactory getDaoAbstractFactory() {
		return daoAbstractFactory;
	}

	public void setDaoAbstractFactory(DaoAbstractFactory daoAbstractFactory) {
		this.daoAbstractFactory = daoAbstractFactory;
	}

	public ExceptionAbstractFactory getExceptionAbstractFactory() {
		return exceptionAbstractFactory;
	}

	public void setExceptionAbstractFactory(ExceptionAbstractFactory exceptionAbstractFactory) {
		this.exceptionAbstractFactory = exceptionAbstractFactory;
	}
}
