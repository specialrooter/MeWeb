package com.mw.test.core.dao;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.mw.framework.dao.DataDicDao;
import com.mw.framework.domain.DataDic;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@TransactionConfiguration(transactionManager="transactionManager") 
@Transactional
public class DataDicDaoTest {

	@Autowired
	private DataDicDao dataDicDao;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	//@Test
	public void testFindByPid() {
		fail("Not yet implemented");
	}

	//@Test
	public void testSaveIterableOfS() {
		fail("Not yet implemented");
	}

	@Test
	@Rollback(value=false)
	public void testSaveS() {
		
		List<DataDic> dataDicList = new ArrayList<DataDic>();
		for (int i = 0; i < 10000; i++) {
			dataDicList.add(new DataDic("DD01",0,"DD","品牌的魅力"+i,"品牌的魅力"+i,"The charm of the brand"+i));
		}
		dataDicDao.save(dataDicList);
	}

	//@Test
	public void testSaveIterableOfS1() {
		fail("Not yet implemented");
	}

}
