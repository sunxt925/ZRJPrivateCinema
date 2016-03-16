package cn.edu.cqu.csp.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONObject;

import cn.edu.cqu.csp.dao.User;
import cn.edu.cqu.csp.dao.UserDAO;

import com.opensymphony.xwork2.ActionSupport;

public class LoadUserAction extends ActionSupport{
	private int id;
	private String username;
	private String password;
	
	private Map<String, Object> dataMap = new HashMap<String, Object>();
	
	private List list;
	
	private JSONObject resultObj;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public Map<String, Object> getDataMap() {
		return dataMap;
	}
	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}
	
	
	public JSONObject getResultObj() {
		return resultObj;
	}
	public void setResultObj(JSONObject resultObj) {
		this.resultObj = resultObj;
	}
	
	public String LoadUser()
	{
		UserDAO userDAO = new UserDAO();
		list = (List) userDAO.findAll();
		dataMap.clear();
		dataMap.put("rows", list);
        return SUCCESS;   
	}

}
