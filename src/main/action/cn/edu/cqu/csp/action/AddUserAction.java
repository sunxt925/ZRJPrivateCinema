package cn.edu.cqu.csp.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.edu.cqu.csp.dao.User;
import cn.edu.cqu.csp.dao.UserDAO;
import cn.edu.cqu.csp.src.MessageDigest5;

import com.opensymphony.xwork2.ActionSupport;

public class AddUserAction extends ActionSupport{
	
	private String user_id;
	private String user_username;
	private String user_password;
	private Map<String, Object> dataMap = new HashMap<String, Object>();
	
	
	
	
	public Map<String, Object> getDataMap() {
		return dataMap;
	}



	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}



	public String getUser_id() {
		return user_id;
	}



	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}



	public String getUser_username() {
		return user_username;
	}



	public void setUser_username(String user_username) {
		this.user_username = user_username;
	}



	public String getUser_password() {
		return user_password;
	}



	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}



	public String AddUser()
	{
		UserDAO userDAO = new UserDAO();
		User user = new User();
		MessageDigest5 md5 = new MessageDigest5();
		//user.setId(userDAO.getMaxID()+1);
		user.setUsername(user_username);
		user.setPassword(md5.MD5(user_password));
		userDAO.save(user);
		dataMap.put("success", 1);
		return "success";
	}

}
