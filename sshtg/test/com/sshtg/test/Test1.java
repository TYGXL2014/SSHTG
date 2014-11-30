package com.sshtg.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sshtg.test.model.Model;
import com.sshtg.test.service.TestService;

public class Test1 {
	@Test
	public void testSpringHibernate(){
		System.out.println("testSpringHibernate..begin...");
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:config/applicationContext.xml");
		Model model = new Model();
		model.setName("ZHANGSAN");
		TestService testService = (TestService)ac.getBean("testService");
		testService.testAdd(model);
		System.out.println("testSpringHibernate..end£¡");
	}
}
