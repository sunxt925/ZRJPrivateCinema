package cn.edu.cqu.csp.action.login;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import cn.edu.cqu.csp.dao.User;
import cn.edu.cqu.csp.dao.UserDAO;
import cn.edu.cqu.csp.src.MessageDigest5;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport{
	private String username;
	private String password;
	
	
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


	public String execute()
	{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		MessageDigest5 md5 = new MessageDigest5();
		UserDAO usersDAO = new UserDAO();
		User user = new User();
		List users = new ArrayList();
		users = usersDAO.findByUsername(username);
		//System.out.println();
		if(users.size()==0)
			return "error";
		user = (User) users.get(0);

		if(user.getUsername().equals(username)&&user.getPassword().equals(md5.MD5(password)))
		{
			request.setAttribute(username, username);
			return "success";
		}
		else
			return "error";
	}

}
