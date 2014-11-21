package com.sshtg.common;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

public class ParamHandler {
	
	public static void getContextParam(){
		ServletContext context = ServletActionContext.getServletContext();
		String value = context.getInitParameter("contextConfigLocation");
		
		System.out.println(value);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getContextParam();
	}

}
