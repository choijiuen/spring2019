package com.bit.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.bit.framework.Controller;

public class IndexController implements Controller{
	
	public IndexController() {
		System.out.println("new IndexController");	//������ ������ ���ÿ� ��ü ������, �ϳ��� ��ü�� ��� ���, �ڿ� ȿ�������� ���
	}

	public String execute(HttpServletRequest req) throws SQLException{
		req.setAttribute("msg", "ȯ���մϴ�");		//�̰� ��Ʈ�ѷ�����
		return "index";
		
	}

}
