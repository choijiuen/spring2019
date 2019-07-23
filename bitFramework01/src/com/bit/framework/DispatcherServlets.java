package com.bit.framework;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlets extends HttpServlet{
//	Map<String,Controller> map=new HashMap<String, Controller>();
	
	@Override
	public void init() throws ServletException {
		
		Properties prop = new Properties();	//������Ƽ���� �׻� src �ֻ��� ��Ʈ�� ��ġ����
		Class<? extends DispatcherServlets> clz = getClass();
		ClassLoader loader= clz.getClassLoader();
		InputStream is= loader.getResourceAsStream("bit.properties");
		try {
			prop.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Set keys = prop.keySet();
		Iterator<String> ite=keys.iterator();
		while(ite.hasNext()){
			String key=ite.next();
			String clInfo= prop.getProperty(key);
			BitHandlerMapping.setMap(key, clInfo);
		
		} 
		
//		Map<String,String> map=new HashMap<String, String>();
//		map.put("/index.bit", "com.bit.controller.IndexController");
//		map.put("/main.bit", "com.bit.controller.MainController");
//		map.put("/list.bit", "com.bit.controller.ListController");
//		map.put("/add.bit", "com.bit.controller.AddController");
//		map.put("/insert.bit", "com.bit.controller.InsertController");	//url�ϰ� Ŭ������ ���ÿ� ������ �ִ� �ڷᱸ����? Map
//		
//		//init�� �̰� ���ָ� �Ź� map�� ���� �ʿ� ���� �� ���� ������ �ȴ�. ���ʿ� �� �� init
//		//�� ����ʹ� ������ ���� 
//		//but ���� ����, �̸� ���� ��ü ��� ���� , �ʿ��� �� �����ٰ� �� ����. �Ź� ��Ʈ�ѷ� �����ʰ� , init������ ��� ���� .
//		//�̷��� �ϸ� �̱������� �� �� �ְ�, �������.
//		//�̰� ���Ϸ� ����� ������ �ҷ��ͼ� �� ��.
//		
//		Set<String> keys = map.keySet();
//		Iterator<String> ite=keys.iterator();
//		while(ite.hasNext()){
//			String key=ite.next();
//			String clInfo= map.get(key);
//			
//			BitHandlerMapping.setMap(key, clInfo);
//		
//		}
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		doDo(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doDo(req, resp);
	}
	
	public void doDo(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("doDo ȣ��...");
			//doget, dopost �� ���� doDo�� �Ѿ�´�. 
		String root=req.getContextPath();
		String path=req.getRequestURI().substring(root.length());
//		System.out.println("->"+root);
//		System.out.println("->"+path);
//		String viewName=null;
		
		
		//////// HandlerMapping
		
		
		Controller controller =null;
		//String clInfo=null;
		
		
		controller=BitHandlerMapping.getController(path);	//�̷��� �ϸ� ���ϴ� ��Ʈ�ѷ� ���� �� ����
		
//		if(path.equals("/index.bit")){
////			 controller = new com.bit.controller.IndexController();
//			clInfo="com.bit.controller.IndexController";
//		}else if(path.equals("/main.bit")){
////			 controller = new com.bit.controller.MainController();
//			clInfo="com.bit.controller.MainController";
//			
//		}else if(path.equals("/list.bit")){
////			 controller=new com.bit.controller.ListController();
//			clInfo="com.bit.controller.ListController";
//			
//			
//		}else if(path.equals("/add.bit")){
////			controller=new com.bit.controller.AddController();
//			clInfo="com.bit.controller.AddController";
//			
//		}else if(path.equals("/insert.bit")){
////			controller = new com.bit.controller.InsertController();  //���⼭ ��Ű���� �� ���ָ� ������ import �� �ʿ� ����.
//			
//			clInfo="com.bit.controller.InsertController";	
//			
//			
//		}
		
		
		String viewName=null;
		try {
			viewName = controller.execute(req);		//��Ʈ�ѷ����� �� sqlexception �����Z �̰� Ʈ���� ĳġ �������
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	//�̰� �ߺ��Ȱ� �E��� �ڷ��� ��ġ �����ָ� �ȴ�.
											//--Controller �������̽� ���� �� ��Ʈ�ѷ� ���� �̰� ��ӽ����༭ �����ļ������� ��ġ�� �ȴ�.
		
		
		//viewResolver
		
		String prefix="/WEB-INF/view/";
		String suffix=".jsp";
		
		if(viewName.startsWith("redirect:")){
			resp.sendRedirect(root+viewName.substring("redirect:".length()));
		}else{
			viewName=prefix+viewName+suffix;
			req.getRequestDispatcher(viewName).forward(req, resp);	//�̰� �信��
		}
	}
	
	
}
