package com.bit.framework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bit.model.entity.Bbs02Vo;

public abstract class TemplateQuery {
	private String driver="oracle.jdbc.OracleDriver";
	private String url="jdbc:oracle:thin:@localhost:1521:xe";
	private String user="scott";
	private String password="tiger";
	
	public Connection getConnection(){
		 Connection conn=null;
		try {
			if(conn==null || conn.isClosed()){
				
				Class.forName(driver);
				conn=DriverManager.getConnection(url,user, password);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	public List executeQuery(String sql,Object[] objs) throws SQLException{	
		List list = new ArrayList();
		Connection conn = getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		try{
			pstmt=conn.prepareStatement(sql);
			
			for(int i=0; i<objs.length; i++){	//������ ? ���� ���� �ذ��ؾ��Ѵ�.
				pstmt.setObject(i+1, objs[i]);
			}
			
			rs=pstmt.executeQuery();
			while(rs.next()){
				list.add(mapper(rs));
			}
		}finally{
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}
		
		 return list;
	
	}
	
	public abstract Object mapper(ResultSet rs) throws SQLException;
//		Bbs02Vo bean = new Bbs02Vo();
//		bean.setNum(rs.getInt("num"));		//�̰Ŵ� setter ȣ���ؾ��ϴϱ� , Object �� �ٲٸ� �ȵȴ�.�׷��� List �ߴ� �� ó�� �ϸ� �ȵ�
//		bean.setName(rs.getString("name"));
//		bean.setSub(rs.getString("sub"));
//		bean.setNalja(rs.getDate("nalja"));
//		return bean;
		
	
}
