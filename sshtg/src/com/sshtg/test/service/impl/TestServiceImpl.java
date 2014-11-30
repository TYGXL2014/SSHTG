package com.sshtg.test.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sshtg.test.dao.TestDao;
import com.sshtg.test.model.Model;
import com.sshtg.test.service.TestService;

@Service("testService")
public class TestServiceImpl implements TestService {
	private TestDao testDao;
	public TestDao getTestDao() {
		return testDao;
	}
	@Resource(name="testDao")
	public void setTestDao(TestDao testDao) {
		this.testDao = testDao;
	}
	@Override
	public void testAdd(Model model) {
		System.out.println("TestServiceImpl..testAdd..begin...");
		testDao.testSave(model);
		System.out.println("TestServiceImpl..testAdd..end!");
	}

}
