package com.group18.asdc;

import com.group18.asdc.dao.test.DaoTestAbstractFactory;
import com.group18.asdc.dao.test.DaoTestAbstractFactoryImpl;
import com.group18.asdc.entities.test.ModelTestAbstractFactory;
import com.group18.asdc.entities.test.ModelTestAbstractFactoryImpl;
import com.group18.asdc.service.test.ServiceTestAbstractFactory;
import com.group18.asdc.service.test.ServiceTestAbstractFactoryImpl;

public class TestConfig {

	private static TestConfig singleInstance = null;
	private DaoTestAbstractFactory daoTestAbstractFactory;
	private ServiceTestAbstractFactory serviceTestAbstractFactory;
	private ModelTestAbstractFactory modelTestAbstractFactory;

	private TestConfig() {

		this.serviceTestAbstractFactory=new ServiceTestAbstractFactoryImpl();
		this.daoTestAbstractFactory=new DaoTestAbstractFactoryImpl();
		this.modelTestAbstractFactory=new ModelTestAbstractFactoryImpl();
	}

	public static TestConfig getTestSingletonIntance() {
		if (null == singleInstance) {
			singleInstance = new TestConfig();
			return singleInstance;
		} else {
			return singleInstance;
		}
	}

	public DaoTestAbstractFactory getDaoTestAbstractFactory() {
		return daoTestAbstractFactory;
	}

	public void setDaoTestAbstractFactory(DaoTestAbstractFactory daoTestAbstractFactory) {
		this.daoTestAbstractFactory = daoTestAbstractFactory;
	}

	public ServiceTestAbstractFactory getServiceTestAbstractFactory() {
		return serviceTestAbstractFactory;
	}

	public void setServiceTestAbstractFactory(ServiceTestAbstractFactory serviceTestAbstractFactory) {
		this.serviceTestAbstractFactory = serviceTestAbstractFactory;
	}

	public ModelTestAbstractFactory getModelTestAbstractFactory() {
		return modelTestAbstractFactory;
	}

	public void setModelTestAbstractFactory(ModelTestAbstractFactory modelTestAbstractFactory) {
		this.modelTestAbstractFactory = modelTestAbstractFactory;
	}
}
