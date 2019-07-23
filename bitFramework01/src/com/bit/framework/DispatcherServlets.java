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
		
		Properties prop = new Properties();	//프로퍼티스는 항상 src 최상위 루트에 위치했음
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
//		map.put("/insert.bit", "com.bit.controller.InsertController");	//url하고 클래스를 동시에 담을수 있는 자료구조는? Map
//		
//		//init에 이거 해주면 매번 map에 담을 필요 없이 한 번만 넣으면 된다. 최초에 한 번 init
//		//그 담부터는 꺼내서 쓰기 
//		//but 제약 있음, 미리 서블렛 객체 찍어 놓고 , 필요할 때 가져다가 쓸 것임. 매번 컨트롤러 찍지않고 , init시점에 찍어 놓고 .
//		//이렇게 하면 싱글톤으로 쓸 수 있고, 성능향상.
//		//이거 파일로 만들고 파일을 불러와서 쓸 것.
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

		System.out.println("doDo 호출...");
			//doget, dopost 를 쓰든 doDo로 넘어온다. 
		String root=req.getContextPath();
		String path=req.getRequestURI().substring(root.length());
//		System.out.println("->"+root);
//		System.out.println("->"+path);
//		String viewName=null;
		
		
		//////// HandlerMapping
		
		
		Controller controller =null;
		//String clInfo=null;
		
		
		controller=BitHandlerMapping.getController(path);	//이렇게 하면 원하는 컨트롤러 얻을 수 있음
		
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
////			controller = new com.bit.controller.InsertController();  //여기서 패키지명 다 쳐주면 위에서 import 할 필요 없다.
//			
//			clInfo="com.bit.controller.InsertController";	
//			
//			
//		}
		
		
		String viewName=null;
		try {
			viewName = controller.execute(req);		//컨트롤러에서 다 sqlexception 던져줫 이거 트라이 캐치 해줘야함
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	//이거 중복된거 뺼라면 자료형 일치 시켜주면 된다.
											//--Controller 인터페이스 만들어서 각 컨트롤러 마다 이거 상속시켜줘서 디스페쳐서블릿에서 고치면 된다.
		
		
		//viewResolver
		
		String prefix="/WEB-INF/view/";
		String suffix=".jsp";
		
		if(viewName.startsWith("redirect:")){
			resp.sendRedirect(root+viewName.substring("redirect:".length()));
		}else{
			viewName=prefix+viewName+suffix;
			req.getRequestDispatcher(viewName).forward(req, resp);	//이건 뷰에서
		}
	}
	
	
}
