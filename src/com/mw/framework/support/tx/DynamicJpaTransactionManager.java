/**
 *
 */
package com.mw.framework.support.tx;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.orm.jpa.JpaTransactionManager;

/**
 * DEP-001:因只能支持已配置的EntityManagerFactory动态切换，不能支持动态拓展数据库，故暂停使用
 * @Project MeWeb
 * @Copyright © 2008-2014 SPRO Technology Consulting Limited. All rights reserved.
 * @fileName com.mw.framework.support.tx.DynamicJpaTransactionManager.java
 * @Version 1.0.0
 * @author Allan Ai
 * @time 2014-2-19
 *
 */
@Deprecated
public class DynamicJpaTransactionManager extends JpaTransactionManager{
	private static final long serialVersionUID = -8470908604136263414L;

	@Override
	public DataSource getDataSource() {
		return super.getDataSource();
	}

	@Override
	public EntityManagerFactory getEntityManagerFactory() {
		System.out.println("这种模式只能动态切换已配置的EntityManagerFactory");
		return super.getEntityManagerFactory();
	}

	
}
