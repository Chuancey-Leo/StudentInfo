package com.java.action;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.java.dao.DataDicDao;
import com.java.dao.DataDicTypeDao;
import com.java.model.DataDicType;
import com.java.util.DbUtil;
import com.java.util.NavUtil;
import com.java.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.java.util.ResponseUtil;

import net.sf.json.JSONObject;

public class DataDicTypeAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	
	private DbUtil dbUtil=new DbUtil();
	private DataDicTypeDao dataDicTypeDao=new DataDicTypeDao();
	private DataDicDao dataDicDao=new DataDicDao();
	
	private List<DataDicType> dataDicTypeList=new ArrayList<DataDicType>();
	
	private String mainPage; 
	private String navCode;
	private String ddTypeId;
	private DataDicType dataDicType;
	
	public String getDdTypeId() {
		return ddTypeId;
	}
	public void setDdTypeId(String ddTypeId) {
		this.ddTypeId = ddTypeId;
	}
	public DataDicType getDataDicType() {
		return dataDicType;
	}
	public void setDataDicType(DataDicType dataDicType) {
		this.dataDicType = dataDicType;
	}
	public List<DataDicType> getDataDicTypeList() {
		return dataDicTypeList;
	}
	public void setDataDicTypeList(List<DataDicType> dataDicTypeList) {
		this.dataDicTypeList = dataDicTypeList;
	}
	public String getMainPage() {
		return mainPage;
	}
	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}
	public String getNavCode() {
		return navCode;
	}
	public void setNavCode(String navCode) {
		this.navCode = navCode;
	}
	
	
	public String list(){
		Connection con=null;
		try{
			con=dbUtil.getCon();
			dataDicTypeList=dataDicTypeDao.dataDicTypeList(con);
			navCode=NavUtil.getNavgation("系统管理", "数据字典类别维护");
			mainPage="dataDicType/dataDicTypeList.jsp";
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
	
	public String preSave(){
		if(StringUtil.isNotEmpty(ddTypeId)){
			Connection con=null;
			try{
				con=dbUtil.getCon();
				dataDicType=dataDicTypeDao.getDataDicTypeById(con, ddTypeId);
				navCode=NavUtil.getNavgation("系统管理", "数据字典类别修改");
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else{
			navCode=NavUtil.getNavgation("系统管理", "数据字典类别添加");
		}
		mainPage="dataDicType/dataDicTypeSave.jsp";
		return SUCCESS;
	}
	
	
	public String save(){
		Connection con=null;
		try{
			con=dbUtil.getCon();
			if(StringUtil.isNotEmpty(ddTypeId)){
				dataDicType.setDdTypeId(Integer.parseInt(ddTypeId));
				dataDicTypeDao.dataDicTypeUpdate(con, dataDicType);
			}else{
				dataDicTypeDao.dataDicTypeAdd(con, dataDicType);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "typeSave";
	}
	
	public String delete(){
		Connection con=null;
		try{
			con=dbUtil.getCon();
			JSONObject resultJson=new JSONObject();
			boolean exist=dataDicDao.existDataDicByTypeId(con, ddTypeId);
			if(exist){
				resultJson.put("error", "数据字典类别下面有数据，不能删除！");
			}else{
				dataDicTypeDao.dataDicTypeDelete(con, ddTypeId);
				resultJson.put("success", true);
			}
			ResponseUtil.write(resultJson, ServletActionContext.getResponse());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
}
