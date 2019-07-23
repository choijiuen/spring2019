package com.bit.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.bit.framework.Controller;

public class IndexController implements Controller{
	
	public IndexController() {
		System.out.println("new IndexController");	//서버의 구동과 동시에 객체 찍혔음, 하나의 객체를 계속 사용, 자원 효율적으로 사용
	}

	public String execute(HttpServletRequest req) throws SQLException{
		req.setAttribute("msg", "환영합니다");		//이건 컨트롤러에서
		return "index";
		
	}

}
