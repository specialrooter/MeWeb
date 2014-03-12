/**
 *
 */
package com.mw.test.core.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.mw.framework.dao.SysOrganDao;
import com.mw.framework.dao.SysUserDao;
import com.mw.framework.domain.SysOrgan;
import com.mw.framework.domain.SysUser;
import com.mw.framework.test.BaseTest;

/**
 * @Project MeWeb
 * @Copyright © 2008-2014 SPRO Technology Consulting Limited. All rights
 *            reserved.
 * @fileName com.mw.test.core.dao.SysOrganDaoTest.java
 * @Version 1.0.0
 * @author Allan Ai
 * @time 2014-3-9
 * 
 */
public class SysOrganDaoTest extends BaseTest {

	@Autowired
	private SysOrganDao sysOrganDao;
	
	@Autowired
	private SysUserDao sysUserDao;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * 测试保存单个
	 */
	//@Test
	@Rollback(value=false)
	public void testSave() {
		SysOrgan organ = new SysOrgan(1,"一级节点");
		sysOrganDao.save(organ);
		
		SysOrgan organ2 = new SysOrgan(1,"二级节点",false,organ);
		sysOrganDao.save(organ2);
	}
	
	/**
	 * 测试保存多个
	 */
	//@Test
	@Rollback(value=false)
	public void testSaveAndList() {
		SysOrgan findOne = sysOrganDao.findOne("oBuhGRMmTGeXqEJ9_ba0gA");
		
		List<SysOrgan> organList = new ArrayList<SysOrgan>();
		
		for (int i = 1; i < 5; i++) {
			organList.add(new SysOrgan(i,"二级节点"+i,true,findOne));
		}
		sysOrganDao.save(organList);
	}
	
	/**
	 * 测试保存关联用户
	 */
	@Test
	@Rollback(value=false)
	public void testSaveAndGLUser() {
		
		SysUser user = new SysUser("admin", "管理员", "admin", 1, "001100");
		
		sysUserDao.save(user);
		
		SysOrgan findOne = sysOrganDao.findOne("oBuhGRMmTGeXqEJ9_ba0gA");
		findOne.getUsers().add(user);
		sysOrganDao.save(findOne);
	}
	
	/**
	 * 根据Id获取从节点信息
	 */
	//@Test
	public void testQuery(){
		SysOrgan findOne = sysOrganDao.findOne("oBuhGRMmTGeXqEJ9_ba0gA");//rooot
		System.out.println(findOne.getName());
		Set<SysOrgan> children = findOne.getChildren();
		for (SysOrgan sysOrgan : children) {
			System.out.println("--"+sysOrgan.getName());
			SysOrgan findOne2 = sysOrganDao.findOne(sysOrgan.getId());
			for (SysOrgan sysOrgan2 : findOne2.getChildren()) {
				System.out.println("----"+sysOrgan2.getName());
			}
		}
	}
	
	 
	
}

