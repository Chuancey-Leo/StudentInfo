package com.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.java.model.DataDicType;




public class DataDicTypeDao {
	public List<DataDicType> dataDicTypeList(Connection con)throws Exception{
		List<DataDicType> dataDicTypeList=new ArrayList<DataDicType>();
		String sql="select * from t_dataDicType";
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			DataDicType dataDicType=new DataDicType();
			dataDicType.setDdTypeId(rs.getInt("ddTypeId"));
			dataDicType.setDdTypeName(rs.getString("ddTypeName"));
			dataDicType.setDdTypeDesc(rs.getString("ddTypeDesc"));
			dataDicTypeList.add(dataDicType);
		}
		return dataDicTypeList;
	}
	
	
	public int dataDicTypeAdd(Connection con,DataDicType dataDicType)throws Exception{
		String sql="insert into t_dataDicType values(null,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, dataDicType.getDdTypeName());
		pstmt.setString(2, dataDicType.getDdTypeDesc());
		return pstmt.executeUpdate();
	}
	
	public DataDicType getDataDicTypeById(Connection con,String ddTypeId)throws Exception{
		String sql="select * from t_dataDicType where ddTypeId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, ddTypeId);
		ResultSet rs=pstmt.executeQuery();
		DataDicType dataDicType=new DataDicType();
		if(rs.next()){
			dataDicType.setDdTypeId(rs.getInt("ddTypeId"));
			dataDicType.setDdTypeName(rs.getString("ddTypeName"));
			dataDicType.setDdTypeDesc(rs.getString("ddTypeDesc"));
		}
		return dataDicType;
	}
	
	public int dataDicTypeUpdate(Connection con,DataDicType dataDicType)throws Exception{
		String sql="update t_dataDicType set ddTypeName=?,ddTypeDesc=? where ddTypeId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, dataDicType.getDdTypeName());
		pstmt.setString(2, dataDicType.getDdTypeDesc());
		pstmt.setInt(3, dataDicType.getDdTypeId());
		return pstmt.executeUpdate();
	}
	
	public int dataDicTypeDelete(Connection con,String typeId)throws Exception{
		String sql="delete from t_dataDicType where ddTypeId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,typeId);
		return pstmt.executeUpdate();
	}
}
