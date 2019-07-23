package com.bit.framework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TemplateUpdate {
	
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
	
	public int executeUpdate(String sql ,Object[] objs) throws SQLException{
		Connection conn =getConnection();
		PreparedStatement pstmt = null;
		try{
			pstmt=conn.prepareStatement(sql);
			for(int i=0; i<objs.length; i++){
				pstmt.setObject(i+1,objs[i] );
			}
			
//			pstmt.setObject(1,name);
//			pstmt.setObject(2,sub);
//			pstmt.setObject(3,content);
			return pstmt.executeUpdate();
		}finally{
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}
	}
}
