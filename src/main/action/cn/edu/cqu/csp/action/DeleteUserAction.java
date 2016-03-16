package cn.edu.cqu.csp.action;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import cn.edu.cqu.csp.dao.User;
import cn.edu.cqu.csp.dao.UserDAO;

import com.opensymphony.xwork2.ActionSupport;

public class DeleteUserAction extends ActionSupport{

	private String idArray;
	

	private Map<String, Object> dataMap = new HashMap<String, Object>();
	
	
	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public String getIdArray() {
		return idArray;
	}

	public void setIdArray(String idArray) {
		this.idArray = idArray;
	}

	public String DeleteUser(){
		UserDAO userDAO = new UserDAO();
		User user = new User();
		//System.out.println(idArray);
		StringTokenizer st = new StringTokenizer(idArray);
		while (st.hasMoreTokens()) {
            user.setId(Integer.parseInt(st.nextToken()));
			userDAO.delete(user);
        }
		dataMap.put("success", 1);
		return "success";
	}
}
