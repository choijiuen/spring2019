package com.bit.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.bit.framework.TemplateQuery;
import com.bit.framework.TemplateUpdate;
import com.bit.model.entity.Bbs02Vo;

public class Bbs02Dao1 {
	
	TemplateQuery template =new TemplateQuery(){
		
		public  Object mapper(ResultSet rs) throws SQLException{
			Bbs02Vo bean = new Bbs02Vo();
			bean.setNum(rs.getInt("num"));		//이거는 setter 호출해야하니깐 , Object 로 바꾸면 안된다.그래서 List 했던 것 처럼 하면 안됨
			bean.setName(rs.getString("name"));
			bean.setSub(rs.getString("sub"));
			bean.setNalja(rs.getDate("nalja"));
			bean.setContent(rs.getString("content"));
			return bean;
			
			}
		};
	
	
//	public List executeQuery(String sql) throws SQLException{
//		List list = new ArrayList();
//		Connection conn = getConnection();
//		PreparedStatement pstmt=null;
//		ResultSet rs= null;
//		try{
//			pstmt=conn.prepareStatement(sql);
//			rs=pstmt.executeQuery();
//			while(rs.next()){
//				Bbs02Vo bean = new Bbs02Vo();
//				
//				bean.setNum(rs.getInt("num"));
//				bean.setName(rs.getString("name"));
//				bean.setSub(rs.getString("sub"));
//				bean.setNalja(rs.getDate("nalja"));
//				list.add(bean);
//			}
//		}finally{
//			closeAll(conn, pstmt,rs);
//		}
//		
//		 return list;
//	
//	}
	
	public List getList() throws SQLException{
		String sql ="select num, name, sub, nalja,content from bbs02";	//쿼리에 ? 있을 때도 해결해야한다.
		
		return template.executeQuery(sql, new Object[]{});
//		List list = new ArrayList();
//		Connection conn = getConnection();
//		PreparedStatement pstmt=null;
//		ResultSet rs= null;
//		try{
//			pstmt=conn.prepareStatement(sql);
//			rs=pstmt.executeQuery();
//			while(rs.next()){
//				Bbs02Vo bean = new Bbs02Vo();
//				
//				bean.setNum(rs.getInt("num"));
//				bean.setName(rs.getString("name"));
//				bean.setSub(rs.getString("sub"));
//				bean.setNalja(rs.getDate("nalja"));
//				list.add(bean);
//			}
//		}finally{
//			closeAll(conn, pstmt,rs);
//		}
//		
//		 return list;
	}


	
	public void insertOne(String name, String sub, String content) throws SQLException {
		System.out.println("name : "+name);
		System.out.println("sub : "+sub);
		System.out.println("content : "+content);
		String sql ="insert into bbs02 values (bbs02_seq.nextval,?,?,?,sysdate)";
		TemplateUpdate template = new TemplateUpdate();
		template.executeUpdate(sql, new Object[]{name, sub,content});
	}

	public Bbs02Vo selectOne(int num) throws SQLException {
		String sql="select * from bbs02 where num=?";
		
			
			return (Bbs02Vo)template.executeQuery(sql, new Object[]{num}).get(0);
//		Connection conn =getConnection();
//		PreparedStatement pstmt=null;
//		ResultSet rs =null;
//		try{
//			pstmt=conn.prepareStatement(sql);
//			pstmt.setObject(1, num);
//			rs=pstmt.executeQuery();
//			if(rs.next()){
//				Bbs02Vo bean=new Bbs02Vo();
//				bean.setNum(rs.getInt("num"));
//				bean.setName(rs.getString("name"));
//				bean.setSub(rs.getString("sub"));
//				bean.setContent(rs.getString("content"));
//				bean.setNalja(rs.getDate("nalja"));
//				return bean;
//			}
//		}finally{
//			closeAll(conn, pstmt,rs);
//		}
//		return null;
	}
	
	public int updateOne(Bbs02Vo bean) throws SQLException{
		String sql ="update bbs02 set sub=?,content=?, where num=?";
		
//		Connection conn=getConnection();
//		PreparedStatement pstmt=null;
//		try{
//			pstmt=conn.prepareStatement(sql);
//			pstmt.setObject(1, bean.getSub());
//			pstmt.setObject(2, bean.getContent());
//			pstmt.setObject(3, bean.getNum());
//			return pstmt.executeUpdate();
//		}finally{
//			closeAll(conn, pstmt,null);
//		}
		TemplateUpdate template = new TemplateUpdate();
		return template.executeUpdate(sql, new Object[]{bean.getSub(), bean.getContent(),bean.getNum()});
		
	}
	
	public int deleteOne(int num) throws SQLException{
		String sql="delete from bbs02 where num=?";
//		Connection conn=getConnection();
//		PreparedStatement pstmt=null;
//		try{
//			pstmt=conn.prepareStatement(sql);
//			pstmt.setObject(1, num);
//			return pstmt.executeUpdate();
//		}finally{
//			closeAll(conn, pstmt,null);
//		}
		TemplateUpdate template = new TemplateUpdate();
		return template.executeUpdate(sql, new Object[]{num});
	}
	
//	public void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs) throws SQLException{
//		if(rs!=null)rs.close();
//		if(pstmt!=null)pstmt.close();
//		if(conn!=null)conn.close();
//	}
}
